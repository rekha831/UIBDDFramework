package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepdefinitions", "hooks"},
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    monochrome = true
	)
public class TestRunner extends AbstractTestNGCucumberTests {
// if you need parallel execution of scenarios, override scenarios() and use DataProvider
}