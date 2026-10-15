package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.ProductsPage;

import java.util.List;

public class ProductsPageTest extends BaseUiTest {

    @Test
    public void testProductsCountOnPageDoesNotExceedSix() {
        int productsCountOnPage = new ProductsPage(getDriver()).getProductsCount();

        Assert.assertTrue(productsCountOnPage > 0,
                "Каталог товаров пустой.");
        Assert.assertTrue(productsCountOnPage <= 6,
                "На странице каталога товаров должно отображаться не более 6 карточек");
    }

    @Test
    public void testProductsHaveImageAndSource() {
        List<String> productsSrcList = new ProductsPage(getDriver()).getAllProductImageSources();
        Assert.assertFalse(productsSrcList.isEmpty(), "Список карточек товаров пуст");

        for (int i = 0; i < productsSrcList.size(); i++) {
            final String prefix = "Карточка товара №" + (i + 1) + ": ";
            String src = productsSrcList.get(i);

            Assert.assertNotNull(
                    src, prefix + "не найдено основное изображение товара. Ожидается только 1 картинка.");
            Assert.assertFalse(
                    src.isBlank(), prefix + "атрибут src пустой или содержит только пробелы");
            Assert.assertTrue(
                    src.startsWith("http://") || src.startsWith("https://"),
                    prefix + "некорректный src: " + src + ". Ожидается полный URL");
        }
    }

    @Test
    public void testProductsCardHaveAllCharacteristics() {
        final List<String> expectedSpecsList = List.of(
                "Описание для ",
                "Цена: ",
                "Категория: ",
                "Производитель: ",
                "Бесплатная доставка: "
        );

        List<List<String>> productsCardSpecList = new ProductsPage(getDriver()).getAllProductsCardCharacteristics();
        Assert.assertFalse(productsCardSpecList.isEmpty(), "Список карточек товаров пуст");

        for (int i = 0; i < productsCardSpecList.size(); i++) {            // <<----
            final String prefix = "Карточка товара №" + (i + 1) + ": ";

            List<String> actualSpecsList = productsCardSpecList.get(i);
            Assert.assertFalse(actualSpecsList.isEmpty(), prefix + "не найдено ни одного тега <p>");

            for (String spec : expectedSpecsList) {
                String expectedSpec = spec.toLowerCase();
                boolean result = actualSpecsList
                        .stream()
                        .anyMatch(t -> t.toLowerCase().contains(expectedSpec));

                Assert.assertTrue(result, prefix + "отсутствует характеристика - '" + expectedSpec +
                        "'. Найдены следующие характеристики: " + actualSpecsList);
            }
        }
    }
}
