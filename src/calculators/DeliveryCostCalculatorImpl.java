package calculators;


import modals.ShoppingCard;

import java.util.stream.Collectors;

public class DeliveryCostCalculatorImpl implements DeliveryCostCalculator {

    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculatorImpl(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }


    @Override
    public double calculateFor(ShoppingCard shoppingCard) {
        return getDeliveryCost(shoppingCard) + getProductCost(shoppingCard) + fixedCost;
    }

    private double getProductCost(ShoppingCard shoppingCard) {
        return shoppingCard.getItems().size() * costPerProduct;
    }

    private double getDeliveryCost(ShoppingCard shoppingCard) {
        return shoppingCard.getItems().stream()
                .map(shoppingCardItem ->
                        shoppingCardItem.getProduct()
                                .getCategory())
                .collect(Collectors.toSet())
                .size()
                * costPerDelivery;
    }
}