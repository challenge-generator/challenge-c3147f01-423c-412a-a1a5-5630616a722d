package com.credito.cartera.bdd.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.credito.cartera.bdd.steps",
        "com.credito.cartera.bdd.hooks"
    },
    tags = "@smoke or @regresion or @integration or @performance",
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber.html",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    monochrome = true,
    strict = true,
    dryRun = false
)
public class RunCucumberTest {
}