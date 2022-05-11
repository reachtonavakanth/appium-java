package com.qa.base;

import com.qa.utils.TestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.URL;

public class BaseClass extends TestUtil {

    protected AppiumDriver driver;

    @BeforeTest
    public void launchApp() {
        String propFilePath = TestUtil.getMainResources("appLaunch.properties");
        String env = getKeyValue(propFilePath, "environment");
        try {
            if (env.equalsIgnoreCase("Android")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, getKeyValue(propFilePath, "androidAutomationName"));
                caps.setCapability(MobileCapabilityType.UDID, getKeyValue(propFilePath, "androidUdid"));
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, getKeyValue(propFilePath, "androidPlatformName"));
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, getKeyValue(propFilePath, "androidPlatformVersion"));
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, getKeyValue(propFilePath, "androidDeviceName"));
                caps.setCapability(MobileCapabilityType.APP, getAppLocation(getKeyValue(propFilePath, "androidAppName")));
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
                caps.setCapability(MobileCapabilityType.APP, getAppLocation(getKeyValue(propFilePath, "iosAppName")));
                // caps.setCapability("ignoreHiddenApiPolicyError", true);
                URL url = new URL(getKeyValue(propFilePath, "appiumURL"));
                driver = new IOSDriver(url, caps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
