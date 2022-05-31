package com.qa.pages;

import com.beust.jcommander.internal.Nullable;
import com.qa.base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;


public class RedbusHomePage extends BaseClass {



    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Account']")
    public MobileElement myAccount;

    @AndroidFindBy(id = "in.redbus.android:id/aboutTV")
    public MobileElement aboutUs;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Blog']")
    public MobileElement blog;
    
    @AndroidFindBy(id = "in.redbus.android:id/cityName1")
    public MobileElement sourceCity;
    
    @AndroidFindBy(id = "in.redbus.android:id/search_src_text")
    public MobileElement sourceEdit;
    
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bangalore']")
    public MobileElement sourceSelect;
    
    @AndroidFindBy(id = "in.redbus.android:id/cityName2")
    public MobileElement destinationCity;
    
    @AndroidFindBy(id = "in.redbus.android:id/search_src_text")
    public MobileElement destinationEdit;
    
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Chennai']")
    public MobileElement destinationSelect;

    @AndroidFindBy(id = "in.redbus.android:id/search_tomorrow_texts")
    public MobileElement tommorow;

    @AndroidFindBy(id = "in.redbus.android:id/search_button")
    public MobileElement searchBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Hail Trip']")
    public MobileElement fromElement;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Asian Xpress']")
    public MobileElement toElement;
    
    

    
    
    public RedbusHomePage selectSource() {
    	sourceCity = (MobileElement) getDriver().findElement(By.id("in.redbus.android:id/search_source_hint_text"));
        tapOnElement(sourceCity);
        
        sourceEdit = (MobileElement) getDriver().findElement(By.id("in.redbus.android.locationPicker:id/edit_search_query"));
        sendDataToElement(sourceEdit,"Bangalore");
        
        
        sourceSelect = (MobileElement) getDriver().findElement(By.xpath("//android.widget.TextView[@text='Bangalore']"));
        tapOnElement(sourceSelect);
        
  
        return this;
    }
   
    public RedbusHomePage selectDestination() {
    	destinationCity = (MobileElement) getDriver().findElement(By.id("in.redbus.android:id/search_destination_hint_text"));
        tapOnElement(destinationCity);
        
        destinationEdit = (MobileElement) getDriver().findElement(By.id("in.redbus.android.locationPicker:id/edit_search_query"));
        sendDataToElement(destinationEdit,"Chennai");
        
        
        destinationSelect = (MobileElement) getDriver().findElement(By.xpath("//android.widget.TextView[@text='Chennai']"));
        tapOnElement(destinationSelect);
        
  
        return this;
    }

    public RedbusHomePage selectTomorrow() {
        tommorow = (MobileElement) getDriver().findElement(By.id("in.redbus.android:id/search_tomorrow_text"));
        tapOnElement(tommorow);


        return this;
    }

    public RedbusHomePage selectSearch() {
        searchBtn = (MobileElement) getDriver().findElement(By.id("in.redbus.android:id/search_button"));
        tapOnElement(searchBtn);
        return this;
    }

    public RedbusHomePage selectTravels(String travels) {
                 // MobileElement travelValue = (MobileElement) getDriver().findElement(By.xpath("in.redbus.android:id/search_button"))
                   //swipeDownToElement(getDriver(),By.xpath("//android.widget.TextView[@text='"+travels+"']"));
                   scrollTillText(travels);
                   tapOnElement((MobileElement) getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+travels+"']")));
                   waitFor(10000);
        return this;
    }


   



    public RedbusHomePage tapMyAccountpage() {
        myAccount = (MobileElement) getDriver().findElement(By.xpath("//android.widget.TextView[@text='My Account']"));
        tapOnElement(myAccount);

        return this;
    }

    public RedbusHomePage tapAboutUs() {
        aboutUs = (MobileElement) getDriver().findElement(By.id("in.redbus.android:id/aboutTV"));
        tapOnElement(aboutUs);
        return this;
    }

    public RedbusHomePage tapBlog(){
        waitFor(5000);
        blog = (MobileElement) getDriver().findElement(By.xpath("//android.widget.TextView[@text='Blog']"));
        tapOnElement(blog);
        return this;
    }

    public RedbusHomePage handleWebViews() {
        waitFor(12000);
        String webContext = getWebContext(getDriver());
        System.out.println(webContext);
        waitFor(3000);
        getDriver().context(webContext);
        String text = getDriver().findElement(By.tagName("body")).getText();
        System.out.println(text);

        return this;
    }
    
   

    public void waitFor(long milliSeconds){
        try{
            Thread.sleep(milliSeconds);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private String getWebContext(AppiumDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                return context;
            }
        }
        return null;
    }




    /*public String getLoginErrMsg() {
        return getText(loginErrMsg);
    }*/

}
