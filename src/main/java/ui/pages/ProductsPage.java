package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By productCards = By.cssSelector("#product-list > div");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public int getProductsCount() {
        return getSize(productCards);
    }

    public List<String> getAllProductImageSources() {
        List<WebElement> productCardList = getList(productCards);

        List<String> productsSrc = new ArrayList<>(productCardList.size());
        for (WebElement card : productCardList) {
            try {
                WebElement productImage = card.findElement(By.tagName("img"));
                String src = productImage.getAttribute("src");
                productsSrc.add(src);

            } catch (NoSuchElementException e) {
                productsSrc.add(null);              // Карточка есть, но img нет, значит, null - отсутствие картинки
            }
        }
        return productsSrc;
    }
}