/**
 * 
 */
package templates; //change to testCases

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import metricValidator.MetricValidator;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import enumTypes.BrowserType;
import graphValidator.GraphValidator;

import seleniumTools.WebDriverTools;

/**
 * @author 
 *
 */
@SuppressWarnings("unused")
public class TestCaseTemplate
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
		return new Object[][] {{BrowserType.CHROME, "Chrome"}, {BrowserType.FIREFOX, "Firefox"}, {BrowserType.IE, "IE"}};
		
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
	public void test(BrowserType browserType, String browserName) throws IOException
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
		
		
		//end: web browser commands to be executed.
		
		//used for report formatting. Do not remove
		Reporter.log("<blockquote>");
    	Reporter.log("</details>");
		Reporter.log("<br><hr><br>");
		
	}//END TEST METHOD test(BrowserType, String)
	
	//------------------------------METHODS-----------------------------------//
	
}//END CLASS 
