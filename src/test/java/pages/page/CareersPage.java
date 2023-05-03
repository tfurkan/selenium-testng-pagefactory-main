package pages.page;

import core.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Log4j
public class CareersPage extends BasePage {
    public CareersPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@class='btn btn-info rounded mr-0 mr-md-4 py-3']")
    public WebElement CAREERSPAGE_FIND_JOB;
    @FindBy(id = "location-slider")
    public WebElement CAREERSPAGE_LOCATIONS_BLOCK;
    @FindBy(xpath = "//*[@class='col-12 d-flex flex-wrap p-0 career-load-more']")
    public WebElement CAREERSPAGE_TEAMS_BLOCK;
    @FindBy (xpath = "//*[@class='elementor-widget-container']")
    public WebElement CAREERSPAGE_LIFE_BLOCK;
    @FindBy(xpath = "//a[contains(text(),'See all teams')]")
    public WebElement SEE_ALL_TEAMS_BTN;
    @FindBy(xpath = "//*[@class='text-center mb-4 mb-xl-5']")
    public List<WebElement> TEAM_LIST;
    @FindBy(xpath = "//*[@class='btn btn-outline-secondary rounded text-medium mt-2 py-3 px-lg-5 w-100 w-md-50']")
    public WebElement SEE_ALL_JOBS;

    public void clickSeeAllTeams(){
        clickElementAfterScroll(SEE_ALL_TEAMS_BTN);
        log.info("Clicked See All Teams button");
    }
    public void clickSelectedJob(String job){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (WebElement webElement : TEAM_LIST) {
            waitForElementToBeClieable(webElement);
            if (webElement.getText().equals(job)){
                clickElementAfterScroll(webElement);
                log.info("Clicked selected job");
                break;
            }

        }
    }
    public void clickSeeAllJobs(){
        clickTo(SEE_ALL_JOBS);
        log.info("Clicked See All Jobs button");
    }
}
