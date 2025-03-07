package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;


import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;

    private Map<String, String> voucherPaymentData;
    private Map<String, String> bankPaymentData;
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");
        bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Free Bird");
        bankPaymentData.put("referenceCode", "2");

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudarajat");
        orders.add(order2);
    }

    @Test
    void testCreatePayment(){
        Payment payment = new  Payment(orders.getFirst().getId(), PaymentMethod.VOUCHER.getValue(), voucherPaymentData);
        doReturn(null).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.addPayment(orders.getFirst(), PaymentMethod.BANK.getValue(), bankPaymentData);
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testUpdateStatusInvalidStatus(){
        Payment payment = new Payment(orders.get(1).getId(), PaymentMethod.VOUCHER.getValue(), voucherPaymentData);
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "OTW"));
        verify(paymentRepository, times(0)).update(any(Payment.class));
    }

    @Test
    void testGetAllPayments() {
        paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER.getValue(), voucherPaymentData);
        paymentService.addPayment(orders.get(1), PaymentMethod.BANK.getValue(), bankPaymentData);
        assertEquals(2, paymentService.getAllPayments().size());
    }

    @Test
    void testFindByIdSuccess() {
        Payment payment = paymentService.addPayment(orders.getFirst(), PaymentMethod.VOUCHER.getValue(), voucherPaymentData);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        assertEquals(payment, paymentService.getPayment(payment.getId()));
    }

    @Test
    void testFindByIdNotFound() {
        doReturn(null).when(paymentRepository).findById("tes");
        assertNull(paymentService.getPayment("tes"));
    }

    @Test
    void testCreateInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () ->
                paymentService.addPayment(orders.getFirst(), "OVO", voucherPaymentData));
    }
}