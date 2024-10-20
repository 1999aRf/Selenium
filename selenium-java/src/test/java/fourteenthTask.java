import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class fourteenthTask {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void FourteenthTask() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("a[href*='edit_country']")).click();

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i.fa-external-link"));

        for (WebElement link : externalLinks) {
            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();

            link.click();

            String newWindow = wait.until(anyWindowOtherThan(existingWindows));

            driver.switchTo().window(newWindow);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            driver.close();

            driver.switchTo().window(originalWindow);
        }
    }

    private static ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> newWindows = driver.getWindowHandles();
                newWindows.removeAll(oldWindows);
                return newWindows.size() > 0 ? newWindows.iterator().next() : null;
            }
        };
    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }
}
