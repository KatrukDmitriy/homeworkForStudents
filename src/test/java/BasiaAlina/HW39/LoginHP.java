package BasiaAlina.HW39;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginHP {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://proton.me/");
        WebElement signIn = driver.findElement(By.xpath("//*[@target='_self']"));
        signIn.click();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#username")));
        WebElement username = driver.findElement(By.cssSelector("#username"));
        username.sendKeys("alinabazia@proton.me");
        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("Test2766_Password");
        password.submit();
    }
}
