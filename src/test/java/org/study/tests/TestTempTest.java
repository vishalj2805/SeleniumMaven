package org.study.tests;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.*;
import java.time.Duration;
import java.util.HashMap;


public class TestTempTest {

    public static void main(String[] args) throws InterruptedException, IOException {

        HashMap<String, Object> downloadPathDirChange = new HashMap<>();
        downloadPathDirChange.put("profile.default_content_settings.popups", 0);
        downloadPathDirChange.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", downloadPathDirChange);
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.ilovepdf.com/word_to_pdf");
        driver.findElement(By.xpath("//a[@id='pickfiles']")).click();
        Thread.sleep(3000);
        Runtime.getRuntime().exec("fileupload.exe");




        Thread.sleep(3000);
        driver.quit();
    }
}
