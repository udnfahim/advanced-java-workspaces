package lsp;

public class Rectangle extends Length {
    private double width;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void area(String name){
        IO.println( name +" Area: " +width*getLength());
    }
}
