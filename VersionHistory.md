#Version History

##Version 1.0.7 - 10/10/2016
* **Enhancements**
 * [**com.orasi.utils.TestReporter**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
   New logging methods have been added and TRACE logs have been added to various low-level areas of the toolkit. Usability is as follows
   	 * TestReporter.NONE (Default):  No additional info printed to console
	 * TestReporter.INFO: Will print useful information to console such as URL's, parameters, and RQ/RS
	 * TestReporter.DEBUG: Will print debugging information for test developer to console 
	 * TestReporter.TRACE: Will print low level information to console from the framework 
  * New Methods
    * setDebugLevel(TestReporter.___) - Using on the the options above (NONE, INFO, DEBUG, or TRACE) will display logs in the console and TestNG report up to that level
    * logInfo(String log) - The first level of logs. This are intended to write useful info to the console to display what the script is doing, but isn't always needed. 
    * logDebug(String log) - The second level of logs intended for developers where they may leave debug messages to display to step through more complex automation code
    * logTrace(String log) - This level of log is intended for the Toolkit itself, where almost every miniscule action in the low levels of the code is displayed and recorded. This is intended to be used for when there are issues in the toolkit, and assistance from other developers is required.
 In addition, new Soft Assertions have been added. This allows a test to report out multiple failures until explictly called to fail
    * softAssertTrue(boolean condition, String description) - If condition is not met, report to TestNG log of failure. If assertAll() is called, test will fail.
    * softAssertFalse(boolean condition, String description) - If condition is not met, report to TestNG log of failure. If assertAll() is called, test will fail.
    * softAssertEquals(Object value1, Object value2, String description) - If both objects are not equal, report to TestNG log of failure. If assertAll() is called, test will fail.
    * softAssertNull(Object condition, String description) - If object condition is not null, report to TestNG log of failure. If assertAll() is called, test will fail.
    * softAssertNotNull(Object condition, String description) - If object condition is null, report to TestNG log of failure. If assertAll() is called, test will fail.   
    * assertAll() - If any soft assert have failed, fail test 
 * [**com.orasi.utils.TestEnvironment**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
   * Removed a lot of code that was made redundant when OrasiDriver was created.
   * Removed location type of "Remote" in favor of dedicated "Sauce" and "Grid".
   * Added location type of "Mobile".   
 * [**com.orasi.utils.ExtendedExpectedConditions**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)  
   This class contains several helper methods for Explicit Waits statements to use in conjunction with the WebDriverWait class. 
   * elementToBeHidden(WebElement element) - An expectation for checking if the given element is hidden on the screen
   * elementToBeVisible(WebElement element) - An expectation for checking if the given element is visible on the screen
   * findWindowWithTitleAndSwitchToIt(String title) - Loop through windows for duration to see if it finds a title with exact queried text
   * findWindowContainsTitleAndSwitchToIt(String title) - Loop through windows for duration to see if it finds a title that contains queried text
   * findWindowMatchesTitleAndSwitchToIt(String regex) - Loop through windows for duration to see if it finds a title with the matching regex pattern
   * textToMatchInElement(WebElement element, String regex) - An expectation for checking if the given text is present in the specified element
   * textToBePresentInElementAttribute(WebElement element, String attribute, String text) - An expectation for checking if the given text is present in the specified elements value attribute
   * textToMatchInElementAttribute(WebElement element, String attribute, String regex) - An expectation for checking if the given regex matches the given element attribute
   * textToBePresentInElementCssProperty(WebElement element, String cssProperty, String text) - An expectation for checking if the given text is present in CSS property the specified elements property value 
   * textToMatchInElementCssProperty(WebElement element, String cssProperty, String regex) - An expectation for checking if the given regex matches the given element CSS Property
   
 * [**com.orasi.utils.DataWarehouse**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)

##Version 1.0.6 - 11/30/2015
* [**com.orasi.utils.OrasiDriver**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
  * New methods
    * getDriverCapability().browserName() - returns browser name from WebDriver Capabilities
    * getDriverCapability().browserVersion() - returns browser version from WebDriver Capabilities
    * getDriverCapability().platformOS() - returns OS name, major and minor version from WebDriver Capabilities
	
  * Enhancements
   * Updated internal core code to associate driver to elements when the OrasiDriver.find methods or @FindBy annotations are used. This allows each element to have access to the OrasiDriver to do javascript execution or set timeouts. This removes the need to sent the driver in as a parameter for methods such as jsClick where Javascript is called to do a click. All methods have been updated as such.
   * Added ability to reach the Angular locators using the ByNG class
     * driver.findTextbox(ByNG.model("user.name")).set("blah");
     * driver.findButton(ByNG.buttonText("Login")).click();
     * driver.findElement(ByNG.controller("HeaderController")).syncPresent();
     * driver.findElement(ByNG.repeater("Employee in Employees)).syncVisible();

* [**com.orasi.utils.TestEnvironment**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
  * New methods
    * setThreadDriver() - force the driver to be multithreaded for multiple data-provider data iterations if true

* [**com.orasi.utils.FrameHandler**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
  * New methods
    * getCurrentFrameName(WebDriver driver) - return the name of the current frame
	* moveToSiblingFrame(WebDriver driver, String frameName) - switch to a frame in the current domain of frames using frame name.
	* moveToSiblingFrame(WebDriver driver, By byFrameLocator) - switch to a frame in the current domain of frames By locator.
	* moveToChildFrame(WebDriver driver, String frameName) - switch to a frame in the next domain of frames using frame name.
	* moveToChildFrame(WebDriver driver, By byFrameLocator) - switch to a frame in the next domain of frames By locator.
	* moveToChildFrame(WebDriver driver, String[] frameName) - Drill down multiple domains of frames using each frame name in array.
	* moveToChildFrame(WebDriver driver, By[] byFrameLocator) - Drill down multiple domains of frames using each By locator in array.
	* moveToDefaultContext(WebDriver driver) - Created to complete frame navigations
	* moveToParentFrame(WebDriver driver) - Created to complete frame navigations

* [**com.orasi.core.Beta**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/core)	
 * New annotation to mark methods, classes and features. Indicates that an item is in active development. This item should not be used as it may not be working, working as intended or removed at a future date 

* **Angular support**
 * Organized classes to fall under a single package. Updated classes with issues and allowed extentions to OrasiDriver 

##Version 1.0.5 - 10/20/2015
* [**com.orasi.utils.OrasiDriver**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
	* Instantiation requirements
		* public OrasiDriver(DesiredCapabilities caps)
			* Launches locally 
			* Driver type is accessed from DesiredCapabilities (ie. DesiredCapabilities.firefox() or DesiredCapabilities.chrome())
			* Expects at least the driver type to be defined
		*  public OrasiDriver(DesiredCapabilities caps, URL url)
			* Launches RemoteWebDriver
			
	* New methods for accessing Element Interfaces
		*  public Element findElement(By by)
    	*  public Textbox findTextbox(By by)
    	*  public Button findButton(By by)
    	*  public Checkbox findCheckbox(By by)
    	*  public Label findLabel(By by)
    	*  public Link findLink(By by)
    	*  public Listbox findListbox(By by)
    	*  public RadioGroup findRadioGroup(By by)     
    	*  public Webtable findWebtable(By by)
    	*  public WebElement findWebElement(By by)   // Original driver.findElement
	* New timeout methods
	 	* Javascript timeouts
 			* public void setScriptTimeout(int timeout) 
 			* public void setScriptTimeout(int timeout, TimeUnit unit) 
 				* Above methods can replace: driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS)
 			* public int getScriptTimeout();
 			
	 	* Page timeouts 
 			* public void setPageTimeout(int timeout)
 			* public void setPageTimeout(int timeout, TimeUnit unit)
 				* Above methods can replace: driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS)
 			* public int getPageTimeout()
 			 
	 	* Element timeouts
 			* public void setElementTimeout(int timeout)
 			* public void setElementTimeout(int timeout, TimeUnit unit)
 				* Above methods can replace: driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS)
 			* public int getElementTimeout()

		* Resetting what the original timeout was
```java
int currentTimeout = driver.getElementTimeout();	
driver.setElementTimeout(0);
//do stuff
driver.setElementTimeout(currentTimeout);
```

	* New Javascript call methods
		* public Object executeJavaScript(String script, Object... parameters)
			* Above method can replace: ((JavascriptExecutor) driver).executeScript(String script, Object... parameters)
		* public Object executeAsyncJavaScript(String script, Object... parameters)
			* Above method can replace: ((JavascriptExecutor) driver).executeAsyncScript(String script, Object... parameters)
	
	* Get Session ID
		* public String getSessionId()
			* Above method can replace: ((RemoteWebDriver) driver).getSessionId().toString() 
* [**com.orasi.utils.TestEnvironment**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
	* Updated to use OrasiDriver as main launching driver	
	* Updated to allow ability to launch the Microsoft EdgeDriver
* **POM.xml**
	* Updated Selenium from 2.46.0 to 2.47.2
	* Update httpcore and httpclient from 4.4 to 4.4.1
	* Removed redundant dependencies
	
* **EdgeDriver**
	* EdgeDriver can now be executed locally, on the grid, or in SauceLabs using the follow parameters in the TestNG XML
		* browserUnderTest = "MicrosoftEdge"
		* browserVersion = "20.10240"
		* operatingSystem = "Windows 10"
	* There are many unsupported methods in EdgeDriver that are commonly used amongst the other drivers. Some of the issues can be tracked in [our Edge tracker](https://github.com/Orasi/Selenium-Java-Core/milestones/Non%20Release%20-%20Edge%20Driver%20Issue%20Tracker)
* **General Class Updates**
	* Removed all unused imports
	* Updated to have default line endings

* **Refactored Classes or Methods**
	* [DateTimeConversion](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/date/DateTimeConversion.java) - Deprecated existing methods due to redundancy and scalability with other formats
		* **These methods are removed**
			* convertToDate(String daysOut)
			* convertToDateYYYYMMDD(String daysOut)
			* convertToDateMMDDYY(String daysOut)
			* format(String date, String format)
		* New DateTimeConversion methods 
			* convert(String date, String fromFormat, String toFormat
			* convert(Date date, String toFormat)
			* getDaysOut(String daysOut, String format)
	* [Webtable](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Webtable.java) 
		* **These methods are removed**
		* All original methods deprecated. Refactored with better handling, improving speed of some methods up to 300%. 
		* Removed redundant code from overridden methods. 
		* All methods now accept the TestEnvironment class instead of WebDriver class
* **Travis CI**
	* Commits and pull request merges to the master and stage branch will now trigger a sanity check on [Travis-CI](https://travis-ci.org/Orasi/Selenium-Java-Core).

* **Issues fixed**
	* [Issue #16- XMLTools.validateNodeContainsValueByXPath - Node loop exiting before last node can be retrieved](https://github.com/Orasi/Selenium-Java-Core/issues/16) 
	
##Version 1.0.4 - 06/11/2015
* [**com.orasi.utils.TestEnvironment**](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils)
	* Created class to facilitate full parallel remote testing that will only be limited by the number of nodes on the Selenium grid
		* Absorb WebDriverSetup and all associated fields required to create a WebDriver, both local and remote
		* Absorb the page class methods pageLoaded(), pageLoaded(Class<?> clazz, Element element) and initializePage(Class<?> clazz)
		* Absorb the fields required for WebDriver setup
			* protected String applicationUnderTest
			* protected String browserUnderTest
			* protected String browserVersion
			* protected String operatingSystem
			* protected String runLocation
			* protected String environment
			* protected String testName
			
* **POM.xml**
	* Updated Selenium to 2.46.0
	* Updated other jars to their latest stable release
	* Adding Allure Reports Dependency

* **Angular Support**
	* Added new angular element By locater for Show directive. Usage in Page classes with PageFactory:
			
			@FindByNG(ngShow = "descriptor")
			private Label labTitle;
		
* **General Class Updates**
	* [SoapService](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/api/soapServices/core/SoapService.java)  
		* Removed refactored StringBufferInputStream in place of ByteArrayInputStream
		* Removed unused imports
	* [Base64Encoder](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/Base64Coder.java) 
		- Added main method to allow user generate an encoded string quickly	
	* [Constants](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/Base64Coder.java) 
		- Added two new constants, Element_Timeout and Page_Timeout
	* [Element](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Element.java) /
	[ElementImpl](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/impl/ElementImpl.java) 
		- getElementIdentifier and getElementLocator now supports HTMLUnit driver
		- Removed redundant code from overridden methods
	* [Link](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Link.java) /
	[LinkImpl](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/impl/LinkImpl.java) 
		- Adding getURL() method
	* [Listbox](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Listbox.java) /
	[ListboxImpl](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/impl/ListboxImpl.java) 
		- Refactored isSelected method. Added methods getAllSelectedOptions and isMultiple to help support multi-listboxes
	* [Radiogroup](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Radiogroup.java) /
	[RadiogroupImpl](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/impl/RadiogroupImpl.java) 
		- Marked some methods as private
	* [Textbox](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Textbox.java) /
	[TextboxImpl](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/impl/TextboxImpl.java) 
		- Corrected places where element field was used instead of getWrappedElement()
	* [PageLoaded](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/PageLoaded.java) 
		- Added overloaded constructor to pass in the TestEnvironment, giving the option for methods not to use a parameter
	* [Screenshot](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/Screenshot.java) 
		- Refactored. Now screenshots will be taken automatically upon test failure. Will also create screenshot and attach 
						to Allure Report if Allure is being used.
	* [TestReporter](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/TestReporter.java) 
		- Added method logFailure to remove redundancy
		
* **Refactored Classes or Methods**
	* [DateTimeConversion](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/date/DateTimeConversion.java) - Deprecated existing methods due to redundancy and scalability with other formats
		* **These methods are scheduled to be removed in 1.0.5**
			* convertToDate(String daysOut)
			* convertToDateYYYYMMDD(String daysOut)
			* convertToDateMMDDYY(String daysOut)
			* format(String date, String format)
		* New DateTimeConversion methods
			* convert(String date, String fromFormat, String toFormat
			* convert(Date date, String toFormat)
			* getDaysOut(String daysOut, String format)
	* [Webtable](https://github.com/Orasi/Selenium-Java-Core/tree/master/src/main/java/com/orasi/utils/core/interfaces/Webtable.java) 
		* **All deprecated methods are scheduled to be removed in 1.0.5**
		* All original methods deprecated. Refactored with better handling, improving speed of some methods up to 300%. 
		* Removed redundant code from overridden methods. 
		* All methods now accept the TestEnvironment class instead of WebDriver class
* **Creation of unit tests**
	
##Version 1.0.3 - 03/26/2015
* **com.jenkins API**
	* Created methods to interact with a Jenkins server to get various information about a jobs latest build. Additional expansions for Jenkins are planned.

* **com.orasi.utils.WebDriverSetup**
	* Uncommented lines of code that was not intended to be commented, preventing the Chrome Driver from being recognized
	* Removed static modifier from the driver in WebDriverSetup which was causing errors in parallel testing.
	
* Rollback to Selenium Version 2.43.1 due to Webdriver Platform discrepancies
	* https://code.google.com/p/selenium/issues/detail?id=8333
	* https://code.google.com/p/selenium/issues/detail?id=8083
	
* Adding JUnit 4.12 dependency to the pom.xml to address issues where JUnit may not be associated with client project

* Adding Sauce Lab dependencies to pom.xml to allow interaction to Sauce Labs
	* sauce_java_common
	* sauce_junit
	* sauce_testng
	* saucerest
	* selenium-client-factory
	* sauce-ondemand-driver
	
##Version 1.0.2 - 03/13/2015
* **POM.xml** - Added Sauce Lab dependencies 
	* sauce_java_common
	* sauce_junit
	* sauce-ondemand-driver
	* selenium-client-factory

* **utils.Constants**
	* Removed unused constants
	* Removed client specific constants
	* Added constants to reference system properties

* **utils.WebDriverSetup**
	* Removed the use of many fields to reference system properties instead
	* Added gets/sets for remote Selenium Hub URL and Test name
	* Added additional constructor to allow option for Test name
	
##Version 1.0.1 - 02/27/2015
* Updated POM.xml structure
* Updated Selenium Version to 2.44.0
* Started removal of unused imports
* Started adding additional comments to RestService.java and SoapService.java	 
 
##Version 1.0.0 - 02/25/2015
* Initial Creation
