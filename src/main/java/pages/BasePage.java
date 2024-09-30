package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import modules.BaseModule;
import modules.Header;

public class BasePage extends BaseModule {
    public Header header;
    public Locator alert;
    public Locator button;
    public Locator shoppingCartButton;

    public BasePage(Page page) {
        super(page);
        this.header = new Header(page);
        this.alert = page.getByRole(AriaRole.ALERT);
        this.button = page.locator("button");
        this.shoppingCartButton = page.locator(".showcart");
    }
}
