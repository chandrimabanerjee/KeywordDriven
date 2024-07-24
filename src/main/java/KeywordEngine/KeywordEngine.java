package KeywordEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Keyword.base.Base;

public class KeywordEngine {
public WebDriver driver;
public Properties prop;
public static Workbook book;
public WebElement element;
public static Sheet sheet;
public final String Scenario_Path=System.getProperty("user.dir")+"\\src\\main\\java\\Scenarios\\Scenarios.xlsx";

public Base base;
public void startExecution(String sheetName) {
	String locatorName=null;
	String locatorValue=null;
	FileInputStream file=null;
	try {
		file=new FileInputStream(Scenario_Path);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	sheet= book.getSheet(sheetName);
	int k=0;
	for(int i=0;i<sheet.getLastRowNum();i++) {
		try {
		String locator_column=sheet.getRow(i+1).getCell(k+1).toString().trim();
		if(!locator_column.equalsIgnoreCase("NA")) {
			locatorName=locator_column.split("=")[0].trim();
			locatorValue=locator_column.split("=")[1].trim();
			
		}
		String action=sheet.getRow(i+1).getCell(k+2).toString().trim();
		String value=sheet.getRow(i+1).getCell(k+3).toString().trim();
		switch(action){
		case "open browser":
			base=new Base();
			prop=base.init_Properties();
			if(value.isBlank()||value.equals("NA") )
			driver=base.init_browser(prop.getProperty("browser"));
			else
				driver=base.init_browser(value);
			break;
		case "launch url":
			if(value.isBlank()||value.equals("NA") )
				driver=base.init_browser(prop.getProperty("url"));
				else
					driver.get(value);
				break;
		case "quit":
			driver.quit();
			break;
			default:
				break;
		}
		switch(locatorName){
		case "id":
			 element= driver.findElement(By.id(locatorValue));
			if(action.equalsIgnoreCase("sendkeys")) {
			element.sendKeys(value);}
			else if (action.equalsIgnoreCase("click")) {
				element.click();
			}
			locatorName=null;
			break;
		default:
			break;
	}
		}
		catch (Exception e) {
			
		}
}
}
}
