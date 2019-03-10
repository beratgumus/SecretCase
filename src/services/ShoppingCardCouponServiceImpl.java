package services;

import exceptions.CouponDiscountOverflowException;
import modals.Coupon;
import modals.DiscountType;
import modals.ShoppingCard;

public class ShoppingCardCouponServiceImpl implements ShoppingCardCouponService {
    private ShoppingCard shoppingCard;

    public ShoppingCardCouponServiceImpl(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        double total = shoppingCard.getTotalAmountAfterDiscounts();
        double couponDiscount = 0;
        if (total > coupon.getMinAmount()) {
            if (coupon.getDiscountType().equals(DiscountType.Amount))
                couponDiscount = coupon.getDiscount();
            else if (coupon.getDiscountType().equals(DiscountType.Rate))
                couponDiscount = total * (coupon.getDiscount() / 100);
        }
        if (shoppingCard.getTotalAmountAfterDiscounts() - couponDiscount < 0)
            throw new CouponDiscountOverflowException();
        else
            shoppingCard.setCouponDiscount(shoppingCard.getCouponDiscount() + couponDiscount);
    }
}