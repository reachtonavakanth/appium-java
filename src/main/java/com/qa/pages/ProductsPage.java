package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BaseClass {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private MobileElement userNameTxtFld;

    public String getPageTitle(String attributeName) {
        return getElementAttribute(userNameTxtFld, attributeName);
    }


}

