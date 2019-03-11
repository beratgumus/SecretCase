package services;

import calculators.DeliveryCostCalculator;
import models.Category;
import models.ShoppingCart;
import models.ShoppingCartItem;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartPrintServiceImpl implements ShoppingCartPrintService {
    private ShoppingCart shoppingCart;
    private DeliveryCostCalculator deliveryCostCalculator;

    public ShoppingCartPrintServiceImpl(ShoppingCart shoppingCart, DeliveryCostCalculator deliveryCostCalculator) {
        this.shoppingCart = shoppingCart;
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    @Override
    public void print() {
        Map<Category, List<ShoppingCartItem>> categoryMap = shoppingCart.getItems().stream()
                .collect(Collectors.groupingBy(o -> o.getProduct().getCategory()));

        for (Category category : categoryMap.keySet()) {
            System.out.println("Category: " + category.getTitle());
            categoryMap.get(category).forEach(item ->
                    System.out.print("    " + "Product Name: " + item.getProduct().getTitle()
                            + ", Quantity: " + item.getQuantity()
                            + ", Unit Price: " + item.getProduct().getPrice() + "\n"

                    ));
            System.out.println();
        }
        System.out.println("Total Price: " + shoppingCart.getTotal());
        System.out.println("Total Discount: " + (shoppingCart.getCampaignDiscount() + shoppingCart.getCouponDiscount()));
        System.out.println("Total Amount: " + shoppingCart.getTotalAmountAfterDiscounts());
        double value = deliveryCostCalculator.calculateFor(shoppingCart);
        System.out.println("Delivery Cost: " + Double.parseDouble(new DecimalFormat("##.####").format(value)));

    }

}
