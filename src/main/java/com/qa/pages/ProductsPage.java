package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage extends MenuPage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]")
    private MobileElement productsLabel;

    @AndroidFindBy(xpath = "")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement sLBPAddCartBtn;

    @AndroidFindBy(xpath = "")
    @iOSXCUITFindBy(xpath ="test-REMOVE")
    private MobileElement removeBtn;


    public String getPageTitle() {
        return getText(productsLabel);
    }

    public MenuPage tapAddCartBtn(){
       tapOnElement(sLBPAddCartBtn);
       return new MenuPage();
    }
}


// //XCUIElementTypeOther[@name ='test-Item']/child::*[1]    - For 1st product