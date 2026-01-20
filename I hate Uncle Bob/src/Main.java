import dip.*;
import isp.Airplane;
import isp.BasicPrinter;
import isp.Car;
import isp.MultiFunctionPrinter;
import lsp.Penguin;
import lsp.Sparrow;
import lsp.Square;
import ocp.*;
import srp.*;

void main() {

    //Single Responsibility Principle (SRP)

    IO.println("Single Responsibility Principle (SRP)");

    IO.println("Employee-SRP");
    Employee employee = new Employee("Jon" , 20000.50 , 500.56);
    ReportGenerator reportGenerator = new ReportGenerator();
    reportGenerator.generatingReports(employee);

    IO.println("Book-SRP");
    Book book = new Book("Game of Throne",1500,"Gorge R R ");
    PrintingBook printingBook = new PrintingBook();
    printingBook.print(book);
    BookRepository bookRepository = new BookRepository();
    bookRepository.save(book);


    //Open/Closed Principle (OCP)

    IO.println("Open/Closed Principle (OCP)");

    IO.println("Customer - OCP");
    Discount regular = new Regular();
    Discount premium = new Premium();
    Discount vip = new VIP();
    DiscountCalculator calculator = new DiscountCalculator();
    calculator.discountForCustomers(regular,"Regular");
    calculator.discountForCustomers(premium,"Premium");
    calculator.discountForCustomers(vip,"VIP");

    IO.println("Area - OCP");
    Circle circle = new Circle();
    circle.setRadius(4.5);
    IO.println("Circle Area: " + circle.area());
    Rectangle rectangle = new Rectangle();
    rectangle.setLength(20.5);
    rectangle.setWidth(10.5);
    IO.println("Rectangle Area: " + rectangle.area());
    Triangle triangle = new Triangle();
    triangle.setBase(2);
    triangle.setWidth(20.1);
    IO.println("Triangle Area: " + triangle.area());


    //Liskov Substitution Principle (LSP)
    IO.println("Liskov Substitution Principle (LSP)");

    IO.println("Bird - LSP");
    Sparrow sparrow = new Sparrow();
    sparrow.eat("Sparrow");
    sparrow.fly("Sparrow");
    Penguin penguin = new Penguin();
    penguin.eat("Penguin");

    IO.println("Area - LSP");
    lsp.Rectangle rectangle1 = new lsp.Rectangle();
    rectangle1.setLength(10);
    rectangle1.setWidth(20);
    rectangle1.area("Rectangle");
    Square square = new Square();
    square.setLength(10);
    square.area("Square");

    //Interface Segregation Principle (ISP)

    IO.println("Interface Segregation Principle (ISP)");

    IO.println("Printer - ISP");
    BasicPrinter basicPrinter = new BasicPrinter();
    basicPrinter.print();
    basicPrinter.scan();
    MultiFunctionPrinter multiFunctionPrinter = new MultiFunctionPrinter();
    multiFunctionPrinter.print();
    multiFunctionPrinter.scan();
    multiFunctionPrinter.fax();

    IO.println("CAR PLAN - ISP");
    Car car = new Car();
    car.drive();
    Airplane airplane = new Airplane();
    airplane.drive();;
    airplane.fly();


    //Dependency Inversion Principle (DIP)

    IO.println("Dependency Inversion Principle (DIP)");

    PaymentProcessor payPalPayment = new PayPalPayment();
    PaymentService paymentService = new PaymentService();
    paymentService.payment(payPalPayment);
    // I use here bkash to chack properly that dependency !
    PaymentProcessor bkash = new Bkash();
    paymentService.payment(bkash);

    IO.println("Notification - DIP");

    Text email = new Email();
    Text sms = new Sms();
    Notification notification = new Notification();
    notification.notification(email);
    notification.notification(sms);

    //ALL SOLID DONE!


}
