package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.ProductsPage;

import java.util.List;

public class ProductsPageTest extends BaseUiTest {

    @Test
    public void testProductsCountOnPageDoesNotExceedSix() {
        int productsCountOnPage = new ProductsPage(getDriver()).getProductsCount();

        Assert.assertTrue(productsCountOnPage <= 6,
                "The number of Product cards on each Catalog page must not exceed 6.");
    }

    @Test
    public void testProductsHaveImageAndSource() {
        List<String> productsSrcList = new ProductsPage(getDriver()).getAllProductImageSources();

        Assert.assertFalse(productsSrcList.isEmpty(),"The product list is empty");
        for (int i = 0; i < productsSrcList.size(); i++) {
            String src = productsSrcList.get(i);

            Assert.assertNotNull(src,"У товара №%d отсутствует атрибут src у тега img".formatted(i + 1));
            Assert.assertFalse(src.isBlank(), "У товара №%d атрибут src пустой".formatted(i + 1));
        }
    }
}