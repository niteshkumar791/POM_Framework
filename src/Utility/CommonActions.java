package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

		//FileInputStream file = new FileInputStream(new File("config.properties"));

		Properties Configpro = new Properties();
		Configpro.load(new FileInputStream(new File("config.properties")));

		return Configpro;
	}

	public String GetScreenshot(WebDriver driver, String stepname) throws IOException {	   
		TakesScreenshot ts= (TakesScreenshot)driver;
		File screenshotSRC= ts.getScreenshotAs(OutputType.FILE); // capturing screen shot as output type file

		Properties configpro = ReadConfigProperty();
		String path = configpro.getProperty("ScreenshotPath")+"/ScreenCaptures/"+stepname+".png";   //Defining path and extension of image

		File screenshotDest= new File(path);	   
		FileUtils.copyFile(screenshotSRC, screenshotDest);     
		return path;

	}



	public void ReadExcel(String filename , String sheetName) throws IOException {

		//File obj1= new File(filename);

		FileInputStream file= new FileInputStream(new File(filename));

		// check extension & create Workbook object
		Workbook obj= null;

		String extension= filename.substring(filename.indexOf("."));

		if(extension.equals(".xls")) {
			obj = new HSSFWorkbook(file);
		}
		else if(extension.equals(".xlsx")) {
			obj = new XSSFWorkbook(file);
		}

		// get sheet object
		Sheet sheet= obj.getSheet(sheetName);

		int rowCount= sheet.getLastRowNum()- sheet.getFirstRowNum();

		for(int i=0; i<rowCount+1; i++) {
			Row row= sheet.getRow(i);
			for(int j=0; j<row.getLastCellNum();j++) {
				if(row.getCell(j) != null) {
					if(Cell.CELL_TYPE_STRING==row.getCell(j).getCellType()) {
						System.out.print(row.getCell(j).getStringCellValue()+"|| ");
					}
					else if (Cell.CELL_TYPE_NUMERIC==row.getCell(j).getCellType()){
						System.out.print(row.getCell(j).getNumericCellValue()+"|| ");
					}
				}
			}
		}
	}
} 