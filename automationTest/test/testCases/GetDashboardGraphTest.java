/**
 * 
 */
package testCases; //change to testCases

import java.io.IOException;
import java.util.ArrayList;

import metricValidator.MetricValidator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import enumTypes.BrowserType;
import enumTypes.ChartType;
import enumTypes.ElementTypes;
import esiActivity.EsiActivity;
import graphValidator.GraphValidator;

import seleniumTools.WebDriverTools;

/**
 * @author 
 *
 */
public class GetDashboardGraphTest
{
	//-----------------------------VARIABLES----------------------------------//
	private static WebDriver driver;
	private static WebDriverTools webi;			//used to instantiate and close browser
	private static final String baseUrl = "http://3es0240:8080/esi.activity/app/#/workspaces";   //esiActivity URL
	private static final int WAIT_TIME = 1000;  //time in milliseconds. Used for delaying web browser command executions
	private ArrayList<Object []> filters;
	
	private StringBuffer verificationErrors = new StringBuffer();
	
	
	//----------------------------CONSTRUCTOR---------------------------------//
	
	@DataProvider
	public Object[][] browserDrivers() throws IOException
	{
		webi = new WebDriverTools();
		//return new Object[][] {{BrowserType.CHROME, "Chrome"}, {BrowserType.FIREFOX, "Firefox"}, {BrowserType.IE, "IE"}};
		return new Object[][] {{BrowserType.CHROME, "Chrome"}};
		
	}//END METHOD browserDrivers()
	
	@BeforeSuite
    public void beforeSuite()
    {	
    	if(System.getProperty("exitOnFail") != null && System.getProperty("exitOnFail").toLowerCase().equals("true"))
    	{
    		GraphValidator.exitOnFail = true;
    		MetricValidator.exitOnFail = true;
    		
    	}//END if(System.getProperty("exitOnFail") != null && System.getProperty("exitOnFail").toLowerCase().equals("true"))
    	
    }//END METHOD beforeSuite()
	
	@AfterMethod
	public void tearDown() throws Exception 
	{
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) 
		{
			Assert.fail(verificationErrorString);
	    }
		
	}//END METHOD tearDown()
	
	//------------------------------GETTERS-----------------------------------//
	//------------------------------SETTERS-----------------------------------//
	
	//--------------------------- TEST METHOD---------------------------------//
	
	@Test(dataProvider="browserDrivers")
	public void test(BrowserType browserType, String browserName) throws IOException, InterruptedException
	{
		//used for report formatting. Do not remove
		Reporter.log("<details>" +
						"<summary>" +
							"<font face='verdana' size='5'>Testcase name: " + this.getClass().getSimpleName().toString() + " Browser: " + browserName + "</font>" +
						"</summary>");
		Reporter.log("<blockquote>");
		
		//begin: web browser commands to be executed.
		driver = webi.instantiateBrowser(browserType);
		
		EsiActivity.setWaitTime(WAIT_TIME);
    	EsiActivity.loadEsiActivity(driver, baseUrl);
    	Thread.sleep(WAIT_TIME + 2000);
		//driver.findElement(By.xpath("(//a[contains(text(),'WorkSpaceTestr')])[2]")).click();
    	driver.findElement(By.xpath("(//a[contains(text(),'test')])[2]")).click();
		Thread.sleep(WAIT_TIME);
    	//this.validateGraphDashboard_Revenue_Oil_High_Column();
		//this.validateGraphDashboard_VariableOpCost_Oil_Electricity_Column();
		EsiActivity.goToDashboard(driver);
		this.validateGraphDashboard_Revenue_Oil_High_Column();
		//this.validateMetricDashboard_Revenue_Oil_High();
		
		//end: web browser commands to be executed.
		
		//used for report formatting. Do not remove
		Reporter.log("<blockquote>");
    	Reporter.log("</details>");
		Reporter.log("<br><hr><br>");
		
	}//END TEST METHOD test(BrowserType, String)
	
	//------------------------------METHODS-----------------------------------//
	
	private void validateMetricDashboard_Revenue_Oil_High() throws InterruptedException, IOException
    {
    	//go to dashboard
    	//add new metric
    	//apply filters
    	//validate metric
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String metricName = "Revenue Oil High";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 1";
    	String reporterMetricValidateName = "Dashboard > Chart > Variable OpCost > Gas > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	/*
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[19]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//a[contains(text(),'Filter')])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[27]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='submit'])[2]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", metricName});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardMetric(driver, metricName, filters);
    	*/
    	WebElement dashboardMetric = EsiActivity.getDashboardMetric(driver, metricName);
    	EsiActivity.validateMetric(driver, reporterMetricValidateName, metricName, excelFileLocation, sheetName, "USD", 42, 8);
    	System.out.println("Validation successfull-------------");
    	/*
    	try {
            AssertJUnit.assertEquals("14,214,800.00", driver.findElement(By.cssSelector("h2.no-margins.ng-scope > span.ng-binding")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            AssertJUnit.assertEquals("USD", driver.findElement(By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div/div/div/div[2]/div/div/ibox-content/div/ul/li/span/a/h2/span[3]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        */
        //EsiActivity.deleteDashboardMetric(driver, metricName);
    	
    }//END METHOD validateMetricDashboard_Revenue_Oil_High()
	
	
	private void validateGraphDashboard_Revenue_Oil_High_Column() throws InterruptedException, IOException
    {    	
    	//go to dashboard
    	//add new chart
    	//apply filters
    	//validate chart
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String graphName = "Revenue Oil High";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 1";
    	String reporterGraphValidateName = "Dashboard > Chart > Revenue > Oil > High > Column";
    	filters = new ArrayList<Object []> ();
    	WebElement dashboardItem = EsiActivity.getDashboardGraph(driver, "Revenue Oil High");
    	
    	Thread.sleep(WAIT_TIME);
    	EsiActivity.maximizeDashboardGraph(dashboardItem);
    	Thread.sleep(3000);
    	WebElement graph = dashboardItem.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 11, 10, 42);
    	EsiActivity.restoreDashboardGraph(dashboardItem);
    	
    	//EsiActivity.deleteDashboardGraph(driver, graphName);
    	
    }//END METHOD validateGraphDashboard_Revenue_Oil_High_Column()
	
	private void validateGraphDashboard_VariableOpCost_Oil_Electricity_Column() throws IOException, InterruptedException
    {    	
    	//go to dashboard
    	//add new chart
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String graphName = "Variable OpCost Oil Electricity";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 1";
    	String reporterGraphValidateName = "Dashboard > Chart > Variable OpCost > Oil > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	WebElement dashboardItem = EsiActivity.getDashboardGraph(driver, graphName);
    	
    	
    	Thread.sleep(WAIT_TIME);
    	EsiActivity.maximizeDashboardGraph(dashboardItem);
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = dashboardItem.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.BAR, excelFileLocation, sheetName, "USD", 11, 10, 56);
    	EsiActivity.restoreDashboardGraph(dashboardItem);
    	Thread.sleep(WAIT_TIME);
    	//EsiActivity.deleteDashboardGraph(dashboardItem);
    	
    }//END METHOD validateGraphDashboard_VariableOpCost_Oil_Electricity_Column()
	
}//END CLASS 
