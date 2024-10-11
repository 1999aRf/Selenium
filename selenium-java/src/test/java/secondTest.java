import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.time.Duration;
import java.util.stream.Collectors;

public class secondTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Appearence")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Logotype")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Product Groups")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Option Groups")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Manufacturers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Suppliers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Delivery Statuses")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Sold Out Statuses")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Quantity Units")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("CSV Import/Export")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Countries")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Currencies")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Customers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("CSV Import/Export")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Newsletter")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Geo Zones")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Languages")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Storage Encoding")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Modules")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Background Jobs")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Customer")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Shipping")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Payment")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Order Total")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Order Success")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Order Action")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Orders")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Order Statuses")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Pages")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Reports")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Most Sold Products")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Most Shopping Customers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Settings")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Store Info")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Defaults")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("General")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Listings")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Images")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Checkout")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Advanced")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Security")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Slides")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Tax")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Tax Rates")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Translations")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Scan Files")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("CSV Import/Export")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Users")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("vQmods")).click();
        driver.findElement(By.tagName("h1"));
    }

    @Test
    public void sevenTask() {
        driver.get("http://localhost/litecart");
        // WebElement element;
        List<WebElement> yellowDuck = driver.findElements(By.cssSelector("#box-most-popular a[title='Yellow Duck'] div.sticker.sale"));
        if (yellowDuck.size() == 1) {
            System.out.println("Элемент " + yellowDuck + "найден и он единственный.");
        } else {
            System.out.println("Найдено элементов: " + yellowDuck.size());
        }

        List<WebElement> greenDucks = driver.findElements(By.cssSelector("#box-most-popular a[title='Green Duck'] div.sticker.new"));
        if (greenDucks.size() == 1) {
            System.out.println("Элемент " + greenDucks + "найден и он единственный.");
        } else {
            System.out.println("Найдено элементов: " + greenDucks.size());
        }

        List<WebElement> blueDucks = driver.findElements(By.cssSelector("#box-most-popular a[title='Blue Duck'] div.sticker.new"));
        if (blueDucks.size() == 1) {
            System.out.println("Элемент " + blueDucks + "найден и он единственный.");
        } else {
            System.out.println("Найдено элементов: " + blueDucks.size());
        }

        List<WebElement> redDucks = driver.findElements(By.cssSelector("#box-most-popular a[title='Red Duck'] div.sticker.new"));
        if (redDucks.size() == 1) {
            System.out.println("Элемент найден и он единственный.");
        } else {
            System.out.println("Найдено элементов: " + redDucks.size());
        }

        List<WebElement> purpleDucks = driver.findElements(By.cssSelector("#box-most-popular a[title='Purple Duck'] div.sticker.new"));
        if (purpleDucks.size() == 1) {
            System.out.println("Элемент найден и он единственный.");
        } else {
            System.out.println("Найдено элементов: " + purpleDucks.size());
        }
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}