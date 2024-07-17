package org.study.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.idealized.target.model.SessionID;
import org.openqa.selenium.devtools.v126.emulation.Emulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChromeDevTools {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
//        DevTools devTools = driver.getDevTools();
//        devTools.createSession();
//        devTools.send(Emulation.setDeviceMetricsOverride(600,1000,50,true,
//                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
//                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        Map<String, Object> data = new HashMap<>();
        data.put("width", 600);
        data.put("height", 1000);
        data.put("deviceScaleFactor", 50);
        data.put("mobile", true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", data);



        driver.get("https://www.google.com");
        Thread.sleep(3000);
        driver.quit();
    }
}
