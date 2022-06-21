package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutPage extends MenuPage {
    @AndroidFindBy(accessibility = "test-CONTINUE SHOPPING")
    @iOSXCUITFindBy(id ="test-CONTINUE SHOPPING")
    private MobileElement continueShoppingBtn;

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    @iOSXCUITFindBy(id ="test-CHECKOUT")
    private MobileElement checkoutBtn;

    public ProductsPage tapContinueShoppingBtn() {
        tapOnElement(continueShoppingBtn);
        return new ProductsPage();
    }

    public CheckoutInformationPage tapCheckoutShoppingBtn() {
      /*  if(!checkoutBtn.isDisplayed()){
            System.out.println("in If condition");
            scrollTillText("CHECKOUT");
        }*/
        scrollTillText("CHECKOUT");
        tapOnElement(checkoutBtn);
        return new CheckoutInformationPage();
    }
}
