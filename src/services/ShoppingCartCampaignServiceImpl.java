package services;

import exceptions.CampaignBeforeCouponException;
import exceptions.CampaignDiscountOverflowException;
import models.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartCampaignServiceImpl implements ShoppingCartCampaignService {
    private ShoppingCart shoppingCart;

    public ShoppingCartCampaignServiceImpl(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    // 'Cart should apply the maximum amount of discount to the cart'. Unclear explanation. 2 possible meaning:
    // 1. Apply the campaign that maximize the discount among given campaigns for particular category. (If a several campaign exists for one category, select one of them)
    // 2. Apply all given campaigns to maximize the discount.
    // I will go with number 1. Because different campaigns can exist for different categories.

    // 'Campaign discount vary based on the number of products in the cart'. Unclear explanation. 2 possible meaning:
    // 1. It cares the number of product in the cart, not the  number of products belong to category
    // 2. Campaigns are applicable to category, so it should care the number of products belong to that category in the cart.
    // I will go with number 2 which makes more sense than number 1.
    @Override
    public void applyDiscounts(Campaign... campaigns) {
        if (shoppingCart.getCouponDiscount() != 0)
            throw new CampaignBeforeCouponException();

        Map<Category, List<ShoppingCartItem>> categoryMap = shoppingCart.getItems().stream()
                .collect(Collectors.groupingBy(o -> o.getProduct().getCategory()));

        Map<Category, List<Campaign>> campaignMap = Arrays.stream(campaigns)
                .collect(Collectors.groupingBy(Campaign::getCategory));

        double totalDiscount = 0;
        for (Category category : campaignMap.keySet()) {
            if (categoryMap.containsKey(category)) {
                int categoryQuantity = categoryMap.get(category).stream().mapToInt(ShoppingCartItem::getQuantity).sum();
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
                        for (ShoppingCartItem item : categoryMap.get(category)) {
                            currentDiscount += item.getProduct().getPrice() * (campaign.getDiscount() / 100) * item.getQuantity();
                            maxDiscount = Math.max(maxDiscount, currentDiscount);
                        }
                    }
                }
                totalDiscount += maxDiscount;

            }

        }
        if (shoppingCart.getTotalAmountAfterDiscounts() - totalDiscount < 0)
            throw new CampaignDiscountOverflowException();
        else
            shoppingCart.setCampaignDiscount(shoppingCart.getCampaignDiscount() + totalDiscount);
    }
}