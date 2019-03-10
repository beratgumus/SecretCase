package exceptions;

public class CouponDiscountOverflowException extends RuntimeException {
    public CouponDiscountOverflowException() {
        super("Coupon discount can not be applied due to negative balance");
    }
}
