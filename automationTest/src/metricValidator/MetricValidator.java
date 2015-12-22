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
 * @author mubari
 *
 */
public class MetricValidator
{
	//-----------------------------VARIABLES----------------------------------//
	public static boolean exitOnFail = false;
	
	//----------------------------CONSTRUCTOR---------------------------------//
	//------------------------------GETTERS-----------------------------------//
	
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
	 * @param driver
	 * @param metric
	 * @param value
	 * @param excelFileLocation
	 * @param sheetName
	 * @param metricName
	 * @param row
	 * @param column
	 * @param units
	 * @param wait_time
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void validateMetric(WebDriver driver,
									  WebElement metric,
									  String excelFileLocation,
									  String sheetName,
									  Object[] validateStatus,
									  String metricName,
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
