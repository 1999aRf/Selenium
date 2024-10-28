package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public void selectFirstProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        products.get(0).click();
    }

    public void goToCart() {
        driver.findElement(By.cssSelector("div#cart a.link")).click();
    }
}