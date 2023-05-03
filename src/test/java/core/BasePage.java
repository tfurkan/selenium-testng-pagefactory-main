package core;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@Log4j
public class BasePage extends PageGenerator{
    public BasePage(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    protected static utilities.Configuration configuration = new utilities.Configuration();

    public void navigateToURL(String url){
        driver.get(url);
        waitForLoad();
        Assert.assertNotNull("Web page could not opened",driver.getTitle());
        Assert.assertEquals("Web page could not opened",driver.getTitle(),configuration.getProperty("PAGE_TITLE"));
        log.info("Web page is opened");
    }
    public String getUrlContext(){
        String[] url = driver.getCurrentUrl().split("(?<=.com/)");
        return url[1];
    }
    public String getUrlContext(String base){
        String[] url = driver.getCurrentUrl().split("(?<="+base+")");
        return url[0];
    }

    public void waitForElementToBeClieable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForElementToBeVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToBeInVisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void clickTo(WebElement clickElement) {
        waitForElementToBeClieable(clickElement);
        waitForElementToBeVisible(clickElement);
        clickElement.click();
    }
    public void write(WebElement element, String text){
        waitForElementToBeClieable(element);
        element.sendKeys(text);
    }
    public String read(WebElement element){
        waitForElementToBeVisible(element);
        return element.getText();
    }
    public void waitForLoad(){
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        wait.until(pageLoadCondition);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void moveToElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void scrollWithValue(int x, int y){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy("+x+","+y+")");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickElementAfterScroll(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(false);", element);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickTo(element);
    }


}
