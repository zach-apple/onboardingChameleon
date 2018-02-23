package blueSource;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Link;
import com.orasi.web.webelements.Webtable;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class TitlesPage {
	private OrasiDriver driver = null;
	@FindBy(linkText = "New Title")
	private Link lnkNewTitle;
	@FindBy(xpath = "//*[@id=\"content\"]/table/tbody")
	private Webtable tblTitles;
	@FindBy(css = "#content > div")
	private Element alertTitleAdded;

	/**
	 * Constructor for the TitlesPage class, initializing default values.
	 */
	public TitlesPage() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
	}

	/**
	 * Returns true if the table from the Titles page is displayed.
	 * 
	 * @return true if Titles table is displayed, false otherwise.
	 */
	public boolean verifyTitlesNav() {
		return tblTitles.isDisplayed();
	}

	/**
	 * Clicks on the 'New title' link on the Titles page
	 */
	public void newTitle() {
		lnkNewTitle.click();
	}

	/**
	 * Returns true if an alert is displayed with the message "Title successfully
	 * created."
	 * 
	 * @return true if successful creation alert is displayed, false otherwise
	 */
	public boolean verifyTitleAdded() {
		return alertTitleAdded.isDisplayed() && alertTitleAdded.getText().equals("×\n" + "Title successfully created.");
	}

	/**
	 * Finds a random title to edit by its row number, and changes the titles to a
	 * new title
	 */
	public void editATitle() {
		// grab an int in the range [1,rowCount]
		int rand = (int) Math.ceil(Math.random() * tblTitles.getRowCount());
		// build the xpath for the randomly chosen table row's edit button
		String xpath = "//*[@id='content']/table/tbody/tr[" + rand + "]/td/div/a[1]/span";
		// click the edit button
		driver.findElement(By.xpath(xpath)).click();
	}

	/**
	 * Returns true if an alert is displayed with the message "Title successfully
	 * updated."
	 * 
	 * @return true if successful update alert is displayed, false otherwise
	 */
	public boolean verifyEdit() {
		return alertTitleAdded.isDisplayed() && alertTitleAdded.getText().equals("×\n" + "Title successfully updated.");
	}
}
