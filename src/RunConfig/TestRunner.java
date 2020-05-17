package RunConfig;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "FeatureFiles/login.feature",
		glue={"StepDefinitions.loginSteps"},
		tags={"@login"} 
		
		)

public class TestRunner {
	
	

}
