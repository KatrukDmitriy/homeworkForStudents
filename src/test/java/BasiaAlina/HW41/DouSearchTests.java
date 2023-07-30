package BasiaAlina.HW41;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.time.Duration;
import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;
public class DouSearchTests {
    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void openSite() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)).pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://dou.ua/");
    }

    @DataProvider(name = "searchData")
    public static Object[][] testData() {
        return new Object[][]{
                {"Ciklum"}, {"Plarium"}
        };
    }

    @Test(groups = "PositiveTest", dataProvider = "searchData")
    public void searchResultsAvailable(String searchQuery) {
        WebElement searchInputField = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        searchInputField.sendKeys(searchQuery);
        searchInputField.sendKeys(Keys.ENTER);
        WebElement searchResult = driver.findElement(By.cssSelector(".gsc-results-wrapper-nooverlay.gsc-results-wrapper-visible"));
        assertTrue(searchResult.isDisplayed(), "No search results found");
    }

    @Test(groups = "PositiveTest")
    public void searchWithValidQuery() {
        WebElement searchInputField = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        String searchQuery = "QA";
        searchInputField.sendKeys(searchQuery);
        searchInputField.sendKeys(Keys.ENTER);
        WebElement searchResult = driver.findElement(By.xpath("//b[contains(text(), 'QA')]"));
        assertTrue(searchResult.isDisplayed(), "Search result is wrong");
    }

    @Test(groups = "NegativeTest")
    public void SearchWithEmptySearchBar() {
        WebElement searchInputField = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        searchInputField.sendKeys(Keys.ENTER);
        WebElement searchResult = driver.findElement(By.cssSelector(".gsc-resultsbox-invisible"));
        assertFalse(searchResult.isDisplayed(), "Search result is not empty");
    }

    @Test(groups = "NegativeTest")
    public void wrongKeyboardLayoutSearch() {
        WebElement searchInputField = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        String searchQuery = "здфкшгь";
        searchInputField.sendKeys(searchQuery);
        searchInputField.sendKeys(Keys.ENTER);
        WebElement suggestedSearchResult = driver.findElement((By.xpath("//b/i[contains(text(), 'plarium')]")));
        assertTrue(suggestedSearchResult.isDisplayed(), "Search result is wrong");
    }

    @Test(groups = "NegativeTest")
    public void queryWithWrongWordsOrder() {
        WebElement searchInputField = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        String searchQuery = "забезпечення програмне";
        searchInputField.sendKeys(searchQuery);
        searchInputField.sendKeys(Keys.ENTER);
        WebElement searchResult = driver.findElement(By.xpath("//b[contains(text(), 'програмне забезпечення')]"));
        assertTrue(searchResult.isDisplayed(), "Search result is wrong");
    }

    @AfterMethod (alwaysRun = true)
    public void driverQuit() {
        driver.quit();
    }
}
