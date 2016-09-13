package com.orasi.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.orasi.core.interfaces.Element;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.saucerest.SauceREST;

/**
 * 
 * @author Justin Phlegar & Waightstill W Avery
 * @summary This class is designed to be extended by page classes and
 *          implemented by test classes. It houses test environment data and
 *          associated getters and setters, setup for both local and remote
 *          WebDrivers as well as page class methods used to sync page behavior.
 *          The need for this arose due to the parallel behavior that indicated
 *          that WebDriver instances were crossing threads and testing on the
 *          wrong os/browser configurations
 * @date April 5, 2015
 *
 */
public class TestEnvironment {
	/*
	 * Test Environment Fields
	 */
	protected String applicationUnderTest = "";
	protected String browserUnderTest = "";
	protected String browserVersion = "";
	protected String operatingSystem = "";
	protected String runLocation = "";
	protected String environment = "";
	protected String testName = "";
	protected String pageUrl = "";
	/*
	 * WebDriver Fields
	 */
	protected OrasiDriver driver;
	protected ThreadLocal<OrasiDriver> threadedDriver = new ThreadLocal<OrasiDriver>();
	private boolean setThreadDriver = false;
	protected ThreadLocal<String> sessionId = new ThreadLocal<String>();

	/*
	 * URL and Credential Repository Field
	 */
	protected ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
	/*
	 * Selenium Hub Field
	 */
	protected String seleniumHubURL = appURLRepository.getString("SELENIUMGRID_HUB_URL");

	/*
	 * Sauce Labs Fields
	 */

	/**
	 * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication}
	 * instance using the supplied user name/access key. To use the
	 * authentication supplied by environment variables or from an external
	 * file, use the no-arg
	 * {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
	 */

