import calculators.DeliveryCostCalculator;
import calculators.DeliveryCostCalculatorImpl;
import models.ShoppingCart;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeliveryCostCalculatorImplTest extends TestObjects {

    @Test
    public void calculateFor() {
        DeliveryCostCalculator delivery1 = getSampleDeliveryCalculator();
        DeliveryCostCalculator delivery2 = new DeliveryCostCalculatorImpl(3.0, 2.0, 2.99);
        ShoppingCart shoppingCart = getSampleShoppingCard();

        assertEquals(delivery1.calculateFor(shoppingCart), 26.99, 0.1);
        assertEquals(delivery2.calculateFor(shoppingCart), 16.99, 0.1);

    }
}