package com.qa.pages;

import com.qa.base.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage extends MenuPage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]")
    private MobileElement productsLabel;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement sLBPAddCartBtn;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/android.widget.ImageView")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement listViewButton;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='+']")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement addWithPlusButton;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement sortButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (low to high)']")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement lowToHigh;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (high to low)']")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement highToLow;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name (A to Z)']")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement nameAtoZ;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name (Z to A)']")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement nameZtoA;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement cancel;
    
    
    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement firstItemPrice;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy(xpath ="(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private MobileElement firstItemTitle;
    
    @AndroidFindBy(accessibility = "test-REMOVE")
    @iOSXCUITFindBy(id ="test-REMOVE")
    private MobileElement removeBtn;


public MobileElement getRemoveBtn(){
    return removeBtn;
}
    public String getPageTitle() {
        return getText(productsLabel);
    }

    public MenuPage tapAddCartBtn(){
       tapOnElement(sLBPAddCartBtn);
       return new MenuPage();
    }
    
    public void tapListButton(){
        tapOnElement(listViewButton);
     }
    
    public String getButtonText()
    {
    	return getText(addWithPlusButton);
    }
    
    public String getItemPrice()
    {
    	return getText(firstItemPrice);
    }

    public String getItemTitle()
    {
        return getText(firstItemTitle);
    }
    
    public void tapSortButton(){
        tapOnElement(sortButton);
      
     }
    
    public void tapSortValue(String sortValue){
         if("lowToHigh".equals(sortValue))
        	  tapOnElement(lowToHigh);
         else if("highToLow".equals(sortValue))
        	  tapOnElement(highToLow);
         else if("AtoZ".equals(sortValue))
       	  tapOnElement(nameAtoZ);
         else if("ZtoA".equals(sortValue))
        	 tapOnElement(nameZtoA);
         else
        	 tapOnElement(cancel);
      
     }
}


// //XCUIElementTypeOther[@name ='test-Item']/child::*[1]    - For 1st product