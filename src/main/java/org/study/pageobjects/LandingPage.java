package org.study.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.study.abstractcomponents.AbstractComponents;

import java.util.List;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement userName;

    @FindBy(xpath = "//input[@id='userPassword']")
    WebElement password;

    @FindBy(xpath = "//input[@id='login']")
    WebElement loginBTN;

    @FindBy(xpath = "//section[@id='products']//div[@class='card']//b")
    List<WebElement> productNames;
    By productNamesBy = By.xpath("//section[@id='products']//div[@class='card']//b");

    @FindBy(xpath = "//section[@id='products']//div[@class='card']//button[2]")
    List<WebElement> productsAddBtn;


    @FindBy(xpath = "//div[contains(@class,'ngx-spinner')]")
    WebElement spinner;

    @FindBy(xpath = "//div[@role='alert']")
    WebElement alertMSG;
    By alertBy = By.xpath("//div[@role='alert']");

    public void login(String userName, String password){
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        loginBTN.click();
    }

    public List<String> getProductNames(){
        waitTillVisible(productNamesBy);
        return productNames.stream().map(WebElement::getText).toList();
    }

    public void addProductToCart(String productName){
        for (int i=0;i<getProductNames().size();i++){
            if(getProductNames().get(i).equals(productName)){
                productsAddBtn.get(i).click();
                break;
            }
        }

        waitTillVisible(alertBy);
    }

    public String getAlertMSG(){
        waitTillVisible(alertBy);
        return alertMSG.getText();
    }

}
