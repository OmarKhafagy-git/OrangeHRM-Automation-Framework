package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class NegativeLoginTest extends BaseTest {

    // ==========================================
    // THE UPGRADED DATA PROVIDER
    // Added a 'Scenario' column to explain what we are testing!
    // ==========================================
    @DataProvider(name = "loginData")
    public Object[][] getNegativeLoginData() {
        return new Object[][] {
                // Scenario Description                 // Username     // Password      // Expected Error
                {"Valid Username, Wrong Password",      "Admin",       "wrongPass",     "Invalid credentials"},
                {"Wrong Username, Valid Password",      "wrongUser",   "admin123",      "Invalid credentials"},
                {"Case Sensitivity (All Caps)",         "ADMIN",       "ADMIN123",      "Invalid credentials"}
        };
    }

    // ==========================================
    // THE TEST
    // We must add 'String scenario' as the first parameter to match the data!
    // ==========================================
    @Test(dataProvider = "loginData", description = "Verify login fails with invalid credentials")
    public void testInvalidLogin(String scenario, String username, String password, String expectedErrorMsg) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String actualErrorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(actualErrorMessage, expectedErrorMsg, "Error message did not match!");

        // The Senior Log Output
        System.out.println("Passed Scenario: [" + scenario + "] | Tried Username: '" + username + "'");
    }
}