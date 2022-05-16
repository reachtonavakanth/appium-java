package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutOverviewPage extends  MenuPage{

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="Sauce Labs Backpack")
    private MobileElement sLBPLbl;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-CANCEL")
    private MobileElement cancelBtn;


    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-FINISH")
    private MobileElement finishBtn;

    public String getSLBPProductDetails() {

        return getText(sLBPLbl);
    }

    public ProductsPage tapCancelBtn() {
        tapOnElement(cancelBtn);
        return new ProductsPage();
    }

    public CheckoutCompletePage tapFinishBtn() {
        tapOnElement(finishBtn);
        return new CheckoutCompletePage();
    }
}

