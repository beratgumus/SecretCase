package services;

import exceptions.CouponDiscountOverflowException;
import models.Coupon;
import models.DiscountType;
import models.ShoppingCart;

public class ShoppingCartCouponServiceImpl implements ShoppingCartCouponService {
    private ShoppingCart shoppingCart;

    public ShoppingCartCouponServiceImpl(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        double total = shoppingCart.getTotalAmountAfterDiscounts();
        double couponDiscount = 0;
        if (total > coupon.getMinAmount()) {
            if (coupon.getDiscountType().equals(DiscountType.Amount))
                couponDiscount = coupon.getDiscount();
            else if (coupon.getDiscountType().equals(DiscountType.Rate))
                couponDiscount = total * (coupon.getDiscount() / 100);
        }
        if (shoppingCart.getTotalAmountAfterDiscounts() - couponDiscount < 0)
            throw new CouponDiscountOverflowException();
        else
            shoppingCart.setCouponDiscount(shoppingCart.getCouponDiscount() + couponDiscount);
    }
}