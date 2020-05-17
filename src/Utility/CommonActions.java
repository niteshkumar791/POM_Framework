package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class CommonActions {
	
	public  Properties ReadDataProperty() throws IOException {
		
		FileInputStream file = new FileInputStream(new File("Data.properties")); // can give path
		
		Properties datapro = new Properties();
		datapro.load(file);
		
		return datapro;
	}
	
   public Properties ReadConfigProperty() throws IOException {
		
		FileInputStream file = new FileInputStream(new File("config.properties"));
		
		Properties Configpro = new Properties();
		Configpro.load(file);
		
		return Configpro;
	}
   
   public  String GetScreenshot(WebDriver driver,String stepname) throws Exception {
	   
	   TakesScreenshot ts= (TakesScreenshot)driver;
	   File screenshotSRC= ts.getScreenshotAs(OutputType.FILE); // capturing screen shot as output type file
	   	   
	   Properties configpro = ReadConfigProperty();

	   String path =  configpro.getProperty("ScreenshotPath")+"/ScreenCapturesPNG/"+stepname+".png";   //Defining path and extension of image
	  
	   System.out.println("Path is " + path);
	   File screenshotDest= new File(path);
	   
	   FileUtils.copyFile(screenshotSRC, screenshotDest);
	   
	   return path;
	   
   }

}
