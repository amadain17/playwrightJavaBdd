package modules;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductCard extends BaseModule {
    public Locator image;
    public Locator link;
    public Locator priceLabel;
    public Locator price;
    public Locator sizeOption;
    public Locator colorOption;
    public Locator addToCartButton;
    public Locator addToWishlistButton;
    public Locator addToCompareButton;

    private final Locator cardContainer;

    public ProductCard(Page page, String name) {
        super(page);
        this.page = page;
        cardContainer = page.locator(".product-item-info", new Page.LocatorOptions().setHasText(name));

        image = cardContainer.getByRole(AriaRole.IMG);
        link = cardContainer.getByRole(AriaRole.LINK);
        priceLabel = cardContainer.locator(".price-label");
        price = cardContainer.locator(".price");
        sizeOption = cardContainer.locator(".size");
        colorOption = cardContainer.locator(".swatch-attribute").last();
        addToCartButton = cardContainer.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to Cart"));
        addToWishlistButton = cardContainer.locator(".towishlist");
        addToCompareButton = cardContainer.locator(".tocompare");
    }

    public ProductCard selectSize(String size) {
        this.sizeOption.getByText(size).click();
        return this;
    }

    public ProductCard selectColor(String color) {
        this.cardContainer.hover();
        this.colorOption.getByLabel(color).click();
        return this;
    }
}
