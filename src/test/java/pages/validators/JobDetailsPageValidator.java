package pages.validators;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.page.JobDetailsPage;
import org.openqa.selenium.WebElement;

@Log4j
public class JobDetailsPageValidator extends JobDetailsPage {
    public JobDetailsPageValidator(WebDriver driver) {
        super(driver);
    }

    public boolean checkAllOpenPositionsPage(){
        waitForLoad();
        boolean isPageOpened;
        try {
            isPageOpened = LOCATION_FILTER.isEnabled() && DEPARTMENT_FILTER.isEnabled();
            return isPageOpened;
        }
        catch (NoSuchElementException e){
            log.warn("Elements does not exist", e);
            return false;
        }
    }
    public void checkJobsAreDisplayed(){
        System.out.println(JOB_LIST_BLOCK.getText());
        if(JOB_LIST_BLOCK.getText().equals("No positions available."))
            log.info("No jobs found matching the selected parameters");
        else {
            for (WebElement element : JOB_LIST) {
                Assert.assertTrue(element + "Element is not displayed", element.isEnabled());
            }
        }
    }
    public void checkJobResults() {
        waitForLoad();
        scrollWithValue(0,750);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean isSuccess = false;
        for(int i = 0; i < JOB_LIST.size(); i++){
            isSuccess = (JOB_LIST.get(i).findElements(POSITIONS).get(i).getText().contains("QA") || JOB_LIST.get(i).findElements(POSITIONS).get(i).getText().contains("Quality Assurance")) &&
                    (JOB_LIST.get(i).findElements(DEPARTMENTS).get(i).getText().contains("Quality Assurance")) &&
                    (JOB_LIST.get(i).findElements(LOCATIONS).get(i).getText().contains("Istanbul, Turkey")) &&
                    (JOB_LIST.get(i).findElements(APPLY_BUTTONS).get(i).isEnabled());
        }
        Assert.assertTrue("Job reason failed",isSuccess);
    }
}
