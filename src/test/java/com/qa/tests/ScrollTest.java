package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.RedbusHomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ScrollTest extends BaseClass {
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
       // rbHomePage.selectSource();
       // rbHomePage.selectDestination();
       // rbHomePage.selectTomorrow();
        rbHomePage.selectSearch();
        rbHomePage.selectTravels("Asian Xpress");

    }




}