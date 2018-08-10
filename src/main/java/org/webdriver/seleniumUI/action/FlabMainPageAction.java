package org.webdriver.seleniumUI.action;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.webdriver.seleniumUI.pageObject.FlabMainPage;
import org.webdriver.seleniumUI.utils.Assertion;
import org.webdriver.seleniumUI.utils.TestBaseCase;

public class FlabMainPageAction extends TestBaseCase {
    private FlabMainPage flabMainPage = PageFactory.initElements(driver, FlabMainPage.class);

    public void navigateToMainPage(String URL) {
        driver.get(URL);
        Assertion.VerifyURL("https://www.fidelitylabs.com/","Verifying URL");
        Assertion.VerifyError();
    }

    public Boolean navigateToAboutSection(String expectedMessage){
        action.sleep(1);
        action.click(flabMainPage.aboutBotton);
        action.DisplayElement(flabMainPage.titleWord);
        return expectedMessage.equalsIgnoreCase(flabMainPage.titleWord.getText());

    }
}
