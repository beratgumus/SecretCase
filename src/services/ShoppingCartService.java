package services;


import modals.Campaign;
import modals.Coupon;
import modals.Product;

public interface ShoppingCartService {
    void addItem(Product product, int quantity);

    void applyDiscounts(Campaign... campaigns);

    void applyCoupon(Coupon coupon);

    double getTotalAmountAfterDiscounts();

    double getCouponDiscount();

    double getCampaignDiscount();

    double getDeliveryCost();

    void print();

    double getTotalAmount();
}