package org.study.testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.study.pageobjects.LandingPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initializeBrowser() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//study//resources//GlobalData.properties"));

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        if(browserName.contains("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            if(browserName.contains("headless")){
                chromeOptions.addArguments("headless");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().setSize(new Dimension(1920,1080));
        } else if (browserName.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else if (browserName.contains("chromehubwin11")) {
            desiredCapabilities.setBrowserName("chrome");
            desiredCapabilities.setPlatform(Platform.WIN11);
            driver = new RemoteWebDriver(new URL("http://192.168.1.38:4444"), desiredCapabilities);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url1"));
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowsers(){
        driver.quit();
    }

    public LandingPage goToLandingPage() throws IOException {
        initializeBrowser();
        return new LandingPage(driver);
    }

    public String takeScreenshot(String name, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        String path = System.getProperty("user.dir") + "//src/test/java/org/study/screenshots//" + name + ".jpg";
        FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE),
                new File(path));
        return path;
    }
}
