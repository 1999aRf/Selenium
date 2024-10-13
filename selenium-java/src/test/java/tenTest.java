import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class tenTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("agliulov.rinat@mail.ru");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("Stown456");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        WebElement campaignProduct = driver.findElement(By.cssSelector("#box-campaigns .product"));

        // Получаем название товара, обычную и акционную цену
        String mainPageProductName = campaignProduct.findElement(By.cssSelector(".name")).getText();
        String mainPageRegularPrice = campaignProduct.findElement(By.cssSelector(".regular-price")).getText();
        String mainPageCampaignPrice = campaignProduct.findElement(By.cssSelector(".campaign-price")).getText();

        // Проверяем оформление обычной цены
        WebElement mainPageRegularPriceElement = campaignProduct.findElement(By.cssSelector(".regular-price"));
        String mainPageRegularPriceColor = mainPageRegularPriceElement.getCssValue("color");
        String mainPageRegularPriceTextDecoration = mainPageRegularPriceElement.getCssValue("text-decoration-line");

        // Проверяем оформление акционной цены
        WebElement mainPageCampaignPriceElement = campaignProduct.findElement(By.cssSelector(".campaign-price"));
        String mainPageCampaignPriceColor = mainPageCampaignPriceElement.getCssValue("color");
        String mainPageCampaignPriceFontWeight = mainPageCampaignPriceElement.getCssValue("font-weight");

        // Переходим на страницу товара
        campaignProduct.click();

        // 3. Получаем информацию со страницы товара
        String productPageProductName = driver.findElement(By.cssSelector("h1.title")).getText();
        String productPageRegularPrice = driver.findElement(By.cssSelector(".regular-price")).getText();
        String productPageCampaignPrice = driver.findElement(By.cssSelector(".campaign-price")).getText();

        // Проверяем оформление обычной цены на странице товара
        WebElement productPageRegularPriceElement = driver.findElement(By.cssSelector(".regular-price"));
        String productPageRegularPriceColor = productPageRegularPriceElement.getCssValue("color");
        String productPageRegularPriceTextDecoration = productPageRegularPriceElement.getCssValue("text-decoration-line");

        // Проверяем оформление акционной цены на странице товара
        WebElement productPageCampaignPriceElement = driver.findElement(By.cssSelector(".campaign-price"));
        String productPageCampaignPriceColor = productPageCampaignPriceElement.getCssValue("color");
        String productPageCampaignPriceFontWeight = productPageCampaignPriceElement.getCssValue("font-weight");

        // 4. Проверяем, что название товара совпадает
        if (mainPageProductName.equals(productPageProductName)) {
            System.out.println("Название товара совпадает.");
        } else {
            System.out.println("Ошибка: Название товара не совпадает!");
        }

        // 5. Проверяем, что цены совпадают
        if (mainPageRegularPrice.equals(productPageRegularPrice) && mainPageCampaignPrice.equals(productPageCampaignPrice)) {
            System.out.println("Цены совпадают.");
        } else {
            System.out.println("Ошибка: Цены не совпадают!");
        }

        // 6. Проверяем оформление обычной цены (серая и зачёркнутая)
        if (isGrayColor(mainPageRegularPriceColor) && mainPageRegularPriceTextDecoration.contains("line-through")) {
            System.out.println("Обычная цена на главной странице оформлена корректно.");
        } else {
            System.out.println("Ошибка: Обычная цена на главной странице оформлена некорректно.");
        }

        if (isGrayColor(productPageRegularPriceColor) && productPageRegularPriceTextDecoration.contains("line-through")) {
            System.out.println("Обычная цена на странице товара оформлена корректно.");
        } else {
            System.out.println("Ошибка: Обычная цена на странице товара оформлена некорректно.");
        }

        // 7. Проверяем оформление акционной цены (красная и жирная)
        if (isRedColor(mainPageCampaignPriceColor) && isBoldFont(mainPageCampaignPriceFontWeight)) {
            System.out.println("Акционная цена на главной странице оформлена корректно.");
        } else {
            System.out.println("Ошибка: Акционная цена на главной странице оформлена некорректно.");
        }

        if (isRedColor(productPageCampaignPriceColor) && isBoldFont(productPageCampaignPriceFontWeight)) {
            System.out.println("Акционная цена на странице товара оформлена корректно.");
        } else {
            System.out.println("Ошибка: Акционная цена на странице товара оформлена некорректно.");
        }

        // 8. Проверяем, что акционная цена крупнее обычной
        if (isCampaignPriceLarger(mainPageRegularPriceElement, mainPageCampaignPriceElement)) {
            System.out.println("Акционная цена на главной странице крупнее обычной.");
        } else {
            System.out.println("Ошибка: Акционная цена на главной странице не крупнее обычной.");
        }

        if (isCampaignPriceLarger(productPageRegularPriceElement, productPageCampaignPriceElement)) {
            System.out.println("Акционная цена на странице товара крупнее обычной.");
        } else {
            System.out.println("Ошибка: Акционная цена на странице товара не крупнее обычной.");
        }

        driver.quit();
    }

    // Проверка, является ли цвет серым
    private static boolean isGrayColor(String color) {
        String[] rgba = color.replace("rgba(", "").replace(")", "").split(", ");
        return rgba[0].equals(rgba[1]) && rgba[1].equals(rgba[2]);
    }

    // Проверка, является ли цвет красным
    private static boolean isRedColor(String color) {
        String[] rgba = color.replace("rgba(", "").replace(")", "").split(", ");
        return rgba[1].equals("0") && rgba[2].equals("0");
    }

    // Проверка, является ли шрифт жирным
    private static boolean isBoldFont(String fontWeight) {
        return Integer.parseInt(fontWeight) >= 700;
    }

    // Проверка, что акционная цена крупнее обычной
    private static boolean isCampaignPriceLarger(WebElement regularPrice, WebElement campaignPrice) {
        try {
            // Проверяем, существует ли элемент и доступно ли CSS-свойство font-size
            if (regularPrice == null || campaignPrice == null) {
                System.out.println("Один из элементов не найден.");
                return false;
            }
    
            // Получаем значение font-size для обоих элементов
            String regularPriceSize = regularPrice.getCssValue("font-size");
            String campaignPriceSize = campaignPrice.getCssValue("font-size");
    
            // Выводим значения в консоль для отладки
            System.out.println("Размер обычной цены: " + regularPriceSize);
            System.out.println("Размер акционной цены: " + campaignPriceSize);
    
            // Если значение font-size не найдено, прерываем проверку
            if (regularPriceSize.isEmpty() || campaignPriceSize.isEmpty()) {
                System.out.println("Не удалось получить значения font-size.");
                return false;
            }
    
            // Убираем 'px' и конвертируем в числа
            regularPriceSize = regularPriceSize.replace("px", "");
            campaignPriceSize = campaignPriceSize.replace("px", "");
    
            return Float.parseFloat(campaignPriceSize) > Float.parseFloat(regularPriceSize);
        } catch (Exception e) {
            // Ловим исключения и выводим их для отладки
            System.out.println("Ошибка при проверке размера цен: " + e.getMessage());
            return false;
        }
        // String regularPriceSize = regularPrice.getCssValue("font-size").replace("px", "");
        // String campaignPriceSize = campaignPrice.getCssValue("font-size").replace("px", "");
        // return Float.parseFloat(campaignPriceSize) > Float.parseFloat(regularPriceSize);
    }
}