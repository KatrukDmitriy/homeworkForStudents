package PetrovaAnastasia.LoginFormTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormTest {
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

        driver.quit();

    }
}
