package com.qa.base;

import com.aventstack.extentreports.Status;
import com.qa.reports.MyExtentReport;
import com.qa.utils.TestUtil;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
    protected static ThreadLocal<String> platformName = new ThreadLocal<String>();
    protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
    protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
    protected static ThreadLocal<String> testDataFilePath = new ThreadLocal<String>();
    protected static ThreadLocal<String> stringsFilePath = new ThreadLocal<String>();
    public static AppiumDriverLocalService service;
    private static final long waitTimeOut = 30;
    TestUtil utils = new TestUtil();


    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public Properties getProps() {
        return props.get();
    }

    public void setProps(Properties props2) {
        props.set(props2);
    }

    public String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platform2) {
        platformName.set(platform2);
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(String dateTime2) {
        dateTime.set(dateTime2);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }

    public String getTestDataFilePath() {
        return testDataFilePath.get();
    }

    public void setTestDataFilePath(String testDataFilePath2) {
        testDataFilePath.set(testDataFilePath2);
    }

    public String getStringsFileFilePath() {
        return stringsFilePath.get();
    }

    public void setStringsFilePath(String stringsFilePath2) {

        stringsFilePath.set(stringsFilePath2);
    }

    public BaseClass() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()),this);
    }

    public AppiumDriverLocalService createAppiumService() {
		//Build the Appium service
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();

		service = AppiumDriverLocalService.buildService(serviceBuilder.usingAnyFreePort());
		service.start();
		System.out.println("Appium Server started successfully");
		return service;

	}


    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort",
            "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeTest
    public void launchApp(@Optional("androidOnly") String emulator, @Optional String platformName,@Optional String udid, @Optional String deviceName,
                          @Optional("androidOnly") String systemPort, @Optional("androidOnly") String chromeDriverPort,
                          @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) throws IOException {
        createAppiumService();
        String propFilePath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator + "resources" +
                File.separator + "appLaunch.properties";

        String testDataPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "testdata" +
                File.separator + "Login.json";

        String stringsPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "testdata" +
                File.separator + "Strings.json";

        String appPath = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "app" +
                File.separator;
        InputStream inputStream = new FileInputStream(propFilePath);
        Properties props = new Properties();
        props.load(inputStream);

        String strFile = "logs" + File.separator + platformName + "_" + deviceName.replaceAll("\\.","_").replaceAll(":","_");
        File logFile = new File(strFile);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }
        ThreadContext.put("ROUTINGKEY", strFile);
        setDateTime(utils.getDateTime());
        setPlatformName(platformName);
        setDeviceName(deviceName);
        setTestDataFilePath(testDataPath);
        setStringsFilePath(stringsPath);
        setProps(props);

        AppiumDriver driver = null;
        URL url;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.UDID, udid);

        url = new URL(props.getProperty("appiumURL"));
        try {
            if (platformName.equalsIgnoreCase("Android")) {
                if (emulator.equalsIgnoreCase("true")) {
                    caps.setCapability("avd", deviceName);
                    caps.setCapability("avdLaunchTimeout", 120000);
                }
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                //caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                caps.setCapability(MobileCapabilityType.APP, appPath + props.getProperty("androidAppName"));
                caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                caps.setCapability("ignoreHiddenApiPolicyError", false);
                caps.setCapability("systemPort", systemPort);
                caps.setCapability("chromeDriverPort", chromeDriverPort);
                caps.setCapability("noReset", true);
                caps.setCapability("chromedriverExecutable",System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+
                        "resources"+File.separator+"drivers"+File.separator+"chromedriver.exe");

               // driver = new AndroidDriver(url, caps);
                try {
        			driver = new AndroidDriver(new URL(service.getUrl().toString()), caps);
                    Thread.sleep(3000);

        		} catch (MalformedURLException e) {
        		          e.printStackTrace();
        		}
                
                // caps.setCapability("--session-override",true);
               // driver = new AndroidDriver(url, caps);

            } else if (platformName.equalsIgnoreCase("iOS")) {
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iosAutomationName"));
                caps.setCapability("bundleId", props.getProperty("iosBundleId"));
                caps.setCapability("wdaLocalPort", wdaLocalPort);
                caps.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
                // caps.setCapability(MobileCapabilityType.APP, appPath + props.getProperty("iosAppName"));
                driver = new IOSDriver(url, caps);
            }
            utils.log().info(getPlatformName() + " driver is Initialized !!");
        } catch (Exception e) {
            utils.log().error(getPlatformName() + " driver initialization Failed");
            e.printStackTrace();
        }
        setDriver(driver);
        System.out.println("driver initialized:");
        if(utils == null){
            System.out.println("utils is null");
            utils= new TestUtil();
        }
        utils.log().info("driver initialized: ");
    }

    @AfterTest
    public void tearDown() {
          getDriver().quit();
 
    	if (service.isRunning() && service != null) 
			service.stop();


    }

    @BeforeMethod
    public void beforeMethod() {
        ((CanRecordScreen) getDriver()).startRecordingScreen();
    }

    @AfterMethod
    public void redirect(ITestResult result) throws IOException {

        stopVideoRecording(result);

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test is Fail"); //getDriver().installApp()
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test is Pass");
        }
        closeApp();
        launchAppAgain();
    }

    public void addLogs(String message){
      //  utils.log().info(message);
        MyExtentReport.getTest().log(Status.INFO, message);
    }
    public void closeApp() {
        ((InteractsWithApps) getDriver()).closeApp();
        addLogs("App is Closed !!");

    }

    public void launchAppAgain() {
        ((InteractsWithApps) getDriver()).launchApp();
        addLogs("App is Launched !!");
    }

    public void waitForEleVisibility(MobileElement ele) {

        WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOut);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForEleVisibility(MobileElement ele, Long time) {
        WebDriverWait wait = new WebDriverWait(getDriver(), time);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public String logCPUMemoryProfile(){
              Process process;
              StringBuffer sb = new StringBuffer();
              String activityName = ((AndroidDriver) getDriver()).currentActivity();
              try {
                  System.out.println("adb shell dumpsys cpuinfo | find \""+getProps().getProperty("androidAppPackage")+"\"");
                  process = Runtime.getRuntime().exec("cmd /c adb shell dumpsys cpuinfo | find \""+getProps().getProperty("androidAppPackage")+"\"");
                  sb.append(activityName +" $ "+getResults(process));
                  process = Runtime.getRuntime().exec("cmd /c adb shell dumpsys meminfo | find \""+getProps().getProperty("androidAppPackage")+"\"");
                  sb.append(" $ "+getResults(process));

              }catch(Exception e){
                  e.printStackTrace();
              }
              return sb.toString();
    }

    public String getResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
                sb.append(line);
        }
        return sb.toString();
    }

    public void waitForEleClickable(MobileElement ele, Long time) {
        WebDriverWait wait = new WebDriverWait(getDriver(), time);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void tapOnElement(MobileElement ele) {
        waitForEleVisibility(ele);
        ele.click();
        addLogs("Tapped on element "+ ele);
    }

    public void clearElement(MobileElement ele) {
        waitForEleVisibility(ele);
        ele.clear();
    }

    public void sendDataToElement(MobileElement ele, String value) {
        waitForEleVisibility(ele);
        clearElement(ele);
        ele.sendKeys(value);
      //  addLogs("Entered data in "+ ele + " as " + value);
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
        if (getPlatformName().equalsIgnoreCase("Android")) {
            text = getAndroidElementAttribute(ele, "text");
        } else if (getPlatformName().equalsIgnoreCase("iOS")) {
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
        return (MobileElement) ((FindsByAndroidUIAutomator) getDriver()).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".description(\"test-Item\")).scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(\"test-FINISH\"));");
    } // CHECKOUT: OVERVIEW

    public MobileElement scrollToElement_Android2(String value) {
        return (MobileElement) ((FindsByAndroidUIAutomator) getDriver()).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().description(" + value + "));");
    }

    public void scrollTillText(String text){

        ((AndroidDriver) getDriver()).
                findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                        "scrollIntoView(new UiSelector().text(\""+text+"\"))");

    }

    public boolean elementPresent(AppiumDriver driver, By element, int time) {
        boolean elementPresentFlag = false;
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            for (int i = 0; i < time; i++) {
                try {
                    MobileElement we = null;
                    if ((we=(MobileElement) getDriver().findElement(element))!= null) {
                        elementPresentFlag = true;
                        break;
                    }

                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return elementPresentFlag;
    }

    public void swipeDownToElement(AppiumDriver driver, By element) {
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            int i = 0;
            while (!elementPresent(driver, element, 1) && i < 20) {
                try {
                    i++;
                    verticalSwipe(driver);
                } catch (Exception e) {
                }
            }
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void verticalSwipe(AppiumDriver driver) {
        try {
            Dimension windowSize = driver.manage().window().getSize();
            int height = windowSize.height;
            int width = windowSize.width;
            int startY = (int) (height * 0.80);
            int endY = (int) (height * 0.20);
            int startX = (int) (width/2);

            System.out.println(startX+" "+startY+" "+startX+" "+endY);
            TouchAction ta = new TouchAction(driver);
            try {
                ta.press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                        .moveTo(PointOption.point(startX, endY))
                        .release().perform();


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Scrollable element is identified by XCUIElementTypeScrollView tag
    public void iosScroll(String scrollType) {
        RemoteWebElement parentElement = (RemoteWebElement) getDriver().findElement(By.className("XCUIElementTypeScrollView"));
        String parElementID = parentElement.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parElementID);
        scrollObject.put("direction", scrollType.toLowerCase(Locale.ROOT));
        getDriver().executeScript("mobile:scroll", scrollObject);
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
                RemoteWebElement parentElement = (RemoteWebElement) getDriver().findElement(By.className("XCUIElementTypeScrollView")); // Parent tag
                String parElementID = parentElement.getId();
                scrollObject.put("element", parElementID);
                if (attributeType.equalsIgnoreCase("label")) {
                    scrollObject.put("predicateString", "label == '" + attributeValue + "'");
                } else if (attributeType.equalsIgnoreCase("name")) {
                    scrollObject.put("name", attributeValue);
                }
                getDriver().executeScript("mobile:scroll", scrollObject);
                break;
            case "id":
                RemoteWebElement actualElement = (RemoteWebElement) getDriver().findElement(By.name(attributeValue));
                String elementID = actualElement.getId();
                scrollObject.put("element", elementID);
                scrollObject.put("toVisible", "RandomText");
                getDriver().executeScript("mobile:scroll", scrollObject);
                break;
        }
    }

    public void keyboardActions(String key) {
        getDriver().findElement(MobileBy.AccessibilityId(key)).click();
    }

    public synchronized void stopVideoRecording(ITestResult result) throws IOException {
        String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();

        Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
        String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
                + File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();

        File videoDir = new File(dirPath);

        synchronized (videoDir) {
            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }
        }

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
            stream.write(Base64.decodeBase64(media));
            stream.close();
            //  utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
        } catch (Exception e) {
            //  utils.log().error("error during video capture" + e.toString());
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}