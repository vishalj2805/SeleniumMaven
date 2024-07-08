package org.study.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.study.abstractcomponents.AbstractComponents;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//div[@class='cartSection removeWrap']/button[1]")
    List<WebElement> productBuyBTN;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//section[contains(@class,'list-group')]//span")
    List<WebElement> countryNamesDropDown;

    @FindBy(xpath = "//a[contains(@class,'action__submit')]")
    WebElement submitBTN;

    @FindBy(xpath = "//label[@class='ng-star-inserted']")
    WebElement orderID;



    @FindBy(xpath = "//th[@scope='row']")
    List<WebElement> orderIDs;

    public String verifyProductNameAndBuy(String productName) {
        List<WebElement> cartProducts = this.cartProducts;
        for (int i=0;i<cartProducts.size();i++){
            if(cartProducts.get(i).getText().equals(productName)){
                productBuyBTN.get(i).click();
                break;
            }
        }

        country.sendKeys("India");
        countryNamesDropDown
                .stream().filter(country -> country.getText().equalsIgnoreCase("India")).toList().getFirst().click();

        submitBTN.click();
        return orderID.getText();
    }

    public boolean verifyOrderID(String orderID){
        goToOrdersPage();
        return orderIDs.stream().anyMatch(orderId -> orderID.contains(orderId.getText()));

    }


}
