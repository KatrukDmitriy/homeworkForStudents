package PetrovaAnastasia.LoginFormTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SendMailWithoutRecepient {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)).pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://accounts.google.com/AccountChooser/identifier?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AccountChooser");

        WebElement login = driver.findElement(By.cssSelector("#identifierId"));
        login.sendKeys("test.for.alevel@gmail.com");
        login.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("test-987");
        password.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.id("password"))));

        WebElement compose = driver.findElement(By.xpath("//*[text()='Compose']"));
        compose.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'agP aFw')]")));


        WebElement text = driver.findElement(By.xpath("//div[contains(@class, 'Am Al editable LW-avf tS-tW')]"));

        text.click();

        text.sendKeys("Hello!");

//        wait.until(new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                WebElement text = driver.findElement(By.xpath("//div[contains(@class, 'Am Al editable LW-avf tS-tW')]"));
//                return text != null && !text.isDisplayed();
//            }
//        });
//        З вейтером не прцює код, не можу знайти в ньому помилку

        WebElement send = driver.findElement(By.xpath("//div[contains(@class, 'T-I J-J5-Ji aoO v7 T-I-atl L3')]"));
        send.click();

        WebElement errorMessage = driver.findElement(By.xpath(" //div[@class='Kj-JD']"));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));



    }
}
