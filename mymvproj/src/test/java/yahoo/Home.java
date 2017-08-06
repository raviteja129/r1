package yahoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

@Listeners({ATUReportsListener.class,MethodListener.class,ConfigurationListener.class})
public class Home extends DriverClass  
{
	{
		System.setProperty("atu.reporter.config", "d:\\atu.properties");
	}
	public void open()
	{
		driver.manage().window().maximize();
		driver.get("http://www.yahoomail.com");
	}
	public void login() throws Exception
	{
		open();
		Thread.sleep(5000);
		driver.findElement(By.name("username")).sendKeys("venkat123456a");
		driver.findElement(By.name("signin")).click();
		Thread.sleep(5000);		
		driver.findElement(By.name("password")).sendKeys("mqq987654");
		driver.findElement(By.name("verifyPassword")).click();
		Thread.sleep(5000);
	}
	public void createacc() throws Exception
	{
		open();
		Thread.sleep(5000);
		new Actions(driver).moveToElement(driver.findElement(By.id("createacc"))).click().perform();
		Thread.sleep(5000);
		try
		{
			if(driver.findElement(By.name("firstName")).isDisplayed())
			{
				ATUReports.add("Reg page is displayed",LogAs.PASSED,new CaptureScreen(ScreenshotOf.DESKTOP));
				//enter firstname, lastname....etc
			}
		}
		catch(Exception e)
		{
			Reporter.log("<font color='red'><b>Reg page not displayed</b></font>");
			//ATUReports.add("Reg page NOT displayed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
		}
	}
}
