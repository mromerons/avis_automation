package com.avis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.avis.utils.Navigation;

public class CarsPage extends Navigation {
	
	private WebDriver driver;
	
	private By bookingFullForm = By.xpath("//div[@id='booking-form' and @class='with-full-form']");
	private By fromPlaceDummy = By.id("txtSucursalDummy");
	private By fromPlace = By.id("txtOficinaRentaInt");
	private By fromPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), 'Aeropuerto De Cancún')]");
	private By fromDate = By.id("frenta"); 
	private By returnPlace = By.id("txtOficinaDevInt");
	private By returnPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), 'Aeropuerto De La Ciudad De México')]");
	private By returnDate = By.id("fdev");
	private By genericPlaceOption;
	private By searchButton = By.id("btnContinuarInt");
	private By closeDatepicker = By.id("datepicker-popup-close");
	
	
	
	public CarsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public CarsPage clickFromPlaceDummy() throws InterruptedException {
		click(driver, this.fromPlaceDummy);
		clickFromPlace();
		pause_for(2);
		return this;
	}
	
	public CarsPage fillFromPlace(String value) throws InterruptedException {
		waitForElementToBeVisible(driver, driver.findElement(bookingFullForm), 10);
		sendKeys(driver, this.fromPlace, value);
		pause_for(1);
		return this;
	}
	
	public CarsPage clickFromPlace() {
		click(driver, this.fromPlace);
		return this;
	}
	
	public CarsPage clickFromDate() {
		click(driver, this.fromDate);
		return this;
	}
	
	public CarsPage setFromDate(String date) {
		sendKeys(driver, this.fromDate, date);
		return this;
	}
	
	public CarsPage clickReturnPlace() {
		click(driver, this.returnPlace);
		return this;
	}
	
	public CarsPage fillReturnPlace(String value) throws InterruptedException {
		sendKeys(driver, this.returnPlace, value);
		pause_for(1);
		return this;
	}
	
	public CarsPage clickReturnDate() {
		click(driver, this.returnDate);
		return this;
	}
	
	public CarsPage setReturnDate(String date) {
		sendKeys(driver, this.returnDate, date);
		return this;
	}
	
	public CarsPage selectPlaceOption(String place) throws InterruptedException {
		this.setPlace(place);
		click(driver, this.genericPlaceOption);
		waitForInvisibilityOfElement(driver, this.genericPlaceOption);
		return this;
	}
	
	private void setPlace(String place) {
		genericPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), '" + place + "')]");
	}
	
	public CarsPage closeDatepicker() {
		click(driver, this.closeDatepicker);
		waitForInvisibilityOfElement(driver, this.closeDatepicker);
		return this;
	}
	
	public ResultsPage searchCar() {	
		waitForJs(driver);
		click(driver, this.searchButton);
		return new ResultsPage(driver);
	}
	
}