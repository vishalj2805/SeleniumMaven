package org.study.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTCs implements IRetryAnalyzer {
    int count = 0;
    int maxRetry = 3;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxRetry){
            count++;
            return true;
        }
        return false;
    }
}
