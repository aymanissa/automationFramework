/**
 * 
 */
package esiActivity;

import enumTypes.ChartType;
import enumTypes.ElementTypes;
import graphValidator.GraphValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import metricValidator.MetricValidator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import seleniumTools.WebDriverTools;
import seleniumTools.highcharts.BarChart;
import seleniumTools.highcharts.ColumnChart;
import seleniumTools.highcharts.LineChart;

/**
 * Class used for executing common tasks in esi.activity. Ex: go to Workspace, creating a new workspace, 
 * validating graphs, etc.
 * 
 * @author mubari
 *
 */
public class EsiActivity
{
	//-----------------------------VARIABLES----------------------------------//
	public static int WAIT_TIME = 1000;
	//----------------------------CONSTRUCTOR---------------------------------//
	//------------------------------GETTERS-----------------------------------//
	//------------------------------SETTERS-----------------------------------//
	
	/**
	 * Sets the wait time before executing a task. This prevents executing 
	 * WebDriver calls immediately. If it gets executed too quickly, test cases 
	 * will fail
	 * @param waitTime Time in milliseconds
	 */
	public static void setWaitTime(int waitTime)
	{
		WAIT_TIME = waitTime;
		
	}//END METHOD setWaitTime(int)
	
	//------------------------------METHODS-----------------------------------//
	
	/**
	 * Navigates the webpage to Dashboard
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void goToDashboard(WebDriver driver) throws InterruptedException
    {
    	try 
    	{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Dashboard")).click();
			
		} 
    	
    	catch (NoSuchElementException e) 
    	{
    		Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Go to Dashboard</font></summary>");
			Reporter.log("Unable to find Dashboard link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Dashboard link");
			//Reporter.log("</details>");
		}
    	
    }//END METOD goToDashboard(WebDriver)
    
	/**
	 * Navigates the webpage to Opportunities
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void goToOpportunities(WebDriver driver) throws InterruptedException
    {
    	try
		{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Opportunities")).click();
		}
    	
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Go to Opportunites</font></summary>");
			Reporter.log("Unable to find Opportunities link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Opportunites link");
			//Reporter.log("</details>");
		}
    	
    }//END METHOD goToOpportunities(WebDriver)
    
	/**
	 * Navigates the webpage to MasterData
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void goToMasterData(WebDriver driver) throws InterruptedException
    {
    	try
		{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Master Data")).click();
		}
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Go to Master Data</font></summary>");
			Reporter.log("Unable to find Master Data link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Master Data link");
			//Reporter.log("</details>");
			
		}
    	
    }//END METHOD goToMasterData(WebDriver)
    
	/**
	 * Navigates the webpage to Configuration
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void goToConfiguration(WebDriver driver) throws InterruptedException
    {
    	try
		{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Configuration")).click();
		}
    	
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Go to Configuration</font></summary>");
			Reporter.log("Unable to find Configuration link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Configuration link");
			//Reporter.log("</details>");
			

		}
    	
    }//END METHOD goToConfiguration(WebDriver)
    
	/**
	 * Navigates the webpage to Calculations
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void goToCalculations(WebDriver driver) throws InterruptedException
    {
    	try
		{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Calculations")).click();
		}
    	
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Go to Calculations</font></summary>");
			Reporter.log("Unable to find Calculations link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Calculations link");
			//Reporter.log("</details>");
			
		}
    	
    }//END METHOD goToCalculations(WebDriver)
    
	/**
	 * Navigates the webpage to Workspaces
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void goToWorkspaces(WebDriver driver) throws InterruptedException
    {
    	try
		{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Workspaces")).click();
		}
    	
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Go to Workspaces</font></summary>");
			Reporter.log("Unable to find Workspaces link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Workspaces link");
			//Reporter.log("</details>");
			
		}
    	
    }//END METHOD goToWorkspaces(WebDriver)
    
	/**
	 * After the browser is open, esi.activity webpage is opened.
	 * @param driver Web browser being used at the moment
	 * @param url Web address to esi.activity
	 */
	public static void loadEsiActivity(WebDriver driver, String url)
    {
    	driver.get(url);
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    	
    }//END METHOD loadEsiActivity(WebDriver, String)
	
