package ocp;

public class VIP implements Discount{
    @Override
    public double discount() {
        return .30;
    }
}
