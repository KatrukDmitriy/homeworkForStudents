package BasiaAlina.HW39;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SendMailNegativePath {
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

        WebDriverWait driverWaitMail = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWaitMail.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button.button-large.button-solid-norm.w100.no-mobile")));
        WebElement newMessage = driver.findElement(By.cssSelector(".button.button-large.button-solid-norm.w100.no-mobile"));
        newMessage.click();
        WebDriverWait driverWaitMessage = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWaitMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='composer:subject']")));
        WebElement subjectField = driver.findElement(By.xpath("//*[@data-testid='composer:subject']"));
        subjectField.sendKeys("Test");
        WebElement sendButton = driver.findElement(By.cssSelector(".button.button-group-item.button-solid-norm.composer-send-button"));
        sendButton.click();

        WebDriverWait driverWaitError = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWaitError.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Recipient missing')]")));

        WebElement recipientMissing = driver.findElement(By.xpath("//*[contains(text(), 'Recipient missing')]"));
        Boolean elementIsVisible = recipientMissing.isDisplayed();
        if (elementIsVisible) {
            System.out.println("'Recipient missing. Please add at least one recipient.' error is displayed");
        } else {
            System.out.println("'Recipient missing. Please add at least one recipient.' error is not displayed");
        }
        driver.quit();
    }
}
