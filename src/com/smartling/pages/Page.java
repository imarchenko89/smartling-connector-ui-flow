package com.smartling.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

	protected WebDriver webDriver;

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
		try {
			element.isEnabled();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void WaitForTextPresent(WebElement webelement, String text)
			throws InterruptedException {
		int waitRetryDelayMs = 100;
		int timeOut = 500;
		boolean first = true;

		for (int milliSecond = 0;; milliSecond += waitRetryDelayMs) {
			if (milliSecond > timeOut * 1000) {
				System.out
						.println("Timeout: Text '" + text + "' is not found ");
				break;
			}

			if (webelement.getText().contains(text)) {
				if (!first)
					System.out.println("Text is found: '" + text + "'");
				break;
			}

			if (first)
				System.out.println("Waiting for text is present: '" + text
						+ "'");

			first = false;
			Thread.sleep(waitRetryDelayMs);
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
