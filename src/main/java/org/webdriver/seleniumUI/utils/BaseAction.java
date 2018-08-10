package org.webdriver.seleniumUI.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.webdriver.seleniumUI.utils.Locator.ByType;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class BaseAction extends TestBaseCase {

	protected HashMap<String,Locator>  locatorMap;
	public String path=null;
	public InputStream path_inputStream_1;
	public InputStream path_inputStream_2;
	Log log=new Log(this.getClass());

	public  void setXmlObjectPath(String path)
	{
		this.path=path;
	}
	public  void setXmlObjectPathForLocator(InputStream path_inputStream)
	{
		this.path_inputStream_1=path_inputStream;
	}
	public  void setXmlObjectPathForPageURL(InputStream path_inputStream)
	{
		this.path_inputStream_2=path_inputStream;
	}
	/**
	 * constructor， when create BasePage object，create with need initialization information and parameters
	 * @param driver
	 * @param path
	 * this.getClass().getCanonicalName() get page class path，or the pagename in XML
	 * @throws Exception
	 */
	public  BaseAction()
	{


	}
	public void getLocatorMap()
	{
		XmlReadUtil xmlReadUtil=new XmlReadUtil();
		try {
			if((path==null||path.isEmpty()))
			{locatorMap = xmlReadUtil.readXMLDocument(path_inputStream_1, this.getClass().getCanonicalName());}
			else {
				locatorMap = xmlReadUtil.readXMLDocument(path, this.getClass().getCanonicalName());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	static By getBy (ByType byType,Locator locator)
	{
		switch(byType)
		{
			case id:
				return By.id(locator.getElement());
			case cssSelector:
				return By.cssSelector(locator.getElement());
			case name:
				return By.name(locator.getElement());
			case xpath:
				return By.xpath(locator.getElement());
			case className:
				return By.className(locator.getElement());
			case tagName:
				return By.tagName(locator.getElement());
			case linkText:
				return By.linkText(locator.getElement());
			case partialLinkText:
				return By.partialLinkText(locator.getElement());
			default:
				return null;
		}


	}

	/**
	 * get element information for locator object library
	 * @param locatorName
	 * @return
	 * @throws IOException
	 */
	public  Locator getLocator(String locatorName)
	{
		Locator locator;
		locator=locatorMap.get(locatorName);
		if(locator==null)
		{
			log.error("CANNOT LOCATE "+locatorName+" WebElement");
		}
		return locator;

	}

	public String getPageURL()
	{
		String pageURL=null;
		try {
			if(path==null||path.isEmpty())
			{pageURL=XmlReadUtil.getXmlPageURL(path_inputStream_1, this.getClass().getCanonicalName());}
			else {
				pageURL=XmlReadUtil.getXmlPageURL(path, this.getClass().getCanonicalName());
			}
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		return pageURL;
	}

	/**
	 *  Open Browser
	 * @param url
	 */
	public void open(String url)
	{
		driver.navigate().to(url);
		log.info(" Open Browser，Open "+url+" Address!");

	}
	/***
	 * close browser
	 */
	public void close()
	{
		driver.close();
		log.info("Close Browser");
	}
	/**
	 * Quit Browser
	 */
	public void quit()
	{
		driver.quit();
		log.info("Quit Browser ");
	}
	/**
	 * Browser Forward
	 */
	public void forward()
	{
		driver.navigate().forward();
		log.info("Browser Forward");
	}
	/**
	 * Browser Backward
	 */
	public void back()
	{
		driver.navigate().back();
		log.info("Browser Backward");
	}
	/**
	 * refresh browser
	 */
	public void refresh()
	{
		driver.navigate().refresh();
		log.info("Browser Refresh");
	}
	public WebElement findElement( final Locator locator) throws IOException
	{
		/**
		 * wait some time before searching for the webElement
		 */
		Waitformax(Integer.valueOf(locator.getWaitSec()));
		WebElement webElement;
		webElement=getElement(locator);
		return webElement;


	}
	public void Waitformax(int t)
	{
		driver.manage().timeouts().implicitlyWait(t,TimeUnit.SECONDS);
	}
	/**
	 * find webElement through locator
	 * @param locator
	 * @return
	 * @throws NoSuchElementException
	 */
	public WebElement getElement(Locator locator)
	{
		/**
		 * locator.getElement(),get the locator information for object library
		 */
		//locator=getLocator(locatorMap.get(key));
		WebElement webElement;
		switch (locator.getBy())
		{
			case xpath :
				//log.info("find element By xpath");
				webElement=driver.findElement(By.xpath(locator.getElement()));
				/**
				 * write into logger if cannot find the webElement
				 */
				break;
			case id:
				//log.info("find element By xpath");
				webElement=driver.findElement(By.id(locator.getElement()));
				break;
			case cssSelector:
				//log.info("find element By cssSelector");
				webElement=driver.findElement(By.cssSelector(locator.getElement()));
				break;
			case name:
				//log.info("find element By name");
				webElement=driver.findElement(By.name(locator.getElement()));
				break;
			case className:
				//log.info("find element By className");
				webElement=driver.findElement(By.className(locator.getElement()));
				break;
			case linkText:
				//log.info("find element By linkText");
				webElement=driver.findElement(By.linkText(locator.getElement()));
				break;
			case partialLinkText:
				//log.info("find element By partialLinkText");
				webElement=driver.findElement(By.partialLinkText(locator.getElement()));
				break;
			case tagName:
				//log.info("find element By tagName");
				webElement=driver.findElement(By.partialLinkText(locator.getElement()));
				break;
			default :
				//log.info("find element By xpath");
				webElement=driver.findElement(By.xpath(locator.getElement()));
				break;

		}
		return webElement;
	}

	public void selectWindow(String windowName) {
		WebDriver popup = null;
		Set<String> windowSet = driver.getWindowHandles();
		for (String window : windowSet) {
			popup = driver.switchTo().window(window);
			// This is specific to Directory tasks, because they only have a sub
			// title and not a real title like everything else.
			if (popup.getTitle().toLowerCase()
					.contains(windowName.toLowerCase())) {
				break;
			}
		}
	}


}
