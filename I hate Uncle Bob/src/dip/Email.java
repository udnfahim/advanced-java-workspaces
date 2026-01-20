package dip;

public class Email implements Text{

    @Override
    public void important() {
        IO.println("Notification by Email");
    }
}
