package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Recruitment_vacancy {
	
	//Page repositories
	WebDriver driver;
	
	
	@FindBy(linkText="Recruitment")
	WebElement Recruitment_linktext;
	
	@FindBy(linkText="Vacancies")
	WebElement Vacancies_linktext;
	
	@FindBy(css="#btnAdd")
	WebElement Add_btn;
	
	@FindBy(id="addJobVacancy_jobTitle")
	WebElement JobTitle_id;
	
	@FindBy(id="addJobVacancy_name")
	WebElement VacancyName_id;
	
	@FindBy(css=".ac_input")
	WebElement HiringManager_css;
	
	@FindBy(css="body > div.ac_results > ul")
	WebElement uList_names_xpath;
	
	@FindBy(id="addJobVacancy_noOfPositions")
	WebElement noPosition_id;
	
	@FindBy(id="addJobVacancy_description")
	WebElement description_id;
	
	@FindBy(id="btnSave")
	WebElement Save_btn_id;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[1]/h1")
	WebElement Msg;
	
	WebDriverWait wait;
	
	//Parametrized constructor
	public Recruitment_vacancy(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, 30);
	}
	
	public void OpenRecruitmentVacancies() {		
		Actions act = new Actions(driver);
		act.moveToElement(Recruitment_linktext).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(Vacancies_linktext)).click();	
	}
	
	public void AddVacancies(String JobTitle, String Vacancy_Name, String Hiring_Manager_Name, String Number_Position, String Job_Description) {
		
		//clicking on add button
		//wait.until(ExpectedConditions.visibilityOf(Add_btn)).click();
		
		JavascriptExecutor my_js = (JavascriptExecutor)driver;
		my_js.executeScript("arguments[0].click();", Add_btn);
		
		//Selecting jobTitle
		Select jobtitle_sel = new Select(JobTitle_id);
		if (JobTitle.equalsIgnoreCase("Sales Manager")) {
			jobtitle_sel.selectByValue("1");
			}
		else if (JobTitle.equalsIgnoreCase("Sales Executive")) {
			jobtitle_sel.selectByValue("2");
			}
		else if (JobTitle.equalsIgnoreCase("HR Manager")) {
			jobtitle_sel.selectByValue("3");
			}
		else if (JobTitle.equalsIgnoreCase("HR Executive")) {
			jobtitle_sel.selectByValue("4");
			}
		else if (JobTitle.equalsIgnoreCase("Finance Manager")) {
			jobtitle_sel.selectByValue("5");
			}
		else if (JobTitle.equalsIgnoreCase("Account Clerk")) {
			jobtitle_sel.selectByValue("6");
			}
		else if (JobTitle.equalsIgnoreCase("IT Executive")) {
			jobtitle_sel.selectByValue("7");
			}
		else if (JobTitle.equalsIgnoreCase("IT Manager")) {
			jobtitle_sel.selectByValue("8");
			}
		else if (JobTitle.equalsIgnoreCase("CEO")) {
			jobtitle_sel.selectByValue("9");
			}
		else if (JobTitle.equalsIgnoreCase("Manger")) {
			jobtitle_sel.selectByValue("10");
			}
		else if (JobTitle.equalsIgnoreCase("testing")) {
			jobtitle_sel.selectByValue("11");
			}
		
		//sending Vacancy Name
		//wait.until(ExpectedConditions.elementToBeClickable(VacancyName_id)).click();
		//VacancyName_id.sendKeys(Vacancy_Name);
		
		my_js.executeScript("arguments[0].value= '"+Vacancy_Name+"'", VacancyName_id);
		
		//sending Hiring manager name
		HiringManager_css.sendKeys(Hiring_Manager_Name);
			
		// selecting HM name from drop down	
		
		List<WebElement> list_names =  uList_names_xpath.findElements(By.tagName("strong"));
		for(WebElement val: list_names) {
			if(val.getText().equalsIgnoreCase(Hiring_Manager_Name))
				val.click();
			break;
		}
		
		//sending Number of Position
		//wait.until(ExpectedConditions.elementToBeClickable(noPosition_id)).click();
		//noPosition_id.sendKeys(Number_Position);
		
		my_js.executeScript("arguments[0].value= '"+ Number_Position+"'", noPosition_id);
		
		//sending job description 
		//wait.until(ExpectedConditions.elementToBeClickable(description_id)).click();
		//description_id.sendKeys(Job_Description);
		
		my_js.executeScript("arguments[0].value= '"+ Job_Description+"'", description_id);
		
		//clicking on save button
		//wait.until(ExpectedConditions.elementToBeClickable(Save_btn_id)).click();
		my_js.executeScript("arguments[0].click();", Save_btn_id);
		
	}
	
	public boolean Validate_Add_Vacancy() {
		
		if (Msg.getText().equalsIgnoreCase("Error"))
		 return true;
		else
			return false;		
	}

}
