/**
 * 
 */
package excelUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class used to read dates, numbers from the excel file.
 * @author mubari
 *
 */
public class ExcelUtils
{
	//-----------------------------VARIABLES----------------------------------//
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	
	//----------------------------CONSTRUCTOR---------------------------------//
	//------------------------------GETTERS-----------------------------------//
	
	/**
	 * Return the value of the cell in a string form. The parameter are index based.
	 * @param rowNum - the row of the cell. First row starts at 0
	 * @param colNum - the column of the cell. First column starts at 0
	 * @return The value of the cell in <code>String</code> of the cell. NULL if no value found.
	 */
	public static String getCellDataString(int rowNum, int colNum)
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		String cellData = cell.getStringCellValue();
		return cellData;
		
	}//END METHOD getCellDataString(int, int)
	
	/**
	 * Returns the value of the cell in <code>double</code> form.
	 * @param rowNum index value of the row of the cell.
	 * @param colNum index value of the column of the cell.
	 * @return The value of the cell in <code>double</code> of the cell. NULL if no value found.
	 */
	public static double getCellDataDouble(int rowNum, int colNum)
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		double cellData = cell.getNumericCellValue();
		return cellData;
		
	}//END METHOD getCellDataDouble(int, int)
	
	/**
	 * Returns the value of the cell in <code>Date</code> form.
	 * @param rowNum index value of the row of the cell.
	 * @param colNum index value of the column of the cell.
	 * @return The value of the cell in <code>Date</code> of the cell. NULL if no value found.
	 */
	public static Date getCellDate(int rowNum, int colNum)
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		return cell.getDateCellValue();
		
	}//END METHOD getCellDate(int, int)
	
	/**
	 * Returns the cell based on the row and column
	 * @param rowNum index value of the row of the cell
	 * @param colNum index value of the column of the cell.
	 * @return The excel cell. NULL if no value found.
	 */
	public static XSSFCell getCell(int rowNum, int colNum)
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		return cell;
		
	}//END METHOD getCell(int, int)
	
	//------------------------------SETTERS-----------------------------------//
	
	/**
	 * Sets the excel file location. Doesn't check if the file exists.
	 * @param path Relative path of the excel file
	 * @param sheetName Sheet The name of the sheet being used in the excel file
	 * @throws IOException Thrown if the file doesn't exists.
	 */
	public static void setExcelFile(String path, String sheetName) throws IOException
	{
		FileInputStream excelfile = new FileInputStream(path);
		excelWBook = new XSSFWorkbook(excelfile);
		excelWSheet = excelWBook.getSheet(sheetName);

	}//END METHOD setExcelFile(String, String)
	
	//------------------------------METHODS-----------------------------------//
	
}//END CLASS ExcelUtils
