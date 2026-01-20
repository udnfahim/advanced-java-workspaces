package ocp;

public class Premium implements Discount{
    @Override
    public double discount() {
        return .20;
    }
}
