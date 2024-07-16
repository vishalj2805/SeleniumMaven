package org.study.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.study.data.ExcelReader;
import org.study.pageobjects.LandingPage;
import org.study.testcomponents.ReportsConfig;
import org.study.testcomponents.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class LoginTest extends TestBase {
    ExcelReader excelReader = new ExcelReader();

    @Test(dataProvider = "getData")
    public void login(String username, String password, String phoneNumber) throws IOException, InterruptedException {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(username, password);
        Thread.sleep(3000);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return excelReader.getDataFromSheet("Credentials");
    }
}
