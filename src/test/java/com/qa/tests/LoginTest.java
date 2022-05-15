package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTest extends BaseClass {
    JSONObject jsonObjData;
    JSONArray jsonArrData;
    JSONObject jsonObjMsg;
    LoginPage loginPage;
    ProductsPage productsPage;
    SettingsPage settingsPage;
    MenuPage menuPage;

    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println(" *** Started " + m.getName() + " ***");
    }

    @AfterMethod
    public void afterMethod(Method m) {

    }

    @Test
    public void invalidLoginTest_01() {
        try {
            jsonObjData = getJsonObject(testDataFilePath, "TC_01");
            jsonObjMsg = getJsonObject(stringsFilePath, "LoginPage");

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
            jsonObjData = getJsonObject(testDataFilePath, "TC_02");
            jsonObjMsg = getJsonObject(stringsFilePath, "Products");
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
            jsonObjData = getJsonObject(testDataFilePath, "TC_02");
            jsonObjMsg = getJsonObject(stringsFilePath, "Products");
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