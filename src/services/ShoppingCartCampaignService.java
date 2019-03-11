package services;

import models.Campaign;

public interface ShoppingCartCampaignService {
    void applyDiscounts(Campaign... campaigns);

}