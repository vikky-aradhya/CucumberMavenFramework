package stepDefinitions;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageOperations.LoginPageOperations;

import com.cucumber.listener.Reporter;

public class Login extends BasePage {

	public LoginPageOperations logOps = new LoginPageOperations();

	@And("^user enters the username$")
	public void user_enters_the_username() throws Throwable {
		logOps.enterUserName(testdataMap.get("Username"));
	}

	@And("^user enters the password$")
	public void user_enters_the_password() throws Throwable {
		logOps.enterPassword(testdataMap.get("Password"));
	}

	@Then("^user clicks on Login$")
	public void user_clicks_on_Login() throws Throwable {
		logOps.clickLoginButton();
	}

	@Then("^fill in the flight details$")
	public void fill_in_the_flight_details() throws Throwable {
		logOps.enterFlightInformation(testdataMap.get("Trip_Type"), testdataMap.get("Passengers"),
				testdataMap.get("Departing_From"), testdataMap.get("Departing_on_month"),
				testdataMap.get("Departing_on_date"), testdataMap.get("Arriving In"),
				testdataMap.get("Returning_month"), testdataMap.get("Returning_day"));
	}

	@Then("^choose the preferences$")
	public void choose_the_preferences() throws Throwable {
		logOps.chooseClassPrefrence(testdataMap.get("Service_Class"), testdataMap.get("Airline"));
	}

	@And("^verify the Login Page title$")
	public void verify_the_login_page_title() throws Throwable {
		String expected = "Find a Flight: Mercury Tours:";
		Assert.assertEquals(driver.getTitle(), expected);

	}

}
