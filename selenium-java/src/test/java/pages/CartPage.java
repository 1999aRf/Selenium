package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void removeAllProducts() {
        List<WebElement> itemsInCart = driver.findElements(By.cssSelector("li.shortcut"));
        for (int i = 0; i < itemsInCart.size(); i++) {
            WebElement removeButton = driver.findElement(By.name("remove_cart_item"));
            removeButton.click();
            wait.until(ExpectedConditions.stalenessOf(removeButton));
        }
    }
}
