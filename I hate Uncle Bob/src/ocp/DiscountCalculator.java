package ocp;

public class DiscountCalculator{
    public void discountForCustomers(Discount discount , String customer){
        IO.println( customer + " Customer Discount: " + discount.discount()* 100+ "%");
    }
}
