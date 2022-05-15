package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SeetingsPage extends BaseClass {
    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-Menu")
    private MobileElement menuIcon;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-Cart")
    private MobileElement cartIcon;

    public void tapMenuICon() {

    }

    public void tapCartICon() {
        tapOnElement(cartIcon);

    }
}
