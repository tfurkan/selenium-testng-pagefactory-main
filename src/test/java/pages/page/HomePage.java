package pages.page;

import core.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Log4j
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "wt-cli-accept-all-btn")
    public WebElement ACCEPT_COOKIES;
    @FindBy(xpath = "//*[@class='nav-link dropdown-toggle']")
    public List<WebElement> HOMEPAGE_DROPDOWN_LIST;
    @FindBy(xpath = "//*[contains(text(),'Careers')]")
    public WebElement HOMEPAGE_MORE_CAREERS;

    public void acceptCookies(){
        try {
            clickTo(ACCEPT_COOKIES);
            log.info("Cookies accepted");
        }
        catch (Exception e){
            log.warn("Cookies could not accepted", e);

        }
    }

    public void selectMoreMenu(){
        scrollWithValue(0,500);
        clickTo(HOMEPAGE_DROPDOWN_LIST.get(3));
        log.info("Clicked More menu");
    }
    public void clickCareers(){
        clickTo(HOMEPAGE_MORE_CAREERS);
        log.info("Clicked Careers");
    }

}
