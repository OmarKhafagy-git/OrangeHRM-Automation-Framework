package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 1. Locators
    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[@type='submit']");

    private By successHeader = By.xpath("//h6[text()='Personal Details']");

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddEmployeeTab() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeTab)).click();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public boolean isEmployeeSavedSuccessfully() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader)).isDisplayed();
    }
}
