package AppleTests;

import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;

import blueSource.LoginPage;

public class UnsuccessfulLogin extends WebBaseTest {

	@Test
	public void testUnsuccessfulLogin() {
		TestReporter.logScenario("Verify that the user cannot be logged in with invalid data");
		TestReporter.logStep("Navigate to the BlueSource landing page");
		setPageURL("https://bluesourcestaging.herokuapp.com");
		testStart("Unsuccessful login");
		TestReporter.logStep("Attempt login with bad username");
		LoginPage loginPage = new LoginPage();
		loginPage.invalidLogin();
		TestReporter.logStep("Verify alert for username not found");
		TestReporter.assertTrue(loginPage.notLoggedIn(), "Alert is visible");

	}
}
