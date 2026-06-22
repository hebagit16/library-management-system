package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutTests extends BaseTest {

    private void navigateToCheckout() {

        LoginPage login = new LoginPage(driver);
        InventoryPage inventory = new InventoryPage(driver);
        CartPage cart = new CartPage(driver);

        login.login("standard_user", "secret_sauce");
        inventory.addItem();
        inventory.openCart();
        cart.goToCheckout();
    }

    @Test
    public void successfulInformationEntry() {

        navigateToCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);

        checkout.fillForm("Menna", "Mohamed", "12345");

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"));
    }

    @Test
    public void missingFirstName() {

        navigateToCheckout();

        driver.findElement(
                org.openqa.selenium.By.id("last-name")
        ).sendKeys("Mohamed");

        driver.findElement(
                org.openqa.selenium.By.id("postal-code")
        ).sendKeys("12345");

        driver.findElement(
                org.openqa.selenium.By.id("continue")
        ).click();

        String error = driver.findElement(
                org.openqa.selenium.By.cssSelector("h3[data-test='error']")
        ).getText();

        Assert.assertTrue(error.contains("First Name"));
    }

    @Test
    public void missingLastName() {

        navigateToCheckout();

        driver.findElement(
                org.openqa.selenium.By.id("first-name")
        ).sendKeys("Menna");

        driver.findElement(
                org.openqa.selenium.By.id("postal-code")
        ).sendKeys("12345");

        driver.findElement(
                org.openqa.selenium.By.id("continue")
        ).click();

        String error = driver.findElement(
                org.openqa.selenium.By.cssSelector("h3[data-test='error']")
        ).getText();

        Assert.assertTrue(error.contains("Last Name"));
    }

    @Test
    public void missingPostalCode() {

        navigateToCheckout();

        driver.findElement(
                org.openqa.selenium.By.id("first-name")
        ).sendKeys("Menna");

        driver.findElement(
                org.openqa.selenium.By.id("last-name")
        ).sendKeys("Mohamed");

        driver.findElement(
                org.openqa.selenium.By.id("continue")
        ).click();

        String error = driver.findElement(
                org.openqa.selenium.By.cssSelector("h3[data-test='error']")
        ).getText();

        Assert.assertTrue(error.contains("Postal Code"));
    }
}
