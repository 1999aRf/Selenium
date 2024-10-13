import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.Collections2;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

public class nineTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        List<WebElement> countries = driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(3) a"));

        // Проходим по каждой стране
        for (int i = 0; i < countries.size(); i++) {
            // Заново находим элементы, так как предыдущий список устареет после клика
            countries = driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(3) a"));
            WebElement country = countries.get(i);
            country.click();  // Заходим в страну

            // Находим все зоны в таблице
            List<WebElement> zones = driver.findElements(By.cssSelector("table.dataTable td:nth-child(3) [selected='selected']"));

            // Получаем текстовые значения зон
            List<String> zoneNames = new ArrayList<>();
            for (WebElement zone : zones) {
                zoneNames.add(zone.getText());
            }

            // Проверяем, что зоны отсортированы в алфавитном порядке
            List<String> sortedZoneNames = new ArrayList<>(zoneNames);
            Collections.sort(sortedZoneNames);

            if (zoneNames.equals(sortedZoneNames)) {
                System.out.println("Зоны для страны отсортированы по алфавиту.");
            } else {
                System.out.println("Зоны для страны не отсортированы по алфавиту!");
            }

            // Возвращаемся на страницу списка стран
            driver.navigate().back();
        }

        // Закрываем браузер
        driver.quit();
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}
