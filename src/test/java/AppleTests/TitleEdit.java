package AppleTests;

import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.EditTitlePage;
import blueSource.LoginPage;
import blueSource.TitlesPage;
import blueSource.TopNavigationBar;

public class TitleEdit extends WebBaseTest {
	@Test
	public void testEditTitle() {
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
		TestReporter.logStep("Click on a title to Edit and verify the page navigated to the edit page");
		titlesPage.editATitle();
		EditTitlePage editTitlePage = new EditTitlePage();
		TestReporter.assertTrue(editTitlePage.verifyEditNav(), "Verify edit page navigation");
		TestReporter.logStep("Edit the name of the title");
		editTitlePage.editTheTitle();
		TestReporter.logStep("Verify that the title was updated");
		TestReporter.assertTrue(titlesPage.verifyEdit(), "Verify edit");

	}
}
