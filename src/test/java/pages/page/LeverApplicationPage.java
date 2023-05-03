package pages.page;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeverApplicationPage extends BasePage {
    public LeverApplicationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@class='posting-headline']//h2")
    public WebElement JOB_TITLE;
    @FindBy(xpath = "//a[@class='postings-btn template-btn-submit shamrock']")
    public List<WebElement> APPLY_BUTTON;
    @FindBy(xpath = "//*[@class='posting-requirements plain-list']")
    public WebElement JOB_REQS;
}
