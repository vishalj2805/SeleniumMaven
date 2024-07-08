package org.study.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsConfig {

    public ExtentReports reportsConfig(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//index.html");
        reporter.config().setReportName("WebApp Automation");
        reporter.config().setDocumentTitle("Test Automation Results");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Vishal Jadhav");
        return extentReports;
    }
}
