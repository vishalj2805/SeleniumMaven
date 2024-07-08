package org.study.testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initializeBrowser() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//study//resources//GlobalData.properties"));

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowsers(){
        driver.quit();
    }

    public String takeScreenshot(String name, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        String path = System.getProperty("user.dir") + "//src/test/java/org/study/screenshots//" + name + ".png";
        FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE),
                new File(path));
        return path;
    }
}
