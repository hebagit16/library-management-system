package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests extends BaseTest {

    @Test
    public void productsDisplayed() {

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void addItemToCart() {

        LoginPage login = new LoginPage(driver);
        InventoryPage inventory = new InventoryPage(driver);

        login.login("standard_user", "secret_sauce");

        inventory.addItem();

        String buttonText = driver.findElement(
                org.openqa.selenium.By.id("remove-sauce-labs-backpack")
        ).getText();

        Assert.assertEquals(buttonText, "Remove");
    }

    @Test
    public void inventoryItemsDisplayed() {

        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");

        int products = driver.findElements(
                By.className("inventory_item")
        ).size();

        Assert.assertTrue(products > 0);
    }
}
