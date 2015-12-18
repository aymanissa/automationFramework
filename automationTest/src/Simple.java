
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
		Thread.sleep(WAIT_TIME);
		driver.findElement(By.xpath("(//a[contains(text(),'WorkSpaceTestr')])[2]")).click();
		Thread.sleep(WAIT_TIME);
		dashboardItems = EsiActivity.getDashboardItems(driver);
		System.out.println("dashboardItems.size()" + dashboardItems.size());
		
		/*
		for(int i = 0; i < dashboardItems.size(); i++)
		{
			System.out.println("Dashboard Item name: " + EsiActivity.getDashboardItemName(driver, dashboardItems.get(i)) + ";");
			//System.out.println("\tNumber of svg in each dashboard items: " + dashboardItems.get(i).findElements(By.cssSelector("svg")).size());
			
			
		}
		*/
		
		
		//validate revenue oil high graph
		WebElement graph = EsiActivity.getDashboardGraph(driver, dashboardItems, "Revenue Oil High").findElement(By.cssSelector("svg"));
		EsiActivity.validateGraph(driver, graph, "lnllk", ChartType.COLUMN, excelFileLocation, sheetName, "USD", 11, 10, 42);
		System.out.println("finished validating revenue oil high graph");
		
		//validate variable OpCost oil electricity graph
		
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
