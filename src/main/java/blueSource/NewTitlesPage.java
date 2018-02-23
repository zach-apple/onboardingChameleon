package blueSource;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class NewTitlesPage {
	private OrasiDriver driver = null;
	@FindBy(id = "title_name")
	private Textbox titleNameBox;
	@FindBy(css = "#new_title > div.actions > input")
	private Button btnNewTitle;
	@FindBy(css = "#content > h1")
	private Element hdrNewTitle;

	protected String newTitle = "aNewTitle";

	/**
	 * Constructor for the NewTitlesPage class, initializing default values.
	 */
	public NewTitlesPage() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
	}

	/**
	 * Returns true if the header on the current page has the value of the header on
	 * the New title page
	 * 
	 * @return true if navigation to New title was successful, false otherwise
	 */
	public boolean verifyNewTitlesNav() {
		return hdrNewTitle.getText().equals("New title");
	}

	/**
	 * Fills the 'Name' textbox with a new title
	 */
	public void addTitle() {
		// sends the name of the title to add, with a random integer ending for slight
		// ease on multiple test runs
		titleNameBox.sendKeys(newTitle + (int) (Math.random() * 500));
		// clicks the 'Create Title' button to submit the new title
		btnNewTitle.click();
	}

}
