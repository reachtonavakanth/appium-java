package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutCompletePage extends MenuPage {

    @AndroidFindBy(accessibility = "THANK YOU FOR YOU ORDER")
    @iOSXCUITFindBy(id = "THANK YOU FOR YOU ORDER")
    private MobileElement confirmationLbl;

    @AndroidFindBy(accessibility = "test-BACK HOME")
    @iOSXCUITFindBy(id = "test-BACK HOME")
    private MobileElement backHomeBtn;

    public String getOrderConfirmationMsg() {

        return getText(confirmationLbl);
    }

    public ProductsPage tapBackHomeBtn() {
        tapOnElement(backHomeBtn);
        return new ProductsPage();
    }
}

