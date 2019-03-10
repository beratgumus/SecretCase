package modals;

import java.util.List;

public class ShoppingCard {
    private List<ShoppingCardItem> items;
    private double couponDiscount;
    private double campaignDiscount;
    private double total;

    public List<ShoppingCardItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setItems(List<ShoppingCardItem> items) {
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
}