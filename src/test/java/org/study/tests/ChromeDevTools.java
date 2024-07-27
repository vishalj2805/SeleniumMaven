package org.study.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.idealized.target.model.SessionID;
import org.openqa.selenium.devtools.v126.emulation.Emulation;
import org.openqa.selenium.devtools.v126.fetch.Fetch;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;
import org.openqa.selenium.devtools.v126.network.model.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChromeDevTools {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request -> {
            String url = request.getRequest().getUrl();
            if (url.contains("=shetty")){
                String mockedURL = url.replace("=shetty", "=BadGuy");
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedURL), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty()));
            }else {
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty()));
            }
        });


        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
        driver.quit();
    }
}
