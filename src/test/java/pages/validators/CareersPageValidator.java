package pages.validators;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.page.CareersPage;

@Log4j
public class CareersPageValidator extends CareersPage {

    public CareersPageValidator(WebDriver driver) {
        super(driver);
    }

    public void checkCareersPage(){
        waitForLoad();
        try {
            Assert.assertTrue("Find your dream job button could not enabled" ,CAREERSPAGE_FIND_JOB.isEnabled());
            Assert.assertTrue("Location block could not enabled",CAREERSPAGE_LOCATIONS_BLOCK.isEnabled());
            Assert.assertTrue("Team block could not enabled",CAREERSPAGE_TEAMS_BLOCK.isEnabled());
            Assert.assertTrue("Life block could not enabled",CAREERSPAGE_LIFE_BLOCK.isEnabled());
            log.info("Careers page is opened");
        }
        catch (NoSuchElementException e){
            log.error("Careers page elements could not find.",e);
            throw e;
        }
    }
}
