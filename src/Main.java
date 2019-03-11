import calculators.DeliveryCostCalculator;
import calculators.DeliveryCostCalculatorImpl;
import models.*;
import services.*;

public class Main {

    public static void main(String[] args) {
        Category food = new Category("food");
        Product apple = new Product("Apple", 100.0, food);
        Product almond = new Product("Almond", 150.0, food);

        Category electronics = new Category("electronics");
        Product phone = new Product("Phone", 2000.0, electronics);
        Product computer = new Product("Computer", 5000.0, electronics);

        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(shoppingCart);
        shoppingCartService.addItem(apple, 3);
        shoppingCartService.addItem(almond, 1);
        shoppingCartService.addItem(phone, 2);
        shoppingCartService.addItem(computer, 1);

        Campaign foodCampaignAmount = new Campaign(new Category("food"), 10, 2, DiscountType.Amount);
        Campaign foodCampaignRate = new Campaign(new Category("food"), 10, 2, DiscountType.Rate);
        ShoppingCartCampaignService shoppingCartCampaignService = new ShoppingCartCampaignServiceImpl(shoppingCart);
        shoppingCartCampaignService.applyDiscounts(foodCampaignAmount, foodCampaignRate);

        Coupon couponAmount = new Coupon(3, 10, DiscountType.Amount);
        ShoppingCartCouponService shoppingCartCouponService = new ShoppingCartCouponServiceImpl(shoppingCart);
        shoppingCartCouponService.applyCoupon(couponAmount);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(2.0, 5.0, 2.99);
        ShoppingCartPrintService shoppingCartPrintService = new ShoppingCartPrintServiceImpl(shoppingCart, deliveryCostCalculator);
        shoppingCartPrintService.print();
    }
}
