package blueSource;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Link;
import com.orasi.web.webelements.impl.internal.ElementFactory;

import org.openqa.selenium.support.FindBy;

public class TopNavigationBar {
	private OrasiDriver driver = null;
	@FindBy(linkText = "Logout")
	private Link lnkLogout;
	@FindBy(linkText = "Projects")
	private Link lnkProjects;
	@FindBy(linkText = "Directory")
	private Link lnkDirectory;
	@FindBy(linkText = "Admin")
	private Link dropmenuAdmin;
	@FindBy(linkText = "Titles")
	private Link lnkTitles;

	/**
	 * Constructor for the TopNavigationBar class, initializing default values.
	 */
	public TopNavigationBar() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
	}

	/**
	 * Returns true if the logout link is visible
	 * 
	 * @return true if logout link is visible, false otherwise
	 */
	public boolean isLogoutVisible() {
		return lnkLogout.isDisplayed();
	}

	/**
	 * Clicks the logout link to log the user out
	 */
	public void logout() {
		lnkLogout.click();
	}

	/**
	 * Clicks the Projects link to navigate the user to the Projects page
	 */
	public void navToProjects() {
		lnkProjects.click();
	}

	/**
	 * Clicks the Directory link to navigate the user to the Directory page
	 */
	public void navToDirectory() {
		lnkDirectory.click();
	}

	/**
	 * Clicks the Titles link to navigate the user to the Titles page
	 */
	public void navToTitles() {
		// click on the Admin menu to display the Tiles link
		dropmenuAdmin.click();
		// click the visible Titles link
		lnkTitles.click();
	}
}
