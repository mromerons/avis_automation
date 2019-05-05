package com.avis.provider;

import org.testng.annotations.DataProvider;

public class TestProvider {
	
	@DataProvider(name = "avisSearchCar")
	public static Object[][] searchCar() {
		return new Object[][] { 
			new Object[] { 
					"Aeropuerto De Cancún", 
					"10/05/2019",
					"Aeropuerto De La Ciudad De México",
					"15/05/2019"
				}
		};
	}
}