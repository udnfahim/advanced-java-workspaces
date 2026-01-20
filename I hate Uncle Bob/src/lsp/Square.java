package lsp;

public class Square extends Length{
    public void area(String name){
        IO.println( name + " Area: " + getLength()*getLength());
    }
}
