package com.qa.tests;

import com.qa.base.BaseClass;
import com.qa.pages.*;
import com.qa.utils.TestUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

public class EndToEndTest2 extends BaseClass {
	LoginPage loginPage;
	ProductsPage productsPage;
	MenuPage menuPage;
	SettingsPage settingsPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutInformationPage checkoutInformationPage;
	CheckoutOverviewPage checkoutOverviewPage;
	CheckoutCompletePage checkoutCompletePage;

	@BeforeMethod
	public void objInit(Method m) {
		loginPage = new LoginPage();
		System.out.println(" *** Started " + m.getName() + " ***");
	}

	@Test
	public void e2eSortLowToHigh() throws InterruptedException {

		JSONObject jsonObjData = null;
		JSONObject jsonObjMsg = null;
		SoftAssert softAssert = new SoftAssert();
		try {
			jsonObjData = new TestUtil().getJsonObject(getTestDataFilePath(), "TC_04");
			jsonObjMsg = new TestUtil().getJsonObject(getStringsFileFilePath(), "LoginPage");

		} catch (IOException e) {
			e.printStackTrace();
		}

		// new TestUtil().log("Before Login");
		productsPage = loginPage.successfulLogin(jsonObjData.getString("ValidUserName"),
				jsonObjData.getString("ValidPassword"));
		// new TestUtil().log("Login Successful !");

		productsPage.tapListButton();
		softAssert.assertEquals("+", productsPage.getButtonText(), "Item has different button type");
		productsPage.tapSortButton();
		productsPage.tapSortValue("lowToHigh");
        System.out.println("================="+productsPage.getItemPrice());
		softAssert.assertEquals("$7.99", productsPage.getItemPrice(), "Item Price not the lowest");

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
		softAssert.assertAll();
	}
}