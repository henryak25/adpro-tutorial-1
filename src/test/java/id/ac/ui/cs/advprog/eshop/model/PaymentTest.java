package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private List<Payment> payments;
    private Order order;
    private Map<String, String> paymentDataVoucher;
    private Map<String, String> paymentDataBank;

    @BeforeEach
    void setUp() {
        paymentDataVoucher = new HashMap<>();
        paymentDataBank = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        paymentDataBank.put("bankName", "cihuy");
        paymentDataBank.put("referenceCode", "1234");
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudarajat");

    }

    @Test
    void testCreatePaymentNullPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(order.getId(),
                    "Voucher Code", null);
        });
    }

    @Test
    void testCreatePaymentEmptyStringBankName() {
        paymentDataBank.put("bankName", "");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(order.getId(),
                    "Bank Transfer", paymentDataBank);
        });
    }

    @Test
    void testCreatePaymentEmptyStringReferenceCode() {
        paymentDataBank.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(order.getId(),
                    "Bank Transfer", paymentDataBank);
        });
    }

    @Test
    void testCreatePaymentVoucher16charsFail() {
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC567899");
        assertThrows(IllegalArgumentException.class, ()-> {new
                Payment(order.getId(),
                "Voucher Code", paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucher8numericFail() {
        paymentDataVoucher.put("voucherCode", "ESHOP1234AB56789");
        assertThrows(IllegalArgumentException.class, ()-> {new
                Payment(order.getId(),
                "Voucher Code", paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucherStartWithESHOPFail() {
        paymentDataVoucher.put("voucherCode", "AYAM1234ABC567899999");
        assertThrows(IllegalArgumentException.class, ()-> {new
                Payment(order.getId(),
                "Voucher Code", paymentDataVoucher);
        });
    }

    @Test
    void testCreateStatusInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(order.getId(), "Voucher Code", "AYAM", this.paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment(order.getId(), "Voucher Code", "SUCCESS", paymentDataVoucher);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusPaymentToSuccess() {
        Payment payment = new Payment(order.getId(), "Voucher Code", "REJECTED", paymentDataVoucher);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusPaymentToInvalidStatus() {
        Payment payment = new Payment(order.getId(), "Voucher Code", "SUCCESS", paymentDataVoucher);
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));
    }
}