/**
 * 
 */
package metricValidator;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import seleniumTools.WebDriverTools;

import excelUtils.ExcelUtils;

/**
 * Class used to validate a dashboard metric in esi.activity.
 * 
 * @author mubari
 *
 */
public class MetricValidator
{
	//-----------------------------VARIABLES----------------------------------//
	/**
	 * A flag to indicate whether or not to stop executing <b>if</b> an assertion fails. 
	 * Default is false.
	 * It is set in the test cases
	 */
	public static boolean exitOnFail = false;
	
	//----------------------------CONSTRUCTOR---------------------------------//
	//------------------------------GETTERS-----------------------------------//
	
	/**
	 * Method used to get the value that the contains the answer key for validating 
	 * a dashboard metric. The cell must contain a number with two decimal digits.
	 * @param row index value where the row of the answer key will be
	 * @param column index value where the column of the answer key will be.
	 * @return The value returned from the cell.
	 */
	private static String getMetricValue(int row, int column)
	{
		DecimalFormat valueFormatter = new DecimalFormat("#,##0.00");
		XSSFCell cell = ExcelUtils.getCell(row, column);
		
		if(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
		{
			return null;
			
		}//END if(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
		
		return valueFormatter.format(cell.getNumericCellValue());
		
	}//END METHOD getMetricValue(int, int)
	
	//------------------------------SETTERS-----------------------------------//
	//------------------------------METHODS-----------------------------------//
	
	/**
	 * Method used to validate metric provided. The value is checked against must 
	 * be stored in an excel file.
	 * 
	 * @param driver Web browser being used at the moment
	 * @param metric The dashboard metric to validate
	 * @param excelFileLocation The locating of the excel file
	 * @param sheetName Excel sheet name to be focused on
	 * @param validateStatus Contains Object[] with two elements. [0] is flagging whether metric validation passed or failed.
	 * [1] contains the Reporter.log content. Ex: validation successful. [1] must be an object of StringBuilder.
	 * @param row The index value where the row of the answer key will be.
	 * @param column The index value where the column the answer key will be.
	 * @param units The unit being used (no space). null if there's no unit
	 * @param wait_time How long will the metric checking will have to wait before proceeding. Time in milliseconds.
	 * @throws IOException If the excel file was not found
	 * @throws InterruptedException Used for Thread.sleep(long). Should be changed for a better alternative.
	 */
	public static void validateMetric(WebDriver driver,
									  WebElement metric,
									  String excelFileLocation,
									  String sheetName,
									  Object[] validateStatus,
									  int row,
									  int column, 
									  String units,
									  long wait_time) throws IOException, InterruptedException
	{
		String reporterFontFace = "'verdana'";
		String reporterNewLine = "<br>";
		ExcelUtils.setExcelFile(new File(excelFileLocation).getCanonicalPath(), sheetName);
		String metricValueExcel = getMetricValue(row, column);
		String metricValueDashboard = metric.findElement(By.cssSelector("h2.no-margins.ng-scope")).getText();
		String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
		
		((StringBuilder) validateStatus[1]).append("<blockquote>");
		
		try
		{
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + ">Metric value in excel: " + metricValueExcel + (units != null ? " " + units : "") + "</font>" + reporterNewLine);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + ">Metric value in dashboard: " + metricValueDashboard + "</font>" + reporterNewLine);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + ">Checking metric value in excel and in dashboard are equal</font>" + reporterNewLine);
			//Assert.assertEquals(metricValue + " ", value + " " units);
			if(units != null)
			{
				Assert.assertEquals(metricValueExcel + " " + units, metricValueDashboard);
				
			}//END if(units != null)
			
			else
			{
				Assert.assertEquals(metricValueExcel, metricValueDashboard);
				
			}//END ELSE FOR if(units != null)
			
			validateStatus[0] = true;
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='green'><b>Metric value are equal</b></font>" + reporterNewLine);
			
		}//END TRY BLOCK
		
		catch(AssertionError e)
		{
			validateStatus[0] = false;
			((StringBuilder) validateStatus[1]).append("<blockquote>");
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='red'>Metric value are <b>NOT</b> equal" + reporterNewLine);
			
			Thread.sleep(wait_time);
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			//screenShotPath + "\\" + screenshotName + "_" + screenShotNumber++ + ".jpg
			((StringBuilder) validateStatus[1]).append("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			
			((StringBuilder) validateStatus[1]).append("</blockquote>");
			((StringBuilder) validateStatus[1]).append("</blockquote>");
			((StringBuilder) validateStatus[1]).append("<hr>" + reporterNewLine);
			
			if(exitOnFail == true)
			{ 
				throw new AssertionError("Metric value is not equal: Metric in dashboard: " + metricValueDashboard + " . Metric in excel: " + metricValueExcel + (units != null ? " " + units: ""));
				
			}
			
			return;
			
		}//END CATCH BLOCK
		
		((StringBuilder) validateStatus[1]).append("</blockquote>");
		
	}//END METHOD validateMetric(WebElement, String, String, int, int, String, long)
	
}//END CLASS MetricValidator
