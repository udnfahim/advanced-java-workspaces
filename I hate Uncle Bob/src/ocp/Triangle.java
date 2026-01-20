package ocp;

public class Triangle implements Shape{
    //a new Triangle class to the code without modifying the existing Shape interface

    private double base;
    private double width;

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double area() {
        return (base*width)/2;
    }


}
