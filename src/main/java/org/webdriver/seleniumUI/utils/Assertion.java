package org.webdriver.seleniumUI.utils;

import org.openqa.selenium.*;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator 周天宇
 *
 */
public class Assertion extends TestBaseCase {

	static List<Error> errors;
	//
	static List<String> assertInfolList= new ArrayList<>();
	//
	static List<String> messageList= new ArrayList<>();
	//
	static Integer errorIndex=0;
	private static Log log=new Log(Assertion.class);
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
		return formatter.format(date);
	}
	private static void snapshotInfo()
	{
		WebDriver driver= TestBaseCase.driver;
		ScreenShot screenShot=new ScreenShot(driver);
		//
		Date nowDate=new Date();
		screenShot.setscreenName(Assertion.formatDate(nowDate));
		screenShot.takeScreenshot();
		//Assertion.assertInfolList.add("&lt;a href=\"snapshot/"+Assertion.formatDate(nowDate)+".jpg\" &gt;&lt;img height=\"100\" width=\"100\" src=\"snapshot\\"+Assertion.formatDate(nowDate)+".jpg\"&gt;&lt;/img&gt;&lt;/a&gt;&lt;br/&gt;"+"&lt;a href=\"snapshot\\"+Assertion.formatDate(nowDate)+".jpg\" &gt;点击查看大图&lt;/a&gt;\n");
		Assertion.messageList.add("&lt;a class=\"clickbox\" href=\"#url\"&gt;\n"
				+ "&lt;img src=\"snapshot/"
				+ Assertion.formatDate(nowDate)
				+ ".jpg\" height=\"100\" width=\"100\" alt=\"\" /&gt;\n"
				+ "&lt;b class=\"lightbox\"&gt;\n"
				+ "&lt;b class=\"light\"&gt;&lt;/b&gt;\n"
				+ "&lt;b class=\"box\"&gt;\n"
				+ "&lt;img src=\"snapshot/"
				+ Assertion.formatDate(nowDate)
				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;\n"
				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面.&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;\n"
				+ "&lt;/b&gt;\n"
				+ "&lt;/b&gt;\n"
				+ "&lt;/a&gt;\n"
				+ "&lt;br class=\"clear\" /&gt;\n"
				+"&lt;a class=\"clickbox\" href=\"#url\"&gt;"
				+ "点击查看大图"
				+ "&lt;b class=\"lightbox\"&gt;"
				+ "&lt;b class=\"light\"&gt;&lt;/b&gt;"
				+ "&lt;b class=\"box\"&gt;&lt;img src=\"snapshot/"
				+ Assertion.formatDate(nowDate)
				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;"
				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面."
				+ "&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;"
				+ "&lt;/b&gt;"
				+ "&lt;/b&gt;"
				+ " &lt;/a&gt;\n&lt;/br&gt;"
				+ "&lt;div id=\"close\"&gt;&lt;/div&gt;\n");
	}

	/**
	 *
	 *  @author Administrator 周天宇
	 */
	public static void VerifyString(String actual,String exceptStr)
	{
		errors= new ArrayList<>();
		String VerifyStr = "Assertion：{" + "Current Value：" + actual + "," + "Expected" + exceptStr + "}";
		log.info(VerifyStr);
		try {
			Assert.assertEquals(actual, exceptStr);
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
		}
	}

	/**
	 *
	 *  @author Administrator 周天宇
	 */
	public static void VerifyString(String actual,String exceptStr,String Message)
	{
		String VerifyStr="Assertion：{"+"Current Value"+actual+","+"Expected"+exceptStr+"} Current Value与Expected是否一致";
		log.info(Message+":"+VerifyStr);
		try {
			Assert.assertEquals(actual, exceptStr);
			AssertPassLog();
			assertInfolList.add(Message+VerifyStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+VerifyStr+":failed");
			messageList.add(Message+":failed");
			Assertion.snapshotInfo();
			//e.printStackTrace();

		}
	}
	/**
	 *
	 *  @author Administrator
	 */
	public static void VerifyNotString(String actual,String exceptStr,String Message)
	{
		String VerifyStr="Assertion：{"+"Current Value"+actual+","+"Expected"+exceptStr+"} Current Value与Expected是否不相等";
		log.info(Message+":"+VerifyStr);
		try {
			Assert.assertNotEquals(actual, exceptStr);
			AssertPassLog();
			assertInfolList.add(Message+VerifyStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+VerifyStr+":failed");
			messageList.add(Message+":failed");
			Assertion.snapshotInfo();

		}
	}
	/**
	 *
	 * @param actual Current Value
	 * @param except Expected
	 * @param message
	 */
	public static void VerifyBoolean(Boolean actual,
									 Boolean except, String message) {

		String VerifyStr="Assertion：{"+"Current Value："+actual+","+"Expected："+except+"} Current Value与Expected是否一致";
		log.info(message+":"+VerifyStr);
		try {
			Assert.assertEquals(actual, except);
			AssertPassLog();
			assertInfolList.add(message+VerifyStr+":pass");
			messageList.add(message+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(message+VerifyStr+":failed");
			messageList.add(message+":failed");
			Assertion.snapshotInfo();
			//e.printStackTrace();
		}
	}


	/**
	 *
	 *  @author Administrator
	 */
	public static  void VerifyTextPresent(String exceptStr)
	{
		String VerifyStr="【Assertion】:"+"If the page has"+"【"+"Expected："+exceptStr+"】"+"String";
		Boolean flag=false;
		log.info(VerifyStr);
		try {
			exceptStr="//*[contains(text(),'"+exceptStr+"')]";
			log.info("Locator Info："+exceptStr);
			driver.findElements(By.xpath(exceptStr));
			if (driver.findElements(By.xpath(exceptStr)).size()>0) {
				flag=true;
			}
			else {
				flag=false;
			}
		} catch (NoSuchElementException e) {
			flag=false;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
		} catch (Error f) {
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
		}


	}

	/**
	 *
	 *  @author Administrator
	 */
	public static  void VerifyTextPresent(String exceptStr,String Message)
	{
		errors= new ArrayList<>();
		String VerifyStr="【Assert】:"+"if WebPage Contains "+"【"+"Expected ："+exceptStr+"】"+"String";
		Boolean flag=false;
		log.info(Message+":"+VerifyStr);
		try {
			exceptStr="//*[contains(text(),'"+exceptStr+"')]";
			System.out.println(exceptStr);
			List<WebElement> webElements= driver.findElements(By.xpath(exceptStr));
			if (webElements.size()>0) {
				flag=true;
			}
			else {
				flag=false;
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			flag=false;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message+VerifyStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+VerifyStr+":failed");
			messageList.add(Message+":failed");
			Assertion.snapshotInfo();
		}


	}

	/**
	 *
	 *  @author Administrator
	 */
	public static  void VerifyNotTextPresent(String exceptStr)
	{
		errors= new ArrayList<>();
		String VerifyStr="【Assertion】:"+"If web page contains"+"【"+"Expected："+exceptStr+"】"+"String";
		Boolean flag=false;
		log.info(VerifyStr);
		try {
			exceptStr="//*[contains(.,'"+exceptStr+"')]";
			driver.findElement(By.xpath(exceptStr));
			flag=false;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			flag=true;
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
			System.out.println(flag);
		} catch (Error f) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
			System.out.println(flag);
		}


	}

	/**
	 *
	 */
	public static  void VerifyTextPresentPrecision(String exceptStr,String Message)
	{
		errors= new ArrayList<>();
		String VerifyStr="【Assert】:"+"if WebPage Contains"+"【"+"Expeted ："+exceptStr+"】"+"String";
		Boolean flag=false;
		log.info(Message+":"+VerifyStr);
		try {
			exceptStr="//*[text()=\""+exceptStr+"\"]";
			WebElement webElement=driver.findElement(By.xpath(exceptStr));
			System.out.println(exceptStr);
			flag=true;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			flag=false;
		}
		System.out.println(flag);
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message+VerifyStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+VerifyStr+":failed");
			messageList.add(Message+":failed");
			Assertion.snapshotInfo();

			//throw f;
		}


	}

	/**
	 *
	 *
	 */
	public static  void VerifyNotTextPresentPrecision(String exceptStr,String Message)
	{
		errors= new ArrayList<>();
		String VerifyStr="【Assert】:"+"if WebPage Contains"+"【"+"Expected："+exceptStr+"】"+"String";
		Boolean flag=false;
		log.info(Message+":"+VerifyStr);
		try {
			exceptStr="//*[text()=\""+exceptStr+"\"]";
			System.out.println(exceptStr);
			driver.findElement(By.xpath(exceptStr));
			flag=false;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			flag=true;
		}
		System.out.println(flag);
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message+VerifyStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error f) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message+VerifyStr+":failed");
			messageList.add(Message+":failed");
			Assertion.snapshotInfo();

			//throw f;
		}


	}

	public static void VerifyText(WebElement webElement, String exceptText) {
		errors= new ArrayList<>();
		String text = webElement.getText();
		String VerifyStr="Assertion：If the text is same as Expected {" + "Current Value：" + text + ", " +" Expected：" + exceptText + "}";
		log.info(VerifyStr);
		try {
			Assert.assertEquals(text, exceptText);
			AssertPassLog();
			assertInfolList.add(VerifyStr + ": pass");
		} catch (Error e) {
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr + ": failed");
			Assertion.snapshotInfo();
		}
	}

	/**

	 */
	public static void VerifyTextNotPresent(WebElement webElement, String exceptText) {
		errors= new ArrayList<>();
		String text=webElement.getText();
		String VerifyStr="Assertion：Text is not present {" + "Current Value：" + text + ", " + " Expected：" + exceptText + "}";
		log.info(VerifyStr);
		try {
			Assert.assertTrue(!text.contains(exceptText));
			AssertPassLog();
			assertInfolList.add(VerifyStr + " :pass");
		} catch (Error e) {
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr + " :failed");
			Assertion.snapshotInfo();
		}
	}

	public static void VerifyText(WebElement webElement, String exceptText, String Message) {
		errors= new ArrayList<>();
		String text = webElement.getText();
		String VerifyStr = "Assertion：If the text is same as Expected {" + "Current Value：" + text + ", " + "Expected: " + exceptText + "}";
		log.info(Message+":"+VerifyStr);
		try {
			Assert.assertEquals(text, exceptText);
			AssertPassLog();
			assertInfolList.add(Message+VerifyStr + ": pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(Message+VerifyStr + ": failed");
			messageList.add(Message + ": failed");
			Assertion.snapshotInfo();
			//throw e;
		}
	}

	public static void VerifyTextContains(WebElement webElement, String exceptText) {
		errors= new ArrayList<>();
		String text = webElement.getText();
		String VerifyStr="Assertion：Actual text contains expected test {" + "Current Value：" + text + "," + "Expected：" + exceptText + "}";
		log.info(VerifyStr);
		try {
			Assert.assertTrue(text.contains(exceptText));
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
		} catch (Error e) {
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
		}
	}

	public static void VerifyTextIsEqualBetweenTwoWebElements(WebElement webElementOne, WebElement webElementTwo) {
		errors= new ArrayList<>();
		String textOne = webElementOne.getText();
		String textTwo = webElementTwo.getText();
		String VerifyStr="Assertion：Two elements contain same text {" + "Webelement one：" + textOne + "," + " Webelement two：" + textTwo + "}";
		log.info(VerifyStr);
		try {
			Assert.assertEquals(textOne, textTwo);
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
			//throw e;
		}
	}

	public static void VerifyWebElementIsDisplayed(WebElement webElement) {
		errors= new ArrayList<>();
		String VerifyStr="Assertion：Webelement " + webElement + " is displayed";
		log.info(VerifyStr);
		try {
			Assert.assertTrue(webElement.isDisplayed());
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
		} catch (Error e) {
			AssertPassLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
		}
	}

	public static void VerifyAttribute(WebElement webElement, String AttributeName, String exceptAttributeValue) {
		errors= new ArrayList<>();
		String attribute = webElement.getAttribute(AttributeName);
		String VerifyStr="Assertion：if the Attribute  is same as Expected{" + " If the text is same as Expected：" + attribute + ", " + "Expected：" + exceptAttributeValue + "}";
		try {
			Assert.assertEquals(attribute, exceptAttributeValue);
			log.info(VerifyStr);
			AssertPassLog();
			assertInfolList.add(VerifyStr+":pass");
		} catch (Error e) {
			AssertFailedLog();
			errorIndex++;
			errors.add(e);
			assertInfolList.add(VerifyStr+":failed");
			Assertion.snapshotInfo();
		}
	}

	public static void VerifyURL(String expectURL,String Message) {
		errors=new ArrayList<>();
		String url=driver.getCurrentUrl();
		String VerifyStr="【Assertion】:If URL is same as Expected{"+"Current Value："+url+","+"Expected："+expectURL+"}";
		log.info(Message+":"+VerifyStr);
		try {
			Assert.assertEquals(url, expectURL);
			AssertPassLog();
			//

			assertInfolList.add(Message+VerifyStr+":pass");
			messageList.add(Message+":pass");
		} catch (Error e) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(e);
			errorIndex++;

			assertInfolList.add(Message+VerifyStr+":failed");
			messageList.add(Message+":failed");
			Assertion.snapshotInfo();
		}
	}

	private static  void AssertPassLog()
	{
		log.info("【Assertion  pass!】");
	}

	private static  void AssertFailedLog()
	{
		log.error("【Assertion  failed!】");
	}

	public static  void VerifyError() {
		Assert.assertEquals(errors.size(), 0);
		Assert.assertEquals(ElementAction.noSuchElementExceptions.size(), 0);
	}


}