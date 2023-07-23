package ShahovaOksana.HW39;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestMailNP {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://proton.me/");
        WebElement signIn = driver.findElement(By.xpath("//*[@target='_self']"));
        signIn.click();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")));
        WebElement email = driver.findElement(By.xpath("//*[@id='username']"));
        email.sendKeys("OksanaShaOksana@proton.me");
        WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
        password.sendKeys("OksanaSha111");
        password.submit();

        System.out.println("Не вдалося ввійти до пошти.");

        driver.quit();
    }
}
