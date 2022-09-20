package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.*;
import com.qa.utils.TestUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class EndToEndTest extends BaseClass {
    LoginPage loginPage;
    ProductsPage productsPage;
    MenuPage menuPage;
    SettingsPage settingsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutInformationPage checkoutInformationPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;
    JSONObject jsonObjData = null;
    JSONArray jsonArrData = null;
    JSONObject jsonObjMsg = null;

    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        addLogs("****************************************", "");
        addLogs("************* Before Method" + m.getName() + "*************","");
    }

    @AfterMethod
    public void afterTests(Method m) {
        addLogs("************* After Method" + m.getName() + "*************","");
        addLogs("****************************************", "");
    }

    @Test
    public void Tc_01_PurchaseProduct() throws InterruptedException {
        try {
            jsonObjData = new TestUtil().getJsonObject(getTestDataFilePath(), "TC_04");
            jsonObjMsg = new TestUtil().getJsonObject(getStringsFileFilePath(), "LoginPage");

        } catch (IOException e) {
            e.printStackTrace();
        }

        productsPage = loginPage.successfulLogin(jsonObjData.getString("ValidUserName"),
                jsonObjData.getString("ValidPassword"));
        new TestUtil().log().info("Login Successful !");
        menuPage = productsPage.tapAddCartBtn();
        checkoutPage = menuPage.tapCartIConWithCount();
        checkoutPage.tapContinueShoppingBtn();
        menuPage.tapCartIConWithCount();
        checkoutInformationPage = checkoutPage.tapCheckoutShoppingBtn();
        checkoutInformationPage.inputFirstName(jsonObjData.getString("FirstName"));
        checkoutInformationPage.inputLastName(jsonObjData.getString("LastName"));
        checkoutInformationPage.inputZipCode(jsonObjData.getString("zip"));
        checkoutOverviewPage = checkoutInformationPage.tapContinueBtn();
        checkoutCompletePage = checkoutOverviewPage.tapFinishBtn();
        checkoutCompletePage.tapBackHomeBtn();
    }
}