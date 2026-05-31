package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class, 'oxd-alert-content-text')]");



    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }

    public void enterUsername(String user) {
        driver.findElement(usernameField).sendKeys(user);

    }

    public void enterPassword(String pass) {
        driver.findElement(passwordField).sendKeys(pass);

    }

    public void clickLogin() {
        driver.findElement(loginButton).click();

    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}