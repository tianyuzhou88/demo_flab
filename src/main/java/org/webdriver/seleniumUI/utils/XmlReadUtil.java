package org.webdriver.seleniumUI.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.webdriver.seleniumUI.utils.Locator.ByType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
//

/**
 * xml reader
 * @author Administrator 
 *
 */
public class XmlReadUtil {
	//locator
	public  HashMap<String, Locator> readXMLDocument(String path,String pageName) {
		Log log=new Log(this.getClass());
		HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
		locatorMap.clear();
		try {
			File file = new File(path);
			if (!file.exists()) {
				throw new IOException("Can't find " + path);
			}
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			Element root = document.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();)
			{
				Element page = (Element) i.next();
				if (page.attribute(0).getValue().equalsIgnoreCase(pageName))
				{
					for (Iterator<?> l = page.elementIterator(); l.hasNext();)
					{
						String type = null;
						String timeOut = "3";
						String value = null;
						String locatorName = null;
						Element locator = (Element) l.next();
						locatorName = locator.getText();
						for (Iterator<?> j = locator.attributeIterator(); j.hasNext();)
						{
							Attribute attribute = (Attribute) j.next();
							if (attribute.getName().equals("type"))
							{
								type = attribute.getValue();
							} else if (attribute.getName().equals("timeout"))
							{
								timeOut = attribute.getValue();
							} else if (attribute.getName().equals("value"))
							{
								value = attribute.getValue();
							}
						}
						Locator temp = new Locator(value.trim(),Integer.parseInt(timeOut), getByType(type),locatorName.trim());
						locatorMap.put(locatorName.trim(), temp);
					}
					continue;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return locatorMap;
	}
	public  HashMap<String, Locator> readXMLDocument(InputStream path,String pageName) {
		Log log=new Log(this.getClass());
		HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
		locatorMap.clear();
		try {
			InputStreamReader inputStreamReader=new InputStreamReader(path,"UTF-8");
			SAXReader reader = new SAXReader();
			Document document=reader.read(inputStreamReader);
			Element root = document.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();)
			{
				Element page = (Element) i.next();
				if (page.attribute(0).getValue().equalsIgnoreCase(pageName))
				{
					for (Iterator<?> l = page.elementIterator(); l.hasNext();)
					{
						String type = null;
						String timeOut = "3";
						String value = null;
						String locatorName = null;
						Element locator = (Element) l.next();
						locatorName = locator.getText();
						for (Iterator<?> j = locator.attributeIterator(); j.hasNext();)
						{
							Attribute attribute = (Attribute) j.next();
							if (attribute.getName().equals("type"))
							{
								type = attribute.getValue();
							} else if (attribute.getName().equals("timeout"))
							{
								timeOut = attribute.getValue();
							} else if (attribute.getName().equals("value"))
							{
								value = attribute.getValue();
							}
						}
						Locator temp = new Locator(value.trim(),Integer.parseInt(timeOut), getByType(type),locatorName.trim());
						locatorMap.put(locatorName.trim(), temp);
					}
					continue;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return locatorMap;
	}
	/**
	 * @param type
	 */
	public static ByType getByType(String type) {
		ByType byType = ByType.xpath;
		if (type == null || type.equalsIgnoreCase("xpath")) {
			byType = ByType.xpath;
		} else if (type.equalsIgnoreCase("id")) {
			byType = ByType.id;
		} else if (type.equalsIgnoreCase("linkText")) {
			byType = ByType.linkText;
		} else if (type.equalsIgnoreCase("name")) {
			byType = ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = ByType.className;
		} else if (type.equalsIgnoreCase("cssSelector")) {
			byType = ByType.cssSelector;
		} else if (type.equalsIgnoreCase("partialLinkText")) {
			byType = ByType.partialLinkText;
		} else if (type.equalsIgnoreCase("tagName")) {
			byType = ByType.tagName;
		}
		return byType;
	}

	public static String getXmlPageURL(InputStream path ,String pageName)
	{
		//System.out.print(pageName);
		String URL=null;
		try {
			InputStreamReader inputStreamReader=new InputStreamReader(path,"UTF-8");
			SAXReader reader = new SAXReader();
			Document document=reader.read(inputStreamReader);
			System.out.println("text"+document.asXML());
			Element rootElement=document.getRootElement();
			for(Iterator<?> i=rootElement.elementIterator();i.hasNext();)
			{
				Element page=(Element)i.next();

				if(page.attribute(0).getValue().equals(pageName))
				{
					for(Iterator<?>n=page.attributeIterator();n.hasNext();)
					{
						Attribute attribute=(Attribute)n.next();
						if(attribute.getName().equals("value"))
						{
							URL=attribute.getValue().trim();
						}

					}

					continue;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		return URL;

	}

	public static String getXmlPageURL(String path,String pageName)
	{
		//System.out.print(pageName);

		String URL=null;
		File file =new File(path);
		try {
			if(!file.exists())
			{
				throw new IOException("can not find xmldomcument"+path);
			}
			SAXReader saxReader=new SAXReader();
			Document document=saxReader.read(file);
			Element rootElement=document.getRootElement();
			for(Iterator<?> i=rootElement.elementIterator();i.hasNext();)
			{
				Element page=(Element)i.next();
				if(page.attribute(0).getValue().equals(pageName))
				{
					for(Iterator<?>n=page.attributeIterator();n.hasNext();)
					{
						Attribute attribute=(Attribute)n.next();
						if(attribute.getName().equals("value"))
						{
							URL=attribute.getValue().trim();
						}

					}

					continue;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return URL;


	}
	public static  String getTestngParametersValue(String path,String ParametersName) throws DocumentException, IOException
	{
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("Can't find " + path);

		}
		String value=null;
		SAXReader reader = new SAXReader();
		Document  document = reader.read(file);
		Element root = document.getRootElement();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();)
		{
			Element page = (Element) i.next();
			if(page.attributeCount()>0)
			{
				if (page.attribute(0).getValue().equalsIgnoreCase(ParametersName))
				{
					value=page.attribute(1).getValue();
					//System.out.println(page.attribute(1).getValue());
				}
				continue;
			}
			//continue;
		}
		return value;

	}

}
