package StepDefinitions;




import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import Pages.Login;
import Utility.Base;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class loginSteps extends Base {
	WebDriver driver;
	ExtentTest logger;
	Login login_obj;
	Base base_obj;
	
	public loginSteps() throws IOException {
		
		base_obj = new Base();
		base_obj.startReport();
	}
	
	
@Given("^user opens the HRM browser \"([^\"]*)\" and url \"([^\"]*)\"$")
public void user_opens_the_HRM_browser_and_url(String browser, String Url) throws Throwable {		
    driver = base_obj.LaunchBrowser(browser,Url);	
}

@Then("^user send username \"([^\"]*)\" and password \"([^\"]*)\"$")
public void user_send_username_and_password(String username, String Password) throws Throwable {
	 logger = extent.createTest("HRM_Login");	 
	 login_obj = new Login(driver);
	 login_obj.do_login(username, Password); 
}

@Then("^user click on login button$")
public void user_click_on_login_button() throws Throwable {
	login_obj = new Login(driver);
    login_obj.Login_btn_click(); 
}

@Then("^validate the outcome$")
public void validate_the_outcome() throws Throwable {	
	driver.navigate().back();
	login_obj = new Login(driver);
	login_obj.validate_Login();  
}

@Then("^user click on logout$")
public void user_click_on_logout() throws Throwable {
	login_obj.logOut();
	base_obj.CloseBrowser();
	
}


}
