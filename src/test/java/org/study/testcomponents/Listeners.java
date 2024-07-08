package org.study.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class Listeners extends TestBase implements ITestListener {

    ExtentReports extentReports = new ReportsConfig().reportsConfig();
    ExtentTest test;
    WebDriver driver;
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();



    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTestThread.set(test);
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String path = getScreenshot(result.getMethod().getMethodName());
        extentTestThread.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThread.get().log(Status.FAIL, "Test Case Failed");
        extentTestThread.get().fail(result.getMethod().getMethodName() + "Failed \n" + result.getThrowable());
        String path = getScreenshot(result.getMethod().getMethodName());
        extentTestThread.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
    }



    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    public String getScreenshot(String name){
        String path;
        try {
            path = takeScreenshot(name, driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }


}
