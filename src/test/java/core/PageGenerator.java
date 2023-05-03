package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageGenerator  {

    public WebDriver driver;
    public WebDriverWait wait;
    public PageGenerator (WebDriver driver){
        this.driver = driver;
    }
    public <TPage extends BasePage> TPage GetInstance (Class<TPage> pageClass) {
        try {
            return PageFactory.initElements(driver, pageClass);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
