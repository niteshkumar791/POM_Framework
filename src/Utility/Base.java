package Utility;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Base {
	

	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public WebDriver driver = null;
    
    

	@BeforeSuite
	 public void startReport() throws IOException{	   
		
		CommonActions action = new CommonActions();
		Properties configPro = action.ReadConfigProperty();
		
	           htmlReporter = new ExtentHtmlReporter(configPro.getProperty("ReportPath"));
	           extent = new ExtentReports();
	           extent.attachReporter(htmlReporter);
	           
	           extent.setSystemInfo("OS", "Widows");
	           extent.setSystemInfo("Host Name", "HRM_Test");
	           extent.setSystemInfo("Environment", "QA");
	           extent.setSystemInfo("User Name", "Nitesh");
	    }
	
	@Parameters({"Browser","url"})  
	@BeforeMethod
	public WebDriver LaunchBrowser(@Optional("chrome") String browser,@Optional("www.google.com")String Url) throws IOException {
		
		CommonActions action = new CommonActions();
		Properties configPro = action.ReadConfigProperty();
		
		if (browser.equalsIgnoreCase("chrome")) {	
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--disable-notifications");
			System.setProperty(configPro.getProperty("ChromeKey"), configPro.getProperty("ChromePath"));
			driver = new ChromeDriver(option);
			driver.manage().window().maximize();
			driver.get(Url);
		}		
		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty(configPro.getProperty("IEKey"),configPro.getProperty("IEpath"));
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(Url);
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty(configPro.getProperty("firefoxKey"),configPro.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(Url);
		}
		return driver;
	}

	
	
	 @AfterMethod
	 public void getResult(ITestResult result) throws Exception{
		 
		 CommonActions act = new CommonActions();
		 String screenshotpath = act.GetScreenshot(driver, "my_snap");

	     if(result.getStatus() == ITestResult.SUCCESS) {

	      logger.log(Status.PASS, "Test Case Passed " + result.getName());
	     }
	    if(result.getStatus() == ITestResult.FAILURE){
	    
	    logger.log(Status.FAIL, "Snapshot below: " + logger.addScreenCaptureFromPath(screenshotpath));
	    logger.log(Status.FAIL, "Test Case Failed is	"+result.getName());
	    logger.log(Status.FAIL, "Test Case Failed is"+result.getThrowable());
	      
	      
	     }
	    else if(result.getStatus() == ITestResult.SKIP){

	      logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
	     }
	    
	}
	
	
	@AfterMethod
	public void CloseBrowser() {
		driver.close();
	}
	
	
	@AfterSuite
    public void endReport(){
         extent.flush();
         //extent.close();
      }


}
