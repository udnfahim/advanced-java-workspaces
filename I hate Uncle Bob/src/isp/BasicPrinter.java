package isp;

public class BasicPrinter implements  PrintFunction , ScanFunction{
    @Override
    public void print() {
        IO.println("Printing...by BasicPrinter");
    }

    @Override
    public void scan() {
        IO.println("Scanning...by BasicPrinter");
    }
}
