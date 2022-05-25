package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BaseClass {
    @AndroidFindBy(accessibility = "test-Menu")
    @iOSXCUITFindBy(id ="test-Menu")
    private MobileElement menuIcon;

    @AndroidFindBy(accessibility = "test-Cart")
    @iOSXCUITFindBy(id ="test-Cart")
    private MobileElement cartIcon;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]//android.widget.TextView")
    @iOSXCUITFindBy(xpath =" (//XCUIElementTypeOther[@name=\"1\"])[4]")
    private MobileElement cartIconWithCount;


    public SettingsPage tapMenuICon() {
       tapOnElement(menuIcon);
       return new SettingsPage();
    }
    public CheckoutPage tapCartIConWithCount() throws InterruptedException {
         long time = 100;
        waitForEleClickable(new ProductsPage().getRemoveBtn(),time);
        tapOnElement(cartIconWithCount);
        return new CheckoutPage();
    }
    public CartPage tapCartICon() {
        tapOnElement(cartIcon);
        return new CartPage();
    }
}
