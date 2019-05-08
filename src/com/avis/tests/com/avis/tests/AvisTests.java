package com.avis.tests;

import com.avis.pages.CarsPage;
import com.avis.pages.ResultsPage;
import com.avis.provider.TestProvider;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AvisTests {
	
	WebDriver driver;
	
	@BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup(); 
    }
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://avis.mx/");
	}
	
	@Test(testName = "findCar", priority = 0, dataProvider = "avisSearchCar", dataProviderClass = TestProvider.class)
	public void findCar(String fromPlace, String fromDate, String returnPlace, String returnDate) throws InterruptedException {
		CarsPage carsPage = new CarsPage(this.driver);
		
		carsPage
			.setFromPlace(fromPlace)
			.setFromDate(fromDate)
			.setReturnPlace(returnPlace)
			.setReturnDate(returnDate);
		
		
		ResultsPage resultsPage = carsPage.searchCar();	
		//Assert.assertEquals(actual, expected);
	}
	
	@Test(testName = "OrderCarPricesAsc", priority = 0, dataProvider = "avisSearchCar", dataProviderClass = TestProvider.class)
	public void OrderCarPricesAsc(String fromPlace, String fromDate, String returnPlace, String returnDate) throws InterruptedException {
		CarsPage carsPage = new CarsPage(this.driver);
		
		carsPage
			.setFromPlace(fromPlace)
			.setFromDate(fromDate)
			.setReturnPlace(returnPlace)
			.setReturnDate(returnDate);
		
		
		ResultsPage resultsPage = carsPage.searchCar();	
		resultsPage.showAllCars();
		
		ArrayList<Integer> carPrices = resultsPage.getAllPrices();
		carPrices = resultsPage.orderPricesManuallyAsc(carPrices);
		resultsPage.orderByPriceAsc();
		ArrayList<Integer> carPricesOrdered = resultsPage.getAllPrices();
		
		
		System.out.println(carPrices);
		System.out.println(carPricesOrdered);
		Assert.assertEquals(carPrices, carPricesOrdered);
	}
	
	@Test(testName = "OrderCarPricesDesc", priority = 0, dataProvider = "avisSearchCar", dataProviderClass = TestProvider.class)
	public void OrderCarPricesDesc(String fromPlace, String fromDate, String returnPlace, String returnDate) throws InterruptedException {
		CarsPage carsPage = new CarsPage(this.driver);
		
		carsPage
			.setFromPlace(fromPlace)
			.setFromDate(fromDate)
			.setReturnPlace(returnPlace)
			.setReturnDate(returnDate);
		
		
		ResultsPage resultsPage = carsPage.searchCar();	
		resultsPage.showAllCars();
		
		ArrayList<Integer> carPrices = resultsPage.getAllPrices();
		carPrices = resultsPage.orderPricesManuallyDesc(carPrices);
		resultsPage.orderByPriceDesc();
		ArrayList<Integer> carPricesOrdered = resultsPage.getAllPrices();
		
		
		System.out.println(carPrices);
		System.out.println(carPricesOrdered);
		Assert.assertEquals(carPrices, carPricesOrdered);
	}
	
	@AfterMethod
	public void  tearDown() {
		driver.quit();
	}
}
