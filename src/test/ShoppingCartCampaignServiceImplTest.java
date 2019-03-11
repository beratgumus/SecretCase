import exceptions.CampaignBeforeCouponException;
import exceptions.CampaignDiscountOverflowException;
import models.*;
import org.junit.Test;
import services.ShoppingCartCampaignService;
import services.ShoppingCartCampaignServiceImpl;
import services.ShoppingCartCouponService;
import services.ShoppingCartCouponServiceImpl;

import static org.junit.Assert.assertEquals;

public class ShoppingCartCampaignServiceImplTest extends TestObjects {


    @Test
    public void applyMaxCampaignForParticularCategory() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartCampaignService shoppingCartCampaignService = new ShoppingCartCampaignServiceImpl(shoppingCart);
        Campaign foodCampaignAmount = new Campaign(new Category("food"), 10, 2, DiscountType.Amount); // discount: 10
        Campaign foodCampaignRate = new Campaign(new Category("food"), 10, 2, DiscountType.Rate);     // discount: 45
        shoppingCartCampaignService.applyDiscounts(foodCampaignAmount, foodCampaignRate);
        assertEquals(45.0, shoppingCart.getCampaignDiscount(), 0.0);
    }

    @Test
    public void applySeveralCampaignForDifferentCategory() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartCampaignService shoppingCartCampaignService = new ShoppingCartCampaignServiceImpl(shoppingCart);
        Campaign foodCampaign = new Campaign(new Category("food"), 10, 2, DiscountType.Amount); // discount: 10
        Campaign electronicsCampaign = new Campaign(new Category("electronics"), 2, 2, DiscountType.Rate); // discount: 180
        shoppingCartCampaignService.applyDiscounts(foodCampaign, electronicsCampaign);
        assertEquals(190, shoppingCart.getCampaignDiscount(), 0.0);

    }

    @Test(expected = CampaignBeforeCouponException.class)
    public void campaignBeforeCouponException() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartCampaignService shoppingCartCampaignService = new ShoppingCartCampaignServiceImpl(shoppingCart);

        ShoppingCartCouponService shoppingCartCouponService = new ShoppingCartCouponServiceImpl(shoppingCart);
        Coupon coupon = new Coupon(1, 1, DiscountType.Amount);
        shoppingCartCouponService.applyCoupon(coupon);

        Campaign foodCampaign = new Campaign(new Category("food"), 10, 2, DiscountType.Amount); // discount: 10
        shoppingCartCampaignService.applyDiscounts(foodCampaign);


    }

    @Test(expected = CampaignDiscountOverflowException.class)
    public void campaignDiscountOverflowException() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartCampaignService shoppingCartCampaignService = new ShoppingCartCampaignServiceImpl(shoppingCart);

        Campaign foodCampaign = new Campaign(new Category("food"), 10000, 2, DiscountType.Amount); // discount: 10
        shoppingCartCampaignService.applyDiscounts(foodCampaign);
    }
}