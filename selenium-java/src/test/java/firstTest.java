import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class firstTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.deepl.com/ru/translator");
    }

    @Test
    public void mySecondTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("testLogin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("testPassword");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}
