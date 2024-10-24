import java.io.File;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class task12 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void task12() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();

        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        String uniqueProductName = "My New Product " + System.currentTimeMillis();
        driver.findElement(By.name("name[en]")).sendKeys(uniqueProductName);
        driver.findElement(By.name("code")).sendKeys("12345");

        List<WebElement> categories = driver.findElements(By.name("categories[]"));
        categories.get(1).click();

        driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-3']")).click();

        WebElement quantity = driver.findElement(By.name("quantity"));
        quantity.clear();
        quantity.sendKeys("100");

        File productImage = new File("src/main/resources/images/product_image.png");
        String absolutePath = productImage.getAbsolutePath();
        driver.findElement(By.name("new_images[]")).sendKeys(absolutePath);

        driver.findElement(By.name("date_valid_from")).sendKeys("01012024");
        driver.findElement(By.name("date_valid_to")).sendKeys("01012025");

        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        pause(1000);

        WebElement select = driver.findElement(By.name("manufacturer_id"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].selectedIndex = 1", select);
    
        driver.findElement(By.name("short_description[en]")).sendKeys("This is a short description of the product.");

        driver.findElement(By.className("trumbowyg-editor")).sendKeys("This is the detailed description of the new product.");

        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        pause(1000);; 

        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        purchasePrice.clear();
        purchasePrice.sendKeys("20.00");
        driver.findElement(By.name("purchase_price_currency_code")).sendKeys("USD"); 

        driver.findElement(By.name("prices[USD]")).sendKeys("25.00");

        driver.findElement(By.name("save")).click();

        driver.findElement(By.linkText(uniqueProductName)); 
        System.out.println("Товар успешно добавлен!");
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }

    private static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
