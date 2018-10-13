package com.aeturnum.scs;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@CucumberOptions(plugin = { "pretty","json:target/cucumber.json" }, glue = { "com.aeturnum.scs.stepdef" },
		// features = {"src/test/features"})
@CucumberOptions(strict = false, 
format = { "pretty",
"json:target/cucumber.json" }, tags = { "~@ignore" },
		features = { "src/test/features/ViewDCP.feature" })

public class CucumberRunnerTest {

}
