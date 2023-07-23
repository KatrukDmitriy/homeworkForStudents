package ShakhovaOksana.HW40;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testProzorro {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        Actions action = new Actions(driver);

        driver.get("https://prozorro.gov.ua/tender/search");
        WebElement newsTab = driver.findElement(By.xpath("/html/body/nav/div/ul/li[1]/a"));
        newsTab.click();

        action.sendKeys(Keys.END).build().perform();

        WebElement page5button = driver.findElement(By.xpath("//*[@id='app']/div[2]/section/div[2]/div[1]/div/ul[2]/li[7]/button"));
        page5button.sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#app > div.bg-color-gray-600 > section > div.row.news__row > div.news__col--left > div > ul.paginate.mt-13 > li:nth-child(7) > button"), "6"));

        action.sendKeys(Keys.END).build().perform();

        WebElement page5buttonActive = driver.findElement(By.xpath("//*[@id='app']/div[2]/section/div[2]/div[1]/div/ul[2]/li[6]/button"));

        System.out.println("Сторінка 5 відображається: " + page5buttonActive.isDisplayed());

        driver.quit();
    }
}