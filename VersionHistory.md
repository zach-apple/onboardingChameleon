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