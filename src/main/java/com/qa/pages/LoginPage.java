package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BaseClass {
    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(id ="test-Username")
    private MobileElement userNameTxtFld;
    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(id ="test-Password")
    private MobileElement passwordTxtFld;
    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(id ="test-LOGIN")
    private MobileElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
    @iOSXCUITFindBy(id ="Username and password do not match any user in this service.")
    private MobileElement loginErrMsg;

    public LoginPage enterUserName(String userName) {
        sendDataToElement(userNameTxtFld, userName);
        return this;
    }

    public LoginPage enterPassword(String userName) {
        sendDataToElement(passwordTxtFld, userName);
        return this;
    }

    public ProductsPage tapLoginButton() {
        tapOnElement(loginBtn);
        return new ProductsPage();
    }

    public String getLoginErrMsg() {
        return getText(loginErrMsg);
    }

}
