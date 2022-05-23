package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyExtentReport {
    static ExtentReports extent;
    final static String filePath = "Extent.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public synchronized static ExtentReports getReporter() throws IOException {
        if (extent == null) {
            ExtentSparkReporter sparktHtml = new ExtentSparkReporter(filePath);
            JsonFormatter json = new JsonFormatter("extent.json");
            sparktHtml.config().setDocumentTitle("Automation Execution Report");
            sparktHtml.config().setReportName("Automation TEAM");
            sparktHtml.config().setTheme(Theme.DARK);
            extent.createDomainFromJsonArchive("extent.json");
            extent = new ExtentReports();
            extent.attachReporter(json,sparktHtml);
        }

        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) throws IOException {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}

