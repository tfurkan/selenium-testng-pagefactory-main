package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import utilities.OptionsManager;

import java.net.MalformedURLException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public SoftAssert softAssert;
    public PageGenerator page;
    protected static utilities.Configuration configuration = new utilities.Configuration();

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) throws MalformedURLException {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(OptionsManager.getChromeOptions());
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            softAssert = new SoftAssert();
            page = new PageGenerator(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            softAssert = new SoftAssert();
            page = new PageGenerator(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }


    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
