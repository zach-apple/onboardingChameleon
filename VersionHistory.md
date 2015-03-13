#Version History

##Version 1.0.0 - 02/25/2015
* Initial Creation

##Version 1.0.1 - 02/27/2015
* Updated POM.xml structure
* Updated Selenium Version to 2.44.0
* Started removal of unused imports
* Started adding additional comments to RestService.java and SoapService

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