import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.webdriver.seleniumUI.action.FlabMainPageAction;
import org.webdriver.seleniumUI.utils.TestBaseCase;

import java.io.IOException;


public class UserManagement extends TestBaseCase {


    private String password = null;
    private String UserNameId = null;
    private  FlabMainPageAction flabMainPageAction;


    @BeforeTest
    @Parameters({"BaseUrl",})
    public void initialization(String BaseUrl) throws IOException {
        flabMainPageAction = new FlabMainPageAction();
        flabMainPageAction.navigateToMainPage(BaseUrl);
    }

    @Test
    @Parameters({"Message",})
    public void NavigatetoAbout(String Message){
        Assert.assertTrue(flabMainPageAction.navigateToAboutSection(Message));
    }
}
