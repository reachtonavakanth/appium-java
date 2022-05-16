package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutPage extends MenuPage {
    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-CONTINUE SHOPPING")
    private MobileElement continueShoppingBtn;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-CHECKOUT")
    private MobileElement checkoutBtn;

    public ProductsPage tapContinueShoppingBtn() {
        tapOnElement(continueShoppingBtn);
        return new ProductsPage();
    }

    public CheckoutInformationPage tapCheckoutShoppingBtn() {
        tapOnElement(checkoutBtn);
        return new CheckoutInformationPage();
    }
}
