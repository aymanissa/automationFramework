package seleniumTools;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import enumTypes.BrowserType;


/**
 * Class used to instantiate a browser, take a screenshot, etc. This is a utility tool that uses Selenium API. 
 * The main purpose is to make it easier to instantiate a browser or take a screenshot.
 * @author Muhammad Umar Bari
 *
 */
public class WebDriverTools 
{	
	private static int screenShotNumber = 1;
	
	
	public static int getScreenShotNumber()
	{
		return screenShotNumber;
		
	}
	
	/**
	 * Method used to configure and load browser driver. Makes sure the browser opens maximized.
	 * @param browserType - The browser to be used. It can be Chrome, Firefox or IE. Must use BrowserType enum
	 * @throws IOException 
	 */
	public WebDriver instantiateBrowser(BrowserType browserType) throws IOException
	{
		WebDriver driver = this.getWebDriver(browserType);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		return driver;
		
	}//END METHOD instantiateBrowser(browserType, url)
	
	/**
	 * Method used to get WebDriver depending on which web browser is going to be used. 
	 * @param browserType -  The browser to be used. It can be Chrome, Firefox or IE. Must use BrowserType enum
	 * @return WebDriver with the proper configuration of the browser.
	 * @throws IOException 
	 */
	private WebDriver getWebDriver(BrowserType browserType) throws IOException
	{
		switch (browserType)
		{
			case FIREFOX: 
			{
				/*
				 * The idea is to use Firefox "default" profile. This is important, make sure Firefox is using the default profile.
				 * To ensure it is, type the following in the run command: 
				 * firefox.exe -P
				 * The following code will extract the user and password information in order to login 
				 * to esi.activity. This means the user needs to login to esi.activity beforehand in order to make this work.
				 * The following code will also bypass the HTTP authentication. A Firefox plugin needs
				 * to be installed. It is located in lib/autoauth-2.1-fx+fn.xpi
				 * Do not move, rename or delete the file.
				 */
				
				//check if the firefox plugin exists				
				String pluginDir = "lib/BrowserDrivers/Firefox_plugin/autoauth-2.1-fx+fn.xpi";
				if(new File(pluginDir).exists())
				{
					FirefoxProfile ffprofile;
					ProfilesIni profile = new ProfilesIni();
					ffprofile = profile.getProfile("default");
					File autoAuthPlugin = new File(pluginDir);
					ffprofile.addExtension(autoAuthPlugin);
					return new FirefoxDriver(ffprofile);
					
				}//END if(new File(pluginDir).exists())
				
				else if(new File(System.getProperty("user.dir") + "/TestRunner_lib/BrowserDrivers/Firefox_plugin/autoauth-2.1-fx+fn.xpi").exists())
				{
					FirefoxProfile ffprofile;
					ProfilesIni profile = new ProfilesIni();
					ffprofile = profile.getProfile("default");
					File autoAuthPlugin = new File(pluginDir);
					ffprofile.addExtension(autoAuthPlugin);
					return new FirefoxDriver(ffprofile);
					
				}
				
				//plugin does not exist
				else
				{
					throw new RuntimeException("Firefox plugin not found");
					
				}//END ELSE FOR if(new File(pluginDir).exists())
				
				
			}//END CASE FIREFOX
			
			case IE: 
			{	
		        String driverDir = "lib/BrowserDrivers/IEDriverServer.exe";
				//check if IE driver exists
				if(new File(driverDir).exists())
				{
					System.setProperty("webdriver.ie.driver", driverDir);
					return new InternetExplorerDriver();
					
				}//END if(new File(driverDir).exists())
				
				else if(new File(System.getProperty("user.dir") + "/TestRunner_lib/BrowserDrivers/IEDriverServer.exe").exists())
				{
					//this will use 32 bit Internet Explorer
					
					System.setProperty("webdriver.ie.driver", driverDir);
					return new InternetExplorerDriver();
					
				}//END else if(new File(System.getProperty("user.dir") + "/TestRunner_lib/BrowserDrivers/IEDriverServer.exe").exists())
				
				//if IE driver not, throw RuntimeException
				else
				{
					throw new RuntimeException("IE driver not found");
					
				}//END ELSE FOR if(new File(driverDir).exists())
				
			}//END CASE IE
			
			case CHROME: 
			{
				String driverDir = "lib/BrowserDrivers/chromedriver.exe";
				
				//check if chrome driver exists
				//this if statement is meant to be used when running it in eclipse
				if(new File(driverDir).exists())
				{
					System.setProperty("webdriver.chrome.driver", driverDir);
					return new ChromeDriver();
					
				}//END if(new File(driverDir).exists())
				
				//this else if statement is meant to be used when running it from a JAR
				//when running the JAR from Terminal, the JAR can't find it in the lib folder, 
				//so an absolute path needs to be generated no matter which computer is being run in.
				else if(new File(System.getProperty("user.dir") + "/TestRunner_lib/BrowserDrivers/chromedriver.exe").exists())
				{
					System.setProperty("webdriver.ie.driver", driverDir);
					return new InternetExplorerDriver();
					
				}
				
				else
				{
					throw new RuntimeException("Chrome driver not found");
					
				}//END ELSE FOR if(new File(driverDir).exists())
				
				
			}//END CASE CHROME
			
			default:
			{
				throw new RuntimeException("Browser type unsupported");
				
			}//END DEFAULT
		
		}//END switch(browserType)
		
	}//END METHOD getWebDriver(browserType)
	
	/**
	 * Method used to create a screenshot. 
	 * @param driver - The WebDriver being used
	 * @param screenshotName - The name of the file. No file extention
	 */
	public static void takeScreenshot(WebDriver driver, String screenShotPath, String screenshotName)
	{
		File browserFile = new File (screenShotPath + "\\" + screenshotName + "_" + screenShotNumber++ + ".jpg");
		screenshotBrowser((TakesScreenshot)driver, screenshotName, browserFile);
		
	}//END METHOD takeScreenshot(driver, screenshotName)
	
	private static void screenshotBrowser(TakesScreenshot driver, String screenshotName, File file)
	{
		try
		{
			File scrFile = driver.getScreenshotAs(OutputType.FILE);
			//System.out.println("PNG browser snapshot file location: " + file.toURI().toString());
			
			FileUtils.deleteQuietly(file);
			FileUtils.moveFile(scrFile, file);
		}//END TRY BLOCK
		
		catch (WebDriverException | IOException e)
		{
			System.out.println("Could not create a screenshot: " + screenshotName + "\n" + e);
			
		}//END catch (WebDriverException | IOException e)
		
	}//END METHOD screenshotBrowser(driver, screenshotName, browserFile)
	
}//END CLASS WebDriverTools
