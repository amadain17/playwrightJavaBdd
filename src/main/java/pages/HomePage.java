package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import modules.ProductCard;

public class HomePage extends BasePage {
    public Locator shoppingCartCounter;

    public HomePage(Page page) {
        super(page);
        shoppingCartCounter = page.locator(".counter-number");
    }

    public void open() {
        page.navigate("/");
    }

    public ProductCard getProductCardLocator(String productName) {
        return new ProductCard(page, productName);
    }
}
