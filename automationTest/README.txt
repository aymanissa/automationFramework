Using Selenium in Google Chrome
-Use the ChromeDriver. Located in the lib folder
-Use the following code
	public class LaunchingChrome 
	{
		public static void main(String[] args) 
		{
			String exePath = "C:\\Users\\abc\\Desktop\\Server\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			WebDriver driver = new ChromeDriver();
			driver.get("http://www.toolsqa.com/automation-practice-form/");
		}
	}

Using Selenium in Mozilla Firefox
-Use the following code 
	WebDriver driver = new FirefoxDriver();
	driver.get(<website link. String format>);

Using Selenium in Internet Explorer
-Use the InternExplorer. Located in the lib folder
-Use the following code
	public class LaunchingIE 
	{
		public static void main(String[] args) 
		{
	        //Path to the folder where you have extracted the IEDriverServer executable
			String service = "C:\\Users\\abc\\Desktop\\Server\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", service);
			InternetExplorerDriver  driver = new InternetExplorerDriver();
	
			driver.get("http://yahoo.com");
		}
	}


Using Selenium in Safari
