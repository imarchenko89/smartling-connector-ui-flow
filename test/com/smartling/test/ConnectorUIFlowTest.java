package com.smartling.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConnectorUIFlowTest extends TestBase {

	@Test(priority = 1)
	public void logIn() {
		adminPage = loginPage.logInToUserPage(username, password);
		Assert.assertTrue(adminPage.isLoggedOK(adminUrl),
				"Hello Admin message isn't displayed");
	}
	
}