package dip;

public class Sms implements Text {
    @Override
    public void important() {
        IO.println("Notification by SMS");
    }
}
