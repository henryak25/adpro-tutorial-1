package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    @Setter
    String status;
    Map<String, String> paymentData;

    Payment(String id, String method, Map<String, String> paymentData) {
        this.checkPaymentMethod(method);
        this.checkPaymentData(paymentData, method);
        this.paymentData = paymentData;
        this.id = id;
        this.method = method;
        this.status = PaymentStatus.REJECTED.getValue();
    }

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.checkPaymentMethod(method);
        this.checkPaymentData(paymentData, method);
        this.paymentData = paymentData;
        this.setStatus(status);
        this.id = id;
        this.method = method;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void checkPaymentMethod(String method){
        if(!method.equals("Voucher Code") && !method.equals("Bank Transfer")){
            throw new IllegalArgumentException();
        }
    }
    public void checkVoucherCode(String voucherCode){
        int numericalCount = 0;
        if (voucherCode.isBlank() || voucherCode.length() != 16) {
            throw new IllegalArgumentException();
        }
        if(!voucherCode.startsWith("ESHOP")){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < voucherCode.length(); i++) {
            char ch = voucherCode.charAt(i);
            if(Character.isDigit(ch)){
                numericalCount++;
            }
        }
        if(numericalCount != 8){
            throw new IllegalArgumentException();
        }
    }

    public void checkPaymentData(Map<String, String> paymentData, String method){
        if(paymentData == null){
            throw new IllegalArgumentException();
        } else {
            if(method.equals("Voucher Code")) {
                checkVoucherCode(paymentData.get("voucherCode"));
            } else if(method.equals("Bank Transfer")) {
                if(paymentData.get("bankName").isEmpty()){
                    throw new IllegalArgumentException();
                }
                if(paymentData.get("referenceCode").isEmpty()){
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}