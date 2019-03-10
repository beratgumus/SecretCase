package exceptions;

public class CampaignDiscountOverflowException extends RuntimeException {
    public CampaignDiscountOverflowException() {
        super("Campaign discount can not be applied due to negative balance");
    }
}
