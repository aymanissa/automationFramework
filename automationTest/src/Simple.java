
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enumTypes.BrowserType;
import enumTypes.ChartType;
import esiActivity.EsiActivity;
import seleniumTools.WebDriverTools;

/**
 * 
 */

/**
 * @author mubari
 *
 */
public class Simple
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		
		WebDriverTools webi = new WebDriverTools();
		ArrayList<WebElement> dashboardItems;
		int WAIT_TIME = 1000;
		String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 1";
		EsiActivity.setWaitTime(WAIT_TIME);
    	
		WebDriver driver = webi.instantiateBrowser(BrowserType.CHROME);
		Thread.sleep(WAIT_TIME);
		driver.get("http://3es0240:8080/esi.activity/app/#/workspaces");
		Thread.sleep(WAIT_TIME + 1000);
		//driver.findElement(By.xpath("(//a[contains(text(),'WorkSpaceTestr')])[2]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'test')])[2]")).click();
		Thread.sleep(WAIT_TIME);
		//EsiActivity.goToCalculations(driver);
		EsiActivity.goToDashboard(driver);
		//Thread.sleep(WAIT_TIME);
		//EsiActivity.deleteWorkspace(driver, "test");
		//dashboardItems = EsiActivity.getDashboardItems(driver);
		WebElement dashboardMetric = EsiActivity.getDashboardMetric(driver, "Revenue Oil High");
		String metricValue = dashboardMetric.findElement(By.cssSelector("h2.no-margins.ng-scope")).getText();
		System.out.println("metricValue: " + metricValue + ".");
		
		Thread.sleep(5000);
		driver.quit();
		
		/*
		StringBuilder stringBuilder = new StringBuilder();
		StringBuffer stringBuffer = new StringBuffer();
		String string;
		
		stringBuilder.append("<blockquote>");
		stringBuilder.append("</blockquote>");
		System.out.println("stringBuilder: " + stringBuilder);
		*/

	}
	//-----------------------------VARIABLES----------------------------------//
	//----------------------------CONSTRUCTOR---------------------------------//
	//------------------------------GETTERS-----------------------------------//
	//------------------------------SETTERS-----------------------------------//
	//------------------------------METHODS-----------------------------------//
	
	
	
	
}
