import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class seventeenthTask {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        List<WebElement> productLinks = driver.findElements(By.cssSelector("table.dataTable tr.row a:not([title='Edit'])"));

        for (int i = 2; i < productLinks.size(); i++) {
            // Повторно ищем ссылки, чтобы избежать StaleElementReferenceException
            productLinks = driver.findElements(By.cssSelector("table.dataTable tr.row a:not([title='Edit'])"));
            WebElement productLink = productLinks.get(i);
            
            // Выводим текст элемента для проверки
            System.out.println("Кликаем на элемент с текстом: " + productLink.getText());

            // Открываем страницу товара
            productLink.click();

            // Проверка на наличие сообщений в логе браузера
            LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry log : logs) {
                System.out.println("Log: " + log.getMessage());
                // Проверяем, что в логах нет сообщений (любого уровня)
                assertTrue(logs.getAll().isEmpty(), "В логе браузера появились сообщения!");
            }

            // Возвращаемся обратно на страницу каталога
            driver.navigate().back();
        }
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}