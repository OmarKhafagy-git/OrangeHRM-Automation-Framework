package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PimEmployeeListPage;

public class EmployeeTest extends BaseTest {

    // ==========================================
    // THE ENTERPRISE FIX: Dynamic Test Data
    // We generate a unique ID based on the current millisecond!
    // ==========================================
    String uniqueId = String.valueOf(System.currentTimeMillis()).substring(7);
    String uniqueFirstName = "QA_John_" + uniqueId;
    String uniqueLastName = "Doe_" + uniqueId;


    // ==========================================
    // TEST 1: CREATE
    // ==========================================
    @Test(priority = 1, description = "Verify Admin can add a new employee")
    public void testAddEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickPimMenu();

        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.clickAddEmployeeTab();

        // We inject the unique variables here instead of hardcoded names
        addEmployeePage.enterFirstName(uniqueFirstName);
        addEmployeePage.enterLastName(uniqueLastName);
        addEmployeePage.clickSave();

        boolean isSaved = addEmployeePage.isEmployeeSavedSuccessfully();
        Assert.assertTrue(isSaved, "Employee failed to save!");
    }


    // ==========================================
    // TEST 2: READ (SEARCH)
    // ==========================================
    @Test(priority = 2, description = "Verify Admin can search for the employee")
    public void testSearchEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickPimMenu();

        PimEmployeeListPage employeeListPage = new PimEmployeeListPage(driver);

        // We search for the exact unique name we just created
        employeeListPage.enterEmployeeName(uniqueFirstName);
        employeeListPage.clickSearch();

        String actualFirstName = employeeListPage.getFirstRowFirstName();
        String actualLastName = employeeListPage.getFirstRowLastName();

        // The assertion now checks against our unique variables
        Assert.assertEquals(actualFirstName, uniqueFirstName, "First name did not match the search!");
        Assert.assertEquals(actualLastName, uniqueLastName, "Last name did not match the search!");
    }


    // ==========================================
    // TEST 3: DELETE (TEARDOWN)
    // ==========================================
    @Test(priority = 3, description = "Verify Admin can delete the employee")
    public void testDeleteEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickPimMenu();

        PimEmployeeListPage employeeListPage = new PimEmployeeListPage(driver);

        // Isolate our unique user
        employeeListPage.enterEmployeeName(uniqueFirstName);
        employeeListPage.clickSearch();

        // Delete them
        employeeListPage.clickDeleteIcon();
        employeeListPage.confirmDeletion();

        // Search one last time to prove the table is empty
        employeeListPage.enterEmployeeName(uniqueFirstName);
        employeeListPage.clickSearch();

        boolean isDeleted = employeeListPage.isNoRecordsMessageDisplayed();
        Assert.assertTrue(isDeleted, "Deletion failed!");

        System.out.println("Success! Employee " + uniqueFirstName + " was created, verified, and wiped cleanly.");
    }
}