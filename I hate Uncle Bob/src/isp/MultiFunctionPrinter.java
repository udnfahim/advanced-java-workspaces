package isp;

public class MultiFunctionPrinter implements PrintFunction, ScanFunction, FaxFunction{
    @Override
    public void fax() {
        IO.println("Faxing... by MultiFunctionPrinter");
    }

    @Override
    public void print() {
        IO.println("Printing... by MultiFunctionPrinter");
    }

    @Override
    public void scan() {
        IO.println("Scanning... by MultiFunctionPrinter");
    }
}
