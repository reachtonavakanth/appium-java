package com.qa.base;

import com.qa.utils.TestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class BaseClass extends TestUtil {
    protected static AppiumDriver driver;
    protected static String env ;
    public static String testDataFilePath;
    public static String stringsFilePath;
    public BaseClass(){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @BeforeTest
    public void launchApp() throws IOException {
        String propFilePath = TestUtil.getMainResourcesPath("appLaunch.properties");
        testDataFilePath = getTestDataPath("Login.json");
        stringsFilePath = getTestDataPath("Strings.json");
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
    public void closeApp() {
        ((InteractsWithApps) driver).closeApp();
    }

    public void launchAppAgain() {
        ((InteractsWithApps) driver).launchApp();
    }

    @AfterMethod
    public void redirect(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test is Fail"); //driver.installApp()
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test is Pass");
        }
        closeApp();
        launchAppAgain();
    }

    public void waitForEleVisibility(MobileElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForEleVisibility(MobileElement ele, Long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void tapOnElement(MobileElement ele){
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


    // In below examples test-Inventory item page - Scrollable parent element () &
    // test-Price is the child element or target ele
    // scrollToElement_Android1 - Can be used if app has multiple scrolls for diff pages
    // else use scrollToElement_Android2 if app has only one scroll for diff pages
    // In android scroll tag is "android.widget.ScrollView" which means ele can be scrollable

    public MobileElement scrollToElement_Android1() {
        return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".description(\"test-Inventory item page\")).scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-Price\"));");
    }
    public MobileElement scrollToElement_Android2() {
        return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-Price\"));");
    }

    // Scrollable element is identified by XCUIElementTypeScrollView tag
    public void iOS_ScrollMethod1() {
        RemoteWebElement parentElement = (RemoteWebElement)driver.findElement(By.className("XCUIElementTypeScrollView"));
        String parElementID = parentElement.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parElementID);
        scrollObject.put("direction", "up");  // up down io iOS up down are in reverse way
        driver.executeScript("mobile:scroll", scrollObject);
    }
    // Scroll to particular element using predicateString
    public void iOS_ScrollMethod2() {
        RemoteWebElement parentElement = (RemoteWebElement)driver.findElement(By.className("XCUIElementTypeScrollView"));
        String parElementID = parentElement.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parElementID);
        scrollObject.put("predicateString", "label == 'ADD TO CART'");   // if label is available
        // or
       //  scrollObject.put("name", "test-ADD TO CART"); //If name is available
        driver.executeScript("mobile:scroll", scrollObject);
    }

    // Scroll to particular element using id (accessibility id is available)

    public void iOS_ScrollMethod3() {
        RemoteWebElement actualElement = (RemoteWebElement)driver.findElement(By.name("test-ADD TO CART"));
        String elementID = actualElement.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "Random Text");
        driver.executeScript("mobile:scroll", scrollObject);
    }
}
