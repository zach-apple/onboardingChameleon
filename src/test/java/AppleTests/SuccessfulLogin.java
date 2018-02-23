package AppleTests;

import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.LoginPage;
import blueSource.TopNavigationBar;

public class SuccessfulLogin extends WebBaseTest {
	@Test
	public void testLogin() {
		TestReporter.logScenario("Verifies that the user can login successfully");
		TestReporter.logStep("Navigate to the BlueSource landing page");
		setPageURL("https://bluesourcestaging.herokuapp.com");
		testStart("Successful login");
		TestReporter.logStep("Login");
		LoginPage loginPage = new LoginPage();
		loginPage.login();
		TestReporter.logStep("Verify that the user could log in");
		TopNavigationBar topNav = new TopNavigationBar();
		TestReporter.assertTrue(topNav.isLogoutVisible(), "Logout is visible");
		TestReporter.logStep("Logout");
		topNav.logout();
		TestReporter.logStep("Verify that the user could log out");
		TestReporter.assertTrue(loginPage.loggedOut(), "Verify that the user is logged out");

	}
}
