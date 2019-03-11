import models.Category;
import models.Product;
import models.ShoppingCart;
import org.junit.Test;
import services.ShoppingCartService;
import services.ShoppingCartServiceImpl;

import static org.junit.Assert.assertEquals;

public class ShoppingCartServiceImplTest extends TestObjects {

    @Test
    public void addItem() {
        ShoppingCart shoppingCart = getSampleShoppingCard();
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(shoppingCart);
        Product banana = new Product("Banana", 100.0, new Category("food"));
        shoppingCartService.addItem(banana, 3);
        shoppingCartService.addItem(banana, 2);
        assertEquals(5, shoppingCart.getItems().stream().filter(item -> item.getProduct().equals(banana)).findFirst().get().getQuantity());

    }
}