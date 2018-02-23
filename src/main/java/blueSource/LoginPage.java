package blueSource;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class LoginPage {
	private OrasiDriver driver = null;

	@FindBy(id = "employee_username")
	private Textbox txtUsername;
	@FindBy(id = "employee_password")
	private Textbox txtPassword;
	@FindBy(name = "commit")
	private Button btnLogin;
	@FindBy(css = "#content > div.alert.alert-danger.alert-dismissable")
	private Element alertUsername;

	/**
	 * Constructor for the LoginPage class, initializing default values.
	 */
	public LoginPage() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
	}

	/**
	 * Logs the user in with valid credentials.
	 */
	public void login() {
		txtUsername.sendKeys("company.admin");
		txtPassword.sendKeys("123");
		btnLogin.click();
	}

	/**
	 * Returns true if the user has been logged out successfully
	 * 
	 * @return true if log out is successful, false otherwise
	 */
	public boolean loggedOut() {
		return txtUsername.isDisplayed() && txtPassword.isDisplayed();
	}

	/**
	 * Attempts to log the user in, but with invalid credentials
	 */
	public void invalidLogin() {
		txtUsername.sendKeys("companyadmin");
		txtPassword.sendKeys("123");
		btnLogin.click();
	}

	/**
	 * Returns true if the alert for incorrect username/password combo is displayed
	 * 
	 * @return true if the alert is displayed, false otherwise
	 */
	public boolean notLoggedIn() {
		return alertUsername.isDisplayed();
	}

}
