package calculators;


import models.ShoppingCart;

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
    public double calculateFor(ShoppingCart shoppingCart) {
        return getDeliveryCost(shoppingCart) + getProductCost(shoppingCart) + fixedCost;
    }

    private double getProductCost(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().size() * costPerProduct;
    }

    private double getDeliveryCost(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().stream()
                .map(shoppingCardItem ->
                        shoppingCardItem.getProduct()
                                .getCategory())
                .collect(Collectors.toSet())
                .size()
                * costPerDelivery;
    }
}