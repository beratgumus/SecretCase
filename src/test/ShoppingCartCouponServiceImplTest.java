import exceptions.CouponDiscountOverflowException;
import models.Coupon;
import models.DiscountType;
import models.ShoppingCart;
import org.junit.Test;
import services.ShoppingCartCouponService;
import services.ShoppingCartCouponServiceImpl;

import static org.junit.Assert.assertEquals;

public class ShoppingCartCouponServiceImplTest extends TestObjects {

    @Test
    public void applyCouponSuccess() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartCouponService shoppingCartCouponService = new ShoppingCartCouponServiceImpl(shoppingCart);
        Coupon couponAmount = new Coupon(3, 10, DiscountType.Amount);
        shoppingCartCouponService.applyCoupon(couponAmount);
        assertEquals(10, shoppingCart.getCouponDiscount(), 0.0);

        Coupon couponRate = new Coupon(3, 10, DiscountType.Rate);
        shoppingCartCouponService.applyCoupon(couponRate);
        assertEquals(954, shoppingCart.getCouponDiscount(), 0.0);

        assertEquals(8496, shoppingCart.getTotalAmountAfterDiscounts(), 0.0);

    }

    @Test(expected = CouponDiscountOverflowException.class)
    public void couponDiscountOverflowException() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartCouponService shoppingCartCouponService = new ShoppingCartCouponServiceImpl(shoppingCart);
        Coupon coupon = new Coupon(3, 10000000, DiscountType.Amount);
        shoppingCartCouponService.applyCoupon(coupon);

    }
}