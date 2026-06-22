package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTests extends BaseTest {

    @Test
    public void itemAppearsInCart() {

        LoginPage login = new LoginPage(driver);
        InventoryPage inventory = new InventoryPage(driver);

        login.login("standard_user", "secret_sauce");

        inventory.addItem();
        inventory.openCart();

        String itemName = driver.findElement(
                org.openqa.selenium.By.className("inventory_item_name")
        ).getText();

        Assert.assertEquals(itemName, "Sauce Labs Backpack");
    }

    @Test
    public void removeItemFromCart() {

        LoginPage login = new LoginPage(driver);
        InventoryPage inventory = new InventoryPage(driver);

        login.login("standard_user", "secret_sauce");

        inventory.addItem();
        inventory.openCart();

        driver.findElement(
                org.openqa.selenium.By.id("remove-sauce-labs-backpack")
        ).click();

        int items = driver.findElements(
                org.openqa.selenium.By.className("cart_item")
        ).size();

        Assert.assertEquals(items, 0);
    }
}