package services;


import modals.Product;
import modals.ShoppingCard;
import modals.ShoppingCardItem;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCard shoppingCard;

    public ShoppingCartServiceImpl(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    @Override
    public void addItem(Product product, int quantity) {
        ShoppingCardItem item = shoppingCard.getItems().stream()
                .filter(shoppingCardItem -> shoppingCardItem.getProduct().equals(product))
                .findAny()
                .orElse(null);
        if (item == null)
            shoppingCard.getItems().add(new ShoppingCardItem(product, quantity));
        else
            item.setQuantity(item.getQuantity() + quantity);
        shoppingCard.setTotal(shoppingCard.getTotal() + product.getPrice() * quantity);
    }


}