	/**
	 * Closes the current browser and it's driver
	 * @param driver Web browser being used at the moment
	 */
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
		
	}//END METHOD closeBrowser(WebDriver)
    
	/**
	 * Creates a workspace based on the provided information. It doesn't do 
	 * anything if a new workspace being created has the same name as existing 
	 * one. Eventually the browser will close and mark the test as failed. Doesn't 
	 * check if excel file exists. 
	 * @param driver Web browser being used at the moment
	 * @param workspaceName Name of the new workspace
	 * @param opportunityFileLocation File location for opportunities. Relative path to excel file. Doesn't check if it exists.
	 * @throws IOException For reading Excel file. Thrown if file doesn't exist
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void createWorkspace(WebDriver driver, String workspaceName, String opportunityFileLocation) throws IOException, InterruptedException
    {
    	goToWorkspaces(driver);
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Workspace")).click();
        Thread.sleep(WAIT_TIME);
    	driver.findElement(By.name("name")).clear();
        Thread.sleep(WAIT_TIME);
    	driver.findElement(By.name("name")).sendKeys(workspaceName);
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.cssSelector("input[type=\"file\"]")).clear();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.cssSelector("input[type=\"file\"]")).sendKeys(new File(opportunityFileLocation).getCanonicalPath());
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//body[@id='ext-gen1018']/div[5]/div/div/div/div[2]/div/div[3]/div/div/div/div/div/div[2]/div/div/span[4]/span")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//button[@type='button']")).click();
    	
    }//END METHOD createWorkspace(WebDriver, String, String)
	
	/**
	 * Creates a measure in calculations page. An error on the web page will 
	 * display if a duplicate measure is being added or if the calculation 
	 * is wrong. In the end the new calculation wont be created if there's 
	 * an error
	 * @param driver Web browser being used at the moment
	 * @param measureName Name of the new measure
	 * @param measureCalcualtion Calculation for the measure
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void createMeasure(WebDriver driver, String measureName, String measureCalcualtion) throws InterruptedException
	{
		goToCalculations(driver);
		Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div[2]/div/div/ibox-title/div[2]/a[2]")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.name("name")).clear();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.name("name")).sendKeys(measureName);
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.name("expression")).clear();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.name("expression")).sendKeys(measureCalcualtion);
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}//END METHOD createMeasure(WebDriver, String, String)
    
	/**
	 * Imports master data from excel file. Doesn't check if the file exists. It will
	 * fail if excel file location is not valid.
	 * @param driver Web browser being used at the moment
	 * @param masterDataFileLocation Excel file location for master data. Relative path to the excel file. Doesn't check if the file exists.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 * @throws IOException For excel file. Thrown if file doesn't exist
	 */
	public static void importMasterData(WebDriver driver, String masterDataFileLocation) throws InterruptedException, IOException
    {
    	goToMasterData(driver);
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.cssSelector("input[type=\"file\"]")).clear();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.cssSelector("input[type=\"file\"]")).sendKeys(new File(masterDataFileLocation).getCanonicalPath());
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//body[@id='ext-gen1018']/div[4]/div/div/div/div[2]/div/div[3]/div/div/div/div/div/div[2]/div/div/span[3]")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//button[@type='button']")).click();
    	
    }//END METHOD importMasterData(WebDriver, String)
    
	/**
	 * Deletes the workspace based on the provided name. <br>
	 * It will fail if the workspace element cannot be found.
	 * Doesn't check if there's any workspaces. Assumes there's at least one workspace.
	 * @param driver Web browser being used at the moment
	 * @param workspaceName Name of the workspace to be deleted
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void deleteWorkspace(WebDriver driver, String workspaceName) throws InterruptedException
    {
    	goToWorkspaces(driver);
    	
    	//get all the workspaces available.
    	ArrayList<WebElement> workspaceItems = getWorkspacesItems(driver);
    	
    	//iterate through them and delete the workspace that is desired.
    	for(int i = 0; i < workspaceItems.size(); i++)
    	{	
    		if(getWorkspaceItemName(driver, workspaceItems.get(i)).equals(workspaceName))
    		{
    			workspaceItems.get(i).findElements(By.cssSelector("a.btn.btn-white.btn-sm.core-perm-allow")).get(2).click();
    			Thread.sleep(WAIT_TIME);
    	        driver.findElement(By.xpath("//button[@type='button']")).click();
    			
    		}//END if(getWorkspaceItemName(driver, workspaceItems.get(i)).equals(workspaceName))
    		
    	}//END for(int i = 0; i < workspaceItems.size(); i++)
    	
    }//END METHOD deleteWorkspace(WebDriver, String)

	/**
	 * Deletes the measure based on the provided name. <br>
	 * It will fail if the measure element cannot be found. <br>
	 * Doesn't check if there's any measures. Assumes there's at least one measure
	 * @param driver Web browser being used at the moment
	 * @param measureName Name of the measure to be deleted
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void deleteMeasure(WebDriver driver, String measureName) throws InterruptedException
	{
		WebElement measure = getMeasure(driver, measureName);
		Thread.sleep(WAIT_TIME);
		measure.findElement(By.cssSelector("input")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div[2]/div/div/ibox-title/div[2]/a")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//button[@type='button']")).click();
		
	}//END METHOD deleteMeasure(WebDriver, String)
	
	/**
	 * Deletes the dashboard graph based on the provided name. <br>
	 * It will fail if it cannot find a graph on the dashboard.
	 * Assumes there's at least one dashboard graph.
	 * @param driver Web browser being used at the moment
	 * @param graphName Name of the graph to be deleted.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void deleteDashboardGraph(WebDriver driver, String graphName) throws InterruptedException
	{
		try
		{
			WebElement dashboardGraph = getDashboardGraph(driver, graphName);
			
			Thread.sleep(WAIT_TIME);
			dashboardGraph.findElement(By.cssSelector("i.fa.fa-times")).click();
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.xpath("//button[@type='button']")).click();
			
		}
		
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Deleting dashboard graph</font></summary>");
			Reporter.log("Unable to delete dashboard graph.");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("<br>");
			Reporter.log("</details>");
		}
		
	}//END METHOD deleteDashboardGraph(WebDriver, String)

	/**
	 * Deletes the dashboard metric based on the provided name. <br>
	 * It will fail if it cannot find a graph on the dashboard.
	 * Assumes there's at least one dashboard metric.
	 * Searches dashboard metric by <b>name</b> only.
	 * The dashboard graph and metric <b>MUST</b> have unique names.
	 * @param driver Web browser being used at the moment
	 * @param metricName Name of the metric to be deleted
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void deleteDashboardMetric(WebDriver driver, String metricName) throws InterruptedException
	{
		try
		{
			WebElement dashboardMetric = getDashboardMetric(driver, metricName);
			
			Thread.sleep(WAIT_TIME);
			dashboardMetric.findElement(By.cssSelector("i.fa.fa-times")).click();
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.xpath("//button[@type='button']")).click();
		}
		
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Deleting dashboard metric</font></summary>");
			Reporter.log("Unable to delete dashboard metric.");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("<br>");
			Reporter.log("</details>");
			
		}
		
	}//END METHOD deleteDashboardMetric(WebDriver, String)

	/**
	 * Method used to validate the graph. This method will call 
	 * {@link graphValidator.GraphValidator#validateGraph(WebDriver, seleniumTools.highcharts.HighCharts, String, String, Object[], int, int, int, String, long)
	 * GraphValidator.validateGraph} 
	 * to validate the graph. <br> 
	 * <b>NOTE: </b> validating line graphs is not implemented. Implementing code 
	 * to validate line graph will come in later. 
	 * The infrastructure is setup once validating a Line graph is working.<br>
	 * @param driver Web browser being used at the moment
	 * @param chart WebElement that contains chart denoted as svg html tag
	 * @param reporterGraphValidateName Used as the a name for the <code>summary</code> tag used by <code>details</code> HTML tag.
	 * @param chartType The chart type being used for the validation.
	 * @param excelFileLocation Relative path location of the excel file that contains the answer key when validating the graph
	 * @param sheetName The name of the sheet being used in the excel file
	 * @param units The unit being used for the validation. null if there's no unit
	 * @param dateRow The index value of where the date row will be in the excel file
	 * @param dateCol The index value of where the date column will be in the excel file
	 * @param valueRow The index value of where the value row will be in the excel file
	 * @throws IOException Thrown if file doesn't exists.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void validateGraph(WebDriver driver,
							   		 WebElement chart, 
							   		 String reporterGraphValidateName,
							   		 ChartType chartType, 
							   		 String excelFileLocation, 
							   		 String sheetName, 
							   		 String units, 
							   		 int dateRow, 
							   		 int dateCol, 
							   		 int valueRow) throws IOException, InterruptedException
	{
		StringBuilder reporterLog = new StringBuilder();
		switch(chartType)
		{
			case BAR: 
			{
				boolean status = false;
				Object[] validateStatus = {status, reporterLog};
				BarChart barChart = new BarChart(driver, chart);
				try
				{
					GraphValidator.validateGraph(driver, barChart, new File(excelFileLocation).getCanonicalPath(), sheetName, validateStatus, dateRow, dateCol, valueRow, units, WAIT_TIME);
					
					if((boolean)validateStatus[0] == true)
					{
						Reporter.log("<details><summary><font face='verdana' size='4' color='green'>Graph: " + reporterGraphValidateName + "</font></summary>");
						Reporter.log(validateStatus[1].toString());
						
					}
					
					else
					{
						Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterGraphValidateName + "</font></summary>");
						Reporter.log(validateStatus[1].toString());
						
					}
					
					Reporter.log("</details>");
					Reporter.log("<hr><br>");
				}
				catch (AssertionError e)
				{
					Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterGraphValidateName + "</font></summary>");
					Reporter.log(validateStatus[1].toString());
					throw new AssertionError(e);
				}
			
				break;
			
			}//END CASE BAR
			
			case COLUMN: 
			{
				boolean status = false;
				Object[] validateStatus = {status, reporterLog};
				ColumnChart columnChart = new ColumnChart(driver, chart);
				try
				{
					GraphValidator.validateGraph(driver, columnChart, new File(excelFileLocation).getCanonicalPath(), sheetName, validateStatus, dateRow, dateCol, valueRow, units, WAIT_TIME);
					
					if((boolean)validateStatus[0] == true)
					{
						Reporter.log("<details><summary><font face='verdana' size='4' color='green'>Graph: " + reporterGraphValidateName + "</font></summary>");
						Reporter.log(validateStatus[1].toString());
						
					}
					
					else
					{
						Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterGraphValidateName + "</font></summary>");
						Reporter.log(validateStatus[1].toString());
						
					}
					Reporter.log("</details>");
					Reporter.log("<hr><br>");
				}
				catch (AssertionError e)
				{
					Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterGraphValidateName + "</font></summary>");
					Reporter.log(validateStatus[1].toString());
					throw new AssertionError(e);
				}
			
				break;
			
			}//END CASE COLUMN
			
			case LINE:
			{
				boolean status = false;
				Object[] validateStatus = {status, reporterLog};
				LineChart lineChart = new LineChart(driver, chart);
				try
				{
					GraphValidator.validateGraph(driver, lineChart, new File(excelFileLocation).getCanonicalPath(), sheetName, validateStatus, dateRow, dateCol, valueRow, units, WAIT_TIME);
					
					if((boolean)validateStatus[0] == true)
					{
						Reporter.log("<details><summary><font face='verdana' size='4' color='green'>Graph: " + reporterGraphValidateName + "</font></summary>");
						Reporter.log(validateStatus[1].toString());
						
					}
					
					else
					{
						Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterGraphValidateName + "</font></summary>");
						Reporter.log(validateStatus[1].toString());
						
					}
					Reporter.log("</details>");
					Reporter.log("<hr><br>");
				}
				catch (AssertionError e)
				{
					Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterGraphValidateName + "</font></summary>");
					Reporter.log(validateStatus[1].toString());
					throw new AssertionError(e);
					
				}
				
				break;
			
			}//END CASE LINE
			
			default:
			{
				//unsupported chart
			
			}//END CASE default
		
		}//END SWITCH(chartType)
	
	}//END METHOD validateGraph(WebDriver, WebElement, String, ChartType, String, String, String, int, int, int)
	
	/**
	 * Method used to validate the metric. The method will call 
	 * {@link MetricValidator#validateMetric(WebDriver, WebElement, String, String, Object[], int, int, String, long) MetricValidator.validateMetric} 
	 * to validate the metric <br>
	 * 
	 * @param driver Web browser being used at the moment
	 * @param reporterMetricValidateName Used as the a name for the <code>summary</code> tag used by <code>detals</code> HTML tag.
	 * @param metricName The name of metric name on the dashboard. The name must be unique on the dashboard. Graphs and metrics.
	 * @param excelFileLocation Relative path location of the excel file that contains the answer key when validating the graph.
	 * @param sheetName The name of the sheet being used in the excel file
	 * @param units The unit being used for the validation. null if there's no unit
	 * @param row The index value of where the answer key for the metric being validated will be.
	 * @param column The index value of where the answer key for the metric being validate will be.
	 * @throws IOException Thrown if file doesn't exists.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void validateMetric(WebDriver driver, 
									  String reporterMetricValidateName, 
									  String metricName, 
									  String excelFileLocation, 
									  String sheetName, 
									  String units, 
									  int row, 
									  int column) throws IOException, InterruptedException
	{
		//find the metric
		//validate metric
		StringBuilder reporterLog = new StringBuilder();
		boolean status = false;
		Object[] validateStatus = {status, reporterLog};
		
		WebElement dashboardMetric = getDashboardMetric(driver, metricName);
		
		try
		{
			MetricValidator.validateMetric(driver, dashboardMetric, excelFileLocation, sheetName, validateStatus, row, column, units, WAIT_TIME);
			
			if((boolean)validateStatus[0] == true)
			{
				Reporter.log("<details><summary><font face='verdana' size='4' color='green'>Metric: " + reporterMetricValidateName + "</font></summary>");
				Reporter.log(validateStatus[1].toString());
				
			}
			
			else
			{
				Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Metric: " + reporterMetricValidateName + "</font></summary>");
				Reporter.log(validateStatus[1].toString());
				
			}
		}
		
		catch (AssertionError e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Metric: " + reporterMetricValidateName + "</font></summary>");
			Reporter.log(validateStatus[1].toString());
			throw new AssertionError(e);
		}
		
		Reporter.log("</details>");
		Reporter.log("<hr><br>");
		
	}//END METHOD validateMetric(WebDriver, String, String, String, String, String, int, int)
	
	/**
	 * Method used to open an opportunity and apply any filters to load a graph.
	 * @param driver Web browser being used at the moment
	 * @param opportunityName Name of the opportunity to open
	 * @param filters Filters to apply when loading a graph. An example the structure of ArrayList< Object[] > <br>
	 * <table border='1'>
	 * 		<tr>
	 * 			<th>Index[0]: enumTypes.ElementTypes</th>
	 * 			<th>Index[1]: command</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>LINKTEXT</td>
	 * 			<td>bbl</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>XPATH</td>
	 * 			<td>(//button[@type='button'])[3]</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>CSS</td>
	 * 			<td>button.btn.btn-success</td>
	 * 		</tr>
	 * </table>
	 * <hr>
	 * This is how it will look when creating ArrayList< Object [] >
	 * <table border='1'>
	 * 		<tr>
	 * 			<td>ArrayList < Object [] > filters;</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.LINKTEXT, "bbl"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success]"});</td>
	 * 		</tr>
	 * </table>
	 * 
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made
	 */
	public static void loadOpportunityGraph(WebDriver driver, String opportunityName, ArrayList<Object[]> filters) throws InterruptedException
	{
		goToOpportunities(driver);
		Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText(opportunityName)).click();
        for(int i = 0; i < filters.size(); i++)
        {
        	switch((ElementTypes)filters.get(i)[0])
        	{
        		case LINKTEXT:
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		default:
        		{
        			//System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}//END METHOD loadOpportunityGraph
	
	/**
	 * Method used to open a measure and apply any filters to load a graph
	 * @param driver Web browser being used at the moment
	 * @param measureName Name of the measure to open
	 * @param filters Filters to apply when loading a graph. An example the structure of ArrayList< Object[] > <br>
	 * <table border='1'>
	 * 		<tr>
	 * 			<th>Index[0]: enumTypes.ElementTypes</th>
	 * 			<th>Index[1]: command</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>LINKTEXT</td>
	 * 			<td>bbl</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>XPATH</td>
	 * 			<td>(//button[@type='button'])[3]</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>CSS</td>
	 * 			<td>button.btn.btn-success</td>
	 * 		</tr>
	 * </table>
	 * <hr>
	 * This is how it will look when creating ArrayList< Object [] >
	 * <table border='1'>
	 * 		<tr>
	 * 			<td>ArrayList < Object [] > filters;</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.LINKTEXT, "bbl"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success]"});</td>
	 * 		</tr>
	 * </table>
	 * 
	 * 
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void loadMeasureGraph(WebDriver driver, String measureName, ArrayList<Object[]> filters) throws InterruptedException
	{
		goToCalculations(driver);
		
		Thread.sleep(WAIT_TIME);
		WebElement measure = getMeasure(driver, measureName);
		
		measure.findElement(By.cssSelector("i.fa.fa-eye")).click();
		for(int i = 0; i < filters.size(); i++)
        {
        	switch((ElementTypes)filters.get(i)[0])
        	{
        		case LINKTEXT:
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		default:
        		{
        			//System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}//END METHOD loadMeasureGraph(WebDriver, String, ArrayList<Object[]>)
	
	/**
	 * Method used to create a dashboard graph and apply any filters to load the graph
	 * @param driver Web browser being used at the moment
	 * @param graphName Name of the dashboard graph to open.
	 * @param filters Filters to apply when loading a graph. An example the structure of ArrayList< Object[] > <br>
	 * <table border='1'>
	 * 		<tr>
	 * 			<th>Index[0]: enumTypes.ElementTypes</th>
	 * 			<th>Index[1]: command</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>LINKTEXT</td>
	 * 			<td>bbl</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>XPATH</td>
	 * 			<td>(//button[@type='button'])[3]</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>CSS</td>
	 * 			<td>button.btn.btn-success</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>INPUTTEXT</td>
	 * 			<td>name</td>
	 * 			<td>graph name</td>
	 * 		</tr>
	 * </table><br>
	 * If the filter uses INPUTTEXT (text box), use the following format when adding an element to ArrayList< Object[] > <br>
	 * enumTypes.ElementTypes.INPUTTEXT must have the size Object[] of 3. <br>
	 * The first element is the enumTypes.ElementTypes .<br>
	 * The second is the name of the text box. It's searched by <code>name</code>.<br>
	 * The third is what the text box will contain. If the text field needs to be empty, then pass in empty string.<br>
	 * 
	 * <hr>
	 * This is how it will look when creating ArrayList< Object [] >
	 * <table border='1'>
	 * 		<tr>
	 * 			<td>ArrayList < Object [] > filters;</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.LINKTEXT, "bbl"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success]"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", "graph name"});</td>
	 * 		</tr>
	 * </table>
	 * 
	 * 
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void createDashboardGraph(WebDriver driver, String graphName, ArrayList<Object[]> filters) throws InterruptedException
	{
		goToDashboard(driver);
		
		Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Chart")).click();
        
        for(int i = 0; i < filters.size(); i++)
        {
        	switch((ElementTypes)filters.get(i)[0])
        	{
        		case LINKTEXT:
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		case INPUTTEXT:
        		{
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name((String) filters.get(i)[1])).clear();
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name((String) filters.get(i)[1])).sendKeys((String)filters.get(i)[2]);
        			
        			
        			break;
        		}
        		
        		default:
        		{
        			//System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}//END METHOD createDashboardGraph(WebDriver, String, ArrayList<Object[]>)
	
	/**
	 * Method used to create a dashboard metric and apply any filters to create the metric
	 * @param driver Web browser being used at the moment
	 * @param metricName Name of the dashboard metric to create
	 * @param filters Filters to apply when loading a graph. An example the structure of ArrayList< Object[] > <br>
	 * <table border='1'>
	 * 		<tr>
	 * 			<th>Index[0]: enumTypes.ElementTypes</th>
	 * 			<th>Index[1]: command</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>LINKTEXT</td>
	 * 			<td>bbl</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>XPATH</td>
	 * 			<td>(//button[@type='button'])[3]</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>CSS</td>
	 * 			<td>button.btn.btn-success</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>INPUTTEXT</td>
	 * 			<td>name</td>
	 * 			<td>graph name</td>
	 * 		</tr>
	 * </table><br>
	 * If the filter uses INPUTTEXT (text box), use the following format when adding an element to ArrayList< Object[] > <br>
	 * enumTypes.ElementTypes.INPUTTEXT must have the size Object[] of 3. <br>
	 * The first element is the enumTypes.ElementTypes .<br>
	 * The second is the name of the text box. It's searched by <code>name</code>.<br>
	 * The third is what the text box will contain. If the text field needs to be empty, then pass in empty string.<br>
	 * 
	 * <hr>
	 * This is how it will look when creating ArrayList< Object [] >
	 * <table border='1'>
	 * 		<tr>
	 * 			<td>ArrayList < Object [] > filters;</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.LINKTEXT, "bbl"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.XPATH, "(//button[@type='button'])[3]"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.CSS, "button.btn.btn-success]"});</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>filters.add(new Object[] {ElementTypes.INPUTTEXT, "name", "metric name"});</td>
	 * 		</tr>
	 * </table>
	 * 
	 * 
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void createDashboardMetric(WebDriver driver, String metricName, ArrayList<Object[]> filters) throws InterruptedException
	{
		goToDashboard(driver);
		
		Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Metrics")).click();
        
        for(int i = 0; i < filters.size(); i++)
        {
        	switch((ElementTypes)filters.get(i)[0])
        	{
        		case LINKTEXT:
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		case INPUTTEXT:
        		{
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name("name")).clear();
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name("name")).sendKeys((String)filters.get(i)[2]);
        			
        			break;
        			
        		}
        		
        		default:
        		{
        			//System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}//END METHOD createDashboardMetric(WebDriver, String, ArrayList<Object[]>)
	
	/**
	 * Method used to get all the items on the dashboard. It will get graphs and metrics
	 * @param driver Web browser being used at the moment
	 * @return A list of all the dashboard items displayed. null if there's no dashboard items.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static ArrayList<WebElement> getDashboardItems(WebDriver driver) throws InterruptedException
	{
		goToDashboard(driver);
		ArrayList<WebElement> dashboardItems = new ArrayList<WebElement>();
		
		//dashboardItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
		dashboardItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div.grid-cell.ibox.ng-scope.ui-draggable"));
		
		return dashboardItems;
		
	}//END METHOD getDashboardItems(WebDriver)
	
	/**
	 * Method used to get the name of the dashboard item. Applies for graph and metrics.
	 * @param dashboardItem The dashboard item
	 * @return The name of the dashboard item.
	 */
	public static String getDashboardItemName(WebElement dashboardItem)
	{
		//get the text
		
		return dashboardItem.findElement(By.cssSelector("h5.ng-binding")).getText();
		
	}//END METHOD getDashboardItemName(WebElement)
	
	/**
	 * Method used to get the dashboard graph based on the name provided. Searches by the name 
	 * of dashboard items.<br>
	 * <b>NOTE: </b> It searches by the name of dashboard item (graph and metric). 
	 * This means if a graph has the same name as metric, this method will return the first one it encounters.<br>
	 * @param driver Web browser being used at the moment
	 * @param graphName The graph item being searched.
	 * @return The dashboard graph. null if it cannot find the dashboard graph
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static WebElement getDashboardGraph(WebDriver driver, String graphName) throws InterruptedException
	{
		ArrayList<WebElement> dashboardItems = getDashboardItems(driver);
		
		for(int i = 0; i < dashboardItems.size(); i++)
		{
			if(getDashboardItemName(dashboardItems.get(i)).equals(graphName))
			{
				return dashboardItems.get(i);
				
			}//END if(getDashboardItemName(dashboardItems.get(i)).equals(graphName))
						
		}//END for(int i = 0; i < dashboardItems.size(); i++)
		
		return null;
		
	}//END METHOD getDashboardGraph(WebDriver, String)
	
	/**
	 * Method used to maximize the dashboard graph based on the WebElement provided which is the dashboard graph.
	 * @param driver Web browser being used at the moment
	 * @param graph Dashboard graph to maximize
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void maximizeDashboardGraph(WebDriver driver, WebElement graph) throws InterruptedException
	{
		try
		{
			Thread.sleep(WAIT_TIME);
			graph.findElement(By.cssSelector("i.fa.fa-plus-square-o")).click();
			Thread.sleep(WAIT_TIME);
		}
		
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Maximize dashboard graph</font></summary>");
			Reporter.log("Unable to find Maximize Dashboard Graph link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Maximize Dashboard Graph link");
			//Reporter.log("</details>");
		}
		
	}//END METHOD maximizeDashboardGraph(WebDriver, WebElement)
	
	/**
	 * Method used to restore Dashboard graph to it's original size.
	 * @param driver Web browser being used at the moment
	 * @param graph Dashboard graph to restore
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static void restoreDashboardGraph(WebDriver driver, WebElement graph) throws InterruptedException
	{
		try
		{
			Thread.sleep(WAIT_TIME);
			graph.findElement(By.cssSelector("i.fa.fa-columns")).click();
			Thread.sleep(WAIT_TIME);
		}
		
		catch (NoSuchElementException e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Restore dashboard graph</font></summary>");
			Reporter.log("Unable to find Restore Dashboard Graph link.<br>");
			String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			Reporter.log("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			Reporter.log("</details>");
			Reporter.log("<br>");
			throw new NoSuchElementException("Cannot find Restore Dashboard Graph link");
			//Reporter.log("</details>");
		}
		
	}//END METHOD restoreDashboardGraph(WebDriver, WebElement)
	
	/**
	 * Method used to get all the workspaces created. Assumes there's at least one workspace created. Will 
	 * fail if there are no workspaces.
	 * @param driver Web browser being used at the moment.
	 * @return A list of all the workspaces.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static ArrayList<WebElement> getWorkspacesItems(WebDriver driver) throws InterruptedException
	{
		goToWorkspaces(driver);
		Thread.sleep(WAIT_TIME);
		
		ArrayList<WebElement> workspaceItems = null;
		
		//dashboardItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
		workspaceItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("tr.ng-scope"));
		
		return workspaceItems;
		
	}//END METHOD getWorkspacesItems(WebDriver)
	
	/**
	 * Method used to get the name of the workspace
	 * @param driver Web browser being used at the moment.
	 * @param workspaceItem Workspace to the name from
	 * @return The name of the workspace.
	 */
	public static String getWorkspaceItemName(WebDriver driver, WebElement workspaceItem)
	{
		//String jQuerySelector = "arguments[0]";
		
		//((JavascriptExecutor) driver).executeScript("var foo = _.clone($(" + jQuerySelector + "));", dashboardItem);
		//((JavascriptExecutor) driver).executeScript("$($(foo).children()[0]).remove();");
		//get the text
		
		return workspaceItem.findElement(By.cssSelector("a.ng-binding")).getText();
		
	}//END METHOD getWorkspaceItemName(WebDriver, WebElement)
	
	/**
	 * Method used to get all the <b>measures</b> from the calculations of a workspace. Assumes 
	 * there's at least one measure created. Will fail if they're no measures
	 * @param driver Web browser being used at the moment
	 * @return A list of all the measures
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static ArrayList<WebElement> getMeasureItems(WebDriver driver) throws InterruptedException
	{
		goToCalculations(driver);
		ArrayList<WebElement> measureItems = new ArrayList<WebElement> ();
		WebElement measures = null;
		//focusing to the measures section. 
		//NOTE: metrics use the similar structure. Hence .get(1) . It's to focus on the measure section.
		measures = driver.findElements(By.cssSelector("div.ui-view-row.ng-scope")).get(1);
		measures = measures.findElement(By.cssSelector("div.col-sm-12.table.ng-scope"));
		
		measureItems = (ArrayList<WebElement>) measures.findElements(By.xpath("div"));
		
		//for removing the header of all the measures. The row that can select all the measures.
		measureItems.remove(0);
		
		return measureItems;
		
	}//END METHOD getMeasureItems(WebDriver)
	
	/**
	 * Method used to get the name of the measure provided.
	 * @param measure The measure to get the name from.
	 * @return The name of the measure.
	 */
	public static String getMeasureName(WebElement measure)
	{
		 String measureName = "";
		 
		 measureName = measure.findElement(By.cssSelector("span.ng-binding.ng-scope")).getText();
		 
		 
		 return measureName;
		 
	}//END METHOD getMeasureName(WebElement)
	 
	/**
	 * Method used to get a measure based on the name provided. All measures <b>must</b> 
	 * be unique in order to get the correct measure.
	 * @param driver Web browser being used at the moment.
	 * @param measureName The measure to get
	 * @return The measure found. null if the measure was not found.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static WebElement getMeasure(WebDriver driver, String measureName) throws InterruptedException
	{
		ArrayList<WebElement> measureItems = getMeasureItems(driver);
		 
		for(int i = 0; i < measureItems.size(); i++)
		{
			if(getMeasureName(measureItems.get(i)).equals(measureName))
			{
				return measureItems.get(i);
				 
			}//END if(getMeasureName(measureItems.get(i)).equals(measureName))
			 
		}//END for(int i = 0; i < measureItems.size(); i++)
		
		return null;
		 
	}//END METHOD getMeasure(WebDriver, String)
	 
	/**
	 * Method used to get the Dashboard metric. It search by the <b>name</b> of the metric. This means all 
	 * dashboard graphs and metric <b>must</b> have unique names. <br>
	 * <b>NOTE: </b> a better solution needs to be made for finding metrics in the dashboard. The 
	 * difference between dashboard graph and dashboard metric is the metric does <b>NOT</b> have <code>svg</code> 
	 * HTML tag and dashboard metric has a div tag that uses a different css class compared to dashboard graph.
	 * @param driver Web browser being used at the moment.
	 * @param metricName The metric to get.
	 * @return The metric found. null if the measure was not found.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to be made.
	 */
	public static WebElement getDashboardMetric(WebDriver driver, String metricName) throws InterruptedException
	{
		ArrayList<WebElement> dashboardItems = getDashboardItems(driver);
		 
		for(int i = 0; i < dashboardItems.size(); i++)
		{
			if(getDashboardItemName(dashboardItems.get(i)).equals(metricName))
			{
				return dashboardItems.get(i);
				
			}//END if(getDashboardItemName(dashboardItems.get(i)).equals(metricName))
			 
		}//END for(int i = 0; i < dashboardItems.size(); i++)
		 
		return null;
		 
	}//END getDashboardMetric(WebDriver, String)
	
}//END CLASS EsiActivity
