package org.study.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.study.abstractcomponents.AbstractComponents;

import java.util.List;

public class OrdersPage extends AbstractComponents {
    WebDriver driver;

    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//th[@scope='row']")
    List<WebElement> orderIDs;

    public boolean isOrderIDValid(String orderID){
        return orderIDs.stream().anyMatch(orderId -> orderID.contains(orderId.getText()));
    }


}
