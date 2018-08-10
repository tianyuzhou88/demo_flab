package org.webdriver.seleniumUI.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FlabMainPage {

    @FindBy(xpath = "//ul[@id='menu-fidelitylabs']" +
            "//a[@href='https://www.fidelitylabs.com/about/']" +
            "[contains(text(),'About')]")
    public WebElement aboutBotton;

    @FindBy(xpath = "//ul[@id='menu-fidelitylabs']" +
            "//a[@href='https://www.fidelitylabs.com/work/']" +
            "[contains(text(),'Work')]")
    public WebElement workButton;


    @FindBy(xpath = "//h1[@class='hero-shot-title font-white']")
    public WebElement titleWord;

}
