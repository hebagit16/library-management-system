


package Tests;

import Base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

    public class loginTest extends BaseTest {

        @Test
        public void validLogin() {

            LoginPage login = new LoginPage(driver);

            login.login("standard_user", "secret_sauce");

            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        }

        @Test
        public void lockedUserLogin() {

            LoginPage login = new LoginPage(driver);

            login.login("locked_out_user", "secret_sauce");

            Assert.assertTrue(login.getError().contains("locked out"));
        }

        @Test
        public void invalidLogin() {

            LoginPage login = new LoginPage(driver);

            login.login("wrong_user", "wrong_pass");

            Assert.assertTrue(login.getError().contains("Username and password"));
        }
    }


