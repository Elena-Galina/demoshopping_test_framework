package ui.pages;

import config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.actions.ElementActions;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    private final WebDriverWait wait;
    private final ElementActions elementActions;

    protected BasePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(new Configuration().getTimeout()));
        this.elementActions = new ElementActions(driver, wait);
    }

    protected BasePage(WebDriver driver, int timeout) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.elementActions = new ElementActions(driver, wait);
    }

    public void click(By locator) {
        elementActions.click(locator);
    }

    public List<WebElement> getList(By locator) {

        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public int getSize(By locator) {

        return getList(locator).size();
    }
}
