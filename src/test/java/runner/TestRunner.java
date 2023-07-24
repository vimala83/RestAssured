package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(features = {"C:\\Users\\Diksha\\Eclipse-workspace3\\Bug_Busters_Team8_Duplicate\\src\\test\\resources\\Features\\AssigAllGET.feature"}, 
					glue = {"stepDefinitions"},
					monochrome = true, 
					dryRun = false, 
					plugin = { "pretty"}, 
//					tags = "@batchput or @programput",
					
					publish = true)
	public class TestRunner {

	}

