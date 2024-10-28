package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void selectSizeIfAvailable() {
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            WebElement sizeSelect = driver.findElement(By.name("options[Size]"));
            sizeSelect.click();
            sizeSelect.findElement(By.cssSelector("option[value='Small']")).click();
        }
    }

    public void addToCart() {
        WebElement cartCounter = driver.findElement(By.cssSelector("div#cart .quantity"));
        String itemCount = cartCounter.getText();
        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(cartCounter, String.valueOf(Integer.parseInt(itemCount) + 1)));
    }
}
