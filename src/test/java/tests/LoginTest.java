package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid Admin credentials")
    public void testValidAdminLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"), "Login failed! We are not on the dashboard.");

        System.out.println("Login Test Passed! Successfully reached the Dashboard.");
    }
}
