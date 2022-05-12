package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseClass {
    @AndroidFindBy(accessibility = "test-Username")
    private MobileElement userNameTxtFld;
    @AndroidFindBy(accessibility = "test-Password")
    private MobileElement passwordTxtFld;
    @AndroidFindBy(accessibility = "test-LOGIN")
    private MobileElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
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
        clickElement(loginBtn);
        return new ProductsPage();
    }

    public String getLoginErrMsg(String attributeName) {
        return getElementAttribute(loginErrMsg, attributeName);
    }
}
