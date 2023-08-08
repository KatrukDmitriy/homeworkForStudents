package LutinDenys.waitersHW39;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class sendMail {
    //    private WebDriver driver;
//    @BeforeTest
//    public void beforeSteUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://account.proton.me/login");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://account.proton.me/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement inputEmail = driver.findElement(By.cssSelector("#username"));
        inputEmail.sendKeys("jetyyya@proton.me");
        WebElement inputPassword = driver.findElement(By.cssSelector("#password"));
        inputPassword.sendKeys("YashimotO18");
        WebElement clickButtonLogin = driver.findElement(By.cssSelector("button.button:nth-child(6)"));
        clickButtonLogin.click();
        WebDriverWait waiterTitle = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isInboxTitleDisplayed = waiterTitle.until(ExpectedConditions.titleContains("Inbox"));
        Assert.assertTrue(isInboxTitleDisplayed, "Логін не відбувся");
        driver.close();
        driver.quit();
    }
    @Test
    public void testSendMail() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://account.proton.me/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement inputEmail = driver.findElement(By.cssSelector("#username"));
        inputEmail.sendKeys("jetyyya@proton.me");
        WebElement inputPassword = driver.findElement(By.cssSelector("#password"));
        inputPassword.sendKeys("YashimotO18");
        WebElement clickButtonLogin = driver.findElement(By.cssSelector("button.button:nth-child(6)"));
        clickButtonLogin.click();
        WebDriverWait waiterTitle = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiterTitle.until(ExpectedConditions.titleContains("Inbox"));
        WebElement newEmailButton = driver.findElement(By.cssSelector(".button-large"));
        newEmailButton.click();
        WebElement inputTo = driver.findElement(By.cssSelector(".composer-editor-collapsed"));
        Actions enterAddress = new Actions(driver);
        enterAddress.sendKeys(inputTo, "jetyyya@proton.me").perform();
        WebElement inputSubject = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div/div/div/div[3]/div/div/input"));
        inputSubject.sendKeys("Test");
        WebElement sendEmailButton = driver.findElement(By.cssSelector(".button.button-group-item:nth-child(1)"));
        sendEmailButton.click();
        WebDriverWait waitPageLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageSentPopUp = waitPageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifications-container")));
        Assert.assertTrue(messageSentPopUp.isDisplayed(), "Вспливаюче повідомлення про відправку листа не відображається");
        driver.close();
        driver.quit();
    }

    @Test
    public void testLoginNegative() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://account.proton.me/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement inputEmail2 = driver.findElement(By.cssSelector("#username"));
        inputEmail2.sendKeys("jetyyya@proton.me");
        WebElement inputPassword2 = driver.findElement(By.cssSelector("#password"));
        inputPassword2.sendKeys("");
        WebElement clickButtonLogin = driver.findElement(By.cssSelector("button.button:nth-child(6)"));
        clickButtonLogin.click();
        WebElement passwordWarningMessage = driver.findElement(By.cssSelector("#id-4"));
        Assert.assertTrue(passwordWarningMessage.isDisplayed(), "Попередження про обов'язковий ввод паролю не відображається");
        driver.close();
        driver.quit();

    }
    @Test
    public void testSendMailWithoutAddress() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://account.proton.me/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement inputEmail = driver.findElement(By.cssSelector("#username"));
        inputEmail.sendKeys("jetyyya@proton.me");
        WebElement inputPassword = driver.findElement(By.cssSelector("#password"));
        inputPassword.sendKeys("YashimotO18");
        WebElement clickButtonLogin = driver.findElement(By.cssSelector("button.button:nth-child(6)"));
        clickButtonLogin.click();
        WebDriverWait waiterTitle = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiterTitle.until(ExpectedConditions.titleContains("Inbox"));
        WebElement newEmailButton = driver.findElement(By.cssSelector(".button-large"));
        newEmailButton.click();
        WebElement inputTo = driver.findElement(By.cssSelector("body > div.app-root > div:nth-child(5) > div > div > div > div > div > div.flex.flex-column.flex-nowrap.flex-align-items-start.mt-0 > div > div"));
        Actions enterAddress = new Actions(driver);
        enterAddress.sendKeys(inputTo, "").perform();
        WebElement inputSubject = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div/div/div/div[3]/div/div/input"));
        inputSubject.sendKeys("Test");
        WebElement sendEmailButton = driver.findElement(By.cssSelector(".button.button-group-item:nth-child(1)"));
        sendEmailButton.click();
        WebDriverWait modalTitle = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageSentPopUp = modalTitle.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inner-modal-title.outline-none")));
        Assert.assertTrue(messageSentPopUp.isDisplayed(), "Вспливаюче повідомлення про відправку листа не відображається");
        driver.close();
        driver.quit();
    }
//    @AfterTest
//    public void testDown() {
//        if (driver != null) {
//            driver.close();
//        }
//    }
}
