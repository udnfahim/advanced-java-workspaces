package srp;

public class PrintingBook {
    public void print(Book book){
        IO.println("Book Name: " + book.getBookName() +  "\n" +
                "Book Price: " + book.getBookPrice() + "\n" +
                "Book Author: " + book.getBookAuthor());
    }
}
