package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;
import com.qa.utils.TestUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTest extends BaseClass {
    LoginPage loginPage;
    ProductsPage productsPage;
    SettingsPage settingsPage;
    MenuPage menuPage;
    JSONObject jsonObjData = null;
    JSONArray jsonArrData = null;
    JSONObject jsonObjMsg;
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        addLogs("****************************************","");
        addLogs("************* Before Method "+ m.getName() + " *************","");
    }

    @AfterMethod
    public void afterMethod(Method m) {
        addLogs("************* After Method "+ m.getName() + " *************","");
        addLogs("****************************************","");
    }

    @Test
    public void invalidLoginTest_01() {

        try {
            jsonObjData = new TestUtil().getJsonObject(getTestDataFilePath(), "TC_01");
            jsonObjMsg = new TestUtil().getJsonObject(getStringsFileFilePath(), "LoginPage");

        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPage.enterUserName(jsonObjData.getString("InvalidUserName"));
        loginPage.enterPassword(jsonObjData.getString("InvalidPassword"));
        loginPage.tapLoginButton();
        String actual = loginPage.getLoginErrMsg();
        String expected = jsonObjMsg.get("InvalidCredentials").toString();
        Assert.assertEquals(actual, expected, "Expected and Actual are matched !");
    }

    @Test
    public void validLoginTest_02() {
        try {
            jsonObjData = new TestUtil().getJsonObject(getTestDataFilePath(), "TC_02");
            jsonObjMsg = new TestUtil().getJsonObject(getStringsFileFilePath(), "Products");
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPage.enterUserName(jsonObjData.getString("ValidUserName"));
        loginPage.enterPassword(jsonObjData.getString("ValidPassword"));
        productsPage = loginPage.tapLoginButton();
        String actual = productsPage.getPageTitle();
        String expected = jsonObjMsg.get("PageTitle").toString();
        Assert.assertEquals(actual, expected, "Page Title is matched !");
    }

    @Test
    public void verifyLogout() {
        try {
            jsonObjData = new TestUtil().getJsonObject(getTestDataFilePath(), "TC_02");
            jsonObjMsg = new TestUtil().getJsonObject(getStringsFileFilePath(), "Products");
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPage.enterUserName(jsonObjData.getString("ValidUserName"));
        loginPage.enterPassword(jsonObjData.getString("ValidPassword"));
        productsPage = loginPage.tapLoginButton();
        settingsPage = productsPage.tapMenuICon();
        loginPage = settingsPage.tapLogoutText();
    }


}