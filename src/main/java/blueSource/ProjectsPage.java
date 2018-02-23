package blueSource;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.orasi.DriverManager;
import com.orasi.web.OrasiDriver;
import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Listbox;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.Webtable;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class ProjectsPage {
	private OrasiDriver driver = null;
	@FindBy(css = "#all-content > h1")
	private Element hdrProjects;
	@FindBy(name = "button")
	private Button btnAdd;
	@FindBy(id = "modal_1")
	private Element addForm;
	@FindBy(id = "project_name")
	private Textbox projName;
	@FindBy(id = "project_client_partner_id")
	private Listbox clientList;
	@FindBy(id = "project_start_date")
	private Textbox startDate;
	@FindBy(id = "project_end_date")
	private Textbox endDate;
	@FindBy(css = "#new_project > div.form-group.modal-footer > input")
	private Button btnCreate;
	@FindBy(id = "resource-content")
	private Webtable projTable;
	@FindBy(css = "#all-content > div.header-btn-section > div > div:nth-child(1) > label")
	private Button btnInactives;

	private String nameOfProject;

	/**
	 * Constructor for the ProjectsPage class, initializing default values.
	 */
	public ProjectsPage() {
		this.driver = DriverManager.getDriver();
		ElementFactory.initElements(driver, this);
		// sets the name of the project to add, with a random integer ending for slight
		// ease on multiple test runs
		nameOfProject = "testz2018" + (int) Math.random() * 500;
	}

	/**
	 * Returns true if the header on the current page has the value of the header on
	 * the Projects page
	 * 
	 * @return true if navigation to Projects was successful, false otherwise
	 */
	public boolean projectsPageNav() {
		return hdrProjects.getText().equals("Projects");
	}

	/**
	 * Clicks on the 'Add' button on the Projects page
	 */
	public void addProj() {
		btnAdd.click();
	}

	/**
	 * Returns true if the 'Add Project' form is visible
	 * 
	 * @return true if form is visible, false otherwise
	 */
	public boolean isAddFormVisible() {
		return addForm.isDisplayed();
	}

	/**
	 * Fills the 'Add project' form with valid input
	 */
	public void fillForm() {
		// fills the name field with nameOfProject
		projName.sendKeys(nameOfProject);
		// selects the first option from the 'Client partner' dropdown list
		Select sel = new Select(clientList);
		sel.selectByIndex(1);
		// enters valid date data
		startDate.sendKeys("06042018");
		endDate.sendKeys("06052018");
	}

	/**
	 * Clicks the 'Create Project' button on the 'Add project' form
	 */
	public void clickCreate() {
		btnCreate.click();
	}

	/**
	 * Returns true if the added project is now displayed
	 * 
	 * @return true if added project is displayed, false otherwise
	 */
	public boolean verifyAdd() {
		return projTable.findElement(By.linkText(nameOfProject)).isDisplayed();
	}

	/**
	 * Returns true if no project in the table is listed as 'Inactive'
	 * 
	 * @return true if there are no inactive projects shown, false otherwise
	 */
	public boolean verifyNoInactives() {
		return !btnInactives.isEnabled();

	}
}
