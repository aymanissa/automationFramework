/**
 * 
 */
package graphValidator;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import excelUtils.ExcelUtils;

import seleniumTools.WebDriverTools;
import seleniumTools.highcharts.HighCharts;

/**
 * @author mubari
 *
 */
public class GraphValidator
{
	//-----------------------------VARIABLES----------------------------------//
	private static XSSFCell dateCell;
	private static XSSFCell valueCell;
	public static boolean exitOnFail = false;
	
	//------------------------------METHODS-----------------------------------//
	
	/**
	 * Method used to check if the next cell is not null (cell to the right)
	 * @param row - current cell row
	 * @param col - current cell column
	 * @return - <code>true</code> if the next cell is not null. <code>false</code> otherwise.
	 */
	private static boolean hasNext(int row, int col)
	{
		XSSFCell cell = ExcelUtils.getCell(row, col + 1);

		//check the cell next to current cell (to the right)
		if(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
		{
			return false;
			
		}
		
		return true;
		
	}//END METHOD hasNext(int, int)
	
	/**
	 * Method used to get the date and value corresponding to the date. It gets stored as String [].<br> 
	 * Each String[] is stored in an arrayList. String[0] contains the date and String [0] contains the value for the date.<br> 
	 * The arrayList is created by reading the excel file 
	 * (the excel file must be set using {@link excelUtils.ExcelUtils#setExcelFile(String, String) setExcelFile(String, String)} beforehand).<br> 
	 * If there is no value for the date, String[1] is set to be null. If a value contains 0 or 0.0, String[1] will store it as 0.00. <br>
	 * If there are 'n' number of dates and 'n + 1' values for those dates, this method will only get up to 'n'. It ignores anything that is extra.<br>
	 * The cells in the excel that contain a date, it <b>must</b> be formatted as a date in excel. This method may throw exceptions otherwise.
	 * @param dateRowStart - The starting <b>index</b> of where the date row will be
	 * @param dateColStart - The starting <b>index</b> of where the date column will be
	 * @param valueRowStart - The starting <b>index</b> of where the value row will be
	 * @return - An arrayList<String[]>. Formatted in: String[0] is the date. String[1] is the value for each element in the arrayList 
	 */
	private static ArrayList<String[]> getDateAndValue(int dateRowStart, int dateColStart, int valueRowStart) throws IOException
	{
		//use a String [] to contain the date and the value corresponding to it.
		//sets String[1] null if there is no value in the excel cell.
		
		//while the next cell is not null
		//	get the cell that contains the date
		//	get the cell that contains the value for the corresponding date
		//	get the date and format it to Month year. Ex: Jan 2014
		//	if valueCell is not blank
		//		if valueCell contains 0 or 0.0
		//			create a String[] = {date, "0.00"}
		//			add String[] the arrayList
		//		else
		//			format the value appropriately
		//			create a String[]
		//			add String[] to the arrayList
		//	else
		//		create a String[] = {date, null}
		//		add String[] to the arrayList
		
		ArrayList <String [] > arrayList = new ArrayList <String []> ();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM yyyy");
		DecimalFormat valueFormatter = new DecimalFormat("#,##0.00");
		
		int currentCol = dateColStart;
		boolean hasNext = hasNext(dateRowStart, currentCol);
		
		
		while(hasNext)
		{	
			dateCell = ExcelUtils.getCell(dateRowStart, currentCol);
			valueCell = ExcelUtils.getCell(valueRowStart, currentCol);
			
			String date = dateFormatter.format(dateCell.getDateCellValue());
			
			if(valueCell != null && valueCell.getCellType() != Cell.CELL_TYPE_BLANK)
			{
				//check if the number returned is a 0.
				//	make sure the string contains 0.00 format.
				String value = null;
				if(valueCell.getNumericCellValue() == 0.00)
				{
					value = "0.00";
					String [] pair = {date, value};
					arrayList.add(pair);
					
				}//END if(valueCell.getNumericCellValue() == 0.00)
				
				else
				{
					value = valueFormatter.format(valueCell.getNumericCellValue());
					String [] pair = {date, value};
					arrayList.add(pair);
					
				}//END ELSE FOR if(valueCell.getNumericCellValue() == 0.00)
				
				
			}//END if(valueCell != null && valueCell.getCellType() != Cell.CELL_TYPE_BLANK)
			
			else
			{
				String [] pair = {date, null};
				arrayList.add(pair);
				
			}//END ELSE FOR if(valueCell != null && valueCell.getCellType() != Cell.CELL_TYPE_BLANK)
			
			currentCol++;
			hasNext = hasNext(dateRowStart, currentCol - 1);
			
		}//END while(hasNext)
		
		return arrayList;
		
	}//END METHOD getDateAndValue()
	
	/**
	 * Method used to truncate leading and trailing empty cells. If there is an empty cell, arrayList.get(i)[1] will contain null. 
	 * If there is an empty cell between a non empty cell, it gets ignored (not truncated). 
	 * Ex: <table border='1'> 
	 * 			<tr>
	 * 				<td>Non empty cell</td>
	 * 				<td>Empty cell</td>
	 * 				<td>Non empty cell</td>
	 * 			</tr>
	 * 	   </table>
	 * This method must be executed after the method {@link #getDateAndValue(int, int, int) getRow(int, int, int)}
	 * @param arrayList
	 * @return
	 */
	private static ArrayList<String [] > truncateColumns(ArrayList<String [] > arrayList)
	{
		//set startingIndex to 0
		//set tempIndex to 0
		//iterate through the whole list.
		//	check if current index has a value.
		//		if the startIndex has been set
		//			set startingIndex to the current index
		//		set tempIndex to the current index
		//	
		//	check if tempIndex + 1 has a value
		//		increment tempIndex by 1
		//		set valueEnd to the tempIndex
		//
		//	else for if tempIndex + 1 has a value
		//		if tempIndex is equal to array size - 1
		//			break the loop. Array has been iterated.
		//		else 
		//			increment tempIndex by one
		//
		
		int startIndex = 0;				//actual range of the arrayList
		int endIndex = 0;				//actual range of the arrayList
		int tempIndex =	0;				//index pointer.
		boolean startIndexSet = false;	//to indicate the startIndex has been used. Only used once
		
		//iterate through the list
		for(int i = 0 ; i < arrayList.size(); i++)
		{	
			//check if current index has a value
			if(arrayList.get(i)[1] != null && arrayList.get(i)[1] != "0.00")
			{
				//if startIndex has been set
				if(startIndexSet == false)
				{
					startIndex = i;
					startIndexSet = true;
					
				}//END if(startIndexSet == false)
				
				tempIndex = i;
				
			}//END if(arrayList.get(i)[1] != null)
			
			//check if the next cell has a value.
			if(tempIndex + 1 < arrayList.size() && arrayList.get(tempIndex + 1)[1] != null && arrayList.get(tempIndex + 1)[1] != "0.00")
			{
				tempIndex++;
				endIndex = tempIndex;
				
			}//END if(tempIndex + 1 < arrayList.size() && arrayList.get(tempIndex + 1)[1] != null)
			
			//there might be empty cells in between or have reached the end of the list.
			else
			{
				if(tempIndex == arrayList.size() - 1)
				{
					break;
					
				}//END if(tempIndex == arrayList.size() - 1)
				
				else
				{
					tempIndex++;
					
				}//END ELSE FOR if(tempIndex == arrayList.size() - 1)
				
			}//END ELSE FOR if(tempIndex + 1 < arrayList.size() && arrayList.get(tempIndex + 1)[1] != null)
			
		}//END for(int i = 0 ; i < arrayList.size(); i++)
		
		
		//copy the arrayList from startIndex to endIndex
		ArrayList <String[] > newList = new ArrayList <String []> ();
		if(startIndex != endIndex)
		{
			for(int i = startIndex; i < endIndex + 1; i++)
			{
				newList.add(arrayList.get(i));
				
			}//END for(int i = startIndex; i < endIndex + 1; i++)
			
			
		}//END if(startIndex != endIndex)
		
		return newList;
		
	}//END METHOD truncateRows(ArrayList<String []>)
	
	
	
	//public static void validateColumnGraph(ColumnChart chart, String excelFile, int colMin, int colMax) throws IOException, InterruptedException
	/**
	 * Method used to validate the graph provided. The values that are checked against must be stored in an excel file. <br>
	 * It first checks if the number of columns/bars/points/etc are equal to number of values in the excel file. <br>
	 * The first row must be the dates (formated in date) and the second must be in numbers with two decimal digits included.<br>
	 * It will ignore any leading or trailing empty cells (which this method regards as null). It will keep empty cells between non-empty cells. <br>
	 * If they're not, then the validation of the graph is skipped and returns to the caller. <br> 
	 * If the number of columns/bars/points/etc are equal to number of values, it proceeds to validating each column/bar/point/etc. <br>
	 * If one of the columns/bars/point/etc don't match with the values in the excel file, 
	 * it gets noted in the reporter (check Reporter output in TestNG reports). <br>
	 * If the assertion fails, it gets noted in a red font, green if it passes the assertion. <br>
	 * If any assertion fails, it won't show the test has failed, since the assertion fail is handled in this method. <br>
	 * The only to tell is if there are any red fonts.
	 * 
	 * @param chart The chart that contains the graph. The graph can be ColumnChart, BarChart or LineChart. (Note: this list can grow as new Graph types are defined)
	 * @param excelFile The location of the excel file. Note: Should use File.getCanonicalFile() to avoid relative path errors. 
	 * @param sheetName Excel sheet name to be focused on
	 * @param dateRow The row where the start date will be
	 * @param dateCol The column where the start date will be
	 * @param valueRow The row where the start value will be.
	 * @param units Units being used (no space) ex: "USD". If there is no units being used, pass in null.
	 * @param wait_time How long will the graph checking will have to wait before proceeding. Takes in milliseconds form.
	 * @throws IOException If the excel file was not found
	 * @throws InterruptedException Used for Thread.sleep(long). Should be changed for a better alternative
	 */
	public static void validateGraph(WebDriver driver,
									 HighCharts chart, 
									 String excelFile, 
									 String sheetName, 
									 Object [] validateStatus,
									 int dateRow, 
									 int dateCol, 
									 int valueRow, 
									 String units, 
									 long wait_time) throws IOException, InterruptedException
	{
		//NOTE: the parameter HighCharts is meant to be used in a polymorphic way. The subclass of HighCharts
		//		will have a method called hoverOver(WebElement), the subclass methods will be used. 
		//		As long the parameter charts is the subclass of HighCharts, the hoverOver and getToolTip should
		//		operate like normal.
		//		This approach is used in case more chart types are added. So no additional code has to be written and maintained.
		//		One method for all chart types.
		//		The subclass of HighCharts must define their own way of hovering over a bar, column, line, etc.
		
		
		//get the rows that contain the date and values corresponding to the dates
		//truncate the leading and trailing rows that are empty
		//instantiate the excel file
		//get the columns from the graph
		//check number of columns in the graph with truncated arrayList size
		//iterate through the columns
		//	hover over the column
		//	get the toolTip
		//	check the date (Month year) in the column with the truncated arrayList
		//	check the value corresponding to the date with the truncated arrayList
		
		//log every check
		//if the column checks did not pass, skip to the next column. Log the mismatched column
		
		//for report logging
		String reporterNewline = "<br>";
		String reporterFontFace =  "'verdana'";
		String screenShotPath = "res\\screenshots\\TestNG_report\\" + System.getProperty("testNG_screenshotDir");
		
		
		ExcelUtils.setExcelFile(excelFile, sheetName);
		ArrayList<String[]> arrayList = truncateColumns(getDateAndValue(dateRow, dateCol, valueRow));
		ExcelUtils.setExcelFile(excelFile, sheetName);
		
		List<WebElement> columns = chart.getXAxisLabelsWebElementList();
		
		((StringBuilder) validateStatus[1]).append("<blockquote>");
		validateStatus[0] = true;
		
		try
		{
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + ">Number of columns/bars/points in graph: " + columns.size() + "</font>" + reporterNewline);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + ">Number of values in excel file: " + arrayList.size() + "</font>" + reporterNewline);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " >Checking the number of columns/bars/points with number of elements in excel<font>" + reporterNewline);
			Assert.assertEquals(columns.size(), arrayList.size());
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='green'><b>Number of columns/bars/points are equal with number of elements in excel</b></font>" + reporterNewline);
			
			
		}//END TRY BLOCK
		
		catch(AssertionError e)
		{
			((StringBuilder) validateStatus[1]).append("<blockquote>");
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='Red'>Number of columns/bars/points are <b>NOT</b> equal to number elements in excel</font>" + reporterNewline);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='Red'>Number of columns/bars/points: " + columns.size() + "</font>" + reporterNewline);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='Red'>Number of elements in excel: " + arrayList.size() + "</font>" + reporterNewline);
			((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='Red'>Exiting validate graph</font>" + reporterNewline);
			
			Thread.sleep(wait_time);
			String screenshotName = "screenshot";
			WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
			String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
			//screenShotPath + "\\" + screenshotName + "_" + screenShotNumber++ + ".jpg
			((StringBuilder) validateStatus[1]).append("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
			
			((StringBuilder) validateStatus[1]).append("</blockquote>");
			((StringBuilder) validateStatus[1]).append("</blockquote>");
			((StringBuilder) validateStatus[1]).append("<hr>" + reporterNewline);
			
			//exit out of the method.
			
			validateStatus[0] = false;
			if(exitOnFail == true)
			{
				throw new AssertionError("Number of columns/bars/points does NOT match. Number of columns/bars/point in excel: " + arrayList.size() + " .Number of columns/bars/points in graph: " + columns.size());
				
			}
			
			return;
			
		}//END CATCH BLOCK
		
		for(int i = 0; i < columns.size(); i++)
		{
			Thread.sleep(wait_time);
			((StringBuilder) validateStatus[1]).append(reporterNewline + "Checking column/bar/point: [" + i + "]" + reporterNewline);
			chart.hoverOver(columns.get(i));
			
			//get the tooltip
			String date = chart.getToolTipLine(1);
			//String value = chart.getToolTipLine(2);
			String value = chart.getToolTipLine(3);
			
			//assert the tooltip date
			((StringBuilder) validateStatus[1]).append("<blockquote>");
			try
			{
				((StringBuilder) validateStatus[1]).append("Column/bar/point [" + i + "].date: " + date + reporterNewline);
				((StringBuilder) validateStatus[1]).append("Excel file [" + i + "].date: " + arrayList.get(i)[0] + reporterNewline);
				((StringBuilder) validateStatus[1]).append("Checking if date in graph is equal to excel file: " + reporterNewline);
				Assert.assertEquals(date, arrayList.get(i)[0]);
				((StringBuilder) validateStatus[1]).append("<b><font face=" + reporterFontFace + " color='green'>Column/bar/point date and excel date match</font></b>" + reporterNewline + reporterNewline);
				
				
			}//END TRY BLOCK
			
			catch(AssertionError e)
			{
				((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='red'>Column/bar/point [" + i + "].date: " + date + " in graph does <b>NOT</b> match with: " + arrayList.get(i)[0] + "</font>" + reporterNewline);
				if(i != columns.size() - 1)
				{
					((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " >Skipping to next column/bar/point</font><br>");
					
				}//END if(i != columns.size() - 1)
				
				
				
				Thread.sleep(wait_time);
				String screenshotName = "screenshot";
				WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
				String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
				//screenShotPath + "\\" + screenshotName + "_" + screenShotNumber++ + ".jpg
				((StringBuilder) validateStatus[1]).append("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
				
				((StringBuilder) validateStatus[1]).append("</blockquote>");
				validateStatus[0] = false;
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
				
				if(exitOnFail == true)
				{
					throw new AssertionError("Column/bar/point [" + i + "].date: " + date + " in graph does NOT math with date in excel file");
					
				}//END if(exitOnFail == true)
				
				continue;
				
			}//END CATCH BLOCK
			
			//assert tooltip value
			
			try
			{
				((StringBuilder) validateStatus[1]).append("Column/bar/point [" + i + "].value: " + value + reporterNewline);
				((StringBuilder) validateStatus[1]).append("Excel file [" + i + "].value: " + arrayList.get(i)[1] + (units != null ? " " + units : "") + reporterNewline);
				((StringBuilder) validateStatus[1]).append("Checking if value in graph is equal to excel file: " + reporterNewline);
				
				//if there values is not null. This is for if there is an empty cell between non-empty cell. 
				//ex: | non-empty cell | empty cell | non-empty cell |
				if(arrayList.get(i)[1] != null)
				{
					Assert.assertEquals(value, arrayList.get(i)[1] + (units != null ? " " + units : ""));					
					
				}//END if(arrayList.get(i)[1] != null)
				
				else
				{
					Assert.assertEquals(value, "0.00" + (units != null ? " " + units : ""));
					
				}//END ELSE FOR if(arrayList.get(i)[1] != null)
				
				((StringBuilder) validateStatus[1]).append("<b><font face=" + reporterFontFace + " color='green'>Column/bar/point value and excel value match</font></b>" + reporterNewline);
				
			}//END TRY BLOCK
			
			catch(AssertionError e)
			{
				((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " color='red'>Column/bar/point [" + i + "].value: " + value + " in graph does <b>NOT</b> match with: " + arrayList.get(i)[1] + (units != null ? " " + units : "") + "</font>" + reporterNewline);
				if(i != columns.size() - 1)
				{
					((StringBuilder) validateStatus[1]).append("<font face=" + reporterFontFace + " >Skipping to next column/bar/point</font> <br>");
					
				}//END if(i != columns.size() - 1)
				
				Thread.sleep(wait_time);
				String screenshotName = "screenshot";
				WebDriverTools.takeScreenshot(driver, screenShotPath, screenshotName);
				String webImageLink = "..\\..\\..\\" + screenShotPath + "\\" + screenshotName + "_" + (WebDriverTools.getScreenShotNumber() - 1) + ".jpg";
				//screenShotPath + "\\" + screenshotName + "_" + screenShotNumber++ + ".jpg
				((StringBuilder) validateStatus[1]).append("<a target='_blank' href='" + webImageLink + "'><img src='" + webImageLink + "' style='width:25%; height:25%'></a> <br>");
				
				((StringBuilder) validateStatus[1]).append("</blockquote>");
				validateStatus[0] = false;
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
				
				if(exitOnFail == true)
				{
					throw new AssertionError("Column/bar/point [" + i + "].value: " + value + " in graph is not equal the value in excel file: " + arrayList.get(i)[1] + (units != null ? " " + units : ""));					
					
				}//END if(exitOnFail == true)
				
				continue;
			}
			
			((StringBuilder) validateStatus[1]).append("</blockquote>");
			
		}//END for(int i = 0; i < columns.size(); i++)
		
		((StringBuilder) validateStatus[1]).append("</blockquote>");
		
		
	}//END validateColumnGraph
	
}//END CLASS GraphValidator
