## Orasi Selenium Core Project

This is the homepage for the Orasi Selenium Core libraries. These libraries contain both original code created by Orasi developers
and open source code from various other projects which are listed further down. With these libraries, consumers have access to extended 
functionality for creating testing suites for Web Applications and API Web Services.

## Web Application Testing
*ToDo*

##API Web Service Testing
###Soap Services  
*ToDo*
###REST Services  
*ToDo*

## Third Party Resources
These resources are being used directly, or have been extended upon.
* [Selenium 2.44](https://github.com/SeleniumHQ/selenium): The base library that allows for automation of web browsers.
* [TestNG 6.8.7](https://github.com/cbeust/testng/): Test execution framework that extends JUnit tests and allows more flexibility for testing.
* [Smartbear SoapUI 4.5.0](https://github.com/SmartBear/soapui): Allows consumer to build requests files at runtime and sends request through HTTPClient
* **More to come...**
	
## Accessing this module
* Clone/Fork Git repository: This will be the preferred method if the consumer does not want all the functionality of the libraries,
							but still wants access to some. This also allows the consumer to control what Maven dependencies are used and what functionality he wants to keep or tweak.

* Link Git repository as Maven Module: This method will separate the core functionality from the consumers source code. The core module
										can then be pulled at any time for general updates, and allows consumers to control what updates
										are allowed. This is recommended for advanced Maven and GIT users.

* Add as Maven Dependency: By adding the Orasi Selenium Core Jar to the Maven POM file, the consumer will always have easy access to
							updated code. This also allows the option for versioning in the case the consumer needs to test the core
							updates before fully committing to them.