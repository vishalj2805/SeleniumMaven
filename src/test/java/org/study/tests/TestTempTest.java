package org.study.tests;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.*;
import java.time.Duration;


public class TestTempTest {

    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://admin:admin@the-internet.herokuapp.com/");
        driver.findElement(By.linkText("A/B Testing")).click();
        driver.navigate().back();
        driver.findElement(By.linkText("Basic Auth")).click();


        Thread.sleep(3000);
        driver.quit();
    }
}
