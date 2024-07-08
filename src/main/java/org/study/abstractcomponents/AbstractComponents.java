package org.study.abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.study.pageobjects.CartPage;
import org.study.pageobjects.OrdersPage;

import java.time.Duration;

public class AbstractComponents {
    WebDriverWait wait;
    WebDriver driver;

    public AbstractComponents(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
    WebElement cartBTN;

    @FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
    WebElement ordersTab;
    By ordersTabBy = By.xpath("(//button[@class='btn btn-custom'])[2]");

    public void waitTillVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public CartPage goToCartPage(){
        cartBTN.click();
        return CartPageObject();
    }

    public CartPage CartPageObject(){
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage(){
        waitTillVisible(ordersTabBy);
        ordersTab.click();
        return ordersPageObject();
    }

    public OrdersPage ordersPageObject(){
        return new OrdersPage(driver);
    }





}
