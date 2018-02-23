package AppleTests;

import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.LoginPage;
import blueSource.NewTitlesPage;
import blueSource.TitlesPage;
import blueSource.TopNavigationBar;

public class TitleAdd extends WebBaseTest {
	@Test
	public void testAddTitle() {
		TestReporter.logScenario("Verify the user can add a Title");
		TestReporter.logStep("Navigate to the BlueSource landing page and log in with valid credentials");
		setPageURL("https://bluesourcestaging.herokuapp.com");
		testStart("Successful Title Add");
		LoginPage loginPage = new LoginPage();
		loginPage.login();
		TopNavigationBar topNav = new TopNavigationBar();
		TestReporter.assertTrue(topNav.isLogoutVisible(), "Logout is visible");
		TestReporter.logStep("Navigate to the Titles page");
		topNav.navToTitles();
		TestReporter.logStep("Verify navigation to the Titles page");
		TitlesPage titlesPage = new TitlesPage();
		TestReporter.assertTrue(titlesPage.verifyTitlesNav(), "Verify navigation to Titles");
		TestReporter.logStep("Click on the 'New Title' link");
		titlesPage.newTitle();
		TestReporter.logStep("Verify New title page");
		NewTitlesPage newTitlesPage = new NewTitlesPage();
		TestReporter.assertTrue(newTitlesPage.verifyNewTitlesNav(), "Verify navigation to New Titles");
		TestReporter.logStep("Add new title");
		newTitlesPage.addTitle();
		TestReporter.logStep("Verify title was added");
		// TODO
		TestReporter.assertTrue(titlesPage.verifyTitleAdded(), "Verify title found after add");

	}
}
