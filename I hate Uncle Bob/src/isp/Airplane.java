package isp;

public class Airplane implements  Drive , Fly{
    @Override
    public void drive() {
        IO.println("Airplane drive in runaway ...");
    }

    @Override
    public void fly() {
        IO.println("Airplane fly...");
    }
}
