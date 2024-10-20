import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class thirteenthTask {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void ThirteenthTask() {
        driver.get("http://localhost/litecart/en/");

        for (int i = 0; i < 3; i++) {
            addProductToCart(driver, wait);
            driver.navigate().back();
        }

        driver.findElement(By.cssSelector("div#cart a.link")).click();
        removeAllProductsFromCart(driver, wait);
    }

    private static void addProductToCart(WebDriver driver, WebDriverWait wait) {
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        products.get(0).click();

        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            WebElement sizeSelect = driver.findElement(By.name("options[Size]"));
            sizeSelect.click();
            sizeSelect.findElement(By.cssSelector("option[value='Small']")).click();
        }

        driver.findElement(By.name("add_cart_product")).click();

        WebElement cartCounter = driver.findElement(By.cssSelector("div#cart .quantity"));
        wait.until(ExpectedConditions.textToBePresentInElement(cartCounter, String.valueOf(Integer.parseInt(cartCounter.getText()) + 1)));
    }

    private static void removeAllProductsFromCart(WebDriver driver, WebDriverWait wait) {
        List<WebElement> itemsInCart = driver.findElements(By.cssSelector("li.shortcut"));
        for (int i = 0; i < itemsInCart.size(); i++) {
            WebElement removeButton = driver.findElement(By.name("remove_cart_item"));
            removeButton.click();

            wait.until(ExpectedConditions.stalenessOf(removeButton));
        }
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}
