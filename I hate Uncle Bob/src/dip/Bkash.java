package dip;

public class Bkash implements PaymentProcessor{
    @Override
    public void pay() {
        IO.println("Bkash Paid!");
    }
}
