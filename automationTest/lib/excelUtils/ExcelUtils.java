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
	 * Return the value at the cell. The parameter are index based.
	 * @param rowNum - the row of the cell. First row starts at 0
	 * @param colNum - the column of the cell. First column starts at 0
	 * @return The value on the string. Returns NULL if no value found
	 */
	public static String getCellDataString(int rowNum, int colNum)
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		String cellData = cell.getStringCellValue();
		return cellData;
		
	}
	
	public static double getCellDataDouble(int rowNum, int colNum)
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		double cellData = cell.getNumericCellValue();
		return cellData;
		
	}
	
	public static Date getCellDate(int rowNum, int colNum)throws NullPointerException
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		return cell.getDateCellValue();
		
	}
	
	public static XSSFCell getCell(int rowNum, int colNum)throws NullPointerException
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		return cell;
		
	}
	
	//------------------------------SETTERS-----------------------------------//
	
	public static void setExcelFile(String path, String sheetName) throws IOException
	{
		FileInputStream excelfile = new FileInputStream(path);
		excelWBook = new XSSFWorkbook(excelfile);
		excelWSheet = excelWBook.getSheet(sheetName);

	}
	
	//------------------------------METHODS-----------------------------------//
}
