package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    
    // ✅ Add Allure plugin along with pretty & html
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // <-- Allure integration
    },
    
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // ✅ Optional: For parallel execution, you can override scenarios() with a DataProvider
    // @DataProvider(parallel = true)
    // public Object[][] scenarios() {
    //     return super.scenarios();
    // }
}
