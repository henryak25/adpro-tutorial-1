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
        this.checkPaymentData(paymentData);
        this.paymentData = paymentData;
        this.id = id;
        this.method = method;
        this.status = PaymentStatus.REJECTED.getValue();
    }

    Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.checkPaymentData(paymentData);
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

    public void checkPaymentData(Map<String, String> paymentData){
        if(paymentData == null){
            throw new IllegalArgumentException();
        } else if (!paymentData.containsKey("voucherCode") && !paymentData.containsKey("bankName") && !paymentData.containsKey("referenceCode")) {
            throw new IllegalArgumentException();
        } else {
            if(paymentData.get("voucherCode").isEmpty() || paymentData.get("voucherCode").length()!=16){
                throw new IllegalArgumentException();
            }
            if(!paymentData.get("voucherCode").startsWith("ESHOP")){
                throw new IllegalArgumentException();
            }
            if(paymentData.get("voucherCode").isEmpty()){
                throw new IllegalArgumentException();
            }
            if(paymentData.get("bankName").isEmpty()){
                throw new IllegalArgumentException();
            }
            if(paymentData.get("referenceCode").isEmpty()){
                throw new IllegalArgumentException();
            }
        }
    }
}