package pages.validators;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.page.LeverApplicationPage;

@Log4j
public class LeverApplicationPageValidator extends LeverApplicationPage {
    public LeverApplicationPageValidator(WebDriver driver) {
        super(driver);
    }
    public void checkSelectedTrueJob(String jobTitle) {
        waitForLoad();
        try {
            Assert.assertEquals("job title did not match", jobTitle, JOB_TITLE.getText());
            Assert.assertTrue("The apply button on the top could not be displayed", APPLY_BUTTON.get(0).isEnabled());
            Assert.assertTrue("Job requirements could not be displayed", JOB_REQS.isEnabled());
        } catch (NoSuchElementException e) {
            log.error("Failed checkSelectedTrueJob",e);
            throw(e);
        }
    }
}
