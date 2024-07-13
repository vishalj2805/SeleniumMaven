package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "org.study.stepdefinations", tags = "@Regression", monochrome = true, plugin = {"html:reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
