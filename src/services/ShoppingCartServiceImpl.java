package services;


import calculators.DeliveryCostCalculator;
import modals.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCard shoppingCard;
    private DeliveryCostCalculator deliveryCostCalculator;

    public ShoppingCartServiceImpl(ShoppingCard shoppingCard, DeliveryCostCalculator deliveryCostCalculator) {
        this.shoppingCard = shoppingCard;
        this.deliveryCostCalculator = deliveryCostCalculator;
    }


    @Override
    public void addItem(Product product, int quantity) {
        ShoppingCardItem item = shoppingCard.getItems().stream()
                .filter(shoppingCardItem -> shoppingCardItem.getProduct().equals(product))
                .findAny()
                .orElse(null);
        if (item == null)
            shoppingCard.getItems().add(new ShoppingCardItem(product, quantity));
        else
            item.setQuantity(item.getQuantity() + quantity);
        shoppingCard.setTotal(shoppingCard.getTotal() + product.getPrice() * quantity);
    }


    // 'Cart should apply the maximum amount of discount to the cart'. Unclear explanation. 2 possible meaning:
    // 1. Apply the campaign that maximize the discount among given campaigns (If a several campaign exists for one category).
    // 2. Apply all given campaigns to maximize the discount.
    // I will go with number 2. Because different campaigns can exist for different categories.

    // 'Campaign discount vary based on the number of products in the cart'. Unclear explanation. 2 possible meaning:
    // 1. It cares the number of product in the cart, not the  number of products belong to category
    // 2. Campaigns are applicable to category, so it should care the number of products belong to that category in the cart.
    // I will go with number 2 which makes more sense than number 1.
    @Override
    public void applyDiscounts(Campaign... campaigns) {
        Map<Category, List<ShoppingCardItem>> categoryMap = shoppingCard.getItems().stream()
                .collect(Collectors.groupingBy(o -> o.getProduct().getCategory()));

        Map<Category, List<Campaign>> campaignMap = Arrays.stream(campaigns)
                .collect(Collectors.groupingBy(Campaign::getCategory));

        double totalDiscount = 0;
        for (Category category : campaignMap.keySet()) {
            int categoryQuantity = categoryMap.get(category).stream().mapToInt(ShoppingCardItem::getQuantity).sum();
            if (categoryMap.containsKey(category)) {
                List<Campaign> validCampaigns = campaignMap.get(category).stream()
                        .filter(campaign -> categoryQuantity > campaign.getAmount())
                        .collect(Collectors.toList());
                double maxDiscount = 0;
                for (Campaign campaign : validCampaigns) {
                    if (campaign.getDiscountType().equals(DiscountType.Amount)) {
                        double currentDiscount = campaign.getDiscount();
                        maxDiscount = Math.max(maxDiscount, currentDiscount);
                    } else if (campaign.getDiscountType().equals(DiscountType.Rate)) {
                        double currentDiscount = 0;
                        for (ShoppingCardItem item : categoryMap.get(category)) {
                            currentDiscount += item.getProduct().getPrice() * (campaign.getDiscount() / 100) * item.getQuantity();
                            maxDiscount = Math.max(maxDiscount, currentDiscount);
                        }
                    }
                    totalDiscount += maxDiscount;
                }
            }

        }
        shoppingCard.setCampaignDiscount(shoppingCard.getCampaignDiscount() + totalDiscount);
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        double total = getTotalAmountAfterDiscounts();
        double couponDiscount = 0;
        if (total > coupon.getMinAmount()) {
            if (coupon.getDiscountType().equals(DiscountType.Amount))
                couponDiscount = coupon.getDiscount();
            else if (coupon.getDiscountType().equals(DiscountType.Rate))
                couponDiscount = total * (coupon.getDiscount() / 100);
        }
        shoppingCard.setCouponDiscount(shoppingCard.getCouponDiscount() + couponDiscount);
    }

    @Override
    public double getTotalAmountAfterDiscounts() {
        return shoppingCard.getTotal() - (getCouponDiscount() + getCampaignDiscount());
    }

    @Override
    public double getCouponDiscount() {
        return shoppingCard.getCouponDiscount();
    }

    @Override
    public double getCampaignDiscount() {
        return shoppingCard.getCouponDiscount();
    }

    @Override
    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(shoppingCard);
    }

    @Override
    public void print() {

    }

    @Override
    public double getTotalAmount() {
        return shoppingCard.getTotal();
    }
}