package services;


import models.Product;
import models.ShoppingCart;
import models.ShoppingCartItem;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCart shoppingCart;

    public ShoppingCartServiceImpl(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void addItem(Product product, int quantity) {
        ShoppingCartItem item = shoppingCart.getItems().stream()
                .filter(shoppingCartItem -> shoppingCartItem.getProduct().equals(product))
                .findAny()
                .orElse(null);
        if (item == null)
            shoppingCart.getItems().add(new ShoppingCartItem(product, quantity));
        else
            item.setQuantity(item.getQuantity() + quantity);
        shoppingCart.setTotal(shoppingCart.getTotal() + product.getPrice() * quantity);
    }
}