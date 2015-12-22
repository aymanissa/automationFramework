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
public class SimpleTest
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
	public void test(BrowserType browserType, String browserName) throws Exception
	{
		//used for report formatting. Do not remove
		Reporter.log("<details>" +
						"<summary>" +
							"<font face='verdana' size='5'>Testcase name: "  + " Browser: " + browserName + "</font>" +
						"</summary>");
		Reporter.log("<blockquote>");
		
		//begin: web browser commands to be executed.
		driver = webi.instantiateBrowser(browserType);
		
    	EsiActivity.setWaitTime(WAIT_TIME);
    	EsiActivity.loadEsiActivity(driver, baseUrl);
    	EsiActivity.createWorkspace(driver, "WorkSpaceTest", "res\\importFiles\\ES-284\\case1\\ES-284 - Step 4 - Aggregations - Inventory - Case 1.xlsx");
    	EsiActivity.importMasterData(driver, "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - MasterData.xlsx");
    	
    	//validate an opportunity
    	this.validateGraphOpportunity_Well_1_Production_Production_Oil_Column();
    	
    	//create and validate a measure
//    	EsiActivity.createMeasure(driver, "Revenue", "Production * Price");
//    	this.validateMeasure_Revenue_Oil_High_Column();
//    	EsiActivity.createMeasure(driver, "Variable OpCost", "Production * Variable Opex");
    	
    	//create and validate measure on dashboard graph
//    	this.validateGraphDashboard_Revenue_Oil_High_Column();
    	
    	//create and validate metrics on dashboard
//    	this.validateMetricDashboard_Revenue_Oil_High();
    	
    	EsiActivity.deleteWorkspace(driver, "WorkSpaceTest");
    	EsiActivity.closeBrowser(driver);
    	
		//end: web browser commands to be executed.
		
		//used for report formatting. Do not remove
		Reporter.log("<blockquote>");
    	Reporter.log("</details>");
		Reporter.log("<br><hr><br>");
		
	}//END TEST METHOD test(BrowserType, String)
	
	//------------------------------METHODS-----------------------------------//
	
	private void validateGraphOpportunity_Well_1_Production_Production_Oil_Column() throws Exception
    {
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	String opportunityName = "Well 1 Production";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Production > Oil > Column";
    	
    	EsiActivity.goToOpportunities(driver);
    	filters = new ArrayList<Object []> ();
    	//filters = Buildfilters(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"}, new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"}, )
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[8]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "//body[@id='ext-gen1018']/div[4]/div/div/div/div[2]/div/div/div/div/div/div/div/form/div[4]/div/div/div/div/div"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "bbl"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	
		EsiActivity.validateGraph(driver, graph, reporterGraphValidateName , ChartType.COLUMN, "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx", "Case 1", "bbl", 11, 10, 12);
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_Production_Oil_Column()
	
	@SuppressWarnings("unused")
	private void validateMeasure_Revenue_Oil_High_Column() throws InterruptedException, IOException
    {	
    	//go to calculations
    	//open measure
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToCalculations(driver);
    	
    	String measureName = "Revenue";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 1";
    	String reporterGraphValidateName = "Calculations > Revenue > Oil > High > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[17]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[22]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[26]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[5]"});
    	
    	EsiActivity.loadMeasureGraph(driver, measureName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 11, 10, 42);
    	//GraphValidator.validateGraph(chart, new File("res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx").getCanonicalPath(), "Case 1", 11, 10, 42, "USD", WAIT_TIME);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[27]")).click();
    	
    }//END METHOD validateMeasure_Revenue_Oil_High_Column()
	
	@SuppressWarnings("unused")
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
    	
    	filters.add(new Object []{ElementTypes.XPATH, "(//button[@type='button'])[2]"});
    	filters.add(new Object []{ElementTypes.LINKTEXT, "Sources"});
    	filters.add(new Object []{ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object []{ElementTypes.XPATH, "(//button[@type='button'])[29]"});
    	filters.add(new Object []{ElementTypes.XPATH, "(//button[@type='button'])[34]"});
    	filters.add(new Object []{ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object []{ElementTypes.XPATH, "(//input[@type='checkbox'])[4]"});
    	filters.add(new Object []{ElementTypes.CSS, "button.btn.btn-success"});
    	filters.add(new Object []{ElementTypes.CSS, "i.fa.fa-plus-square-o"});
    	
    	EsiActivity.createDashboardGraph(driver, graphName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 11, 10, 42);
    	
    	EsiActivity.deleteDashboardGraph(driver, graphName);
    	
    }//END METHOD validateGraphDashboard_Revenue_Oil_High_Column()
	
	@SuppressWarnings("unused")
	private void validateMetricDashboard_Revenue_Oil_High() throws InterruptedException
    {
    	//go to dashboard
    	//add new metric
    	//apply filters
    	//validate metric
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String metricName = "Revenue Oil High";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 1";
    	String reporterGraphValidateName = "Dashboard > Chart > Variable OpCost > Gas > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
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
        
        EsiActivity.deleteDashboardMetric(driver, metricName);
    	
    }//END METHOD validateMetricDashboard_Revenue_Oil_High()
	
}//END CLASS 
