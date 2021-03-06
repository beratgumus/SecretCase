package models;


public class Campaign {
    private Category category;
    private double discount;
    private int amount;
    private DiscountType discountType;

    public Campaign(Category category, double discount, int amount, DiscountType discountType) {
        this.category = category;
        this.discount = discount;
        this.amount = amount;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}