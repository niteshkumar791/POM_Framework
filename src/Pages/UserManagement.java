package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utility.WaitHelper;

public class UserManagement {
	
	//Object repository	
	WebDriver driver;
	
	@FindBy(linkText="Admin")
	WebElement Admin_linkText;
	
	@FindBy(css=".arrow")
	WebElement UserManagement_linkText;
	
	@FindBy(linkText="Users")
	WebElement Users_LinkText;
	
	@FindBy(id="searchSystemUser_userName")
	WebElement usernameinput_ID;
	
	@FindBy(id="searchBtn")
	WebElement search_btnId;
	
	@FindBy(id="resultTable")
	WebElement Table_id;
	
	@FindBy(id="searchSystemUser_employeeName_empName")
	WebElement EmployeeName_id;
	
	@FindBy(id="searchSystemUser_userType")
	WebElement UserRole_ID;
	
	@FindBy(id="searchSystemUser_status")
	WebElement Status_ID;
	
	WaitHelper wait;
	ExtentTest logger;
	Actions act;
	
	
	//parameterized constructor
	public UserManagement(WebDriver driver, ExtentTest logger) {	
			this.driver = driver;
			this.logger = logger ;
			PageFactory.initElements(driver, this);
			wait = new WaitHelper(driver);
			act = new Actions(driver);
		}
	
	//Page Methods
	public void clickAdmin() {	
		logger.log(Status.INFO, "Mouse hover to Admin menu");
		act.moveToElement(Admin_linkText).build().perform();
	}
	
	public void OpenUser() {
		logger.log(Status.INFO, "Mouse hover to User Management menu");
		act.moveToElement(UserManagement_linkText).build().perform();
		
		logger.log(Status.INFO, "Clicking on Users menu");
		Users_LinkText.click();
	}
	
	public void SendUserDetails(String username, String UserRole, String EmployeeName, String Status) {
		logger.log(com.aventstack.extentreports.Status.INFO, "Sending all details one by one");
		
		if(! (username.isEmpty())) {
			wait.WaitforVisibilityof(usernameinput_ID, 20);
			usernameinput_ID.sendKeys(username);
		}		
		else if(! (UserRole.isEmpty())) {
			Select roles = new Select(UserRole_ID);
			if (UserRole.equalsIgnoreCase("ESS")) {
				roles.selectByValue("2");
			}
			else if (UserRole.equalsIgnoreCase("Admin")){
				roles.selectByVisibleText("Admin");
			} 						
		}		
		else if(! (EmployeeName.isEmpty())) {
			wait.WaitforVisibilityof(EmployeeName_id, 20);
			EmployeeName_id.sendKeys(EmployeeName);			
		}		
		else if(! (Status.isEmpty())) {
			Select sel_status = new Select(Status_ID);
			if (Status.equalsIgnoreCase("Enabled")) {
				sel_status.selectByValue("1");
			}
			else if (Status.equalsIgnoreCase("Disabled")) {
				sel_status.selectByValue("0");
			}			
		}		
				
	}
	
	public void SearchUser() {	
		wait.WaitforClickableof(search_btnId, 20);
		search_btnId.click();
	}
	
	public boolean validate_search_user(String expected_val) {
		logger.log(Status.INFO, "checking on expected values from table");
		wait.WaitforVisibilityof(Table_id, 20);
		List<WebElement> List_val =  Table_id.findElements(By.tagName("td"));
		
		for(WebElement val:List_val) {
			if(val.getText().equals(expected_val)) 
				return true;				
			}
		return false;				
	}
}
