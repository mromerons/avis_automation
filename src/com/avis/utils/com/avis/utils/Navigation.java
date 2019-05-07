package com.avis.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigation extends CommonUtils{
	
	public static void click(WebDriver driver, By element) {
		waitForElementToBeClickable(driver, driver.findElement(element), 10).click();;
	}
	
	public static void sendKeys(WebDriver driver, By element, String value) {
		waitForElementToBeClickable(driver, driver.findElement(element), 10).sendKeys(value);
	}
	
	public static String getText(WebDriver driver, By element) {
		return waitForElementToBeVisible(driver, driver.findElement(element), 10).getText();
	}
	
	public static void js_click(WebDriver driver, By byElement) {
		WebElement element = driver.findElement(byElement);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void action_click(WebDriver driver, By byElement) {
		WebElement element = driver.findElement(byElement);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}
	
}
