import calculators.DeliveryCostCalculator;
import calculators.DeliveryCostCalculatorImpl;
import models.Category;
import models.Product;
import models.ShoppingCart;
import services.ShoppingCartService;
import services.ShoppingCartServiceImpl;

public class TestObjects {

    ShoppingCart getSampleShoppingCard() {
        Category food = new Category("food");
        Product apple = new Product("Apple", 100.0, food);
        Product almond = new Product("Almond", 150.0, food);
        Category electronics = new Category("electronics");
        Product phone = new Product("Phone", 2000.0, electronics);
        Product computer = new Product("Computer", 5000.0, electronics);

        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(shoppingCart);
        shoppingCartService.addItem(apple, 3);
        shoppingCartService.addItem(almond, 1);
        shoppingCartService.addItem(phone, 2);
        shoppingCartService.addItem(computer, 1);

        return shoppingCart;

    }

    DeliveryCostCalculator getSampleDeliveryCalculator() {
        return new DeliveryCostCalculatorImpl(2.0, 5.0, 2.99);
    }

}
