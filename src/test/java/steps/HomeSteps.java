package steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import enums.SharedInfoTag;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.ProductModel;
import modules.ProductCard;
import pages.HomePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomeSteps extends BaseSteps {
    private final HomePage homePage;

    public HomeSteps(ScenarioContext scenarioContext) {
        super(scenarioContext);
        homePage = new HomePage(page);
    }

    @Given("^I am on the LUMA homepage$")
    public void iAmOnTheLumaHomepage() {
        homePage.open();
    }

    @When("^I add (.*?) coloured (.*?) in (.*?) size to cart$")
    public void iAddInSizeToCart(String color, String name, String size) {
        ProductCard productCard = homePage.getProductCardLocator(name);
        productCard.selectColor(color).selectSize(size);
        productCard.addToCartButton.click();
        homePage.header.welcomeMessage.first().waitFor(
                new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
        );

        scenarioContext.setSharedInfo(
                SharedInfoTag.PRODUCT,
                ProductModel
                        .builder()
                        .price(productCard.price.innerText())
                        .color(color)
                        .size(size)
        );
    }

    @Then("^I should see shopping cart counter to be (.*?)$")
    public void iShouldSeeShoppingCartCounterToBe(String shoppingCartQuantity) {
        assertThat(homePage.shoppingCartCounter).hasText(shoppingCartQuantity);
    }
}

