package com.smartling.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.smartling.pages.AdminPage;
import com.smartling.pages.LoginPage;
import com.smartling.utility.XmlPropertyLoader;

public class TestBase {

	protected WebDriver webDriver;
	protected String testUrl;
	protected String timeout;
	protected LoginPage loginPage;
	protected AdminPage adminPage;
	protected String username;
	protected String password;
	protected String adminUrl;

	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("logback.configurationFile",
				"src/com/smartling/utility/logbackConfig.xml");

		testUrl = XmlPropertyLoader.loadProperty("testsite.url");
		timeout = XmlPropertyLoader.loadProperty("implicit-timeout");
		username = XmlPropertyLoader.loadProperty("username");
		password = XmlPropertyLoader.loadProperty("password");
		adminUrl = XmlPropertyLoader.loadProperty("adminUrl");
		webDriver = new FirefoxDriver();
		webDriver.manage().timeouts()
				.implicitlyWait(Integer.valueOf(timeout), TimeUnit.SECONDS);
		webDriver.get(testUrl);
		webDriver.manage().window().maximize();
		loginPage = PageFactory.initElements(webDriver, LoginPage.class);
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}
}
