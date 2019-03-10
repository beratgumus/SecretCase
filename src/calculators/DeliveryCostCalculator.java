package calculators;


import modals.ShoppingCard;

public interface DeliveryCostCalculator {
  
      public double calculateFor(ShoppingCard shoppingCard);
}