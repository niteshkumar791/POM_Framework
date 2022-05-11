package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import com.aventstack.extentreports.Status;

import Utility.Base;
import Utility.WaitHelper;


public class Login extends Base {
	
	//Object repository	
	@FindBy(id="txtUsername")
	WebElement Username_id;
	
	@FindBy(id="txtPassword")
	WebElement Password_id;
	
	@FindBy(id="btnLogin")
	WebElement Login_Btn_id;
	
	@FindBy(xpath="//a[@id='welcome']")
	WebElement Welcome_Admin_linktxt;
	
	@FindBy(linkText="Logout")
	WebElement logout;
	
	@FindBy(className="head")
	WebElement head;
	
	WaitHelper wait;
	
	
	//parameterized constructor
	public Login(WebDriver driver) {	
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
	}
		
	//Page Methods
	public void do_login(String username,String Password) {		
		
		logger.log(Status.INFO, "Sending username");
		wait.WaitforVisibilityof(Username_id, 20);
		Username_id.sendKeys(username);
		
		logger.log(Status.INFO, "Sending password");
		wait.WaitforVisibilityof(Password_id, 20);
		Password_id.sendKeys("admin123");
		
	}
	
	public void Login_btn_click() {
		
		logger.log(Status.INFO, "clicking on login button");
		wait.WaitforClickableof(Login_Btn_id, 20);		
		Login_Btn_id.click();
	}
	
	public void logOut() {
		wait.WaitforVisibilityof(Welcome_Admin_linktxt, 20);
		Welcome_Admin_linktxt.click();
		wait.WaitforVisibilityof(logout, 20);
		logout.click();		
	}
	
	
	
	public boolean validate_Login() {
		wait.WaitforVisibilityof(head, 20);
		String txt = head.getText();
		if(txt.equals("Dashboard")) {
			return true;
			}			
		else 
			return false;
		}	
	}