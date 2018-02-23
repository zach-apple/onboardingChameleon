package AppleTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.DirectoryPage;
import blueSource.LoginPage;
import blueSource.TopNavigationBar;

public class CompanySearchSuccess extends WebBaseTest {
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
	public void testSearch() {

		TestReporter.logStep("Navigate to the Directory page");
		topNav.navToDirectory();
		TestReporter.logStep("Verify the user is on the Directory page");
		DirectoryPage directoryPage = new DirectoryPage();
		// Need to wait for table to load?
		directoryPage.verifyDirectoryNav();
		TestReporter.logStep("User enters text into the search bar");
		directoryPage.enterTextToSearch();
		TestReporter.logStep("Verify companies listed contain search input");
		TestReporter.assertTrue(directoryPage.verifySearch(), "Filtered companies are shown");

	}
}
