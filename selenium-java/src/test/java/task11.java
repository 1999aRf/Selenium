import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class task11 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        // driver = new ChromeDriver();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();

        String uniqueEmail = "user" + System.currentTimeMillis() + "@test.com";

        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Doe");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("New York");

        WebElement countryDropdown = driver.findElement(By.cssSelector("span.select2-selection__rendered"));
        countryDropdown.click();
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-search__field")));
        searchField.sendKeys("United States");
        driver.findElement(By.xpath("//li[text()='United States']")).click();

        WebElement stateDropdown = driver.findElement(By.name("zone_code"));
        // Проверяем, является ли элемент видимым и доступным для взаимодействия
        if (stateDropdown.isDisplayed() && stateDropdown.isEnabled()) {
        String tagName = stateDropdown.getTagName().toLowerCase();
    
        if (tagName.equals("select")) {
        // Если это select, используем JavaScript для выбора опции
        String optionValue = "значение_опции"; 
        String script = "arguments[0].value = '" + optionValue + "'; " +
                        "arguments[0].dispatchEvent(new Event('change'));";
        ((JavascriptExecutor) driver).executeScript(script, stateDropdown);
    } else if (tagName.equals("input")) {
        // Если это input, просто отправляем ключи
        stateDropdown.clear();
        stateDropdown.sendKeys("значение");
    } else {
        // Для других типов элементов
        System.out.println("Неподдерживаемый тип элемента: " + tagName);
    }
} else {
    System.out.println("Элемент не виден или недоступен для взаимодействия");
}
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
        driver.findElement(By.name("phone")).sendKeys("6073233248");

        String password = "password123";
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);

        driver.findElement(By.name("create_account")).click();

        // 4) Logout после регистрации
        driver.findElement(By.linkText("Logout")).click();

         // 5) Повторный вход с новыми данными
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        // 6) Повторный выход
        driver.findElement(By.linkText("Logout")).click();
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}
