package org.study.tests;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.Duration;
import java.util.HashMap;


public class TestTempTest {

    public static void main(String[] args) throws InterruptedException, IOException, AWTException {

        StringSelection filePath = new StringSelection("D:\\Resume\\Vishal Jadhav QA Automation Resume.docx");
        Robot robot = new Robot();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.ilovepdf.com/word_to_pdf");
        driver.findElement(By.xpath("//a[@id='pickfiles']")).click();
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(3000);
        driver.quit();
    }
}
