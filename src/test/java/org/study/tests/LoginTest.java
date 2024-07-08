package org.study.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.study.pageobjects.LandingPage;
import org.study.testcomponents.ReportsConfig;
import org.study.testcomponents.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends TestBase {

    @Test
    public void login() throws IOException {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.login("vishal.jadhav.2805@gmail.com", "Vishalj2805");
    }




}
