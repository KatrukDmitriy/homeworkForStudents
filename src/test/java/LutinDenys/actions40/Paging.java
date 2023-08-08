package LutinDenys.actions40;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;


public class Paging {

    private WebDriver driver;

    @BeforeTest
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://prozorro.gov.ua/tender/search/");
    }

    @Test
    public void testProzorroNews() {

        driver.findElement(By.xpath("//a[@href='/news']")).click();

        Actions scrollToEnd = new Actions(driver);
        scrollToEnd.sendKeys(Keys.END).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement page5Button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(normalize-space(.), '5')]")));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", page5Button);

        scrollToEnd.sendKeys(Keys.END).build().perform();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement page5ActiveButton = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='paginate__btn active' and normalize-space(text())='5']")));

        Assert.assertTrue(page5ActiveButton.isDisplayed(), "5-та вкладка не відображається як вибрана");

    }

    @AfterTest
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

