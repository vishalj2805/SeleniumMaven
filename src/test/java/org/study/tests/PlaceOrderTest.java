package org.study.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.study.data.DataReader;
import org.study.pageobjects.CartPage;
import org.study.pageobjects.LandingPage;
import org.study.pageobjects.OrdersPage;
import org.study.testcomponents.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderTest extends TestBase {

    private String orderIDOnTYPage;
    @Test(dataProvider = "testData")
    public void placeOrder(HashMap<String, String> data) throws InterruptedException, IOException {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(data.get("email"), data.get("password"));
        landingPage.addProductToCart(data.get("product"));
        CartPage cartPage = landingPage.goToCartPage();
        String[] orderDetails = cartPage.verifyProductNameAndBuy(data.get("product"));

        if (Arrays.stream(orderDetails).toList().get(1).strip().equals(" Thankyou for the order. ".strip())){
            Assert.assertTrue(true);
        }

        orderIDOnTYPage = Arrays.stream(orderDetails).toList().get(0);
        if (cartPage.verifyOrderID(orderIDOnTYPage)){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Test(dependsOnMethods = {"placeOrder"}, dataProvider = "testData")
    public void verifyOrderIDPresence(HashMap<String, String> data) throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(data.get("email"), data.get("password"));
        OrdersPage ordersPage = landingPage.goToOrdersPage();

        if (ordersPage.isOrderIDValid(orderIDOnTYPage)){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @DataProvider
    public Object[][] testData() throws IOException {

        DataReader dataReader = new DataReader();
        List<HashMap<String, String>> data = dataReader.readJSONData("//src/test/java/org/study/data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
