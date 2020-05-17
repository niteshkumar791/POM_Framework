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

public class Leave {
	
	@FindBy(linkText="Leave")
	WebElement Leavemenu;
	
	@FindBy(id="menu_leave_Entitlements")
	WebElement Entitlementsmenu;
	
	@FindBy(linkText="Add Entitlements")
	WebElement Add_Entitlements;
	
	@FindBy(css=".ac_input")
	WebElement EmployeeName;
	
	@FindBy(css="body > div.ac_results > ul")
	WebElement ulistnames;
	
	@FindBy(id="entitlements_leave_type")
	WebElement entitlements_leave_type;
	
	@FindBy(id="period")
	WebElement Leave_Period;
	
	@FindBy(id="entitlements_entitlement")
	WebElement EntitlementID;
	
	@FindBy(id ="btnSave")
	WebElement Save_btn;
	
	@FindBy(css="#resultTable")
	WebElement resultTable;
	
	@FindBy(linkText="Assign Leave")
	WebElement Assign_Leave_menu;
	
	@FindBy(xpath="//*[@id=\"assignleave_txtEmployee_empName\"]")
	WebElement Assign_leave_menu_empname;
	
	
	@FindBy(xpath="/html/body/div[6]/ul")
	WebElement AssignLeave_ulnames;
	
	@FindBy(id="assignleave_txtLeaveType")
	WebElement assignleave_LeaveType;
	
	@FindBy(id="leaveBalance_details_link")
	WebElement leaveBalance_details_link;
	
	@FindBy(id="assignleave_txtFromDate")
	WebElement assignleave_FromDate;
	
	@FindBy(css=".ui-datepicker-month")
	WebElement assignleaveDate_month;
	
	@FindBy(css=".ui-datepicker-year")
	WebElement assignleaveDate_year;
	
	@FindBy(css=".ui-datepicker-calendar")
	WebElement assignleaveDate_tbl;
	
	@FindBy(id="assignleave_txtToDate")
	WebElement assignleave_Todate;
	
	@FindBy(id="assignleave_txtComment")
	WebElement assignleave_Comment;
	
	@FindBy(css="#assignBtn")
	WebElement assignBtn;
	
	@FindBy(id="leaveBalanceConfirm")
	WebElement leaveBalanceConfirm_wndw;
	
	@FindBy(id="confirmOkButton")
	WebElement confirmOkButton;
	
	
	WebDriver driver;
	ExtentTest logger;
	WaitHelper wait;
	Actions act;
	
	public Leave(WebDriver driver,ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		wait = new WaitHelper(driver);
		PageFactory.initElements(driver, this);	
		act = new Actions(driver);
	}
	
	
	public void ClickonLeave() {
		logger.log(Status.INFO, "Mouse hover to leave menu");		
		act.moveToElement(Leavemenu).build().perform();				
	}

	public void ClickonEntitlementsMenu() {
		logger.log(Status.INFO, "Mouse hover to Entitlements menu");
		act.moveToElement(Entitlementsmenu).build().perform();
		
	}
	
	public void clickonAddEntitlements() {
		logger.log(Status.INFO, "Mouse hover and clicking to Add Entitlements ");
		Add_Entitlements.click();
	}
	
	public void SendDetailsLeaveEntitlements(String Employee, String LeaveType, String LeavePeriod, String Entitlement) {
		
		//selecting from employee
		logger.log(Status.INFO, "Sending EmployeeName");
		wait.WaitforVisibilityof(EmployeeName, 20);
		EmployeeName.sendKeys(Employee);
		
		List<WebElement> list_names = ulistnames.findElements(By.tagName("strong"));		
		for(WebElement val:list_names) {
			if (val.getText().equalsIgnoreCase(Employee)) {
				val.click();
				break;
			}
		}
		
		//selecting leave type
		logger.log(Status.INFO, "Sending LeaveType");
		Select sel = new Select(entitlements_leave_type);
		if (LeaveType.equalsIgnoreCase("FMLA US"))
			sel.selectByValue("2");
		else if (LeaveType.equalsIgnoreCase("Maternity US"))
			sel.selectByValue("3");
		else if (LeaveType.equalsIgnoreCase("Paternity US"))
			sel.selectByValue("4");
		else if (LeaveType.equalsIgnoreCase("Vacation US"))
			sel.selectByValue("1");
	
		//Selecting leave period
		logger.log(Status.INFO, "Sending LeavePeriod days");
		Select LP_sel = new Select(Leave_Period);
		if(LeavePeriod.equals("2015-01-01 - 2015-12-31"))
			LP_sel.selectByVisibleText("2015-01-01 - 2015-12-31");
		else if(LeavePeriod.equals("2016-01-01 - 2016-12-31"))
			LP_sel.selectByVisibleText("2016-01-01 - 2016-12-31");
		else if(LeavePeriod.equals("2017-01-01 - 2017-12-31"))
			LP_sel.selectByVisibleText("2017-01-01 - 2017-12-31");
		else if(LeavePeriod.equals("2018-01-01 - 2018-12-31"))
			LP_sel.selectByVisibleText("2018-01-01 - 2018-12-31");
		else if(LeavePeriod.equals("2019-01-01 - 2019-12-31"))
			LP_sel.selectByVisibleText("2019-01-01 - 2019-12-31");
		else if(LeavePeriod.equals("2020-01-01 - 2020-12-31"))
			LP_sel.selectByVisibleText("2020-01-01 - 2020-12-31");
		else if(LeavePeriod.equals("2021-01-01 - 2021-12-31"))
			LP_sel.selectByVisibleText("2021-01-01 - 2021-12-31");
		
		//set entitlement
		logger.log(Status.INFO, "Sending Entitlement days");
		EntitlementID.sendKeys(Entitlement);
	}	
	
