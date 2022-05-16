package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutInformationPage extends MenuPage {

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id = "test-First Name")
    private MobileElement firstNameTxtFld;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id = "test-Last Name")
    private MobileElement lastNameFld;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id = "test-Zip/Postal Code")
    private MobileElement zipCodeFld;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id = "test-CANCEL")
    private MobileElement cancelBtn;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id = "test-CONTINUE")
    private MobileElement continueBtn;

    public void inputFirstName(String firstName) {
        sendDataToElement(firstNameTxtFld, firstName);
    }

    public void inputLastName(String lastName) {
        sendDataToElement(lastNameFld, lastName);
    }

    public void inputZipCode(String zipcode) {
        sendDataToElement(zipCodeFld, zipcode);
    }

    public ProductsPage tapCancelBtn() {
        tapOnElement(cancelBtn);
        return new ProductsPage();
    }

    public CheckoutOverviewPage tapContinueBtn() {
        tapOnElement(continueBtn);
        return new CheckoutOverviewPage();
    }

}

