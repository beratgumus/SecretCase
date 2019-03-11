package models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> items = new ArrayList<>();
    private double couponDiscount;
    private double campaignDiscount;
    private double total;

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public double getTotalAmountAfterDiscounts() {
        return this.getTotal() - (getCouponDiscount() + getCampaignDiscount());
    }


}