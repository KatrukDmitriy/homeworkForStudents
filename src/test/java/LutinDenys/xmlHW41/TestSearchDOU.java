package LutinDenys.xmlHW41;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestSearchDOU {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dou.ua/search/?q=");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }
    @DataProvider(name = "unrealQueryDataProvider")
    public Object[][] unrealQueryDataProvider() {
        return new Object[][]{
                {"фхзівждлфхслфзфсзфдсфісдхфсххїїфівї"},
                {"dfgdgfdzzgzzdegdfgdzgdzgdgdgdgfdzgdzg"},
                {"@#(^$*@*$^@@)%(@%&@)%&@%^&@@#($@$)@(&"},
                {"123975720917509752-575123-590375325375"},
        };
    }
    @Test
    public void positiveSearch1() { //перевірка кирилиці та функцональності батону пошуку
        String searchQuery = "Україна";

        WebElement inputSearch = driver.findElement(By.cssSelector("#gs_tti50"));
        Actions enterQuery = new Actions(driver);
        enterQuery.sendKeys(inputSearch, searchQuery).perform();
        driver.findElement(By.className("gsc-search-button")).click();
        WebDriverWait waiterResult = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchResult = waiterResult.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gsc-result-info")));
        Assert.assertTrue(searchResult.isDisplayed(), "Немає жодного результату по запиту '" + searchQuery + "' при натисканні батону пошуку");
    }
    @Test
    public void positiveSearch2() { //перевірка регулярних виразів та функціональності натискання Enter
        String searchQuery = "site:https://dou.ua/ QA";

        WebElement inputSearch = driver.findElement(By.cssSelector("#gs_tti50"));
        Actions enterQuery = new Actions(driver);
        enterQuery.sendKeys(inputSearch, searchQuery).sendKeys(Keys.RETURN).perform();
        WebDriverWait waiterResult = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchResult = waiterResult.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gsc-result-info")));
        Assert.assertTrue(searchResult.isDisplayed(), "Немає жодного результату по запиту '" + searchQuery + "' при натисканні Enter");
    }
    @Test (dataProvider = "unrealQueryDataProvider")
    public void negativeSearch1(String searchQuery) { //перевірка відсутності результатів пошуку за нереальних запитів

        WebElement inputSearch = driver.findElement(By.cssSelector("#gs_tti50"));
//        inputSearch.clear();
        Actions enterQuery = new Actions(driver);
        enterQuery.sendKeys(inputSearch, searchQuery).sendKeys(Keys.RETURN).perform();
        WebDriverWait waiterResult = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchResult = waiterResult.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Результатів немає')]")));
        Assert.assertTrue(searchResult.isDisplayed(), "Є резултати пошуку за нереальних запитів");
    }
    @Test
    public void negativeSearch2() { //перевірка null пошуку
        String searchQuery = "";

        WebElement inputSearch = driver.findElement(By.cssSelector("#gs_tti50"));
        Actions enterQuery = new Actions(driver);
        enterQuery.sendKeys(inputSearch, searchQuery).sendKeys(Keys.RETURN).perform();

        WebDriverWait waiterResult = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExpectedCondition<Boolean> isWarningDisplayed = driver -> {
            WebElement warningMessage = driver.findElement(By.cssSelector(".warning-message-for-null"));
            return warningMessage.isDisplayed();
        };

        try {
            waiterResult.until(isWarningDisplayed);
        } catch (Exception ex) {
            Assert.fail("Попередження про 'пустий' пошук не з'являється");
        }
    }
    @Test
    public void negativeSearch3() { //перевірка вводу лише пробілов у пошук
        String searchQuery = "             ";

        WebElement inputSearch = driver.findElement(By.cssSelector("#gs_tti50"));
        Actions enterQuery = new Actions(driver);
        enterQuery.sendKeys(inputSearch, searchQuery).sendKeys(Keys.RETURN).perform();

        WebDriverWait waiterResult = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExpectedCondition<Boolean> isWarningDisplayed = driver -> {
            WebElement warningMessage = driver.findElement(By.cssSelector(".warning-message-for-spaces"));
            return warningMessage.isDisplayed();
        };

        try {
            waiterResult.until(isWarningDisplayed);
        } catch (Exception ex) {
            Assert.fail("Попередження про ввод у пошук лише пробілов не з'являється");
        }
    }
    @AfterMethod()
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

