package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        System.out.println(payment.getId() +" limao");
        paymentRepository.save(payment);
        System.out.print(paymentRepository.findById(payment.getId()));
        System.out.println(" masih ada2");

        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayment();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (paymentRepository.findById(payment.getId()) == null) {
            throw new NoSuchElementException();
        }
        payment.setStatus(status);
        return payment;
    }

    @Override
    public Payment getPayment(String id) {
        return paymentRepository.findById(id);
    }
}