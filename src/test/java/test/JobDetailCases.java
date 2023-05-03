package test;

import core.BasePage;
import core.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.page.CareersPage;
import pages.page.HomePage;
import pages.page.JobDetailsPage;
import pages.validators.CareersPageValidator;
import pages.validators.JobDetailsPageValidator;
import pages.validators.LeverApplicationPageValidator;


public class JobDetailCases extends BaseTest {

    @Parameters({"job","location"})
    @Test(priority = 0, successPercentage = 100, invocationCount = 1)
    public void checkJobDetails(String job, String location){
        page.GetInstance(HomePage.class).navigateToURL(configuration.getProperty("URL"));
        softAssert.assertEquals(page.GetInstance(BasePage.class).getCurrentUrl(), configuration.getProperty("URL"),"Base URL and Current URL did not match.");
        page.GetInstance(HomePage.class).acceptCookies();
        page.GetInstance(HomePage.class).selectMoreMenu();
        page.GetInstance(HomePage.class).clickCareers();
        softAssert.assertEquals(page.GetInstance(BasePage.class).getUrlContext(), "careers/", "Careers page URL could not verify");
        page.GetInstance(CareersPageValidator.class).checkCareersPage();
        page.GetInstance(CareersPage.class).clickSeeAllTeams();
        page.GetInstance(CareersPage.class).clickSelectedJob(job);
        page.GetInstance(CareersPage.class).clickSeeAllJobs();
        softAssert.assertTrue(page.GetInstance(JobDetailsPageValidator.class).checkAllOpenPositionsPage());
        page.GetInstance(JobDetailsPage.class).selectLocation(location);
        page.GetInstance(JobDetailsPage.class).selectDepartment(job);
        page.GetInstance(JobDetailsPageValidator.class).checkJobsAreDisplayed();
        page.GetInstance(JobDetailsPageValidator.class).checkJobResults();
        String jobTitle = page.GetInstance(JobDetailsPage.class).clickRandomJob();
        page.GetInstance(LeverApplicationPageValidator.class).checkSelectedTrueJob(jobTitle);
        softAssert.assertEquals(page.GetInstance(BasePage.class).getUrlContext(".co/"),"https://jobs.lever.co/", "Lever Application Page URL could not verify");
        softAssert.assertAll();
    }

}