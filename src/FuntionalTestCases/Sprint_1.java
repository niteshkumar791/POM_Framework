package FuntionalTestCases;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.aventstack.extentreports.Status;

import Pages.KPI;
import Pages.Leave;
import Pages.Login;
import Pages.Recruitment_vacancy;
import Pages.UserManagement;
import Utility.Base;
import Utility.CommonActions;

public class Sprint_1 extends Base {
	
	@Test(priority=1,description="Login",enabled=true)
	public void TC01() throws IOException {
		
		logger = extent.createTest("HRM_Login");
		
		CommonActions action = new CommonActions();// creating obj for CommonAction class
		Properties pro = action.ReadDataProperty(); // calling method from Commonaction class and saving it in properties obj
				
		logger.log(Status.INFO,"Entering credentials");
		Login obj = new Login(driver,logger);
		obj.do_login(pro.getProperty("username"),pro.getProperty("password"));
		
		obj.Login_btn_click();
		
		logger.log(Status.INFO,"Checking for Successful Login ");				
		Assert.assertFalse(obj.validate_Login());
		
		logger.log(Status.INFO,"Successful Logout ");
		obj.logOut();
	}
	
	
	@Test(priority=2,description="Search_username",enabled=false)
	public void TC02() throws Exception {
		
		CommonActions act = new CommonActions();
		Properties pro = act.ReadDataProperty();
		
		logger = extent.createTest("Search UserName in User_Management");
		
		logger.log(Status.INFO,"Entering credentials");
		Login obj = new Login(driver,logger);
		obj.do_login(pro.getProperty("username"),pro.getProperty("password"));
		
		logger.log(Status.INFO,"Opening Users");
		UserManagement obj1 = new UserManagement(driver, logger);
		obj1.clickAdmin();
				
		obj1.OpenUser();
		
		logger.log(Status.INFO,"Sending user details");
		obj1.SendUserDetails(pro.getProperty("username"),pro.getProperty("UserRole"),pro.getProperty("EmployeeName"),pro.getProperty("Status"));
		
		logger.log(Status.INFO,"Clicking on search button");
		obj1.SearchUser();
		
		logger.log(Status.INFO,"Validating username");
		Assert.assertTrue(obj1.validate_search_user("Admin"));
		
		logger.log(Status.INFO,"Successful Logout ");
		obj.logOut();
		
	}
	
	@Test(priority=3,description="Add KPI",enabled=false)
	public void TC03() throws Exception {
		CommonActions act = new CommonActions();
		 Properties pro = act.ReadDataProperty();
		
		logger = extent.createTest("Add Key Performance Indicator");
		
		logger.log(Status.INFO,"Entering login credentials");
		Login login_obj = new Login(driver,logger);
		login_obj.do_login(pro.getProperty("username"),pro.getProperty("password"));
		
		
		logger.log(Status.INFO,"Opening KPI page");
		KPI kpi_obj = new KPI(driver, logger);
		kpi_obj.ClickonPerformanceMenu();
		
		kpi_obj.ClickonConfigureMenu();
		
		kpi_obj.openKPIMenu();
		
		kpi_obj.Add_Kpi();
		
		logger.log(Status.INFO,"Adding KPI credentials");
		kpi_obj.Send_kpi_Details(pro.getProperty("JobTitle"),pro.getProperty("KPI_name"),pro.getProperty("Min_Rating"),pro.getProperty("Max_Rating"));
		
		kpi_obj.Save_KPI();
		
		logger.log(Status.INFO,"Validating KPI");
		Assert.assertTrue(kpi_obj.Validate_AddKpi(pro.getProperty("Search_value")));	
		
		logger.log(Status.INFO,"Successful Logout ");
		login_obj.logOut();
	}
	
	@Test(priority=4,description="Add Recruitment Vacancy",enabled=false)
	public void TC04() {
		
		logger = extent.createTest("Add Recruitment Vacancies");
		
		logger.log(Status.INFO, "Entering login credentials");
		Login login_obj = new Login(driver,logger);
		login_obj.do_login("Admin", "admin123");
		
		logger.log(Status.INFO, "Opening Recruitment vacancies");
		Recruitment_vacancy vac_obj = new Recruitment_vacancy(driver);
		vac_obj.OpenRecruitmentVacancies();
		
		logger.log(Status.INFO, "Adding Vacancy requiremnts info");
		vac_obj.AddVacancies("IT Executive", "Software Associate", "Linda Anderson", "1", "Selenium");
		
		logger.log(Status.INFO, "Validating Vacancy requiremnts info");		
		Assert.assertFalse(vac_obj.Validate_Add_Vacancy());
		
		logger.log(Status.INFO,"Successful Logout ");
		login_obj.logOut();
	}
	
	@Test(priority=5,description="Add Leave Entitlements",enabled=false)
	public void TC05() throws Exception {
		
		logger = extent.createTest("Add Leave Entitlements");
		CommonActions act = new CommonActions();
		Properties pro =  act.ReadDataProperty();
		
		logger.log(Status.INFO, "Login by username & password");
		Login login_obj = new Login(driver, logger);
		login_obj.do_login(pro.getProperty("username"), pro.getProperty("password"));
		
		Leave leave_obj = new Leave(driver, logger);
		logger.log(Status.INFO, "Opening Add Entitlements");
		leave_obj.ClickonLeave();		
		
		leave_obj.ClickonEntitlementsMenu();	
		
		leave_obj.clickonAddEntitlements();
		
		logger.log(Status.INFO, "Sending details");
		leave_obj.SendDetailsLeaveEntitlements(pro.getProperty("Employee"),pro.getProperty("LeaveType"),pro.getProperty("LeavePeriod"),pro.getProperty("Entitlement"));
		
		logger.log(Status.INFO, "clicking on save button");
		leave_obj.ClickSave();
		
		logger.log(Status.INFO, "Validating add leave entitlements");
		Assert.assertTrue(leave_obj.validateAddLeaveEntitlement(pro.getProperty("expected_val")));
		
		logger.log(Status.INFO,"Successful Logout ");
		login_obj.logOut();
		
	}
	
	
	@Test(priority=6,description="Assign Leave",enabled=false)
	public void TC06() throws Exception {
		
		logger = extent.createTest("Assign Leave");
		CommonActions act = new CommonActions();
		Properties pro =  act.ReadDataProperty();
		
		logger.log(Status.INFO, "Login by username & password");
		Login login_obj = new Login(driver, logger);
		login_obj.do_login(pro.getProperty("username"), pro.getProperty("password"));
		
		
		Leave leave_obj = new Leave(driver, logger);
		logger.log(Status.INFO, "Opening Assign Leave page");
		leave_obj.ClickonLeave();	
		
		leave_obj.clickonAssignLeaveMenu();
		
		logger.log(Status.INFO, "Sending Details in Assign Leave page");
		leave_obj.SendDetailsAssignLeave(pro.getProperty("EmployeeName"),pro.getProperty("AssignLeave_LeaveType"),pro.getProperty("FromDate"),pro.getProperty("ToDate"),pro.getProperty("AnyComments"));
		
		logger.log(Status.INFO, "Saving Assign Leave page");
		leave_obj.Save_assignLeave();
		
		logger.log(Status.INFO,"Successful Logout ");
		login_obj.logOut();
		
		
	}

















}



