/**
 * 
 */
package testCases; //change to testCases

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import metricValidator.MetricValidator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
public class Es284TestCase2
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
		return new Object[][] {{BrowserType.CHROME, "Chrome"}};
		//return new Object[][] {{BrowserType.CHROME, "Chrome"}, {BrowserType.FIREFOX, "Firefox"}, {BrowserType.IE, "IE"}};
		
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
							"<font face='verdana' size='5'>Testcase name: " + this.getClass().getSimpleName().toString() + " Browser: " + browserName + "</font>" +
						"</summary>");
		Reporter.log("<blockquote>");
		
		Date currentDate = new Date();
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
    	String workspaceName = this.getClass().getSimpleName().toString() + "_" + dateFormatter.format(currentDate);
		
		//begin: web browser commands to be executed.
		driver = webi.instantiateBrowser(browserType);
		EsiActivity.setWaitTime(WAIT_TIME);
    	EsiActivity.loadEsiActivity(driver, baseUrl);
    	EsiActivity.createWorkspace(driver, workspaceName, "res\\importFiles\\ES-284\\case2\\ES-284 - Step 4 - Aggregations - Inventory - Case 2.xlsx");
    	EsiActivity.importMasterData(driver, "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - MasterData.xlsx");
		
		this.validateGraphOpportunity_Well_1_Production_Production_Oil_Column();
		this.validateGraphOpportunity_Well_1_Production_Production_Gas_Column();
		this.validateGraphOpportunity_Well_1_Production_Resources_Crew_ProductionCrew_Column();
		this.validateGraphOpportunity_Well_1_Production_Resources_Crew_Supervisors_Column();
		this.validateGraphOpportunity_Well_1_Production_Equipment_Shovel_LargeEquipment_Column();
		this.validateGraphOpportunity_Well_1_Production_FixedOpex_Column();
		this.validateGraphOpportunity_Well_1_Production_VariableOpex_Electricity();
		this.validateGraphOpportunity_Well_1_Production_VariableOpex_Maintenance();
		this.validateGraphOpportunity_Well_2_Production_Production_Oil_Column();
		this.validateGraphOpportunity_Well_2_Production_Production_Gas_Column();
		this.validateGraphOpportunity_Well_2_Production_Resources_Crew_ProductionCrew_Column();
		this.validateGraphOpportunity_Well_2_Production_Resources_Crew_Supervisors_Column();
		this.validateGraphOpportunity_Well_2_Production_Equipment_Shovel_Equipment_Column();
		this.validateGraphOpportunity_Well_2_Production_FixedOpex_Column();
		this.validateGraphOpportunity_Well_2_Production_VariableOpex_Electricity_Column();
		this.validateGraphOpportunity_Well_2_Production_VariableOpex_Maintenance_Column();
		
		//creating and validating measures
		EsiActivity.createMeasure(driver, "Variable OpCost", "Production * Variable Opex");
		this.validateMeasure_VariableOpCost_Oil_Electricity_Column();
		this.validateMeasure_VariableOpCost_Oil_Maintenance_Column();
		this.validateMeasure_VariableOpCost_Gas_Electricity_Column();
		this.validateMeasure_VariableOpCost_Gas_Maintenance_Column();
		
		//creating and validating measures on dashboard graph.
		this.validateGraphDashboard_VariableOpCost_Oil_Electricity_Bar();
		this.validateGraphDashboard_VariableOpCost_Oil_Maintenance_Bar();
		this.validateGraphDashboard_VariableOpCost_Gas_Electricity_Bar();
		this.validateGraphDashboard_VariableOpCost_Gas_Maintenance_Bar();
		
		//creating and validate metrics on dashboard.
		this.validateMetricDashboard_VariableOpCost_Oil_Electricity();
		this.validateMetricDashboard_VariableOpCost_Oil_Maintenance();
		this.validateMetricDashboard_VariableOpCost_Gas_Electricity();
		this.validateMetricDashboard_VariableOpCost_Gas_Maintenance();
    	
    	//end: web browser commands to be executed.
		
		//EsiActivity.deleteWorkspace(driver, workspaceName);
		EsiActivity.closeBrowser(driver);
		
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
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx";
    	
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
    	
		EsiActivity.validateGraph(driver, graph, reporterGraphValidateName , ChartType.COLUMN, excelFileLocation, "Case 2", "bbl", 10, 10, 11);
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_Production_Oil_Column()
    
    private void validateGraphOpportunity_Well_1_Production_Production_Gas_Column() throws InterruptedException, IOException
    {    	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	String opportunityName = "Well 1 Production";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Production > Gas > Column";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx";
    	
    	EsiActivity.goToOpportunities(driver);
    	filters = new ArrayList<Object[]> ();
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "//body[@id='ext-gen1018']/div[4]/div/div/div/div[2]/div/div/div/div/div/div/div/form/div[4]/div/div/div/div/div"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "cfe"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	Thread.sleep(WAIT_TIME);
	    WebElement graph = driver.findElement(By.cssSelector("svg"));
	    EsiActivity.validateGraph(driver, graph, reporterGraphValidateName , ChartType.COLUMN, excelFileLocation, "Case 2", "cfe", 10, 10, 12);
    	Thread.sleep(WAIT_TIME);
    	driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_Productiion_Gas_Column()
    
    private void validateGraphOpportunity_Well_1_Production_Resources_Crew_ProductionCrew_Column() throws InterruptedException, IOException
    {	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 1 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx";
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Resources > Crew > Production Crew > Column";
    	
    	filters = new ArrayList<Object []> ();
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[4]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[12]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName , ChartType.COLUMN, excelFileLocation, sheetName, "person", 10, 10, 13);
    	
    	Thread.sleep(WAIT_TIME);
    	driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_Resources_Crew_ProductionCrew_Column()
    
    private void validateGraphOpportunity_Well_1_Production_Resources_Crew_Supervisors_Column() throws InterruptedException, IOException
    {	
    	//go to opporunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 1 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Resources > Crew > Supervisors > Column";
    	filters = new ArrayList<Object[]> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[4]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[8]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[12]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "person", 10, 10, 14);
    	
    	Thread.sleep(WAIT_TIME);
    	driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_Resources_Crew_Supervisors_Column()

    private void validateGraphOpportunity_Well_1_Production_Equipment_Shovel_LargeEquipment_Column() throws InterruptedException, IOException
    {   	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 1 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Equipment > Shovel > Large Equipment > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[1]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[9]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[11]"});
    	//filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[12]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, null, 10, 10, 15);
    	
    	Thread.sleep(WAIT_TIME);
	    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    	
    }//END METHOD validateGraphOpportinity_Well_1_Production_Equipment_Shovel_LargeEquipment_Column()
    
    private void validateGraphOpportunity_Well_1_Production_FixedOpex_Column() throws InterruptedException, IOException
    {	
    	//go to opportunitues
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 1 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Fixed Opex > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[9]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 10, 10, 16);
    	
    	Thread.sleep(WAIT_TIME);
	    driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
    	
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_FixedOpex_Column()
    
    private void validateGraphOpportunity_Well_1_Production_VariableOpex_Electricity() throws InterruptedException, IOException
    {
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 1 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Variable Opex > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD/boe", 10, 10, 17);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_VariableOpex_Electricity()
    
    private void validateGraphOpportunity_Well_1_Production_VariableOpex_Maintenance() throws InterruptedException, IOException
    {
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 1 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 1 Production > Variable Opex > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[8]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD/boe", 10, 10, 18);
    	
    	Thread.sleep(WAIT_TIME);
	    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_1_Production_VariableOpex_Maintenance()
    
    private void validateGraphOpportunity_Well_2_Production_Production_Oil_Column() throws InterruptedException, IOException
    {   	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > Production > Oil > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[8]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "//body[@id='ext-gen1018']/div[4]/div/div/div/div[2]/div/div/div/div/div/div/div/form/div[4]/div/div/div/div/div"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "bbl"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "bbl", 10, 10, 19);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_Production_Oil_Column()
    
    private void validateGraphOpportunity_Well_2_Production_Production_Gas_Column() throws InterruptedException, IOException
    {	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > Production > Gas > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "//body[@id='ext-gen1018']/div[4]/div/div/div/div[2]/div/div/div/div/div/div/div/form/div[4]/div/div/div/div/div"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "cfe"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "cfe", 10, 10, 20);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_Production_Gas_Column()
    
    private void validateGraphOpportunity_Well_2_Production_Resources_Crew_ProductionCrew_Column() throws InterruptedException, IOException
    {  	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > Resources > Crew > Production Crew > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[4]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[12]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "person", 10, 10, 21);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_Resources_Crew_ProductionCrew_Column()
    
    private void validateGraphOpportunity_Well_2_Production_Resources_Crew_Supervisors_Column() throws InterruptedException, IOException
    {
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > Resources > Crew > Supervisors > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[4]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[8]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[12]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "person", 10, 10, 22);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_Resources_Crew_Supervisors_Column()
    
    private void validateGraphOpportunity_Well_2_Production_Equipment_Shovel_Equipment_Column() throws InterruptedException, IOException
    {    	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > Equipment > Shovel > Equipment > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[1]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[9]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[11]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, null, 10, 10, 23);
    	
    	Thread.sleep(WAIT_TIME);
	    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_Equipment_Shovel_Equipment_Column()
    
    private void validateGraphOpportunity_Well_2_Production_FixedOpex_Column() throws InterruptedException, IOException
    {    	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > FixedOpex > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[9]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 10, 10, 24);
    	
    	Thread.sleep(WAIT_TIME);
	    driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_FixedOpex_Column()
    
    private void validateGraphOpportunity_Well_2_Production_VariableOpex_Electricity_Column() throws InterruptedException, IOException
    {	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > VariableOpex > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[7]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD/boe", 10, 10, 25);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_VariableOpex_Electricity_Column()
    
    private void validateGraphOpportunity_Well_2_Production_VariableOpex_Maintenance_Column() throws InterruptedException, IOException
    {    	
    	//go to opportunities
    	//open opportunity
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToOpportunities(driver);
    	
    	String opportunityName = "Well 2 Production";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Opportunities > Well 2 Production > VariableOpex > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[8]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	
    	EsiActivity.loadOpportunityGraph(driver, opportunityName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD/boe", 10, 10, 26);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	
    }//END METHOD validateGraphOpportunity_Well_2_Production_VariableOpex_Maintenance_Column()
    
    private void validateMeasure_VariableOpCost_Oil_Electricity_Column() throws IOException, InterruptedException
    {
    	//go to calcualtions
    	//open measure
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToCalculations(driver);
    	
    	String measureName = "Variable OpCost";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Calculations > Variable OpCost > Oil > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[20]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[5]"});
    	
    	EsiActivity.loadMeasureGraph(driver, measureName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 10, 10, 46);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[26]")).click();
    	
    	
    }//END METHOD validateMeasure_VariableOpCost_Oil_Electricity_Column()
    
    private void validateMeasure_VariableOpCost_Oil_Maintenance_Column() throws IOException, InterruptedException
    {    	
    	//go to calculations
    	//open measure
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToCalculations(driver);
    	
    	String measureName = "Variable OpCost";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Calculations > Variable OpCost > Oil > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[20]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[14]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[5]"});
    	
    	EsiActivity.loadMeasureGraph(driver, measureName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 10, 10, 47);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[26]")).click();
    	
    	
    }//END METHOD validateMeasureVariableOpCost_Oil_Maintenance()
    
    private void validateMeasure_VariableOpCost_Gas_Electricity_Column() throws IOException, InterruptedException
    {    	
    	//go to calculations
    	//open measure
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToCalculations(driver);
    	
    	String measureName = "Variable OpCost";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Calculations > Variable OpCost > Gas > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[19]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[5]"});
    	
    	EsiActivity.loadMeasureGraph(driver, measureName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 10, 10, 48);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[26]")).click();
    	
    }//END METHOD validateMeasure_VariableOpCost_Gas_Electricity_Column()
    
    private void validateMeasure_VariableOpCost_Gas_Maintenance_Column() throws IOException, InterruptedException
    {    	
    	//go to calculations
    	//open measure
    	//apply filters
    	//validate graph
    	
    	String measureName = "Variable OpCost";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Calculations > Variable OpCost > Gas > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[19]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[14]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[5]"});
    	
    	EsiActivity.loadMeasureGraph(driver, measureName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = driver.findElement(By.cssSelector("svg"));
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.COLUMN, excelFileLocation, sheetName, "USD", 10, 10, 49);
    	
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("(//button[@type='button'])[26]")).click();
    	
    }//END METHOD validateMeasure_VariableOpCost_Gas_Maintenance_Column()
    
    private void validateGraphDashboard_VariableOpCost_Oil_Electricity_Bar() throws IOException, InterruptedException
    {    	
    	//go to dashboard
    	//add new chart
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String graphName = "Chart: Variable OpCost Oil Electricity";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Dashboard > Chart > Variable OpCost > Oil > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Sources"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[31]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[24]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[4]"});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", graphName});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "description", ""});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardGraph(driver, graphName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = EsiActivity.getDashboardGraph(driver, graphName);
    	EsiActivity.maximizeDashboardGraph(driver, graph);
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.BAR, excelFileLocation, sheetName, "USD", 10, 10, 46);
    	EsiActivity.restoreDashboardGraph(driver, graph);
    	
    	//EsiActivity.deleteDashboardGraph(driver, graphName);
    	
    }//END METHOD validateGraphDashboard_VariableOpCost_Oil_Electricity_Column()
    
    private void validateGraphDashboard_VariableOpCost_Oil_Maintenance_Bar() throws IOException, InterruptedException
    {	
    	//go to dashboard
    	//add new chart
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String graphName = "Chart: Variable OpCost Oil Maintenance";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Dashboard > Chart > Variable OpCost > Oil > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Sources"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[31]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[4]"});
    	filters.add(new Object []{ElementTypes.INPUTTEXT, "name", graphName});
    	filters.add(new Object []{ElementTypes.INPUTTEXT, "description", ""});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardGraph(driver, graphName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = EsiActivity.getDashboardGraph(driver, graphName);
    	EsiActivity.maximizeDashboardGraph(driver, graph);
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.BAR, excelFileLocation, sheetName, "USD", 10, 10, 47);
    	EsiActivity.restoreDashboardGraph(driver, graph);
    	
    	//EsiActivity.deleteDashboardGraph(driver, graphName);
    	
    }//END METHOD validateGraphDashboard_VariableOpCost_Oil_Maintenance_Column()
    
    private void validateGraphDashboard_VariableOpCost_Gas_Electricity_Bar() throws IOException, InterruptedException
    {
    	//go to dashboard
    	//add new chart
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String graphName = "Chart: Variable OpCost Gas Electricity";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Dashboard > Chart > Variable OpCost > Gas > Electricity > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Sources"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[30]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[24]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[4]"});
    	filters.add(new Object []{ElementTypes.INPUTTEXT, "name", graphName});
    	filters.add(new Object []{ElementTypes.INPUTTEXT, "description", ""});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardGraph(driver, graphName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = EsiActivity.getDashboardGraph(driver, graphName);
    	EsiActivity.maximizeDashboardGraph(driver, graph);
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.BAR, excelFileLocation, sheetName, "USD", 10, 10, 48);
    	EsiActivity.restoreDashboardGraph(driver, graph);
    	
    	//EsiActivity.deleteDashboardGraph(driver, graphName);
    	
    }//END METHOD validateGraphDashboard_Variable_OpCost_Gas_Electricity()
    
    private void validateGraphDashboard_VariableOpCost_Gas_Maintenance_Bar() throws IOException, InterruptedException
    {	
    	//go to dashboard
    	//add new chart
    	//apply filters
    	//validate graph
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String graphName = "Chart: Variable OpCost Gas Maintenance";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterGraphValidateName = "Dashboard > Chart > Variable OpCost > Gas > Maintenance > Column";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[5]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Sources"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[10]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[30]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//input[@type='checkbox'])[4]"});
    	filters.add(new Object []{ElementTypes.INPUTTEXT, "name", graphName});
    	filters.add(new Object []{ElementTypes.INPUTTEXT, "description", ""});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardGraph(driver, graphName, filters);
    	
    	Thread.sleep(WAIT_TIME);
    	WebElement graph = EsiActivity.getDashboardGraph(driver, graphName);
    	EsiActivity.maximizeDashboardGraph(driver, graph);
    	EsiActivity.validateGraph(driver, graph, reporterGraphValidateName, ChartType.BAR, excelFileLocation, sheetName, "USD", 10, 10, 49);
    	EsiActivity.restoreDashboardGraph(driver, graph);
    	
    	//EsiActivity.deleteDashboardGraph(driver, graphName);
    	
    }//END METHOD validateGraphDashboard_VariableOpCost_Gas_Maintenance_Column()
    
    private void validateMetricDashboard_VariableOpCost_Oil_Electricity() throws InterruptedException, IOException
    {
    	//go to dashboard
    	//add new metric
    	//apply filters
    	//validate metric
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String metricName = "Metric: Variable OpCost Oil Electricity";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterMetricValidateName = "Dashboard > Metric > Variable OpCost > Oil > Electricity";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[17]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//a[contains(text(),'Filter')])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[26]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[22]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='submit'])[2]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", metricName});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardMetric(driver, metricName, filters);
    	//WebElement metric = EsiActivity.getDashboardMetric(driver, metricName);
    	EsiActivity.validateMetric(driver, reporterMetricValidateName, metricName, excelFileLocation, sheetName, "USD", 46, 8);
    	
    	/*
    	try {
            AssertJUnit.assertEquals("1,042,100.00", driver.findElement(By.cssSelector("h2.no-margins.ng-scope > span.ng-binding")).getText());
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
    	
    }//END METHOD validateMetricDashboard_VariableOpCost_Oil_Electricity()
    
    private void validateMetricDashboard_VariableOpCost_Oil_Maintenance() throws InterruptedException, IOException
    {	
    	//go to dashboard
    	//add new metric
    	//apply filters
    	//validate metric
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String metricName = "Metric: Variable OpCost Oil Maintenance";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterMetricValidateName = "Dashboard > Metric > Variable OpCost > Oil > Maintenance";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[17]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//a[contains(text(),'Filter')])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[26]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[23]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='submit'])[2]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", metricName});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardMetric(driver, metricName, filters);
    	//WebElement metric = EsiActivity.getDashboardMetric(driver, metricName);
    	EsiActivity.validateMetric(driver, reporterMetricValidateName, metricName, excelFileLocation, sheetName, "USD", 47, 8);
    	
    	/*
    	try {
            AssertJUnit.assertEquals("4,954,000.00", driver.findElement(By.cssSelector("h2.no-margins.ng-scope > span.ng-binding")).getText());
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
    	
    }//END METHOD validateMetricDashboard_VariableOpCost_Oil_Maintenance()
    
    private void validateMetricDashboard_VariableOpCost_Gas_Electricity() throws InterruptedException, IOException
    {    	
    	//go to dashboard
    	//add new metric
    	//apply filters
    	//validate metric
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String metricName = "Metric: Variable OpCost Gas Electricity";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterMetricValidateName = "Dashboard > Metric > Variable OpCost > Gas > Electricity";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[17]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//a[contains(text(),'Filter')])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[22]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='submit'])[2]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", metricName});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardMetric(driver, metricName, filters);
    	//WebElement metric = EsiActivity.getDashboardMetric(driver, metricName);
    	EsiActivity.validateMetric(driver, reporterMetricValidateName, metricName, excelFileLocation, sheetName, "USD", 48, 8);
    	
    	/*
    	try {
            AssertJUnit.assertEquals("20.84", driver.findElement(By.cssSelector("h2.no-margins.ng-scope > span.ng-binding")).getText());
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
    	
    }//END METHOD validateMetricDashboard_VariableOpCost_Gas_Electricity()
    
    private void validateMetricDashboard_VariableOpCost_Gas_Maintenance() throws InterruptedException, IOException
    {    	
    	//go to dashboard
    	//add new metric
    	//apply filters
    	//validate metric
    	
    	EsiActivity.goToDashboard(driver);
    	
    	String metricName = "Metric: Variable OpCost Gas Maintenance";
    	String excelFileLocation = "res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - Test to Automate.xlsx"; 
    	String sheetName = "Case 2";
    	String reporterMetricValidateName = "Dashboard > Metric > Variable OpCost > Gas > Maintenance";
    	filters = new ArrayList<Object []> ();
    	
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[13]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[17]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//a[contains(text(),'Filter')])[2]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[25]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[23]"});
    	filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='submit'])[2]"});
    	filters.add(new Object[] {ElementTypes.LINKTEXT, "Settings"});
    	filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", metricName});
    	filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success"});
    	
    	EsiActivity.createDashboardMetric(driver, metricName, filters);
    	//WebElement metric = EsiActivity.getDashboardMetric(driver, metricName);
    	EsiActivity.validateMetric(driver, reporterMetricValidateName, metricName, excelFileLocation, sheetName, "USD", 49, 8);
    	
    	/*
    	try {
            AssertJUnit.assertEquals("99.08", driver.findElement(By.cssSelector("h2.no-margins.ng-scope > span.ng-binding")).getText());
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
    	
    }//END METHOD validateMetricDashboard_VariableOpCost_Gas_Maintenance()
    
	
}//END CLASS 
