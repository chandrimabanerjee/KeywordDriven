package Keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
 public WebDriver driver;
 public Properties prop;
 
 public WebDriver init_browser(String browserName) {
	 if(browserName.equals("chrome"))
		 driver= new ChromeDriver();
	 return driver;
 }
 public Properties init_Properties() {
	 prop= new Properties();
	 try {
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
			prop.load(fs);}
	 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return prop; 
 }
}
