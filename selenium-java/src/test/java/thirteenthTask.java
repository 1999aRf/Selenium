import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.MainPage;
import pages.ProductPage;
import pages.CartPage;

public class thirteenthTask {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void ThirteenthTask() {
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver, wait);
        cartPage = new CartPage(driver, wait);

        mainPage.open();

        for (int i = 0; i < 3; i++) {
            mainPage.selectFirstProduct();
            productPage.selectSizeIfAvailable();
            productPage.addToCart();
            driver.navigate().back();
        }

        mainPage.goToCart();
        cartPage.removeAllProducts();
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}
