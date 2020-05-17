package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	public WebDriver driver;
	
	public WaitHelper(WebDriver driver) {		
		this.driver = driver;
	}
	
	public void WaitforVisibilityof(WebElement element, long timeoutseconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.visibilityOf(element));				
	}
	
	public void WaitforClickableof(WebElement element,long timeoutseconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
