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
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void goToDashboard(WebDriver driver) throws InterruptedException
    {
    	try 
    	{
			Thread.sleep(WAIT_TIME);
			driver.findElement(By.linkText("Dashboard")).click();
			Reporter.log("<font face='verdana' size='4' color='green'>Go to Dashboard</font><br>");
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
			Reporter.log("<br>");
			
			throw new NoSuchElementException("</details>Cannot find Dashboard link");
			//Reporter.log("</details>");
		}
    	
    }//END METOD goToDashboard(WebDriver)
    
	/**
	 * Navigates the webpage to Opportunities
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void goToOpportunities(WebDriver driver) throws InterruptedException
    {
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Opportunities")).click();
    	
    }//END METHOD goToOpportunities(WebDriver)
    
	/**
	 * Navigates the webpage to MasterData
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void goToMasterData(WebDriver driver) throws InterruptedException
    {
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Master Data")).click();
    	
    }//END METHOD goToMasterData(WebDriver)
    
	/**
	 * Navigates the webpage to Configuration
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void goToConfiguration(WebDriver driver) throws InterruptedException
    {
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Configuration")).click();
    	
    }//END METHOD goToConfiguration(WebDriver)
    
	/**
	 * Navigates the webpage to Calculations
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void goToCalculations(WebDriver driver) throws InterruptedException
    {
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Calculations")).click();
    	
    }//END METHOD goToCalculations(WebDriver)
    
	/**
	 * Navigates the webpage to Workspaces
	 * @param driver Web browser being used at the moment
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void goToWorkspaces(WebDriver driver) throws InterruptedException
    {
    	Thread.sleep(WAIT_TIME);
        driver.findElement(By.linkText("Workspaces")).click();
    	
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
	 * @param opportunityFileLocation File location for opportunities. Relative path to excel file.
	 * @throws IOException For reading Excel file. Thrown if file doesn't exist
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
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
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
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
	 * @param masterDataFileLocation Excel file location for master data. Relative path to the excel file.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 * @throws IOException For excel file. Thrown if file doesn't exist
	 */
	public static void importMasterDate(WebDriver driver, String masterDataFileLocation) throws InterruptedException, IOException
    {
    	goToMasterData(driver);
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.cssSelector("input[type=\"file\"]")).clear();
        Thread.sleep(WAIT_TIME);
        //driver.findElement(By.cssSelector("input[type=\"file\"]")).sendKeys("C:\\Users\\mubari\\eclipse\\automationTest\\res\\importFiles\\ES-284\\ES-284 - Step 4 - Aggregations - MasterData.xlsx");
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
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
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
	 * <b>NOTE: </b> deletes the first measure available in the calculations page. 
	 * Implementing code to delete measure based on the name provided rather than 
	 * deleting the first measure available will come in later. <br>
	 * It will fail if the measure element cannot be found
	 * @param driver Web browser being used at the moment
	 * @param measureName Name of the measure to be deleted
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
	 */
	public static void deleteMeasure(WebDriver driver, String measureName) throws InterruptedException
	{
		WebElement measure = getMeasure(driver, measureName);
		Thread.sleep(WAIT_TIME);
//        measure.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		System.out.println("//////////////checkbox");
		measure.findElement(By.cssSelector("input")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div[2]/div/div/ibox-title/div[2]/a")).click();
        Thread.sleep(WAIT_TIME);
        driver.findElement(By.xpath("//button[@type='button']")).click();
		
	}//END METHOD deleteMeasure(WebDriver, String)
	
	/**
	 * Deletes the dashboard graph based on the provided name. <br>
	 * <b>NOTE: </b> Only able to delete dashboard graph <b>IF</b> there's only 
	 * one dashboard graph displayed. It will get confused if there are more. 
	 * Implementing code to delete the correct dashboard graph based on the name 
	 * provided will come in later. <br>
	 * It will fail if it cannot find a graph on the dashboard
	 * @param driver Web browser being used at the moment
	 * @param graphName Name of the graph to be deleted.
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
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
	 * <b>NOTE: </b> this is not functional at the moment. Implementing code to 
	 * delete the correct metric on the dashboard will come in later.
	 * @param driver Web browser being used at the moment
	 * @param metricName Name of the metric to be deleted
	 * @throws InterruptedException For using Thread.sleep(long). A better solution needs to made
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
	 * {@link GraphValidator#validateGraph(seleniumTools.highcharts.HighCharts, String, String, int, int, int, String, long) 
	 * GraphValidator.validateGraph(HighCharts, String, String, int, int, int, String, long)} 
	 * to validate the graph. <br> 
	 * <b>NOTE: </b> validating line graphs is not implemented. Implementing code 
	 * to validate line graph will come in later.
	 * @param driver Web browser being used at the moment
	 * @param chart WebElement that contains chart denoted as svg html tag
	 * @param reporterGraphValidateName Used as the a name for the 
	 * @param chartType
	 * @param excelFileLocation
	 * @param sheetName
	 * @param units
	 * @param dateRow
	 * @param dateCol
	 * @param valueRow
	 * @throws IOException
	 * @throws InterruptedException
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
				//Reporter.log("<details><summary><font face='verdana' size='4'>Graph: " + reporterGraphValidateName + "</font></summary>");
				//Reporter.log("<summary>" + reporterGraphValidateName + "</summary>");
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
					System.out.println("----------------");
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
	
	}//END METHOD validateGraph(WebDriver, WebElement, ChartType, String, String, String, int, int, int)
	
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
			MetricValidator.validateMetric(driver, dashboardMetric, excelFileLocation, sheetName, validateStatus, metricName, row, column, units, WAIT_TIME);
			
			if((boolean)validateStatus[0] == true)
			{
				Reporter.log("<details><summary><font face='verdana' size='4' color='green'>Graph: " + reporterMetricValidateName + "</font></summary>");
				Reporter.log(validateStatus[1].toString());
				
			}
			
			else
			{
				Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterMetricValidateName + "</font></summary>");
				Reporter.log(validateStatus[1].toString());
				
			}
		}
		
		catch (AssertionError e)
		{
			Reporter.log("<details><summary><font face='verdana' size='4' color='red'>Graph: " + reporterMetricValidateName + "</font></summary>");
			Reporter.log(validateStatus[1].toString());
			throw new AssertionError(e);
		}
		
		Reporter.log("</details>");
		Reporter.log("<hr><br>");
		
	}
	
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
        			System.out.println("LINKTEXT");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			System.out.println("XPATH");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			System.out.println("CSS");
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		default:
        		{
        			System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}//END METHOD loadOpportunityGraph
	
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
        			System.out.println("LINKTEXT");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			System.out.println("XPATH");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			System.out.println("CSS");
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		default:
        		{
        			System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}//END METHOD loadMeasureGraph(WebDriver, String, ArrayList<Object[]>)
	
	public static void loadDashboardGraph(WebDriver driver, String graphName, ArrayList<Object[]> filters) throws InterruptedException
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
        			System.out.println("LINKTEXT");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			System.out.println("XPATH");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			System.out.println("CSS");
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		case INPUTTEXT:
        		{
        			System.out.println("INPUTTEXT");
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name((String) filters.get(i)[1])).clear();
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name((String) filters.get(i)[1])).sendKeys((String)filters.get(i)[2]);
        			
        			
        			break;
        		}
        		
        		default:
        		{
        			System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
		
	}
	
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
        			System.out.println("LINKTEXT");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.linkText((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE LINKTEXT
        		
        		case XPATH: 
        		{
        			System.out.println("XPATH");
        			Thread.sleep(WAIT_TIME);
        	        driver.findElement(By.xpath((String) filters.get(i)[1])).click();
        			
        			break;
        			
        		}//END CASE XPATH
        		
        		case CSS:
        		{
        			System.out.println("CSS");
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.cssSelector((String) filters.get(i)[1])).click();
        			break;
        			
        		}//END CASE CSS
        		
        		case INPUTTEXT:
        		{
        			System.out.println("INPUTTEXT");
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name("name")).clear();
        			Thread.sleep(WAIT_TIME);
        			driver.findElement(By.name("name")).sendKeys((String)filters.get(i)[2]);
        			
        			break;
        			
        		}
        		
        		default:
        		{
        			System.out.println("Invalid element type");
        			break;
        			
        		}//END CASE default
        		
        	}//END SWITCH((ElementTypes)filters.get(i)[0])
        	
        }//END for(int i = 0; i < filters.size(); i++)
		
	}
	
	
	public static ArrayList<WebElement> getDashboardItems(WebDriver driver) throws InterruptedException
	{
		goToDashboard(driver);
		ArrayList<WebElement> dashboardItems = new ArrayList<WebElement>();
		
		//dashboardItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
		dashboardItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div.grid-cell.ibox.ng-scope.ui-draggable"));
		
		return dashboardItems;
		
	}
	
	public static String getDashboardItemName(WebDriver driver, WebElement dashboardItem)
	{
		//String jQuerySelector = "arguments[0]";
		
		//((JavascriptExecutor) driver).executeScript("var foo = _.clone($(" + jQuerySelector + "));", dashboardItem);
		//((JavascriptExecutor) driver).executeScript("$($(foo).children()[0]).remove();");
		//get the text
		
		return dashboardItem.findElement(By.cssSelector("h5.ng-binding")).getText();
		
	}
	
	public static WebElement getDashboardGraph(WebDriver driver, String graphName) throws InterruptedException
	{
		ArrayList<WebElement> dashboardItems = getDashboardItems(driver);
		
		for(int i = 0; i < dashboardItems.size(); i++)
		{
			if(getDashboardItemName(driver, dashboardItems.get(i)).equals(graphName))
			{
				return dashboardItems.get(i);
				
			}
						
		}
		
		return null;
		
	}
	
	public static void maximizeDashboardGraph(WebElement graph) throws InterruptedException
	{
		Thread.sleep(WAIT_TIME);
		graph.findElement(By.cssSelector("i.fa.fa-plus-square-o")).click();
		Thread.sleep(WAIT_TIME);
		
	}
	
	public static void restoreDashboardGraph(WebElement graph) throws InterruptedException
	{
		Thread.sleep(WAIT_TIME);
		graph.findElement(By.cssSelector("i.fa.fa-columns")).click();
		Thread.sleep(WAIT_TIME);
		
	}
	
	public static ArrayList<WebElement> getWorkspacesItems(WebDriver driver) throws InterruptedException
	{
		goToWorkspaces(driver);
		Thread.sleep(WAIT_TIME);
		
		ArrayList<WebElement> workspaceItems = null;
		
		//dashboardItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
		workspaceItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("tr.ng-scope"));
		
		return workspaceItems;
		
	}
	
	public static String getWorkspaceItemName(WebDriver driver, WebElement workspaceItem)
	{
		//String jQuerySelector = "arguments[0]";
		
		//((JavascriptExecutor) driver).executeScript("var foo = _.clone($(" + jQuerySelector + "));", dashboardItem);
		//((JavascriptExecutor) driver).executeScript("$($(foo).children()[0]).remove();");
		//get the text
		
		return workspaceItem.findElement(By.cssSelector("a.ng-binding")).getText();
		
	}
	
	public static ArrayList<WebElement> getMeasureItems(WebDriver driver) throws InterruptedException
	{
		goToCalculations(driver);
		ArrayList<WebElement> measureItems = new ArrayList<WebElement> ();
		WebElement measures = null;
		measures = driver.findElements(By.cssSelector("div.ui-view-row.ng-scope")).get(1);
		System.out.println("class: " + measures.getAttribute("class"));
		
		measures = measures.findElement(By.cssSelector("div.col-sm-12.table.ng-scope"));
		System.out.println("class: " + measures.getAttribute("class"));
		
		measureItems = (ArrayList<WebElement>) measures.findElements(By.xpath("div"));
		measureItems.remove(0);
		
		return measureItems;
		
	}
	
	 public static String getMeasureName(WebElement measure)
	 {
		 String measureName = "";
		 
		 measureName = measure.findElement(By.cssSelector("span.ng-binding.ng-scope")).getText();
		 
		 
		 return measureName;
		 
	 }
	 
	 public static WebElement getMeasure(WebDriver driver, String measureName) throws InterruptedException
	 {
		 ArrayList<WebElement> measureItems = getMeasureItems(driver);
		 
		 for(int i = 0; i < measureItems.size(); i++)
		 {
			 if(getMeasureName(measureItems.get(i)).equals(measureName))
			 {
				 return measureItems.get(i);
				 
			 }
			 
		 }
		return null;
		 
	 }
	 
	 public static WebElement getDashboardMetric(WebDriver driver, String metricName) throws InterruptedException
	 {
		 ArrayList<WebElement> dashboardItems = getDashboardItems(driver);
		 
		 for(int i = 0; i < dashboardItems.size(); i++)
		 {
			 if(getDashboardItemName(driver, dashboardItems.get(i)).equals(metricName))
			{
				 System.out.println("Metric found!!!");
				return dashboardItems.get(i);
				
			}
			 
		 }
		 
		 return null;
		 
	 }
	
}//END CLASS EsiActivity
