package pages.page;

import core.BasePage;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Log4j
public class JobDetailsPage extends BasePage {
    public JobDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "select2-filter-by-location-container")
    public WebElement LOCATION_FILTER;
    @FindBy(id = "select2-filter-by-department-container")
    public WebElement DEPARTMENT_FILTER;
    @FindBy(xpath = "//*[@class='select2-results__option select2-results__option--highlighted']")
    public WebElement HIGHLIGHTED_FILTER;
    @FindBy(xpath = "//*[@class='select2-results__option']")
    public List<WebElement> LOCATION_AND_DEPARTMENT_LIST;
    @FindBy(xpath = "//*[@class='position-list-item-wrapper bg-light']")
    public List<WebElement> JOB_LIST;
    @FindBy(id = "jobs-list")
    public WebElement JOB_LIST_BLOCK;
    public By POSITIONS = By.xpath("//*[@class='position-title font-weight-bold']");
    public By DEPARTMENTS = By.xpath("//*[@class='position-department text-large font-weight-600 text-primary']");
    public By LOCATIONS = By.xpath("//*[@class='position-location text-large']");
    public By APPLY_BUTTONS = By.xpath("//*[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']");


    public void selectLocation(String location) {
        waitForLoad();
        clickTo(LOCATION_FILTER);
        if (HIGHLIGHTED_FILTER.getText().equals(location)) {
            clickTo(HIGHLIGHTED_FILTER);
            log.info("Location selected from filter");
        }
        else {
            for (WebElement webElement : LOCATION_AND_DEPARTMENT_LIST) {
                waitForElementToBeClieable(webElement);
                if (webElement.getText().equals(location)) {
                    clickTo(webElement);
                    log.info("Location selected from filter");
                    break;
                }
            }
        }

    }

    public void selectDepartment(String department) {
        clickTo(DEPARTMENT_FILTER);
        if (HIGHLIGHTED_FILTER.getText().equals(department)) {
            clickTo(HIGHLIGHTED_FILTER);
            log.info("Department selected from filter");
        }
        else {
            for (WebElement webElement : LOCATION_AND_DEPARTMENT_LIST) {
                waitForElementToBeClieable(webElement);
                if (webElement.getText().equals(department)) {
                    clickTo(webElement);
                    log.info("Department selected from filter");
                    break;
                }
            }
        }


    }
    public String clickRandomJob() {
        String title;
        try {
            Random r = new Random();
            int randomValue = r.nextInt(JOB_LIST.size());
            moveToElement(JOB_LIST.get(randomValue));
            title = read(JOB_LIST.get(randomValue).findElements(POSITIONS).get(randomValue));
            clickTo(JOB_LIST.get(randomValue).findElements(APPLY_BUTTONS).get(randomValue));
            log.info("Clicked Apply Now");
        } catch (Exception e) {
            log.error("Failed to click Apply Now", e);
            throw (e);
        }
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
            }
        }
        return title;
    }
}
