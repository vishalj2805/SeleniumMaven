package org.study.tests;

import org.study.pageobjects.LandingPage;
import org.study.testcomponents.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddProductToCartTest extends TestBase {
    @Test
    public void addProductsToCart() {
        String productName = "ZARA COAT 3";

        LandingPage landingPage = new LandingPage(driver);
        landingPage.login("vishal.jadhav.2805@gmail.com", "Vishalj2805");
        landingPage.addProductToCart(productName);

    }
}
