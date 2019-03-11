package models;


public class Coupon {
    private int minAmount;
    private double discount;
    private DiscountType discountType;

    public Coupon(int minAmount, double discount, DiscountType discountType) {
        this.minAmount = minAmount;
        this.discount = discount;
        this.discountType = discountType;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }


}
