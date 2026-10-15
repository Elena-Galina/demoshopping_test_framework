package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By productCard = By.cssSelector("#product-list > div");
    private final By productImage = By.cssSelector(":scope > img");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public int getProductsCount() {
        return getSize(productCard);
    }

    public List<String> getAllProductImageSources() {
        List<WebElement> productCardList = getList(productCard);
        List<String> productsSrc = new ArrayList<>(productCardList.size());

        for (WebElement card : productCardList) {
            List<WebElement> productImageList = card.findElements(productImage);

            if (productImageList.size() != 1) {
                productsSrc.add(null);
            } else {
                String src = productImageList.get(0).getAttribute("src");
                productsSrc.add(src);
            }
        }
        return productsSrc;
    }

    public List<List<String>> getAllProductsCardCharacteristics() {
        List<WebElement> productCardList = getList(productCard);
        List<List<String>> productsSpecList = new ArrayList<>(productCardList.size());

        for (WebElement card : productCardList) {
            List<WebElement> specList = card.findElements(By.tagName("p"));
            List<String> specs = specList.stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            productsSpecList.add(specs);
        }
        return productsSpecList;
    }
}
