package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTest extends BaseClass {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println(" *** Started " + m.getName()+ " ***");
    }

    @AfterMethod
    public void afterMethod(Method m) {

    }

    @Test
    public void invalidLoginTest_01() {
        try{
            loginPage.enterUserName("dummy");
            loginPage.enterPassword("dummy");
            loginPage.tapLoginButton();
            String actual = loginPage.getLoginErrMsg("text");
            String expected = "Username and password do not match any user in this service.";
            Assert.assertEquals(actual, expected, "Expected and Actual are matched !");
        }catch (Exception e){
           // e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void validLoginTest_02() {
        try {
            loginPage.enterUserName("standard_user");
            loginPage.enterPassword("secret_sauce");
            productsPage = loginPage.tapLoginButton();
            String actual = productsPage.getPageTitle("text");
            String expected = "PRODUCT";
            Assert.assertEquals(actual, expected, "Page Title is matched !");
        }catch (Exception e){
           // e.printStackTrace();
            throw e;
        }
    }
}