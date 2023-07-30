package dou;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DouTest {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
//        ClientConfig config = ClientConfig.defaultConfig().connectionTimeout(Duration.ofMinutes(20))
//                .readTimeout(Duration.ofMinutes(20)); // I change this 3 minute(Default) to 20 minutes.
//        DesiredCapabilities caps = new DesiredCapabilities("chrome", "", Platform.ANY);

        System.setProperty("otel.traces.exporter", "jaeger");
        System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
        System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

        ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        driver = RemoteWebDriver.builder().oneOf(caps).address("http://localhost:5555/wd/hub").config(config).build(); // now you can use this remoteWebDriver.

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)).pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://dou.ua/");
    }


    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {"EPAM"}, {"Luxoft"}
        };
    }


    @Test(groups = "Positive_Test")
    public void searchInputAvailability() {

        WebElement input = driver.findElement(By.cssSelector("#txtGlobalSearch"));

        assertTrue(input.isEnabled(), "Input is disabled");

    }


    @Test(groups = "Positive_Test", dataProvider = "test-data")
    public void searchResultsAvailability(String keyWord) {
        WebElement input = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        input.sendKeys(keyWord);
        Reporter.log("Keyword entered is : " + keyWord);

        input.sendKeys(Keys.ENTER);


        WebElement searchResultBox = driver.findElement(By.cssSelector(".gsc-results-wrapper-nooverlay.gsc-results-wrapper-visible"));

        assertTrue(searchResultBox.isDisplayed(), "No search results found");

    }

    @Test(groups = "Positive_Test")
    public void searchWithValidKeyword() {

        WebElement input = driver.findElement(By.cssSelector("#txtGlobalSearch"));

        String searchQuery = "epam";

        input.sendKeys(searchQuery);
        input.sendKeys(Keys.ENTER);

        String searchResult = driver.findElement(By.xpath("//b[contains(text(), 'EPAM')]")).getText().toUpperCase();

        assertTrue(searchResult.contains(searchQuery.toUpperCase()), "Search result is not relevant.");
    }


    @Test(groups = "Negative_Test")
    public void searchWithInvalidKeyword() {
        WebElement input = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        input.sendKeys("vdfghj");
        input.sendKeys(Keys.ENTER);


        WebElement noResultsMessage = driver.findElement(By.xpath("//*[text()='Результатів немає']"));

        assertTrue(noResultsMessage.isDisplayed(), "Error message is unavailable");

    }


    @Test(groups = "Negative_Test")
    public void searchWithWrongKeyboardLayout() {
        WebElement input = driver.findElement(By.cssSelector("#txtGlobalSearch"));

        String searchQuery = "узфь";

        input.sendKeys(searchQuery);
        input.sendKeys(Keys.ENTER);


        String searchResult = driver.findElement(By.xpath("//b[contains(text(), 'EPAM')]")).getText().toUpperCase();

        String expectedSearchResult = "epam";

        assertTrue(searchResult.contains(expectedSearchResult.toUpperCase()), "Search result is not relevant.");

    }

    @Test(groups = "Negative_Test")
    public void searchWithEmptyInput() {
        WebElement input = driver.findElement(By.cssSelector("#txtGlobalSearch"));
        input.sendKeys(Keys.ENTER);


        WebElement emptySearchResultBox = driver.findElement(By.cssSelector(".gsc-resultsbox-invisible"));

        assertFalse(emptySearchResultBox.isDisplayed(), "Search result is not empty.");
    }


    @AfterMethod
    public void driverQuit() {
        driver.quit();

    }


}

