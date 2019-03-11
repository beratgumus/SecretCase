package calculators;


import models.ShoppingCart;

public interface DeliveryCostCalculator {

    public double calculateFor(ShoppingCart shoppingCart);
}