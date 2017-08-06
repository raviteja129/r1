package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ATUReportsListener.class,MethodListener.class,ConfigurationListener.class})
public class SanityTest extends DriverClass
{
	{
		System.setProperty("atu.reporter.config", "d:\\atu.properties");
	}
	@Test
	@Parameters({"browser"})
	public void sanitytesting(String str) throws Exception
	{
		if(str.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(str.matches("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","d:\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		FileInputStream fin=new FileInputStream("D:\\july_22_17\\testdata.xlsx");  
		XSSFWorkbook wb=new XSSFWorkbook(fin); 
		XSSFSheet ws=wb.getSheet("sanitytest");    
		Row row;
		String classname;
		String methodname;
		for(int r=1;r<=ws.getLastRowNum();r++) 
		{
	   	  row=ws.getRow(r);
		  if(row.getCell(4).getStringCellValue().matches("yes"))
		  {
			  classname=row.getCell(2).getStringCellValue();
			  methodname=row.getCell(3).getStringCellValue();
			  
			  Class c=Class.forName(classname);  //load the class into memory
			  Method m=c.getMethod(methodname,null);  //get method in the class
			  Object obj=c.newInstance();   //create instance for the class
			  m.invoke(obj,null);  //call the method 
		  }
		}
		fin.close();
	}
}










