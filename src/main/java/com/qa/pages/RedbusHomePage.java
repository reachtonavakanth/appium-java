package com.qa.pages;

import com.beust.jcommander.internal.Nullable;
import com.qa.base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;


public class RedbusHomePage extends BaseClass {



    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Account']")
    public MobileElement myAccount;

    @AndroidFindBy(id = "in.redbus.android:id/aboutTV")
    public MobileElement aboutUs;

  //  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Blog']")
    public MobileElement blog;



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
