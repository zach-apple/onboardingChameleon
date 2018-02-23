package AppleTests;

import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.LoginPage;
import blueSource.ProjectsPage;
import blueSource.TopNavigationBar;

public class AddProject extends WebBaseTest {
	@Test
	public void testAddProject() {
		TestReporter.logScenario("Verify that the user can add a project");
		TestReporter.logStep("Navigate to the BlueSource landing page and log in with valid credentials");
		setPageURL("https://bluesourcestaging.herokuapp.com");
		testStart("Successful Project Add");
		LoginPage loginPage = new LoginPage();
		loginPage.login();
		TopNavigationBar topNav = new TopNavigationBar();
		TestReporter.assertTrue(topNav.isLogoutVisible(), "Logout is visible");
		TestReporter.logStep("Navigate to the Projects page");
		topNav.navToProjects();
		TestReporter.logStep("Verify that the user is at the Projects page");
		ProjectsPage projectsPage = new ProjectsPage();
		TestReporter.assertTrue(projectsPage.projectsPageNav(), "Projects page is shown");
		TestReporter.logStep("User clicks on the 'Add' button");
		projectsPage.addProj();
		TestReporter.logStep("Verify that the 'Add Project' form is visble");
		projectsPage.isAddFormVisible();
		TestReporter.logStep("User fills form with valid data");
		projectsPage.fillForm();
		TestReporter.logStep("User clicks 'Create Project' button");
		projectsPage.clickCreate();
		TestReporter.logStep("Verify the project has been added");
		TestReporter.assertTrue(projectsPage.verifyAdd(), "Created project is added");
	}

}
