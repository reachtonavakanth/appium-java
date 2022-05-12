package com.qa.base;

import com.qa.utils.TestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;

public class BaseClass extends TestUtil {
    protected static AppiumDriver driver;
    protected static String env ;
    public BaseClass(){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @BeforeTest
    public void launchApp() {
        String propFilePath = TestUtil.getMainResourcesPath("appLaunch.properties");
        env = getKeyValue(propFilePath, "environment");
        try {
            if (env.equalsIgnoreCase("Android")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                if(getKeyValue(propFilePath, "emulator").equalsIgnoreCase("Yes")){
                    caps.setCapability("avd", getKeyValue(propFilePath, "emulatorName"));
                    caps.setCapability("avdLaunchTimeout", 900000);
                }
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getKeyValue(propFilePath, "androidAutomationName"));
                //caps.setCapability(MobileCapabilityType.UDID, getKeyValue(propFilePath, "androidUdid"));
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getKeyValue(propFilePath, "androidPlatformName"));
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, getKeyValue(propFilePath, "androidPlatformVersion"));
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, getKeyValue(propFilePath, "androidDeviceName"));
                caps.setCapability(MobileCapabilityType.APP, getAppPath(getKeyValue(propFilePath, "androidAppName")));
                caps.setCapability("appPackage", getKeyValue(propFilePath, "androidAppPackage"));
                caps.setCapability("appActivity", getKeyValue(propFilePath, "androidAppActivity"));
                caps.setCapability("ignoreHiddenApiPolicyError", true);
                URL url = new URL(getKeyValue(propFilePath, "appiumURL"));
                driver = new AndroidDriver(url, caps);
            } else if (env.equalsIgnoreCase("iOS")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getKeyValue(propFilePath, "iosPlatformName"));
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getKeyValue(propFilePath, "iosAutomationName"));
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, getKeyValue(propFilePath, "iosDeviceName"));
                caps.setCapability(MobileCapabilityType.UDID, getKeyValue(propFilePath, "iosUdid"));
                caps.setCapability("bundleId", getKeyValue(propFilePath, "iosBundleId"));
              //  caps.setCapability(MobileCapabilityType.APP, getAppPath(getKeyValue(propFilePath, "iosAppName")));
                // caps.setCapability("ignoreHiddenApiPolicyError", true);
                URL url = new URL(getKeyValue(propFilePath, "appiumURL"));
                driver = new IOSDriver(url, caps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
      //  driver.quit();
    }

    public void waitForEleVisibility(MobileElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForEleVisibility(MobileElement ele, Long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void clickElement(MobileElement ele){
        waitForEleVisibility(ele);
        ele.click();
    }

    public void clearElement(MobileElement ele){
        waitForEleVisibility(ele);
        ele.clear();
    }
    public void sendDataToElement(MobileElement ele,String value){
        waitForEleVisibility(ele);
        clearElement(ele);
        ele.sendKeys(value);
    }
    public String getAndroidElementAttribute(MobileElement ele, String attributeName){
        waitForEleVisibility(ele);
        return ele.getAttribute(attributeName);
    }

    public String getIOSElementAttribute(MobileElement ele, String attributeName){
        waitForEleVisibility(ele);
        return ele.getAttribute(attributeName);
    }
    // in iOS text attribute will not work hence using label attribute
    public String getText(MobileElement ele){
        String text= null;
        if(env.equalsIgnoreCase("Android")){
            text=  getAndroidElementAttribute(ele, "text");
        }
        else if(env.equalsIgnoreCase("iOS")){
            text=  getIOSElementAttribute(ele,"label");
        }
        return text;
    }
}
