package ShahovaOksana.HW39;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WithoutRecepient {
    public static void main(String[] args) {

        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("https://proton.me/");
        WebElement signIn = driver.findElement(By.xpath("//*[@target='_self']"));
        signIn.click();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")));
        WebElement email = driver.findElement(By.xpath("//*[@id='username']"));
        email.sendKeys("OksanaShaOksana@proton.me");
        WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
        password.sendKeys("OksanaSha");
        password.submit();

        System.out.println("Успішний вхід до пошти!");

        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement composeButton = driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='sidebar:compose']")));
        composeButton.click();

        WebElement topicField = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-testid='composer:subject']")));
        topicField.sendKeys("Test");

        WebElement sendButton = driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-testid='composer:send-button']")));
        sendButton.click();

        System.out.println("Повідомлення не відправлено!");

        driver.quit();
    }
}
