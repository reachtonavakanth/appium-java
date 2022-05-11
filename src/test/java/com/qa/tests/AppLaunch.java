package com.qa.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppLaunch {
    // Android iOS
    public static String env = "Android";


    public static void main(String[] args) throws MalformedURLException {
        if (env.equalsIgnoreCase("Android")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "OPPO A31");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554"); //HYK7BAV45P6S756T
           // caps.setCapability(MobileCapabilityType.APP, getAppLocation("ApiDemos-debug.apk"));
            caps.setCapability("appPackage", "io.appium.android.apis");
            caps.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
            caps.setCapability("ignoreHiddenApiPolicyError", true);
            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            AppiumDriver driver = new AndroidDriver(url, caps);
        }
        else if(env.equalsIgnoreCase("iOS")){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            caps.setCapability(MobileCapabilityType.UDID, "EC60F21C-F390-46FA-98EC-14B3E69621A0");
            caps.setCapability(MobileCapabilityType.APP, getAppLocation("UIKitCatalog-iphonesimulator.app"));
            caps.setCapability("ignoreHiddenApiPolicyError", true);
            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            AppiumDriver driver = new IOSDriver(url, caps);
        }
    }

    private static String getAppLocation(String fileName) {
        String appPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "app" +
                File.separator + fileName;
        return appPath;
    }
}
