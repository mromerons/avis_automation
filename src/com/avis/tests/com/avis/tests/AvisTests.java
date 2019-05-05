package com.avis.tests;

import com.avis.pages.CarsPage;
import com.avis.pages.ResultsPage;
import com.avis.provider.TestProvider;
import com.avis.utils.CommonUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AvisTests extends CommonUtils{
	
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
	
	@Test(priority = 0, dataProvider = "avisSearchCar", dataProviderClass = TestProvider.class)
	public void findCar(String fromPlace, String fromDate, String returnPlace, String returnDate) throws InterruptedException {
		CarsPage carsPage = new CarsPage(this.driver);
		carsPage
			.clickFromPlaceDummy()
			.fillFromPlace(fromPlace);
		pause_for(1);
		carsPage.selectPlaceOption(fromPlace);
		carsPage
			.clickFromDate()
			.setFromDate(fromDate)
			.closeDatepicker();
		pause_for(1);
		carsPage
			.clickReturnPlace()
			.fillReturnPlace(returnPlace);
		pause_for(1);
		carsPage.selectPlaceOption(returnPlace);
		carsPage
			.clickReturnDate()
			.setReturnDate(returnDate)
			.closeDatepicker();
		pause_for(2);
		ResultsPage resultsPage = carsPage.searchCar();
		pause_for(5);
		
		resultsPage.showAllCars();
		resultsPage.orderByPrice();
		
		ArrayList<String> carPrices = resultsPage.getAllPrices();
		System.out.println("Prices in raw format   : "+ carPrices);
		Collections.sort(carPrices);
		System.out.println("\n-- Recomended car price is excluded from test --\n");
		System.out.println("Ordered Prices Manually: " + carPrices);
		ArrayList<String> orderedCarPrices = resultsPage.getAllPrices();
		System.out.println("Ordered prices by page : " + orderedCarPrices);
		
		Assert.assertEquals(carPrices, orderedCarPrices);
	}
	
	@AfterMethod
	public void  tearDown() {
		driver.quit();
	}
}
