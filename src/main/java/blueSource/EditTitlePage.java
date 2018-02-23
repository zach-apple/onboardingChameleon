package blueSource;

import org.openqa.selenium.support.FindBy;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class EditTitlePage {
	private OrasiDriver driver = null;
	@FindBy(xpath = "//input[@id='title_name']")
	private Textbox nameBox;
	@FindBy(name = "commit")
	private Button updateTitle;
	@FindBy(css = "#content > h1")
	private Element hdrEditTitle;
	String newTitle;

	/**
	 * Constructor for the EditTitlePage class, initializing default values.
	 */
	public EditTitlePage() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
		// sets the name of the title to add, with a random integer ending for slight
		// ease on multiple test runs
		newTitle = "newTitle" + (int) Math.random() * 500;
	}

	/**
	 * Returns true if the header on the current page has the value of the header on
	 * the Editing title page
	 * 
	 * @return true if navigation to Editing title was successful, false otherwise
	 */
	public boolean verifyEditNav() {
		return hdrEditTitle.getText().equals("Editing title");
	}

	/**
	 * Fills the 'Name' textbox with the title to change to
	 */
	public void editTheTitle() {
		nameBox.sendKeys(newTitle);
		// clicks the 'Update Title' button to submit the changed title
		updateTitle.click();
	}

}
