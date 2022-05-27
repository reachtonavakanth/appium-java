package com.qa.pages;

import com.qa.utils.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CheckoutOverviewPage extends  MenuPage{

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]") //
    @iOSXCUITFindBy(id ="Sauce Labs Backpack")
    private MobileElement sLBPLbl;

    @AndroidFindBy(accessibility = "test-CANCEL")
    @iOSXCUITFindBy(id ="test-CANCEL")
    private MobileElement cancelBtn;


    @AndroidFindBy(accessibility = "test-FINISH")
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
        long time = 50;
        waitForEleVisibility(sLBPLbl,time);
        ScrollToFinishBtn();
        tapOnElement(finishBtn);
        return new CheckoutCompletePage();
    }

    public void ScrollToFinishBtn()  {
        if(getPlatformName().equalsIgnoreCase("iOS")){
      iosScroll(String.valueOf(Constants.ScrollActions.down));
        }
        else if (getPlatformName().equalsIgnoreCase("Android")){
       // scrollToElement_Android2("test-FINISH");
            scrollToElement_Android1();
        }
    }

    public void ScrollToCancelBtn(){
        if(getPlatformName().equalsIgnoreCase("iOS")){
            iosScroll(String.valueOf(Constants.ScrollActions.down));
        }
        else if (getPlatformName().equalsIgnoreCase("Android")){
            scrollToElement_Android2("test-FINISH");
        }
    }
}

