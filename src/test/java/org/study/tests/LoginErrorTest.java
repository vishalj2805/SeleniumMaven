package org.study.tests;

import org.study.pageobjects.LandingPage;
import org.study.testcomponents.RetryTCs;
import org.study.testcomponents.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginErrorTest extends TestBase {

    @Test(groups = {"InvalidLogins"})
    public void invalidPassLogin() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login("vishal.jadhav.2805@gmail.com", "12345");
        Assert.assertEquals(landingPage.getAlertMSG().strip(), " Incorrect email or password. ".strip());
    }

    @Test(groups = {"InvalidLogins"}, retryAnalyzer = RetryTCs.class)
    public void invalidEmailLogin() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login("vishal.jadhav@gmail.com", "12345");
        Assert.assertEquals(landingPage.getAlertMSG().strip(), " Incorrect email or password.1 ".strip());
    }

}
