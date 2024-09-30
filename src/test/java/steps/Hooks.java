package steps;

import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;
import java.util.Locale;

public class Hooks extends BaseSteps {

    public Hooks(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    @AfterAll
    static void after_all() {
        ScenarioContext.playwright.close();
    }

    @After
    public void after(Scenario scenario) {
        String scenarioTraceName = scenario.getName().toLowerCase(Locale.ROOT).replace(" ", "-");
        this.scenarioContext.context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target", "results", scenarioTraceName + "-trace.zip")));
        this.scenarioContext.context.close();

        page.video().saveAs(Paths.get("target", "results","videos", scenarioTraceName + ".webm"));
        page.video().delete();

        this.scenarioContext.cleanUp();
    }
}
