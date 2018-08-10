package org.webdriver.seleniumUI.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * listener
 * @author Administrator
 *
 */
public class TestListener  extends TestListenerAdapter{
	Log log=new Log(this.getClass());
	public static StringBuffer sb=new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<failed>\n");
	String path="test-output/failed.xml";
	File file=new File(path);
	FileWriter fileWriter=null;

	public static StringBuffer sb2=new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<passed>\n");
	String path2="test-output/passed.xml";
	File file2=new File(path2);
	@Override
	public void onTestStart(ITestResult  tr)
	{

		Assertion.errors.clear();
		Assertion.errorIndex=0;
		Assertion.messageList.clear();
		ElementAction.noSuchElementExceptions.clear();
		log.info("Test Cases:"+tr.getMethod().getDescription()+"---start");
	}
	@Override
	public void onTestFailure( ITestResult  tr)
	{
		TestBaseCase testBaseCase=(TestBaseCase) tr.getInstance();
		WebDriver driver=testBaseCase.driver;
		ScreenShot screenShot=new ScreenShot(driver);
		screenShot.setscreenName(tr.getMethod().getDescription()+Assertion.errorIndex.toString());
		log.error(Assertion.errorIndex.toString());
		screenShot.takeScreenshot();
		for(int i = 0; i<Assertion.messageList.size(); i++)
		{
			if (tr.getParameters().length>0) {
				sb.append("<err_assert_info"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"."+tr.getEndMillis()+"\">\n");
			}
			else {
				sb.append("<err_assert_info"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"\">\n");
			}
			if(Assertion.messageList.get(i).contains("pass"))
			{
				sb.append("<span class=\"pass_span\">"+Assertion.messageList.get(i)+"</span></br>\n");
			}
			else if(Assertion.messageList.get(i).contains("failed"))
			{
				sb.append("<span class=\"err_span\">"+Assertion.messageList.get(i)+"</span></br>\n");
			}
			sb.append("</err_assert_info>\n");
		}


		for (Exception e : ElementAction.noSuchElementExceptions) {
			StackTraceElement[] errorTraces = e.getStackTrace();
			StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
			if (tr.getParameters().length>0) {
				sb.append("<err_assert_info_StackTrace"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"."+tr.getEndMillis()+"\">\n");
			}
			else {
				sb.append("<err_assert_info_StackTrace"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"\">\n");
			}
			sb.append("<span class=\"err_span\" >"+e.getMessage()+"</span></br>\n");
			for(int i=0;i<et.length;i++)
			{
				sb.append("<span class=\"err_span\">"+et[i].toString()+"</span></br>\n");
			}
			sb.append("</err_assert_info_StackTrace>\n");


		}

		for (Error e : Assertion.errors) {
			StackTraceElement[] errorTraces = e.getStackTrace();
			StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
			if (tr.getParameters().length>0) {
				sb.append("<err_assert_info_StackTrace"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"."+tr.getEndMillis()+"\">\n");
			}
			else {
				sb.append("<err_assert_info_StackTrace"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"\">\n");
			}
			sb.append("<span class=\"err_span\" >"+e.getMessage()+"</span></br>\n");
			for(int i=0;i<et.length;i++)
			{
				sb.append("<span class=\"err_span\">"+et[i].toString()+"</span></br>\n");
			}

			sb.append("</err_assert_info_StackTrace>\n");


		}
		if(file.exists())
		{
			file.delete();;
		}
		// log.error(sb.toString());
		//this.handAssertion(tr);
		log.error("Test Cases: "+tr.getMethod().getDescription()+"--failed");
		log.info("Test Cases:"+tr.getMethod().getDescription()+"---end");
	}
	@Override
	public void onTestSkipped(ITestResult tr) {
		TestBaseCase testBaseCase=(TestBaseCase) tr.getInstance();
		WebDriver driver=testBaseCase.driver;
		ScreenShot screenShot=new ScreenShot(driver);
		screenShot.setscreenName(tr.getMethod().getDescription());
		screenShot.takeScreenshot();
		log.warn("Test Cases: "+tr.getMethod().getDescription()+"--skipped");
		log.info("Test Cases:"+tr.getMethod().getDescription()+"---end");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		for(int i = 0; i<Assertion.messageList.size(); i++)
		{
			if (tr.getParameters().length>0) {
				sb2.append("<pass_assert_info"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"."+tr.getEndMillis()+"\">\n");
			}
			else {
				sb2.append("<pass_assert_info"+"   method=\""+tr.getTestClass().getName()+"."+tr.getMethod().getMethodName()+"\">\n");
			}
			sb2.append("<span class=\"pass_span\">"+Assertion.messageList.get(i)+"</span></br>\n");

			sb2.append("</pass_assert_info>\n");
		}
		if(file2.exists())
		{
			file2.delete();;
		}
		log.info("Test Cases: "+tr.getMethod().getDescription()+"--passed");
		log.info("Test Cases:"+tr.getMethod().getDescription()+"---end");

	}
	private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
		List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){
				ets.add(stackTraceElement);
			}
		}
		StackTraceElement[] et = new StackTraceElement[ets.size()];
		for (int i = 0; i < et.length; i++) {
			et[i] = ets.get(i);
		}
		return et;
	}

}
