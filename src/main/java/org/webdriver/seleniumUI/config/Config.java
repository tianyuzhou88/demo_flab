package org.webdriver.seleniumUI.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


public class Config {
	public static String path="TestXML/AdminLoginTest.xml";
	public static void main(String[] args) {
		// TODO

		String Base_Url=args[0];
		String UserName=args[1];
		String PassWord=args[2];
		String driver=args[3];
		String Node_Url=args[4];
		String Recipients=args[5];
		String ReportUrl=args[6];
		String LogUrl=args[7];
		try {
			//Config.SetXML(args[0], args[1], args[2]);
			if (driver.equalsIgnoreCase("Firefox BROWSER")) {
				driver="FirefoxDriver";
				//Node_Url=Node_Url+":3155";

			}
			else if (driver.equalsIgnoreCase("Chrome BROWSER")) {
				driver="ChromeDriver";
				//Node_Url=Node_Url+":3166";
			}
			else if(driver.equalsIgnoreCase("IE9 BROWSER")) {
				driver="InternetExplorerDriver-9";
				//Node_Url=Node_Url+":3177";
			}
			else if(driver.equalsIgnoreCase("IE8 BROWSER")) {
				driver="InternetExplorerDriver-8";
				//Node_Url=Node_Url+":3188";
			}
			else {
				driver="FirefoxDriver";
				//Node_Url=Node_Url+":3155";
			}
			Config.SetXML(Base_Url, UserName, PassWord,driver,Node_Url,Recipients,ReportUrl,LogUrl);
			//Config.formatXMLFile(path);
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO
			e.printStackTrace();
		}
	}
	public static void SetXML(String Base_Url,String UserName,String PassWord,String driver,String nodeURL,String Recipients,String ReportUrl,String LogUrl) throws IOException, DocumentException
	{

		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("CANNOT FIND " + path);

		}
		SAXReader reader = new SAXReader();
		Document  document = reader.read(file);
		Element root = document.getRootElement();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();)
		{
			Element page = (Element) i.next();
			if(page.attributeCount()>0)
			{
				if (page.attribute(0).getValue().equalsIgnoreCase("Base_Url"))
				{
					page.attribute(1).setValue(Base_Url);
					//System.out.println(page.attribute(1).getValue());
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("UserName")) {
					page.attribute(1).setValue(UserName);
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("PassWord")) {
					page.attribute(1).setValue(PassWord);
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("driver")) {
					page.attribute(1).setValue(driver);
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("nodeURL")) {
					page.attribute(1).setValue("http://"+nodeURL+"/wd/hub");
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("Recipients"))
				{
					page.attribute(1).setValue(Recipients);
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("ReportUrl"))
				{
					page.attribute(1).setValue(ReportUrl);
				}
				if (page.attribute(0).getValue().equalsIgnoreCase("LogUrl"))
				{
					page.attribute(1).setValue(LogUrl);
				}
				continue;
			}
			//continue;
		}
		if (driver.equalsIgnoreCase("FirefoxDriver")) {
			driver="Firefox BROWSER";

		}
		else if (driver.equalsIgnoreCase("ChormeDriver")) {
			driver="Chrome BROWSER";
		}
		else if(driver.equalsIgnoreCase("InternetExplorerDriver-8")) {
			driver="IE8 BROWSER";
		}
		else if(driver.equalsIgnoreCase("InternetExplorerDriver-9")) {
			driver="IE9 BROWSER";
		}
		else {
			driver="Firefox BROWSER";
		}
		try{
			/** format output */
			OutputFormat format = OutputFormat.createPrettyPrint();
			//format.setEncoding("gb2312");
			/**  */
			XMLWriter writer = new XMLWriter(new FileWriter(new File(path)),format);
			writer.write(document);
			writer.close();
			/** retrun 1 after success*/
			int returnValue = 1;
			System.out.println("Initialize Testing Environment："+Base_Url
					+ ";User Name:"+UserName
					+ "Password:"
					+ PassWord
					+"Browser："
					+driver
					+"Success!");
			System.out.println("Setting Report url:"+ReportUrl);
			System.out.println("Setting Log url:"+LogUrl);
			System.out.println("Setting Recipient email Address："+Recipients);
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Cogitation for Test Bed："+Base_Url
					+ ";User Name:"+UserName
					+ "Password:"
					+ PassWord
					+"Browser："
					+driver
					+"Fail!");
		}


	}
}
