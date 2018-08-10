//package org.webdriver.seleniumUI.utils;
//
//import org.openqa.selenium.*;
//import org.testng.Assert;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author Administrator 周天宇
// *
// */
//public class AssertionOld extends org.webdriver.seleniumUI.utils.TestBaseCase {
//
//	public static List<Error> errors=new ArrayList<Error>();
//	//
//	public static List<String> assertInfolList=new ArrayList<String>();
//	//
//	public static List<String> messageList=new ArrayList<String>();
//	//
//	public static Integer errorIndex=0;
//	private static Log log=new Log(Assertion.class);
//	public static String formatDate(Date date)
//	{
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
//		return formatter.format(date).toString();
//	}
//	private static void snapshotInfo()
//	{
//		WebDriver driver=org.webdriver.seleniumUI.utils.TestBaseCase.driver;
//		ScreenShot screenShot=new ScreenShot(driver);
//		//
//		Date nowDate=new Date();
//		screenShot.setscreenName(Assertion.formatDate(nowDate));
//		screenShot.takeScreenshot();
//		//Assertion.assertInfolList.add("&lt;a href=\"snapshot/"+Assertion.formatDate(nowDate)+".jpg\" &gt;&lt;img height=\"100\" width=\"100\" src=\"snapshot\\"+Assertion.formatDate(nowDate)+".jpg\"&gt;&lt;/img&gt;&lt;/a&gt;&lt;br/&gt;"+"&lt;a href=\"snapshot\\"+Assertion.formatDate(nowDate)+".jpg\" &gt;点击查看大图&lt;/a&gt;\n");
//		Assertion.messageList.add("&lt;a class=\"clickbox\" href=\"#url\"&gt;\n"
//				+ "&lt;img src=\"snapshot/"
//				+ Assertion.formatDate(nowDate)
//				+ ".jpg\" height=\"100\" width=\"100\" alt=\"\" /&gt;\n"
//				+ "&lt;b class=\"lightbox\"&gt;\n"
//				+ "&lt;b class=\"light\"&gt;&lt;/b&gt;\n"
//				+ "&lt;b class=\"box\"&gt;\n"
//				+ "&lt;img src=\"snapshot/"
//				+ Assertion.formatDate(nowDate)
//				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;\n"
//				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面.&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;\n"
//				+ "&lt;/b&gt;\n"
//				+ "&lt;/b&gt;\n"
//				+ "&lt;/a&gt;\n"
//				+ "&lt;br class=\"clear\" /&gt;\n"
//				+"&lt;a class=\"clickbox\" href=\"#url\"&gt;"
//				+ "点击查看大图"
//				+ "&lt;b class=\"lightbox\"&gt;"
//				+ "&lt;b class=\"light\"&gt;&lt;/b&gt;"
//				+ "&lt;b class=\"box\"&gt;&lt;img src=\"snapshot/"
//				+ Assertion.formatDate(nowDate)
//				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;"
//				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面."
//				+ "&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;"
//				+ "&lt;/b&gt;"
//				+ "&lt;/b&gt;"
//				+ " &lt;/a&gt;\n&lt;/br&gt;"
//				+ "&lt;div id=\"close\"&gt;&lt;/div&gt;\n");
//	}
//	/**
//	 *
//	 */
//	public  static void VerifyCationString(String actual,String exceptStr)
//	{
//		String VerifyStr="Assertion：{"+"Current Value："+actual+","+"Expected："+exceptStr+"} Current Value是否包含Expected";
//		Boolean flagBoolean=actual.contains(exceptStr);
//		log.info(flagBoolean.toString());
//		try {
//			Assert.assertTrue(flagBoolean);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//
//		} catch (Error e) {
//			// TODO: handle exception
//			errors.add(e);
//			AssertFailedLog();
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//		}
//
//	}
//	/**
//	 *
//	 *  @author Administrator 周天宇
//	 */
//	public  static void VerifyCationString(String actual,String exceptStr,String Message)
//	{
//		String VerifyStr="Assertion：{"+"Current Value："+actual+","+"Expected："+exceptStr+"} Current Value是否包含Expected";
//		Boolean flagBoolean=actual.contains(exceptStr);
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertTrue(flagBoolean);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//		}
//
//	}
//	/**
//	 *
//	 *  @author Administrator 周天宇
//	 */
//	public static void VerifyString(String actual,String exceptStr)
//	{
//		String VerifyStr="Assertion：{"+"Current Value："+actual+","+"Expected"+exceptStr+"} Current Value与Expected是否一致";
//		log.info(VerifyStr);
//		try {
//			Assert.assertEquals(actual, exceptStr);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//e.printStackTrace();
//		}
//	}
//	/**
//	 *
//	 *  @author Administrator 周天宇
//	 */
//	public static void VerifyString(String actual,String exceptStr,String Message)
//	{
//		String VerifyStr="Assertion：{"+"Current Value"+actual+","+"Expected"+exceptStr+"} Current Value与Expected是否一致";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(actual, exceptStr);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			//e.printStackTrace();
//
//		}
//	}
//	/**
//	 *
//	 *  @author Administrator
//	 */
//	public static void VerifyNotString(String actual,String exceptStr,String Message)
//	{
//		String VerifyStr="Assertion：{"+"Current Value"+actual+","+"Expected"+exceptStr+"} Current Value与Expected是否不相等";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertNotEquals(actual, exceptStr);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//
//		}
//	}
//	/**
//	 *
//	 * @param actual Current Value
//	 * @param except Expected
//	 * @param message
//	 */
//	public static void VerifyBoolean(Boolean actual,
//									 Boolean except, String message) {
//
//		String VerifyStr="Assertion：{"+"Current Value："+actual+","+"Expected："+except+"} Current Value与Expected是否一致";
//		log.info(message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(actual, except);
//			AssertPassLog();
//			assertInfolList.add(message+VerifyStr+":pass");
//			messageList.add(message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(message+VerifyStr+":failed");
//			messageList.add(message+":failed");
//			Assertion.snapshotInfo();
//			//e.printStackTrace();
//		}
//	}
//
//
//	/**
//	 *
//	 *  @author Administrator
//	 */
//	public static  void VerifyTextPresent(String exceptStr)
//	{
//		String VerifyStr="【Assertion】:"+"If the page has"+"【"+"Expected："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(VerifyStr);
//		try {
//			exceptStr="//*[contains(text(),'"+exceptStr+"')]";
//			log.info("Locator Info："+exceptStr);
//			driver.findElements(By.xpath(exceptStr));
//			if (driver.findElements(By.xpath(exceptStr)).size()>0) {
//				flag=true;
//			}
//			else {
//				flag=false;
//			}
//		} catch (NoSuchElementException e) {
//			flag=false;
//			ElementActionOld.noSuchElementExceptions.add(e);
//			e.printStackTrace();
//		}
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error f) {
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//		}
//
//
//	}
//
//	/**
//	 *
//	 *  @author Administrator
//	 */
//	public static  void VerifyTextPresent(String exceptStr,String Message)
//	{
//		String VerifyStr="【Assert】:"+"if WebPage Contains "+"【"+"Expected ："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(Message+":"+VerifyStr);
//		try {
//			exceptStr="//*[contains(text(),'"+exceptStr+"')]";
//			System.out.println(exceptStr);
//			List<WebElement> webElements= driver.findElements(By.xpath(exceptStr));
//			if (webElements.size()>0) {
//				flag=true;
//			}
//			else {
//				flag=false;
//			}
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=false;
//			ElementActionOld.noSuchElementExceptions.add(e);
//			e.printStackTrace();
//		}
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//		}
//
//
//	}
//
//	/**
//	 *
//	 *  @author Administrator
//	 */
//	public static  void VerifyNotTextPresent(String exceptStr)
//	{
//		String VerifyStr="【Assertion】:"+"If web page contains"+"【"+"Expected："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(VerifyStr);
//		try {
//			exceptStr="//*[contains(.,'"+exceptStr+"')]";
//			driver.findElement(By.xpath(exceptStr));
//			flag=false;
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=true;
//		}
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//			System.out.println(flag);
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			System.out.println(flag);
//		}
//
//
//	}
//
//	/**
//	 *
//	 */
//	public static  void VerifyNotTextPresent(String exceptStr,String Message)
//	{
//		String VerifyStr="【Assertion】:"+"if web page does not contain"+"【"+"Expected："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(Message+":"+VerifyStr);
//		try {
//			exceptStr="//*[contains(.,'"+exceptStr+"')]";
//			driver.findElement(By.xpath(exceptStr));
//			flag=false;
//			System.out.println(flag);
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=true;
//			System.out.println(flag);
//		}
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			System.out.println(flag);
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			System.out.println(flag);
//			Assertion.snapshotInfo();
//		}
//
//
//	}
//
//	/**
//	 *
//	 * @param
//	 */
//	public static  void VerifyTextPresentPrecision(String exceptStr)
//	{
//		String VerifyStr="【Assertion】:"+"If the page has"+"【"+"Expected："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(VerifyStr);
//		try {
//			exceptStr="//*[text()=\""+exceptStr+"\"]";
//			System.out.println(exceptStr);
//			driver.findElement(By.xpath(exceptStr));
//			flag=true;
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=false;
//			ElementActionOld.noSuchElementExceptions.add(e);
//			e.printStackTrace();
//			///AssertFailedLog();
//		}
//		System.out.println(false);
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw f;
//		}
//
//
//	}
//	/**
//	 *
//	 */
//	public static  void VerifyTextPresentPrecision(String exceptStr,String Message)
//	{
//		String VerifyStr="【Assert】:"+"if WebPage Contains"+"【"+"Expeted ："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(Message+":"+VerifyStr);
//		try {
//			exceptStr="//*[text()=\""+exceptStr+"\"]";
//			WebElement webElement=driver.findElement(By.xpath(exceptStr));
//			System.out.println(exceptStr);
//			flag=true;
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=false;
//		}
//		System.out.println(flag);
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//
//			//throw f;
//		}
//
//
//	}
//
//	/**
//	 *
//	 *
//	 */
//	public static  void VerifyNotTextPresentPrecision(String exceptStr)
//	{
//		String VerifyStr="【Assert】:"+"if WebPage Contains"+"【"+"Expected："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(VerifyStr);
//		try {
//			exceptStr="//*[text()=\""+exceptStr+"\"]";
//			System.out.println(exceptStr);
//			driver.findElement(By.xpath(exceptStr));
//			flag=false;
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=true;
//			ElementActionOld.noSuchElementExceptions.add(e);
//			e.printStackTrace();
//			///AssertFailedLog();
//		}
//		System.out.println(false);
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//		}
//
//
//	}
//
//	/**
//	 *
//	 *
//	 */
//	public static  void VerifyNotTextPresentPrecision(String exceptStr,String Message)
//	{
//		String VerifyStr="【Assert】:"+"if WebPage Contains"+"【"+"Expected："+exceptStr+"】"+"String";
//		Boolean flag=false;
//		log.info(Message+":"+VerifyStr);
//		try {
//			exceptStr="//*[text()=\""+exceptStr+"\"]";
//			System.out.println(exceptStr);
//			driver.findElement(By.xpath(exceptStr));
//			flag=false;
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//			flag=true;
//		}
//		System.out.println(flag);
//		try {
//			Assert.assertTrue(flag);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error f) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(f);
//			errorIndex++;
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//
//			//throw f;
//		}
//
//
//	}
//
//	/**
//
//	 */
//	public static void VerifyTitle(String exceptTitle)
//	{
//
//		String title=driver.getTitle();
//		String VerifyStr="Assert:if web page title is same as expected {"+"actual web title："+title+","+"expected web title："+exceptTitle+"}";
//		log.info(VerifyStr);
//		try {
//			Assert.assertEquals(title, exceptTitle);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			e.printStackTrace();
//			//throw e;
//		}
//	}
//
//
//	/**
//
//	 */
//	public static void VerifyTitle(String exceptTitle,String Message)
//	{
//
//		String title=driver.getTitle();
//		String VerifyStr="Assertion:If Web Page Title as Expected {"+"Actual Web Page Title："+title+","+"Expected Web Page Title："+exceptTitle+"}";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(title, exceptTitle);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			e.printStackTrace();
//			//throw e;
//		}
//	}
//
//	/**
//
//	 */
//	public static void VerifyText(Locator locator,String exceptText)
//	{
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String text=webElement.getText();
//		String VerifyStr="Assertion：If the text is same as Expected{"+"Current Value："+text+","+"Expected："+exceptText+"}";
//		log.info(VerifyStr);
//		try {
//			Assert.assertEquals(text, exceptText);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertPassLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//
//	/**
//
//	 */
//	public static void VerifyTextNotPresent(Locator locator, String exceptText) {
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String text=webElement.getText();
//		String VerifyStr="Assertion：Text is not present {"+"Current Value："+text+","+"Expected："+exceptText+"}";
//		log.info(VerifyStr);
//		try {
//			Assert.assertTrue(!text.contains(exceptText));
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertPassLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//	/**
//
//	 */
//	public static void VerifyText(Locator locator,String exceptText,String Message)
//	{
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String text=webElement.getText();
//		String VerifyStr="Assertion：If the text is same as Expected{"+"Current Value："+text+","+"Expected:"+exceptText+"}";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(text, exceptText);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertPassLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//
//	public static void VerifyTextContains(Locator locator, String exceptText) {
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String text=webElement.getText();
//		String VerifyStr="Assertion：Actual text contains expected test {"+"Current Value：" + text + "," + "Expected：" + exceptText + "}";
//		log.info(VerifyStr);
//		try {
//			Assert.assertTrue(text.contains(exceptText));
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertPassLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//
//    public static void VerifyTextIsEqualBetweenTwoWebElements(Locator locatorOne, Locator locatorTwo) {
//        ElementActionOld action=new ElementActionOld();
//        WebElement webElementOne = action.findElement(locatorOne);
//        WebElement webElementTwo = action.findElement(locatorTwo);
//        String textOne = webElementOne.getText();
//        String textTwo = webElementTwo.getText();
//        String VerifyStr="Assertion：Two elements contain same text {" + "Webelement one：" + textOne + "," + " Webelement two：" + textTwo + "}";
//        log.info(VerifyStr);
//        try {
//            Assert.assertEquals(textOne, textTwo);
//            AssertPassLog();
//            assertInfolList.add(VerifyStr+":pass");
//        } catch (Error e) {
//            // TODO: handle exception
//            AssertPassLog();
//            errorIndex++;
//            errors.add(e);
//            assertInfolList.add(VerifyStr+":failed");
//            Assertion.snapshotInfo();
//            //throw e;
//        }
//    }
//
//    public static void VerifyWebElementIsDisplayed(Locator locator) {
//        ElementActionOld action=new ElementActionOld();
//        WebElement webElement=action.findElement(locator);
//        String VerifyStr="Assertion：Webelement " + webElement + " is displayed";
//        log.info(VerifyStr);
//        try {
//            Assert.assertTrue(webElement.isDisplayed());
//            AssertPassLog();
//            assertInfolList.add(VerifyStr+":pass");
//        } catch (Error e) {
//            // TODO: handle exception
//            AssertPassLog();
//            errorIndex++;
//            errors.add(e);
//            assertInfolList.add(VerifyStr+":failed");
//            Assertion.snapshotInfo();
//            //throw e;
//        }
//    }
//	/**
//
//	 */
//	public static void VerifyAttribute(Locator locator,String AttributeName,String exceptAttributeValue)
//	{
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String attribute=webElement.getAttribute(AttributeName);
//		String VerifyStr="Assertion：if the Attribute  is same as Expected{"+"If the text is same as Expected："+attribute+","+"Expected："+exceptAttributeValue+"}";
//		try {
//			Assert.assertEquals(attribute, exceptAttributeValue);
//			log.info(VerifyStr);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//
//	/**
//	 *
//	 */
//	public static void VerifyAttribute(Locator locator,String AttributeName,String exceptAttributeValue,String Message)
//	{
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String attribute=webElement.getAttribute(AttributeName);
//		String VerifyStr="Assertion：if the Attribute  is same as Expected{"+"If the text is same as Expected："+attribute+","+"Expected："+exceptAttributeValue+"}";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(attribute, exceptAttributeValue);
//			log.info(VerifyStr);
//			AssertPassLog();
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//	/**
//
//	 */
//	public static void VertityNoEdit(Locator locator)
//	{
//		Boolean  status=false;
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String value1=webElement.getAttribute("disabled");
//		String value2=webElement.getAttribute("readOnly");
//		String VerifyStr="【Assertion】:If the text area is editable{"+"Current Value："+status.toString()+"，"+"Expected：false"+"}";
//		if(value1.equals("true"))
//		{
//			status=true;
//		}
//		else if(value2.equals("true"))
//		{
//			status=true;
//		}
//		else {
//			status=false;
//		}
//		log.info(VerifyStr);
//		try {
//			Assert.assertTrue(status);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//
//	}
//
//	/**
//
//	 */
//	public static void VertityNoEdit(Locator locator,String Message)
//	{
//		Boolean  status=false;
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String value1=webElement.getAttribute("disabled");
//		String value2=webElement.getAttribute("readOnly");
//		if(value1.equals("true"))
//		{
//			status=true;
//		}
//		else if(value2.equals("true"))
//		{
//			status=true;
//		}
//		else {
//			status=false;
//		}
//		String VerifyStr="【Assertion】:If the text area is editable{"+"Current Value："+status.toString()+"，"+"Expected：false"+"}";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertTrue(status);
//			AssertPassLog();
//
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errorIndex++;
//			errors.add(e);
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//
//	}
//	/**
//
//	 */
//	public static void VertityEdit(Locator locator)
//	{
//		Boolean  status=false;
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String value1=webElement.getAttribute("disabled");
//		String value2=webElement.getAttribute("readOnly");
//		String VerifyStr="【Assertion】:If the text area is editable {"+"Current Value："+status.toString()+"，"+"Expected：true"+"}";
//		if(value1.equals("false"))
//		{
//			status=true;
//		}
//		else if(value2.equals("false"))
//		{
//			status=true;
//		}
//		else
//		{
//			status=false;
//		}
//		//AssertLog("true", status.toString());
//		log.info(VerifyStr);
//		try {
//			Assert.assertTrue(status);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//	/**
//
//	 */
//	public static void VertityEdit(Locator locator,String Message)
//	{
//		Boolean  status=false;
//		ElementActionOld action=new ElementActionOld();
//		WebElement webElement=action.findElement(locator);
//		String value1=webElement.getAttribute("disabled");
//		String value2=webElement.getAttribute("readOnly");
//		String VerifyStr="【Assertion】:If the text area is editable {"+"Current Value："+status.toString()+"，"+"Expected：true"+"}";
//		if(value1.equals("false"))
//		{
//			status=true;
//		}
//		else if(value2.equals("false"))
//		{
//			status=true;
//		}
//		else
//		{
//			status=false;
//		}
//		//AssertLog("true", status.toString());
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertTrue(status);
//			AssertPassLog();
//
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//	}
//	//还要修改
//	/**
//
//	 */
//	public static void VerifyAlertText(String expectAlertText)
//	{
//		Alert alert=driver.switchTo().alert();
//		String  alertText=alert.getText();
//		String VerifyStr="【Assertion】:if the alert text are same{"+alertText+","+expectAlertText+"}";
//		log.info("【Assertion】:if the alert text are same{"+"Current Value："+alertText+","+"Expected"+expectAlertText+"}");
//		try {
//			Assert.assertEquals(alertText, expectAlertText);
//			AssertPassLog();
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//
//	}
//
//	/**
//	 *
//
//	 */
//	public static void VerifyAlertText(String expectAlertText,String Message)
//	{
//		Alert alert=driver.switchTo().alert();
//		String  alertText=alert.getText();
//		String VerifyStr="【Assertion】:if the alert text are same{"+"Current Value："+alertText+","+"Expected："+expectAlertText+"}";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(alertText, expectAlertText);
//			AssertPassLog();
//
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//			//throw e;
//		}
//
//	}
//	/**
//
//	 */
//	public static void VerifyURL(String expectURL)
//	{
//		String url=driver.getCurrentUrl();
//		String VerifyStr="【Assertion】:If URL is same as Expected{"+"Current Value："+url+","+"Expected："+expectURL+"}";
//		log.info(VerifyStr);
//		try {
//			Assert.assertEquals(url, expectURL);
//			AssertPassLog();
//			//
//			assertInfolList.add(VerifyStr+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			//throw e;
//			errorIndex++;
//			assertInfolList.add(VerifyStr+":failed");
//			Assertion.snapshotInfo();
//		}
//	}
//
//	/**
//
//	 */
//	public static void VerifyURL(String expectURL,String Message)
//	{
//		String url=driver.getCurrentUrl();
//		String VerifyStr="【Assertion】:If URL is same as Expected{"+"Current Value："+url+","+"Expected："+expectURL+"}";
//		log.info(Message+":"+VerifyStr);
//		try {
//			Assert.assertEquals(url, expectURL);
//			AssertPassLog();
//			//
//
//			assertInfolList.add(Message+VerifyStr+":pass");
//			messageList.add(Message+":pass");
//		} catch (Error e) {
//			// TODO: handle exception
//			AssertFailedLog();
//			errors.add(e);
//			errorIndex++;
//
//			assertInfolList.add(Message+VerifyStr+":failed");
//			messageList.add(Message+":failed");
//			Assertion.snapshotInfo();
//		}
//	}
//	//
//	private static  void AssertPassLog()
//	{
//		log.info("【Assertion  pass!】");
//	}
//	//
//	private static  void AssertFailedLog()
//	{
//		log.error("【Assertion  failed!】");
//	}
//	//
//	private static void AssertLog(String str1,String str2)
//	{
//		log.info("【Assertion】:"+"compare"+"{"+str1+","+str2+"}"+"if they are same");
//	}
//
//	public static  void VerifyError()
//	{
//		Assert.assertEquals(errors.size(), 0);
//
//		Assert.assertEquals(ElementActionOld.noSuchElementExceptions.size(), 0);
//	}
//	public static void main(String[] args) {
//
//		String str1 = "timothy 129", str2 = "http://";
//
//		CharSequence cs1 = "test";
//
//		// string contains the specified sequence of char values
//		boolean retval = str1.contains(cs1);
//		System.out.println("Method returns : " + retval);
//
//		// string does not contain the specified sequence of char value
//		retval = str2.contains("_");
//		System.out.println("Methods returns: " + retval);
//		Assertion.VerifyCationString("timothy 129", "timothy");
//		Assertion.VerifyCationString("timothy 129", "timothy", "sdfsdfsdf");
//	}
//
//
//}
