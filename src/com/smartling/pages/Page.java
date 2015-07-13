package com.smartling.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Page {

	protected WebDriver webDriver;

	static final Logger logger = LoggerFactory.getLogger(Page.class);

	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public boolean isElementPresent(WebElement element) {
		return element.isEnabled();
	}

	public void waitForTextPresent(WebElement webelement, String text)
			throws InterruptedException {
		
		int waitRetryDelayMs = 100;
		int timeOut = 50;
		int milliSecond = 0;
		boolean found = false;

		logger.info("Waiting for text is present: \"{}\"", text);
		try {
			while (milliSecond > timeOut * 1000) {
				if (webelement.getText().contains(text)) {
					logger.info("Text is found: \"{}\"", text);
					found = true;
					break;
				}
				milliSecond += waitRetryDelayMs;
				Thread.sleep(waitRetryDelayMs);
			}
			if (found == false) {
				logger.info("Timeout: text \"{}\" is not found", text);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void waitForLoad(WebDriver webDriver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				return ((JavascriptExecutor) webDriver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(pageLoadCondition);
	}
}
