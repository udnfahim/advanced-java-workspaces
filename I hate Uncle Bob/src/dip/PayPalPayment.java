package dip;

public class PayPalPayment implements PaymentProcessor{
    @Override
    public void pay() {
        IO.println("PayPal Paid!");
    }
}
