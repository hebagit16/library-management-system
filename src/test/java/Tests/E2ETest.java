package Tests;

import Base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    @Test
    public void completeFlow() {

        LoginPage login = new LoginPage(driver);
        InventoryPage inventory = new InventoryPage(driver);
        CartPage cart = new CartPage(driver);
        CheckoutPage checkout = new CheckoutPage(driver);

        // LOGIN
        login.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        // ADD ITEM
        inventory.addItem();

        // CART
        inventory.openCart();

        // CHECKOUT
        cart.goToCheckout();

        // FORM
        checkout.fillForm("John", "Doe", "12345");

        // FINISH
        checkout.finish();

        // ASSERT SUCCESS
        String msg = driver.findElement(
                org.openqa.selenium.By.className("complete-header")
        ).getText();

        Assert.assertEquals(msg, "Thank you for your order!");
    }
}