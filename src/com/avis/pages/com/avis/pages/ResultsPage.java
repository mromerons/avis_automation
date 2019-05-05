package com.avis.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {
	
	private WebDriver driver;
	
	private By priceElements = By.xpath("//div[contains(@id, 'divAuto') and @style='display: block;']"
			+ "//div[@class='price-btn price-btn-large-copy']/div[@class='price-btn-copy']"
			+ "[not(ancestor::div[@id='divAutoRecomendadoContenedor'])]");
	private By sortButton = By.id("car-filters-wide-sort-by");
	private By priceSortAscOption = By.xpath("//a[@id='aOrdenaPrecio' and @data-orden='A']");
	private By priceSortDescOption = By.xpath("//a[@id='aOrdenaPrecio' and @data-orden='D']");
	private By carFilterButton = By.id("divfiltroAutos");
	//private By carFilterCars = By.xpath("//li[@data-vehicle-type='AUTOS']");
	//private By carFilterTrucksSUV = By.xpath("//li[@data-vehicle-type='CAMIONETAS/SUV']");
	private By carFilterAll = By.xpath("//li[@data-vehicle-type='All Vehicles']");
	
	
	public ResultsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void showAllCars() {
		driver.findElement(carFilterButton).click();
		driver.findElement(carFilterAll).click();
	}
	
	public ArrayList<String> getAllPrices() {
		ArrayList<String> allCarPrices = new ArrayList<String>();
		List<WebElement> allCarPricesRaw = driver.findElements(priceElements);
		for(WebElement carPrice : allCarPricesRaw) {
			allCarPrices.add(carPrice.getText().replaceAll("[$,[a-zA-Z]+\\s+]", ""));
		}		
		return allCarPrices;
	}
	
	public ResultsPage orderByPrice() {
		driver.findElement(sortButton).click();
		driver.findElement(priceSortDescOption).click();
		driver.findElement(sortButton).click();
		driver.findElement(priceSortAscOption).click();
		return this;
	}
	
	public void printList(ArrayList<String> list) {
		for(String listItem : list) {
			System.out.println("item: " + listItem);
		}
	}
}
