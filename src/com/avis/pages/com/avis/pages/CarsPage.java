package com.avis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.avis.utils.Navigation;

public class CarsPage extends Navigation {
	
	private WebDriver driver;
	
	private By bookingFullForm = By.xpath("//div[@id='booking-form' and @class='with-full-form']");
	private By fromPlaceDummy = By.id("txtSucursalDummy");
	private By fromPlace = By.id("txtOficinaRentaInt");
	private By fromDate = By.id("frenta"); 
	private By returnPlace = By.id("txtOficinaDevInt");
	private By returnDate = By.id("fdev");
	private By genericPlaceOption;
	private By searchButton = By.id("btnContinuarInt");
	private By datePicker = By.id("datepicker-popup");
	private By closeDatepicker = By.id("datepicker-popup-close");
	
	
	public CarsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public CarsPage setFromPlace(String value) throws InterruptedException {
		clickFromPlaceDummy();
		fillFromPlace(value);
		selectPlaceOption(value);
		return this;
	}
	
	public CarsPage setFromDate(String value) {
		clickFromDate();
		fillFromDate(value);
		closeDatepicker();
		return this;
	}
	
	public CarsPage setReturnPlace(String value) throws InterruptedException {
		clickReturnPlace();
		fillReturnPlace(value);
		selectPlaceOption(value);
		return this;
	}
	
	public CarsPage setReturnDate(String value) {
		clickReturnDate();
		fillReturnDate(value);
		closeDatepicker();
		return this;
	}
	
	public void clickFromPlaceDummy() throws InterruptedException {
		click(driver, this.fromPlaceDummy);
		clickFromPlace();
		pause_for(2);
	}
	
	public void clickFromPlace() {
		click(driver, this.fromPlace);
	}
	
	public void fillFromPlace(String value) throws InterruptedException {
		waitForElementToBeVisible(driver, driver.findElement(bookingFullForm), 10);
		sendKeys(driver, this.fromPlace, value);
		pause_for(1);
	}
	
	public void clickFromDate() {
		click(driver, this.fromDate);
	}
	
	public void fillFromDate(String date) {
		sendKeys(driver, this.fromDate, date);
	}
	
	public void clickReturnPlace() {
		click(driver, this.returnPlace);
	}
	
	public void fillReturnPlace(String value) throws InterruptedException {
		sendKeys(driver, this.returnPlace, value);
		pause_for(1);
	}
	
	public void clickReturnDate() {
		click(driver, this.returnDate);
	}
	
	public void fillReturnDate(String date) {
		sendKeys(driver, this.returnDate, date);
	}
	
	public void selectPlaceOption(String place) throws InterruptedException {
		this.setPlace(place);
		click(driver, this.genericPlaceOption);
		waitForInvisibilityOfElement(driver, this.genericPlaceOption);
	}
	
	private void setPlace(String place) {
		genericPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), '" + place + "')]");
	}
	
	public void closeDatepicker() {
		click(driver, this.closeDatepicker);
		waitForInvisibilityOfElement(driver, datePicker);
	}
	
	public ResultsPage searchCar() {	
		waitForJs(driver);
		click(driver, this.searchButton);
		return new ResultsPage(driver);
	}
	
}