	protected SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));

	protected String sauceLabsURL = "http://" + authentication.getUsername() + ":" + authentication.getAccessKey()
			+ "@ondemand.saucelabs.com:80/wd/hub";

	/*
	 * Constructors for TestEnvironment class
	 */

	public TestEnvironment() {
	};

	
	public TestEnvironment(String application, String browserUnderTest, String browserVersion, String operatingSystem,
			String runLocation, String environment) {
		this.applicationUnderTest = application;
		this.browserUnderTest = browserUnderTest;
		this.browserVersion = browserVersion;
		this.operatingSystem = operatingSystem;
		this.runLocation = runLocation;
		this.environment = environment;
	}
	
	/**TODO
	 * Ask Justin about this constructor - why would we instantiate the class this way?
	 * */
	
	public TestEnvironment(TestEnvironment te) {
		setApplicationUnderTest(te.getApplicationUnderTest());
		setBrowserUnderTest(te.getBrowserUnderTest());
		setBrowserVersion(te.getBrowserVersion());
		setOperatingSystem(te.getOperatingSystem());
		setRunLocation(te.getRunLocation());
		setTestEnvironment(te.getTestEnvironment());
		setTestName(te.getTestName());
		setDriver(te.getDriver());
	}
	

	/*
	 * Getters and setters
	 */
	protected void setApplicationUnderTest(String aut) {
		applicationUnderTest = aut;
	}
	public String getApplicationUnderTest() {
		return applicationUnderTest;
	}
	protected void setPageURL(String url) {
		pageUrl = url;
	}
	public String getPageURL() {
		return pageUrl;
	}
	protected void setBrowserUnderTest(String but) {
		if (but.equalsIgnoreCase("jenkinsParameter")) {
			browserUnderTest = System.getProperty("jenkinsBrowser").trim();
		} else {
			browserUnderTest = but;
		}
	}
	public String getBrowserUnderTest() {
		return browserUnderTest;
	}
	protected void setBrowserVersion(String bv) {
		if (bv.equalsIgnoreCase("jenkinsParameter")) {
			if (System.getProperty("jenkinsBrowserVersion") == null
					|| System.getProperty("jenkinsBrowserVersion") == "null") {
				browserVersion = "";
			} else {
				browserVersion = System.getProperty("jenkinsBrowserVersion").trim();
			}
		} else {
			browserVersion = bv;
		}
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	protected void setOperatingSystem(String os) {
		if (os.equalsIgnoreCase("jenkinsParameter")) {
			operatingSystem = System.getProperty("jenkinsOperatingSystem").trim();
		} else {
			operatingSystem = os;
		}
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	protected void setRunLocation(String rl) {
		if (rl.equalsIgnoreCase("jenkinsParameter")) {
			runLocation = System.getProperty("jenkinsRunLocation".trim());
		} else {
			runLocation = rl;
		}
	}
	public String getRunLocation() {
		return runLocation;
	}
	protected void setTestEnvironment(String env) {
		environment = env;
	}
	public String getTestEnvironment() {
		return environment;
	}
	protected void setTestName(String tn) {
		testName = tn;
	}
	public String getTestName() {
		return testName;
	}
	public String getRemoteURL() {
		if (runLocation.equalsIgnoreCase("sauce"))
			return sauceLabsURL;
		else if (runLocation.equalsIgnoreCase("grid"))
			return seleniumHubURL;
		else
			return "";
	}

	/*TODO ask justin why we would set the this as a system property,
	 * if so, need to add back adding this method in the constructors*/
	protected void setSeleniumHubURL(String url) {
		System.setProperty(Constants.SELENIUM_HUB_URL, url);
	}
	
	/*
	 * Getter and setter for default test timeout
	 * TODO Ask justin why these are set as property values.  And really what is a test timeout anyways?  
	 * Is it just a value?
	 
	public void setDefaultTestTimeout(int timeout) {
		System.setProperty(Constants.TEST_DRIVER_TIMEOUT, Integer.toString(timeout));
	}

	public static int getDefaultTestTimeout() {
		return Integer.parseInt(System.getProperty(Constants.TEST_DRIVER_TIMEOUT));
	}
	*/

	// ************************************
	// ************************************
	// ************************************
	// WEBDRIVER SETUP
	// ************************************
	// ************************************
	// ************************************

	/*
	 * Getter and setter for the actual WebDriver
	 */
	private void setDriver(OrasiDriver driverSession) {
		if (isThreadedDriver())
			threadedDriver.set(driverSession);
		else
			driver = driverSession;
	}

	public OrasiDriver getDriver() {
		if (isThreadedDriver())
			return threadedDriver.get();
		else
			return driver;
	}

	/**
	 * User controls to see the driver to be threaded or not. Only use when
	 * using data provider threading
	 */
	private boolean isThreadedDriver() {
		return setThreadDriver;
	}

	public void setThreadDriver(boolean setThreadDriver) {
		this.setThreadDriver = setThreadDriver;
	}

	/*
	 * Method to retrive the URL and Credential Repository
	 */
	protected ResourceBundle getEnvironmentURLRepository() {
		return appURLRepository;
	}

	/**
	 * Launches the application under test using a URL passed into method
	 * 
	 * @version 12/16/2014
	 * @author Justin Phlegar
	 * @return Nothing
	 */
	private void launchApplication(String URL) {
		getDriver().get(URL);
	}

	/**
	 * Launches the application under test using the URL grabbed from the EnvironmentURLs properties file
	 * It will look for a key in the properties file with the
	 * prefix of the application under test + "_" + the environment being tested. 
	 * @version 12/16/2014
	 * @author Justin Phlegar
	 * @return Nothing
	 */
	private void launchApplication() {
		launchApplication(appURLRepository
					.getString(getApplicationUnderTest().toUpperCase() + "_" + getTestEnvironment().toUpperCase()));
	}


	/**
	 * Initializes the webdriver, sets up the run location, driver type,
	 * launches the application.  Gives the option of using the EnvironmentURL properties
	 * file to launch the URL of the application, or you can set the page URL during setup by calling
	 * setPageURL("http://urlforthepage.com").  Unless you are wanting the test to start from a specific
	 * page in the application under test, you will not set that field & will instead just use the base 
	 * URL from the properties file
	 * 
	 * @version 12/16/2014
	 * @author Jessica Marshall
	 */
	protected OrasiDriver testStart(String testName) {
		// Uncomment the following line to have TestReporter outputs output to
		// the console
		TestReporter.setPrintToConsole(true);
		setTestName(testName);
		driverSetup();
		//launch the application under test normally 
		if (pageUrl.isEmpty())
			launchApplication();
		//Otherwise if you have a specific page you want the test to start from
		else
			launchApplication(pageUrl);
		return getDriver();
	}

	/**
	 * Ends the test and grabs the test result (pass/fail) in case need to use that 
	 * for additional reporting - such as to a sauce labs run.  Allows for different
	 * ways of quiting the browser depending on r
	 * Use ITestResult from @AfterMethod to determine run status before closing
	 * test if reporting to sauce labs
	 */
	protected void endTest(String testName, ITestResult testResults) {
		//Sauce labs specific to end test
		if (runLocation.equalsIgnoreCase("sauce")) {
			endSauceTest(testResults.getStatus());
		} 
		//quit driver
		if (getDriver() != null && getDriver().getWindowHandles().size() > 0) {
			getDriver().quit();
		}
		
		
	}


	/**
	 * Ends the test for a sauce labs run by passing in the test results (pass/fail)
	 * and quits 
	 * @param result
	 */
	private void endSauceTest(int result) {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("name", getTestName());

		if (result == ITestResult.FAILURE) {
			updates.put("passed", false);
		} else {
			updates.put("passed", true);
		}
		SauceREST client = new SauceREST(authentication.getUsername(), authentication.getAccessKey());
		client.updateJobInfo(driver.getSessionId().toString(), updates);
	}


	/**
	 * Sets up the driver type, location, browser under test, os
	 * 
	 * @param None
	 * @version 12/16/2014
	 * @author Justin Phlegar
	 * @return Nothing
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void driverSetup() {
		//local execution
		if (runLocation.equalsIgnoreCase("local")) {
			localDriverSetup();
			
		// Code for running on remote execution such as a selenium grid or saucelabs
		} else if (runLocation.equalsIgnoreCase("grid") || runLocation.equalsIgnoreCase("sauce")) {
			remoteDriverSetup();
		} else {
			throw new RuntimeException(
					"Parameter for run [Location] was not set to 'Local', 'Grid', 'Sauce'");
		}

		//Set the timeouts to the defaults according to the constants class
		getDriver().setElementTimeout(Constants.ELEMENT_TIMEOUT);
		getDriver().setPageTimeout(Constants.PAGE_TIMEOUT);
		getDriver().setScriptTimeout(Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);

		//Microsoft Edge Browser 
		if (!browserUnderTest.toLowerCase().contains("edge")) {
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
		}
	}
	
	/**
	 * Creates a local web driver instance based on browser, browser version (required only for firefox). 
	 * It uses driver servers for each browser that are stored within the project. 
	 * For firefox versions greater than 46, you will need to use the marionette/gecko driver.  
	 * @author jessica.marshall
	 * @date 9/13/2016
	 */
	private void localDriverSetup(){
		
		File file = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		
		switch (browserUnderTest.toLowerCase().trim()) {
		case "firefox":
			
			//For firefox versions greater than 46, will need to use the marionette/gecko driver
			if (browserVersion == null || Integer.parseInt(browserVersion) > 46 )  {
				file = new File(
						this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "geckodriver.exe").getPath());
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
			}
			caps = DesiredCapabilities.firefox();
			break;

		case "ie":
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("enablePersistentHover", false);
			file = new File(
					this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "IEDriverServer.exe").getPath());
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			caps = DesiredCapabilities.internetExplorer();
			break;
			
		case "microsoftedge":
			file = new File(this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "MicrosoftWebDriver.exe")
					.getPath());
			System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
			caps = DesiredCapabilities.edge();
			break;
			
		case "chrome":
			file = new File(
					this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "ChromeDriver.exe").getPath());
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			caps = DesiredCapabilities.chrome();
			//Mac operating system with chrome browser
			if (operatingSystem.equalsIgnoreCase("mac")){
				file = new File(
						this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "mac/chromedriver").getPath());
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				try {
					// Ensure the permission on the driver include
					// executable permissions
					Process proc = Runtime.getRuntime()
							.exec(new String[] { "/bin/bash", "-c", "chmod 777 " + file.getAbsolutePath() });
					proc.waitFor();
					caps = DesiredCapabilities.chrome();

				} catch (IllegalStateException ise) {
					ise.printStackTrace();
					throw new IllegalStateException(
							"This has been seen to occur when the chromedriver file does not have executable permissions. In a terminal, navigate to the directory to which Maven pulls the drivers at runtime (e.g \"/target/classes/drivers/\") and execute the following command: chmod +rx chromedriver");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}

			break;

		default:
			throw new RuntimeException("Parameter not set for browser type");
		}

		setDriver(new OrasiDriver(caps));
	}
	
	/**
	 * Creates the remote webdriver instance based on browser, browser version
	 * OS, and the remote grid URL
	 * @author jessica.marshall
	 * @date 9/13/2016
	 */
	private void remoteDriverSetup(){
		//Capabilities for the remote web driver
		DesiredCapabilities caps = new DesiredCapabilities();
		//Browser
		caps.setCapability(CapabilityType.BROWSER_NAME, browserUnderTest);
		//Browser version
		if (browserVersion != null) {
			caps.setCapability(CapabilityType.VERSION, browserVersion);
		}
		//Operating System
		caps.setCapability(CapabilityType.PLATFORM, getGridPlatformByOS(operatingSystem));
		//IE specific capabilities
		if (browserUnderTest.toLowerCase().contains("ie")
				|| browserUnderTest.toLowerCase().contains("iexplore")) {
			caps.setCapability("ignoreZoomSetting", true);
		}
		caps.setCapability("name", testName);
		//Create the remote web driver
		try {
			setDriver(new OrasiDriver(caps, new URL(getRemoteURL())));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Problem with creatting the remote web driver: ");

		}
	}
	
	/**
	 * 
	 * @param os
	 * @return
	 */
	private Platform getGridPlatformByOS(String os) {
		switch (os.toLowerCase()) {
		case "android":
			return Platform.ANDROID;
		case "windows":
			return Platform.WINDOWS;
		case "win8":
			return Platform.WIN8;
		case "win8.1":
			return Platform.WIN8_1;
		case "xp":
			return Platform.XP;
		case "linux":
			return Platform.LINUX;
		case "mac":
			return Platform.MAC;
		case "mavericks":
			return Platform.MAVERICKS;
		case "mountain_lion":
			return Platform.MOUNTAIN_LION;
		case "snow_leopard":
			return Platform.SNOW_LEOPARD;
		case "yosemite":
			return Platform.YOSEMITE;
		default:
			return Platform.ANY;
		}
	}

	// ************************************
	// ************************************
	// ************************************
	// PAGE OBJECT METHODS
	// ************************************
	// ************************************
	// ************************************

	/**
	 * loops for a predetermined amount of time (defined by
	 *          WebDriverSetup.getDefaultTestTimeout()) to determine if the DOM
	 *          is in a ready-state
	 * @return boolean: true is the DOM is completely loaded, false otherwise
	 * @param N
	 *            /A
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isDomComplete(getDriver());
	}

	/**
	 * loops for a predetermined amount of time (defined by
	 *          WebDriverSetup.getDefaultTestTimeout()) to determine if the
	 *          Element is not null
	 * @return boolean: true is the DOM is completely loaded, false otherwise
	 * @param clazz
	 *            - page class that is calling this method
	 * @param element
	 *            - element with which to determine if a page is loaded
	 *            TODO - CAN WE REMOVE THIS?
	 */
	@Deprecated
	public boolean pageLoaded(Class<?> clazz, Element element) {
		return new PageLoaded().isElementLoaded(clazz, getDriver(), element);
	}
	
	/**
	 *  loops for a predetermined amount of time (defined by
	 *          OrasiDriver.getElementTimeout()) to determine if the
	 *          Element is present in the DOM
	 * @param element - element with which to determine if a page is loaded
	 * @return boolean: true if the element is present in the DOM, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return PageLoaded.syncPresent(getDriver(), element);
	}

	

}
