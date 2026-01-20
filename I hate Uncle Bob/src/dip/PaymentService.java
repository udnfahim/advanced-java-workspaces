package dip;

public class PaymentService {
    public void payment(PaymentProcessor paymentProcessor){
        paymentProcessor.pay();
    }
}
