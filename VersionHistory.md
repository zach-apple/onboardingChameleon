#Version History

##Version 1.0.0 - 02/25/2015
* Initial Creation

##Version 1.0.1 - 02/27/2015
* Updated POM.xml structure
* Updated Selenium Version to 2.44.0
* Started removal of unused imports
* Started adding additional comments to RestService.java and SoapService.java

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