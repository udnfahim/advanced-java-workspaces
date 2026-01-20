package isp;

public class Car implements Drive{
    @Override
    public void drive() {
        IO.println("Car Drive...");
    }
}
