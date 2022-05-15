package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BaseClass {
    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-Menu")
    private MobileElement menuIcon;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-Cart")
    private MobileElement cartIcon;

    public SettingsPage tapMenuICon() {
       tapOnElement(menuIcon);
       return new SettingsPage();
    }

    public CartPage tapCartICon() {
        tapOnElement(cartIcon);
        return new CartPage();
    }
}
