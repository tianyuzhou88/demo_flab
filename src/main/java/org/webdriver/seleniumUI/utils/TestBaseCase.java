package org.webdriver.seleniumUI.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

public class TestBaseCase {
	public static WebDriver driver;
	public static String description;
	public Log log=new Log(this.getClass().getSuperclass());
    public static ElementAction action;
    private final static String NODE_URL = "https://seleniumbox.cisco.com/wd/hub";
    private final static String Docker_NODE_URL = "http://localhost:4444/wd/hub";
	public static int idGenerator = new Random().nextInt(10000);

	protected Properties properties = new TestAutomationProperties();
	public String workingDir = properties.getProperty("workingDir.path");
	public static String gridSession;

	public static String grid;
	public static String browser;

    @BeforeSuite
	@Parameters({"Browser","Grid"})
	public void  setup( String Browser,String Grid) throws MalformedURLException {
    	grid = Grid;
    	browser = Browser;
    	action = new ElementAction();
		log.info("------------------Starting Executing Tests---------------");
		if(Grid.equalsIgnoreCase("no")||Grid.isEmpty())
		{
			System.out.println("BROWSER: " + Browser);
			log.info("Reading AccountRegistrationTest.xml Configuration:"+Browser+"Initialize Browser\n");
			try {
				TestBaseCase.driver =setDriver(Browser);
				driver.manage().window().fullscreen();
			} catch (Exception e) {
				log.error("Fail to Initialize the browser from Configuration");
				e.printStackTrace();
			}

		}
		else if (Grid.equalsIgnoreCase("yes")){
			log.info("reading xml config：browser:"+driver);
			try {
                TestBaseCase.driver=setRemoteDriver(Browser);
                driver.manage().window().fullscreen();
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Fail to Initialize the browser from Configuration");
			}
		}
	}

	@AfterTest
	public void tearDown() {
        TestBaseCase.driver.close();
    	TestBaseCase.driver.quit();
		log.info("-------------Test Finished，Closing Browsers-------------");
	}

	/**
	 *
	 * @author zhou
	 *
	 */
	private WebDriver setDriver(String browsername)
	{

		switch (browsername)
		{
			case "Firefox":
				WebDriverManager.firefoxdriver().setup();
				TestBaseCase.driver = new FirefoxDriver();
				break;
            case "Chrome":
				WebDriverManager.chromedriver().setup();
				HashMap<String, Object> chromePrefs = new HashMap<>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", workingDir);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setExperimentalOption("prefs", chromePrefs);
                TestBaseCase.driver=new ChromeDriver(chromeOptions);
                break;
		}
		return driver;
	}

	private WebDriver setRemoteDriver(String browsername) throws MalformedURLException {
		switch (browsername)
		{
			case "Firefox" :
				DesiredCapabilities capabilities=DesiredCapabilities.firefox();
//				capabilities.setBrowserName("firefox");
				capabilities.setCapability("e34:auth","-vij02owbi28x1chi-");
				capabilities.setCapability("e34:video", true);
				capabilities.setCapability("e34:per_test_timeout_ms", 120000);
				capabilities.setCapability("e34:geckodriver", "0.20.0");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setAcceptUntrustedCertificates(true);
				firefoxProfile.setPreference("browser.download.folderList", 2);
				firefoxProfile.setPreference("browser.download.dir", workingDir);
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/txt");
				firefoxOptions.setProfile(firefoxProfile);
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
				driver= new RemoteWebDriver(new URL(NODE_URL), capabilities);
				gridSession = ((RemoteWebDriver) driver).getSessionId().toString();
				System.out.println("Live View URL > https://seleniumbox.cisco.com/ui/liveview?session="+ ((RemoteWebDriver) driver).getSessionId().toString());
				System.out.println("Video URL > https://seleniumbox.cisco.com/videos/"+ ((RemoteWebDriver) driver).getSessionId().toString() + ".mp4");
                break;
			case "Chrome":
				DesiredCapabilities capabilities2=DesiredCapabilities.chrome();
				capabilities2.setBrowserName("chrome");
				capabilities2.setCapability("e34:auth","-vij02owbi28x1chi-");
				capabilities2.setCapability("e34:per_test_timeout_ms", 120000);
				capabilities2.setCapability("e34:video", true);
//				HashMap<String, Object> chromePrefs = new HashMap<>();
//				chromePrefs.put("profile.default_content_settings.popups", 0);
//				chromePrefs.put("download.default_directory", workingDir);
//				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.setExperimentalOption("prefs", chromePrefs);
//				capabilities2.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				driver= new RemoteWebDriver(new URL(NODE_URL), capabilities2);
				gridSession = ((RemoteWebDriver) driver).getSessionId().toString();
				System.out.println("Live View URL > https://seleniumbox.cisco.com/ui/liveview?session="+ ((RemoteWebDriver) driver).getSessionId().toString());
				System.out.println("Video URL > https://seleniumbox.cisco.com/videos/"+ ((RemoteWebDriver) driver).getSessionId().toString() + ".mp4");
				break;
            case "Chrome_Docker":
                DesiredCapabilities capabilities3=DesiredCapabilities.chrome();
				capabilities3.setCapability("e34:per_test_timeout_ms", 120000);
                driver= new RemoteWebDriver(new URL(Docker_NODE_URL), capabilities3);
                System.out.println("Live View URL > http://localhost:4444/grid/admin/live");
                System.out.println("DashBoard > http://localhost:4444/DashBoard");
                break;
            case "FireFox_Docker":
                DesiredCapabilities capabilities4=DesiredCapabilities.firefox();
				capabilities4.setCapability("e34:per_test_timeout_ms", 120000);
                driver= new RemoteWebDriver(new URL(Docker_NODE_URL), capabilities4);
                System.out.println("Live View URL > http://localhost:4444/grid/admin/live");
                System.out.println("DashBoard > http://localhost:4444/DashBoard");
                break;
			default:
                TestBaseCase.driver=new ChromeDriver();
				break;
		}
		return driver;
	}

	public static void main(String args[]) throws Exception
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.fidelitylabs.com/");
		driver.wait(20);

	}


}
