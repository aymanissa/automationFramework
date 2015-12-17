/**
 * 
 */
package metricValidator;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

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
	
	public static void validateMetric(WebDriver driver,
									  WebElement metric,
									  String value, //to be removed once able to detect web objects dynamically
									  String excelFileLocation,
									  String sheetName,
									  String metricName,
									  int row, 
									  int column,
									  String units,
									  long wait_time) throws IOException
	{
		String reporterFontFace = "'verdana'";
		String reporterNewLine = "<br>";
		ExcelUtils.setExcelFile(new File(excelFileLocation).getCanonicalPath(), sheetName);
		String metricValue = getMetricValue(row, column);
		String metricUnit = null;
		
		
		Reporter.log("<blockquote>");
		
		try
		{
			Reporter.log("<font face=" + reporterFontFace + ">Metric value in excel: " + metricValue + "</font>" + reporterNewLine);
			Reporter.log("<font face=" + reporterFontFace + ">Metric value in dashboard: " + metricName + "</font>" + reporterNewLine);
			Reporter.log("<font face=" + reporterFontFace + ">Checking metric value in excel and in dashboard are equal</font>" + reporterNewLine);
			//Assert.assertEquals(metricValue + " ", value + " " units);
			if(units != null)
			{
				Assert.assertEquals(metricValue + " ", value + " " + units);
				
			}//END if(units != null)
			
			else
			{
				Assert.assertEquals(metricValue, value);
				
			}//END ELSE FOR if(units != null)
			
			Reporter.log("<font face=" + reporterFontFace + " color='green'><b>Metric value are equal</b></font>" + reporterNewLine);
			
		}//END TRY BLOCK
		
		catch(AssertionError e)
		{
			Reporter.log("<blockquote>");
			Reporter.log("<font face=" + reporterFontFace + " color='red'>Metric value are <b>NOT</b> equal" + reporterNewLine);
			Reporter.log("</blockquote>");
			Reporter.log("</blockquote>");
			Reporter.log("<hr>" + reporterNewLine);
			
			if(exitOnFail == true)
			{
				throw new AssertionError("Metric value is not equal: Metric in dashboard: " + value + " . Metric in excel: " + metricValue);
				
			}
			
			return;
			
		}//END CATCH BLOCK
		
		Reporter.log("</blockquote>");
		
	}//END METHOD validateMetric(WebElement, String, String, int, int, String, long)
	
}//END CLASS MetricValidator
