package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class WebViewTest extends BaseClass {
    RedbusHomePage rbHomePage;


    @BeforeClass
    public void beforeClass() {
        rbHomePage = new RedbusHomePage();
    }

   /* @AfterClass
    public void afterClass() {

    }*/

    @BeforeMethod
    public void beforeMethod(Method m) {

        System.out.println(" *** Started " + m.getName() + " ***");
    }

   /* @AfterMethod
    public void afterMethod(Method m) {


    }*/

    @Test
    public void validateWebViews() {
        rbHomePage.tapMyAccountpage();
        rbHomePage.tapAboutUs();
        rbHomePage.tapBlog();
        rbHomePage.handleWebViews();

    }




}