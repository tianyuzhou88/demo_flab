package org.webdriver.seleniumUI.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ElementAction extends TestBaseCase {

    public static String defaultTimeout = "30";
    public static ArrayList<Exception> noSuchElementExceptions = new ArrayList<Exception>();
    private Log log = new Log(this.getClass());

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date);
    }

    private String screenshotMessage(String nowDate) {
        return "&lt;a class=\"clickbox\" href=\"#url\"&gt;\n"
                + "&lt;img src=\"snapshot/"
                + nowDate
                + ".jpg\" height=\"100\" width=\"100\" alt=\"\" /&gt;\n"
                + "&lt;b class=\"lightbox\"&gt;\n"
                + "&lt;b class=\"light\"&gt;&lt;/b&gt;\n"
                + "&lt;b class=\"box\"&gt;\n"
                + "&lt;img src=\"snapshot/"
                + nowDate
                + ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;\n"
                + "&lt;/b&gt;\n"
                + "&lt;/b&gt;\n"
                + "&lt;/a&gt;\n"
                + "&lt;br class=\"clear\" /&gt;\n"
                + "&lt;a class=\"clickbox\" href=\"#url\"&gt;"
                + "&lt;b class=\"lightbox\"&gt;"
                + "&lt;b class=\"light\"&gt;&lt;/b&gt;"
                + "&lt;b class=\"box\"&gt;&lt;img src=\"snapshot/"
                + nowDate
                + ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;"
                + "&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;"
                + "&lt;/b&gt;"
                + "&lt;/b&gt;"
                + " &lt;/a&gt;\n&lt;/br&gt;"
                + "&lt;div id=\"close\"&gt;&lt;/div&gt;\n";
    }

    public void type(WebElement webElement, String value) {
        try {
            webElement.sendKeys(value);
            log.info("Type on the Input Field of ：" + webElement  + "with value:" + value + "]");
        } catch (NoSuchElementException e) {
            log.error("Cannot Find the WebElement，Fail to Input on the Field:" + webElement  + "with value:" + value + "]");
            e.printStackTrace();
        }
    }

    public void keyStroke(WebElement webElement, Keys value) {
        try {
            webElement.sendKeys(value);
            log.info("Key stroke on the Input Field of ：" + webElement  + " " + value.toString());
        } catch (NoSuchElementException e) {
            log.error("Cannot Find the WebElement，Fail to Input on the Field : " + webElement  + " " + value.toString());
            e.printStackTrace();
        }
    }

    public void clean_type(WebElement webElement, String value) {
        try {
            webElement.clear();
            webElement.sendKeys(value);
            log.info("Type on the Input Field of ：" + webElement  + "with value:" + value + "]");
        } catch (NoSuchElementException e) {
            log.error("Cannot Find the WebElement，Fail to Input on the Field:" + webElement  + "with value:" + value + "]");
            e.printStackTrace();
        }
    }

    public void click(WebElement webElement) {
        try {
            webElement.click();
            log.info("Click the WebElement ：" + webElement + "] Success！");
        } catch (NoSuchElementException e) {
            log.error("Cannot Find the WebElement，Fail to Click the WebElement:" + webElement + "]");
            e.printStackTrace();
            throw e;
        }
    }

    public void clear(WebElement webElement) {
        try {
            webElement.clear();
            log.info(" Clear Input Field:" + webElement + "]");
        } catch (Exception e) {
            log.error(" Clear Input Field Fail:" + webElement + "]");
            throw e;
        }
    }

    public String getText(WebElement webElement) {
        return webElement.getText();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void sleep(double inSecs) {
        try {
            Thread.sleep((long) inSecs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeJS(String js) {
        ((JavascriptExecutor) driver).executeScript(js);
        System.out.println("execute javascript ：" + js);
    }

    public boolean isElementsPresent(List<WebElement> webElements, int timeOut) throws InterruptedException {
        log.info(" Wait" + timeOut + " Determine if ：" + webElements + "existence");
        boolean isPresent = false;
        Thread.sleep(timeOut * 1000);
        if (webElements.size() != 0) {
            isPresent = true;
        }
        log.info("result are：" + isPresent);
        return isPresent;
    }

    public boolean DisplayElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void NotDisplayElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void refreshPage() {
        driver.navigate().refresh();
        action.sleep(5);
    }

    public void open(String url) {
        driver.navigate().to(url);
        action.sleep(1);
        log.info(" Open Browser，Open "+url+" Address!");
    }

    public void moveByOffset(int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().perform();
    }
}
