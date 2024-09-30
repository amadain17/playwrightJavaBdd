package steps;

import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import pages.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class CommonSteps extends BaseSteps {
    private final BasePage lumaPage;

    public CommonSteps(ScenarioContext scenarioContext) {
        super(scenarioContext);
        this.lumaPage = new BasePage(page);
    }

    @Then("^I should see (.*?) notification$")
    public void iShouldSeeNotification(String expectedAlertText) {
        assertThat(lumaPage.alert).hasText(expectedAlertText);
    }

    @Then("^I should see (.*?) in my shopping cart$")
    public void iShouldSeeInMyShoppingCart(String productName) {
        lumaPage.shoppingCartButton.click();
        log.info("Checking for product " + productName);
        System.out.println("mini cart text" +page.locator("#mini-cart").textContent() + " end mini cart text");
        System.out.println("Qty text " + page.getByLabel("Qty").textContent() + " end qty text");
    }

    @Then("^I should see (.*?) button$")
    public void iShouldSeeButton(String buttonText) {
        assertThat(lumaPage.button.getByText(buttonText)).isVisible();

    }
}
