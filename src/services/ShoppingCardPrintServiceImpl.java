package services;

import calculators.DeliveryCostCalculator;
import modals.Category;
import modals.ShoppingCard;
import modals.ShoppingCardItem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCardPrintServiceImpl implements ShoppingCardPrintService {
    private ShoppingCard shoppingCard;
    private DeliveryCostCalculator deliveryCostCalculator;

    public ShoppingCardPrintServiceImpl(ShoppingCard shoppingCard, DeliveryCostCalculator deliveryCostCalculator) {
        this.shoppingCard = shoppingCard;
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    @Override
    public void print() {
        Map<Category, List<ShoppingCardItem>> categoryMap = shoppingCard.getItems().stream()
                .collect(Collectors.groupingBy(o -> o.getProduct().getCategory()));

        for (Category category : categoryMap.keySet()) {
            System.out.println("Category: " + category.getTitle());
            categoryMap.get(category).forEach(item ->
                    System.out.print("    " + "Product Name: " + item.getProduct().getTitle()
                            + " Quantity: " + item.getQuantity()
                            + " Unit Price: " + item.getProduct().getPrice()
                    ));
        }
        System.out.println("Total Price: " + shoppingCard.getTotal());
        System.out.println("Total Discount: " + shoppingCard.getCampaignDiscount() + shoppingCard.getCouponDiscount());
        System.out.println("Total Amount: " + shoppingCard.getTotalAmountAfterDiscounts());
        System.out.println("Delivery Cost: " + deliveryCostCalculator.calculateFor(shoppingCard));

    }

}
