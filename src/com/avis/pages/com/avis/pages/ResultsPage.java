package com.avis.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.avis.utils.Navigation;

public class ResultsPage extends Navigation{
	
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
	private By carFilterAllOption = By.xpath("//li[@data-vehicle-type='All Vehicles']");
	
	
	public ResultsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void showAllCars() {
		click(driver, carFilterButton);
		click(driver, carFilterAllOption);
	}
	
	public ArrayList<Integer> getAllPrices() {
		ArrayList<Integer> allCarPrices = new ArrayList<Integer>();
		List<WebElement> allCarPricesRaw = driver.findElements(priceElements);
		for(WebElement carPrice : allCarPricesRaw) {
			allCarPrices.add(Integer.valueOf(carPrice.getText().replaceAll("[$,[a-zA-Z]+\\s+]", "")));
		}
		return allCarPrices;
	}
	
	public ResultsPage orderByPriceAsc() {
		click(driver, sortButton);
		click(driver, priceSortDescOption);
		click(driver, sortButton);
		click(driver, priceSortAscOption);
		return this;
	}
	
	public ResultsPage orderByPriceDesc() {
		click(driver, sortButton);
		click(driver, priceSortDescOption);
		return this;
	}
	
	public ArrayList<Integer> orderPricesManuallyAsc(ArrayList<Integer> carPrices) {
		Collections.sort(carPrices);
		return carPrices;
	}
	
	public ArrayList<Integer> orderPricesManuallyDesc(ArrayList<Integer> carPrices) {
		Collections.sort(carPrices, Collections.reverseOrder());
		return carPrices;
	}
	
}
