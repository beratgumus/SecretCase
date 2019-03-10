package exceptions;

public class CampaignBeforeCouponException extends RuntimeException {
    public CampaignBeforeCouponException() {
        super("Campaign must be applied before coupon discount");
    }
}
