package com.qa.base;

import com.qa.utils.TestUtil;
import io.appium.java_client.*;
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
import java.util.Locale;

public class BaseClass extends TestUtil {
    protected static AppiumDriver driver;
    protected static String env;
    public static String testDataFilePath;
    public static String stringsFilePath;

    public BaseClass() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
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
                if (getKeyValue(propFilePath, "emulator").equalsIgnoreCase("Yes")) {
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
    public void redirect(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test is Fail"); //driver.installApp()
        } else if (result.getStatus() == ITestResult.SUCCESS) {
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

    public void tapOnElement(MobileElement ele) {
        waitForEleVisibility(ele);
        ele.click();
    }

    public void clearElement(MobileElement ele) {
        waitForEleVisibility(ele);
        ele.clear();
    }

    public void sendDataToElement(MobileElement ele, String value) {
        waitForEleVisibility(ele);
        clearElement(ele);
        ele.sendKeys(value);
    }

    public String getAndroidElementAttribute(MobileElement ele, String attributeName) {
        waitForEleVisibility(ele);
        return ele.getAttribute(attributeName);
    }

    public String getIOSElementAttribute(MobileElement ele, String attributeName) {
        waitForEleVisibility(ele);
        return ele.getAttribute(attributeName);
    }

    // in iOS text attribute will not work hence using label attribute
    public String getText(MobileElement ele) {
        String text = null;
        if (env.equalsIgnoreCase("Android")) {
            text = getAndroidElementAttribute(ele, "text");
        } else if (env.equalsIgnoreCase("iOS")) {
            text = getIOSElementAttribute(ele, "label");
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
    public void iosScroll(String scrollType) {
        RemoteWebElement parentElement = (RemoteWebElement) driver.findElement(By.className("XCUIElementTypeScrollView"));
        String parElementID = parentElement.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parElementID);
        scrollObject.put("direction", scrollType.toLowerCase(Locale.ROOT));
        driver.executeScript("mobile:scroll", scrollObject);
    }
    // Scroll to particular element using below
    // identificationType - class or id
    // attributeType - label or name
    // attributeValue - label value or name value
    // Scroll to particular element using predicateString

    public void iosScroll(String identificationType, String attributeType, String attributeValue) {
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        switch (identificationType) {
            case "class":
                RemoteWebElement parentElement = (RemoteWebElement) driver.findElement(By.className("XCUIElementTypeScrollView")); // Parent tag
                String parElementID = parentElement.getId();
                scrollObject.put("element", parElementID);
                if (attributeType.equalsIgnoreCase("label")) {
                    scrollObject.put("predicateString", "label == '" + attributeValue + "'");
                } else if (attributeType.equalsIgnoreCase("name")) {
                    scrollObject.put("name", attributeValue);
                }
                driver.executeScript("mobile:scroll", scrollObject);
                break;
            case "id":
                RemoteWebElement actualElement = (RemoteWebElement) driver.findElement(By.name(attributeValue));
                String elementID = actualElement.getId();
                scrollObject.put("element", elementID);
                scrollObject.put("toVisible", "RandomText");
                driver.executeScript("mobile:scroll", scrollObject);
                break;
        }
    }

    public void keyboardActions(String key) {
        driver.findElement(MobileBy.AccessibilityId(key)).click();
    }
}
