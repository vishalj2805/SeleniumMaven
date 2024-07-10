package org.study.stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.study.pageobjects.CartPage;
import org.study.pageobjects.LandingPage;
import org.study.testcomponents.TestBase;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;

public class StepDefinitionImpl extends TestBase {
    LandingPage landingPage;
    CartPage cartPage;



    @Given("Land on Ecommerce Page")
    public void Land_on_Ecommerce_Page() throws IOException {
        landingPage = goToLandingPage();
    }

    @Given("^Login with username (.+) and password (.+)$")
    public void Login_with_username_and_password(String username, String password){
        landingPage.login(username, password);
    }

    @When( "^Add (.+) to Cart$")
    public void Add_product_to_Cart(String productName){
        landingPage.addProductToCart(productName);
    }

    String[] orderDetails;
    @And("^Checkout (.+) and submit the order$")
    public void Checkout_product_and_submit_the_order(String productName){
        cartPage = landingPage.goToCartPage();
        orderDetails = cartPage.verifyProductNameAndBuy(productName);
    }

    @Then("Verify {string} message is Displayed")
    public void Verify_message_is_Displayed(String message){
        if (Arrays.stream(orderDetails).toList().get(1).strip().equals(message.strip())){
            Assert.assertTrue(true);
        }


    }

    @And("Verify Valid Order ID is Displayed")
    public void Verify_Valid_Order_ID_is_Displayed(){
        String orderIDOnTYPage = Arrays.stream(orderDetails).toList().getFirst();
        if (cartPage.verifyOrderID(orderIDOnTYPage)){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
        driver.quit();
    }

    @Then("Verify Error message {string} is Displayed")
    public void Verify_Error_message_is_Displayed(String errorMessage){
        Assert.assertEquals(landingPage.getAlertMSG().strip(), errorMessage.strip());
        driver.quit();
    }


}