	public void ClickSave() {	
		//clicking on save button
		Save_btn.click();	
	}
	
	public boolean validateAddLeaveEntitlement(String expected_val) {
		
		logger.log(Status.INFO, "checking on expected values from table");
		wait.WaitforVisibilityof(resultTable, 20);
		List<WebElement> list_validate = resultTable.findElements(By.tagName("td"));
		for(WebElement val:list_validate) {
			if (val.getText().equalsIgnoreCase(expected_val))
				return true;
		}		
		return false;
	}
	
	public void clickonAssignLeaveMenu() {
		wait.WaitforVisibilityof(Assign_Leave_menu, 20);
		Assign_Leave_menu.click();		
	}
	
	public void SendDetailsAssignLeave(String EmployeeName, String LeaveType, String FromDate, String ToDate,String AnyComments) {
		
		
		String[] Fromdate_arr = FromDate.split("-");
		String[] Todate_arr = ToDate.split("-");
		
	/*	System.out.println(Fromdate_arr[0]);
		System.out.println(Fromdate_arr[1].substring(1));
		System.out.println(Fromdate_arr[2]); */
		
		logger.log(Status.INFO, "Sending Employee name in Assign leave");
		wait.WaitforVisibilityof(Assign_leave_menu_empname, 20);
		Assign_leave_menu_empname.sendKeys(EmployeeName);
		
		List<WebElement> AssignLeave_lsnames =  AssignLeave_ulnames.findElements(By.tagName("strong"));		
		for(WebElement val:AssignLeave_lsnames) {
			if(val.getText().equalsIgnoreCase(EmployeeName))
				val.click();
				break;
		}
		
		logger.log(Status.INFO, "Set leave type in Assign leave");
		Select sel = new Select(assignleave_LeaveType);
		if (LeaveType.equalsIgnoreCase("FMLA US"))
			sel.selectByValue("2");
		else if (LeaveType.equalsIgnoreCase("Maternity US"))
			sel.selectByValue("3");
		else if (LeaveType.equalsIgnoreCase("Paternity US"))
			sel.selectByValue("4");
		else if (LeaveType.equalsIgnoreCase("Vacation US"))
			sel.selectByValue("1");
		
		
		wait.WaitforVisibilityof(leaveBalance_details_link, 20);
		assignleave_FromDate.click();
		
		logger.log(Status.INFO, "Set From Month in Assign leave");
		wait.WaitforVisibilityof(assignleaveDate_month, 20);
		Select sel_mon = new Select(assignleaveDate_month);
		if ((Fromdate_arr[1].substring(1)).equals("0"))
				sel_mon.selectByVisibleText("Jan");
		else if ((Fromdate_arr[1].substring(1)).equals("1"))
			sel_mon.selectByVisibleText("Feb");
		else if ((Fromdate_arr[1].substring(1)).equals("2"))
			sel_mon.selectByVisibleText("Mar");
		else if ((Fromdate_arr[1].substring(1)).equals("3"))
			sel_mon.selectByVisibleText("Apr");
		else if ((Fromdate_arr[1].substring(1)).equals("4"))
			sel_mon.selectByVisibleText("May");
		else if ((Fromdate_arr[1].substring(1)).equals("5"))
			sel_mon.selectByVisibleText("Jun");
		else if ((Fromdate_arr[1].substring(1)).equals("6"))
			sel_mon.selectByVisibleText("Jul");
		else if ((Fromdate_arr[1].substring(1)).equals("7"))
			sel_mon.selectByVisibleText("Aug");
		else if ((Fromdate_arr[1].substring(1)).equals("8"))
			sel_mon.selectByVisibleText("Sep");
		else if ((Fromdate_arr[1].substring(1)).equals("9"))
			sel_mon.selectByVisibleText("Oct");
		else if (Fromdate_arr[1].equals("10"))
			sel_mon.selectByVisibleText("Nov");
		else if (Fromdate_arr[1].equals("11"))
			sel_mon.selectByVisibleText("Dec"); 
			
		
		logger.log(Status.INFO, "Set From Year in Assign leave");
		Select sel_year = new Select(assignleaveDate_year);
		if (Fromdate_arr[0].equals("2020")) {
			sel_year.selectByVisibleText("2020");
		}
		
		logger.log(Status.INFO, "Set From date in Assign leave");
		
		List<WebElement> assignleaveFromDate_li =  assignleaveDate_tbl.findElements(By.tagName("a"));
		
		for(WebElement val1:assignleaveFromDate_li) {
			
			if((val1.getText()).equalsIgnoreCase(Fromdate_arr[2])) {			
				val1.click();			
				break;
			}
		}
		
		logger.log(Status.INFO, "Set To-Month in Assign leave");
		wait.WaitforVisibilityof(assignleave_Todate, 20);
		assignleave_Todate.click();
		
		wait.WaitforVisibilityof(assignleaveDate_month, 20);
		Select sel_tomon = new Select(assignleaveDate_month);
		if ((Todate_arr[1].substring(1)).equals("0"))
			sel_tomon.selectByVisibleText("Jan");
		else if ((Todate_arr[1].substring(1)).equals("1"))
			sel_tomon.selectByVisibleText("Feb");
		else if ((Todate_arr[1].substring(1)).equals("2"))
			sel_tomon.selectByVisibleText("Mar");
		else if ((Todate_arr[1].substring(1)).equals("3"))
			sel_tomon.selectByVisibleText("Apr");
		else if ((Todate_arr[1].substring(1)).equals("4"))
			sel_tomon.selectByVisibleText("May");
		else if ((Todate_arr[1].substring(1)).equals("5"))
			sel_tomon.selectByVisibleText("Jun");
		else if ((Todate_arr[1].substring(1)).equals("6"))
			sel_tomon.selectByVisibleText("Jul");
		else if ((Todate_arr[1].substring(1)).equals("7"))
			sel_tomon.selectByVisibleText("Aug");
		else if ((Todate_arr[1].substring(1)).equals("8"))
			sel_tomon.selectByVisibleText("Sep");
		else if ((Todate_arr[1].substring(1)).equals("9"))
			sel_tomon.selectByVisibleText("Oct");
		else if (Todate_arr[1].equals("10"))
			sel_tomon.selectByVisibleText("Nov");
		else if (Todate_arr[1].equals("11"))
			sel_tomon.selectByVisibleText("Dec");
		
		logger.log(Status.INFO, "Set To-Year in Assign leave");
		Select sel_Toyear = new Select(assignleaveDate_year);
		if (Todate_arr[0].equals("2020"))
			sel_Toyear.selectByVisibleText("2020");
		
		logger.log(Status.INFO, "Set To-date in Assign leave");	
		List<WebElement> assignleaveToDate_li =  assignleaveDate_tbl.findElements(By.tagName("a"));
		for(WebElement val3:assignleaveToDate_li) {
			if(val3.getText().equals(Todate_arr[2])) {
				val3.click();
				break;
			}
		}
		
		logger.log(Status.INFO, "Set comments in Assign leave");
		assignleave_Comment.sendKeys("AnyComments");
		
	}
	
	public void Save_assignLeave() {
		logger.log(Status.INFO, "clicking on assign button in Assign leave");
		JavascriptExecutor my_js = (JavascriptExecutor)driver;
		my_js.executeScript("window.scrollBy(0,4000)");
		
		wait.WaitforClickableof(assignBtn, 20);
		my_js.executeScript("arguments[0].click();", assignBtn);
		
		logger.log(Status.INFO, "clicking on ok button in Assign leave message window");
		wait.WaitforVisibilityof(leaveBalanceConfirm_wndw, 20);
		confirmOkButton.click();	
	}
	
	










}
