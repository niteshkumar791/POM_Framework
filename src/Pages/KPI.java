package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utility.WaitHelper;

public class KPI {
	
	//Object repository	
	@FindBy(id="menu__Performance")
	WebElement performance_id;
	
	@FindBy(id="menu_performance_Configure")
	WebElement menu_performance_Configure_id;
	
	@FindBy(linkText="KPIs")
	WebElement kpi_linktext;
	
	@FindBy(id="btnAdd")
	WebElement addbtn_id;
	
	@FindBy(css=".formSelect")
	WebElement selectJobTitle_CSS;
	
	@FindBy(css="#defineKpi360_keyPerformanceIndicators")
	WebElement keyPerformanceIndicators_name;
	
	@FindBy(css="#defineKpi360_minRating")
	WebElement minRating_id;
	
	@FindBy(css="#defineKpi360_maxRating")
	WebElement maxRating_id;
	
	@FindBy(css="#saveBtn")
	WebElement save_Btn_id;
	
	@FindBy(css=".table.hover")
	WebElement ResultTable_id;
	
	WebDriver driver;
	WaitHelper wait;
	Actions act;
	ExtentTest logger;
	
	//Parametrized constructor
	public KPI(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;	
		wait = new WaitHelper(driver);
		act = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Page methods
	public void ClickonPerformanceMenu() {
		
		logger.log(Status.INFO, "Mouse hover on performance menu");
		act.moveToElement(performance_id).build().perform(); //mouse hover to performance
	}	
	
	public void ClickonConfigureMenu() {
		logger.log(Status.INFO, "Mouse hover on configure menu");
		act.moveToElement(menu_performance_Configure_id).build().perform();    //mouse hover to configure
	}
	
	public void openKPIMenu() {
		logger.log(Status.INFO, "Clicking on KPI menu");
		wait.WaitforClickableof(kpi_linktext, 20);
		kpi_linktext.click();
	}
	
	public void Add_Kpi() {
		logger.log(Status.INFO, "Clicking on Add KPI button");
		JavascriptExecutor my_JS = (JavascriptExecutor)driver;
		my_JS.executeScript("arguments[0].click();", addbtn_id);		
	}
	
	public void Send_kpi_Details(String JobTitle,String KPI_name,String Min_Rating,String Max_Rating) {		
	
		wait.WaitforVisibilityof(selectJobTitle_CSS, 20);
		logger.log(Status.INFO, "Selecting Job Title");
		Select Select_Job_Title = new Select(selectJobTitle_CSS); //selecting Jobtitle from dropdown
		if (JobTitle.equalsIgnoreCase("Sales Manager")) {
			Select_Job_Title.selectByValue("1");
			}
		else if (JobTitle.equalsIgnoreCase("Sales Executive")) {
			Select_Job_Title.selectByValue("2");
			}
		else if (JobTitle.equalsIgnoreCase("HR Manager")) {
			Select_Job_Title.selectByValue("3");
			}
		else if (JobTitle.equalsIgnoreCase("HR Executive")) {
			Select_Job_Title.selectByValue("4");
			}
		else if (JobTitle.equalsIgnoreCase("Finance Manager")) {
			Select_Job_Title.selectByValue("5");
			}
		else if (JobTitle.equalsIgnoreCase("Account Clerk")) {
			Select_Job_Title.selectByValue("6");
			}
		else if (JobTitle.equalsIgnoreCase("IT Executive")) {
			Select_Job_Title.selectByValue("7");
			}
		else if (JobTitle.equalsIgnoreCase("IT Manager")) {
			Select_Job_Title.selectByValue("8");
			}
		else if (JobTitle.equalsIgnoreCase("CEO")) {
			Select_Job_Title.selectByValue("9");
			}
		else if (JobTitle.equalsIgnoreCase("Manger")) {
			Select_Job_Title.selectByValue("10");
			}
		else if (JobTitle.equalsIgnoreCase("testing")) {
			Select_Job_Title.selectByValue("11");
			}
		
		 JavascriptExecutor my_JS = (JavascriptExecutor)driver;
		 logger.log(Status.INFO, "Sending KPI Name");
		 my_JS.executeScript("arguments[0].value= '"+ KPI_name+"'", keyPerformanceIndicators_name);
		 
		 logger.log(Status.INFO, "Sending Minimum Rating");
		 my_JS.executeScript("arguments[0].value= '"+ Min_Rating+"'", minRating_id);
		 
		 logger.log(Status.INFO, "Sending Maximum Rating");
		 my_JS.executeScript("arguments[0].value= '"+ Max_Rating+"'", maxRating_id);
		 
				
		//wait.until(ExpectedConditions.elementToBeClickable(keyPerformanceIndicators_name)).click();
		//keyPerformanceIndicators_name.sendKeys(KPI_name);
		//wait.until(ExpectedConditions.elementToBeClickable(minRating_id)).click();
		//minRating_id.sendKeys(Min_Rating);		
		//wait.until(ExpectedConditions.elementToBeClickable(maxRating_id)).click();
		//maxRating_id.sendKeys(Max_Rating);		
		//wait.until(ExpectedConditions.elementToBeClickable(save_Btn_id)).click();
	}
	
	public void Save_KPI() {
		JavascriptExecutor my_JS = (JavascriptExecutor)driver;
		logger.log(Status.INFO, "Clicking on save button");
		my_JS.executeScript("arguments[0].click();", save_Btn_id);		
	}		
	

	public boolean Validate_AddKpi(String Search_value) {
		logger.log(Status.INFO, "Checking Expected value in table");
		wait.WaitforVisibilityof(ResultTable_id, 20);
		List<WebElement> list_result =  ResultTable_id.findElements(By.tagName("td"));
		
		for(WebElement val:list_result) {
			if (val.getText().equalsIgnoreCase(Search_value))
				return true;
		}
		return false;
		
	}
	
}

