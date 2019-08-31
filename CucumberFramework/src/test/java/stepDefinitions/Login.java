package stepDefinitions;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import genericFunction.Property;

import com.cucumber.listener.Reporter;

public class Login extends BasePage{ 
	
	public Property loginmap = new Property(System.getProperty("user.dir")+"\\src\\main\\java\\ObjectMapping\\login.properties");
	
	@Given("^test data is read from excel \"([^\"]*)\" under \"([^\"]*)\"$")
	public void test_data_is_read_from_excel_under(String scenarioName, String sheetName) throws Throwable { 

		System.out.println(scenarioName);
		System.out.println(sheetName);
		readexceldata(scenarioName, sheetName);
		Thread.sleep(3000);
		Reporter.addStepLog("Test Data read successfully");
	}
	
	@When("^User opens browser$")
	public void user_opens_browser() throws Throwable { 
		try {
			openBrowser();
			Reporter.addStepLog("Browser opened successfully");
		}
		catch(Exception e) {
			e.getMessage();
		}
		try {
			navigateToUrl();
			Reporter.addStepLog("Navigated to mercury tours successfully");
		}
		catch(Exception e) {
			e.getMessage();
		}
	}

	@And("^user enters the username$")
	public void user_enters_the_username() throws Throwable { 
		sendKeys("username", loginmap, testdataMap.get("Username"));
	}

	@And("^user enters the password$")
	public void user_enters_the_password() throws Throwable { 
		sendKeys("password", loginmap, testdataMap.get("Password"));
	}
	
	@Then("^user clicks on Login$")
	public void user_clicks_on_Login() throws Throwable { 
		clickElement("signin", loginmap);
	}
	
	@Then("^fill in the flight details$")
	public void fill_in_the_flight_details() throws Throwable {
		if(testdataMap.get("Trip_Type").equalsIgnoreCase("oneway")) {
			clickElement("triptypeone", loginmap);
		}
		else {
			clickElement("triptyperound", loginmap);
		}
		
		selectByText("passengers", loginmap, testdataMap.get("Passengers"));
		selectByText("departingfrom", loginmap, testdataMap.get("Departing_From"));
		selectByText("departingonmonth", loginmap, testdataMap.get("Departing_on_month"));
		selectByText("departingondate", loginmap, testdataMap.get("Departing_on_date"));
		selectByText("arrivingin", loginmap, testdataMap.get("Arriving In"));
		
		if(!testdataMap.get("Returning_month").equalsIgnoreCase("NA")) {
			selectByText("returningmonth", loginmap, testdataMap.get("Returning_month"));
			selectByText("returningdate", loginmap, testdataMap.get("Returning_day"));
		}
	}

	@Then("^choose the preferences$")
	public void choose_the_preferences() throws Throwable {
		if(testdataMap.get("Service_Class").equalsIgnoreCase("Economy")) {
			clickElement("economyserviceclass", loginmap);
		}
		else if(testdataMap.get("Service_Class").equalsIgnoreCase("Business")) {
			clickElement("businessserviceclass", loginmap);
		}
		else {
			clickElement("firstserviceclass", loginmap);
		}
		
		selectByText("airline", loginmap, testdataMap.get("Airline"));
		clickElement("findflights", loginmap);
		
	}

	@And("^verify the Login Page title$")
	public void verify_the_login_page__title() throws Throwable { 
		String expected = "Find a Flight: Mercury Tours: ";
		Assert.assertEquals(driver.getTitle(), expected);
		
	}
	
}
