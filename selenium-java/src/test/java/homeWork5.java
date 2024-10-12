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

public class homeWork5 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        List<WebElement> countryElements = driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(5) a"));
        List<String> countries = new ArrayList<>();
            for (WebElement countryElement : countryElements) {
                countries.add(countryElement.getText());
            }

            // Копировать список для сортировки
            List<String> sortedCountries = new ArrayList<>(countries);
            Collections.sort(sortedCountries);

            // Проверить, что оригинальный список совпадает с отсортированным
            if (countries.equals(sortedCountries)) {
                System.out.println("Страны расположены в алфавитном порядке.");
            } else {
                System.out.println("Страны не расположены в алфавитном порядке.");
            }
    }

    @Test
    public void mySecondTest() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int i = 0; i < rows.size(); i++) {

            WebElement zoneCountElement = rows.get(i).findElement(By.cssSelector("td:nth-child(6)"));
            int zoneCount = Integer.parseInt(zoneCountElement.getText());

            if (zoneCount > 0) {
                WebElement countryLink = rows.get(i).findElement(By.cssSelector("td:nth-child(5) a"));
                countryLink.click();

                List<WebElement> zoneElements = driver.findElements(By.cssSelector("table#table-zones tr td:nth-child(3)"));

                List<String> zones = new ArrayList<>();
                for (WebElement zoneElement : zoneElements){
                    String zoneName = zoneElement.getText();
                    if (!zoneName.isEmpty()) {
                        zones.add(zoneName);
                    }
                }

                List<String> sortedZones = new ArrayList<>(zones);
                Collections.sort(sortedZones);

                if (zones.equals(sortedZones)) {
                    System.out.println("Геозоны для страны находятся в алфавитном порядке.");
                } else {
                    System.out.println("Геозоны для страны НЕ находятся в алфавитном порядке.");
                }

                driver.navigate().back();
                rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
            }
        }
    }           
    
    
     @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}