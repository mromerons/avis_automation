package com.avis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarsPage {
	
	private WebDriver driver;
	
	// From Fields
	private By fromPlaceDummy = By.id("txtSucursalDummy");
	private By fromPlace = By.id("txtOficinaRentaInt");
	private By fromPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), 'Aeropuerto De Cancún')]");
	private By fromDate = By.id("frenta"); 
	
	// Return Fields
	private By returnPlace = By.id("txtOficinaDevInt");
	private By returnPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), 'Aeropuerto De La Ciudad De México')]");
	private By returnDate = By.id("fdev");
	
	private By genericPlaceOption;
	
	// Search Button
	private By searchButton = By.id("btnContinuarInt");
	
	// Datepicker Close Button
	private By closeDatepicker = By.id("datepicker-popup-close");
	
	
	
	private By setPlace(String place) {
		genericPlaceOption = By.xpath("//div[@class='angucomplete-result-fullname' and contains(normalize-space(), '" + place + "')]");
		return genericPlaceOption;
	}
	
	public CarsPage selectPlaceOption(String place) {
		setPlace(place);
		driver.findElement(this.genericPlaceOption).click();
		return this;
	}
	
	
	
	public CarsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public CarsPage clickFromPlaceDummy() {
		driver.findElement(this.fromPlaceDummy).click();
		return this;
	}
	
	
	public CarsPage fillFromPlace(String value) {
		driver.findElement(this.fromPlace).sendKeys(value);
		return this;
	}
	
	public CarsPage selectFromPlaceOption(){
		driver.findElement(this.fromPlaceOption).click();
		return this;
	}
	
	public CarsPage clickFromPlace() {
		driver.findElement(this.fromPlace).click();
		return this;
	}
	
	public CarsPage clickFromDate() {
		driver.findElement(this.fromDate).click();
		return this;
	}
	
	public CarsPage setFromDate(String date) {
		driver.findElement(this.fromDate).sendKeys(date);
		return this;
	}
	
	
	public CarsPage clickReturnPlace() {
		driver.findElement(this.returnPlace).click();
		return this;
	}
	
	public CarsPage fillReturnPlace(String value) {
		driver.findElement(this.returnPlace).sendKeys(value);
		return this;
	}
	
	public CarsPage selectReturnPlaceOption() {
		driver.findElement(this.returnPlaceOption).click();
		return this;
	}
	
	public CarsPage clickReturnDate() {
		driver.findElement(this.returnDate).click();
		return this;
	}
	
	public CarsPage setReturnDate(String date) {
		driver.findElement(this.returnDate).sendKeys(date);
		return this;
	}
	
	public CarsPage closeDatepicker() {
		driver.findElement(closeDatepicker).click();
		return this;
	}
	
	public ResultsPage searchCar() {	
		driver.findElement(searchButton).click();
		return new ResultsPage(driver);
	}
	
	
}