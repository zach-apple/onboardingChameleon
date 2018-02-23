package AppleTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.LoginPage;
import blueSource.ProjectsPage;
import blueSource.TopNavigationBar;

public class InactivesHidden extends WebBaseTest {
	private TopNavigationBar topNav;

	@BeforeMethod
	private void getLoggedIn() {
		TestReporter.logScenario("Verify the user can add a Title");
		TestReporter.logStep("Navigate to the BlueSource landing page and log in with valid credentials");
		setPageURL("https://bluesourcestaging.herokuapp.com");
		testStart("Successful Title Add");
		LoginPage loginPage = new LoginPage();
		loginPage.login();
		topNav = new TopNavigationBar();
		TestReporter.assertTrue(topNav.isLogoutVisible(), "Logout is visible");
	}

	@Test
	public void testInactives() {
		TestReporter.logStep("Navigate to the Projects page");
		topNav.navToProjects();
		TestReporter.logStep("Verify that the user is at the Projects page");
		ProjectsPage projectsPage = new ProjectsPage();
		TestReporter.assertTrue(projectsPage.projectsPageNav(), "Projects page is shown");
		TestReporter.logStep("Verify that no shown projects are inactive.");
		TestReporter.assertFalse(projectsPage.verifyNoInactives(),
				"Verifiy no inactives and 'Show Inactives' button disabled");

	}
}
