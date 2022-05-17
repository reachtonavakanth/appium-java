package com.qa.pages;

import com.qa.utils.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutOverviewPage extends  MenuPage{

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="Sauce Labs Backpack")
    private MobileElement sLBPLbl;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-CANCEL")
    private MobileElement cancelBtn;


    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(id ="test-FINISH")
    private MobileElement finishBtn;

    public String getSLBPProductDetails() {

        return getText(sLBPLbl);
    }

    public ProductsPage tapCancelBtn() throws InterruptedException {
        ScrollToCancelBtn();
        tapOnElement(cancelBtn);
        return new ProductsPage();
    }

    public CheckoutCompletePage tapFinishBtn() throws InterruptedException {
         waitForEleVisibility(sLBPLbl);
        ScrollToFinishBtn();
        tapOnElement(finishBtn);
        return new CheckoutCompletePage();
    }

    public void ScrollToFinishBtn()  {
      iosScroll(String.valueOf(Constants.ScrollActions.down));
    }

    public void ScrollToCancelBtn(){

    }
}

