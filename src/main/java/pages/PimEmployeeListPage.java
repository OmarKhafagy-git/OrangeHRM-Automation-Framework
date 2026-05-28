package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PimEmployeeListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By employeeNameSearchField = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.xpath("//button[@type='submit']");

    private By firstRowFirstNameCell = By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//div[@role='row']/div[3]/div");
    private By firstRowLastNameCell = By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//div[@role='row']/div[4]/div");
    private By deleteIcon = By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//i[contains(@class, 'bi-trash')]");
    private By confirmDeleteButton = By.xpath("//button[contains(@class, 'oxd-button--label-danger')]");
    private By noRecordsFoundMessage = By.xpath("//span[text()='No Records Found']");


    public void clickDeleteIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
    }

    public void confirmDeletion() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public boolean isNoRecordsMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsFoundMessage)).isDisplayed();
    }

    public PimEmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmployeeName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameSearchField)).sendKeys(name);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public String getFirstRowFirstName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowFirstNameCell)).getText();
    }

    public String getFirstRowLastName() {
        return driver.findElement(firstRowLastNameCell).getText();
    }
}
