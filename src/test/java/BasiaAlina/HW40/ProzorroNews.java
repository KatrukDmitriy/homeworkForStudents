package BasiaAlina.HW40;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProzorroNews {
        public static void main(String[] args) {
            WebDriver driver = new ChromeDriver();
            Dimension dimension = new Dimension(1100, 1020);
            driver.manage().window().setSize(dimension);
            Actions action = new Actions(driver);
            driver.get("https://prozorro.gov.ua/tender/search");
            WebElement news = driver.findElement(By.xpath("//ul[@class='inline-layout block-center']/li/a[@href='/news']"));
            news.click();
            action.sendKeys(Keys.END).build().perform();
            WebElement page5 = driver.findElement(By.xpath("//*[@class='paginate__btn' and contains(text(),'5')]"));
            page5.sendKeys(Keys.RETURN);
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driverWait.until(ExpectedConditions.textToBe(By.cssSelector("#app > div.bg-color-gray-600 > section > div.row.news__row > div.news__col--left > div > ul.paginate.mt-13 > li:nth-child(7) > button"), "6"));
            action.sendKeys(Keys.END).build().perform();
            WebElement page5Active = driver.findElement(By.xpath("//*[@class='paginate__btn active' and contains(text(),'5')]"));
            System.out.println("Page 5 is active: " + page5Active.isDisplayed());
            driver.quit();
        }
    }

