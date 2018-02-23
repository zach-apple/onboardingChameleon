package blueSource;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.Webtable;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class DirectoryPage {
	private OrasiDriver driver = null;
	@FindBy(css = "#all-content > h1 > div > span:nth-child(1)")
	private Element hdrDirectory;
	@FindBy(xpath = "//input[@id='search-bar']")
	private Textbox txtSearch;
	@FindBy(css = "#resource-content > div.table-responsive > table")
	private Webtable tblCompanies;
	@FindBy(css = "#resource-content > div.table-responsive > h2")
	private Element hdrNoRowsFound;
	String searchCriteria;

	/**
	 * Constructor for the DirectoryPage class, initializing default values.
	 */
	public DirectoryPage() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
		// sets the search criteria to lookup in the directory
		searchCriteria = "123";
	}

	/**
	 * Returns true if the header on the current page has the value of the header on
	 * the Directory page
	 * 
	 * @return true if navigation to Directory was successful, false otherwise
	 */
	public boolean verifyDirectoryNav() {
		return hdrDirectory.getText().equals("Orasi Directory");
	}

	/**
	 * Enters the search criteria (predetermined) to lookup in the directory
	 */
	public void enterTextToSearch() {
		txtSearch.set(searchCriteria);
	}

	/**
	 * Parses through the directory table, ensuring each row contains the search
	 * criteria in any column. Returns true if search criteria exists in every row,
	 * or if there are no results based on the search criteria and the no rows found
	 * message is displayed
	 * 
	 * @return true if search is successful or no rows found, false otherwise
	 */
	public boolean verifySearch() {
		// set beenFound to false, as search has not started
		boolean beenFound = false;
		// create list of all tr elements in the table
		List<Element> list = tblCompanies.findElements(By.tagName("tr"));
		// parse each table row (tr)
		for (Element element : list) {
			// create a list of all td elements in the current table row
			List<Element> list2 = element.findElements(By.tagName("td"));
			// reset beenFound to false for the current row
			beenFound = false;
			// parse the td elements, checking that any contain the search criteria
			for (Element element2 : list2) {
				if (element2.getText().contains(searchCriteria)) {
					beenFound = true;
				}
			}
			// if after a row has been parsed and it did not contain the search criteria in
			// any td element, then return the false value
			if (!beenFound) {
				return beenFound;
			}
		}
		// return either that each row contained the search criteria, or that the no
		// rows found message was displayed
		return beenFound || hdrNoRowsFound.isDisplayed();

	}
}
