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
		//System.out.print("kjknk " + colNum + "\n");
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		//System.out.println("colNum: " + colNum + " " + cell.getReference() + " ");
		//System.out.println(" " + cell.getReference());
		//System.out.println("null? : " + cell.getDateCellValue() == null ? "null" : "not null");
		return cell.getDateCellValue();
		
	}
	
	public static XSSFCell getCell(int rowNum, int colNum)throws NullPointerException
	{
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		//System.out.println("cell == null? : " + (cell == null ? "cell is null" : "cell is not null") + " cell: " + cell.getReference());
		return cell;
		
	}
	
	//------------------------------SETTERS-----------------------------------//
	
	public static void setExcelFile(String path, String sheetName) throws IOException
	{
		FileInputStream excelfile = new FileInputStream(path);
		excelWBook = new XSSFWorkbook(excelfile);
		excelWSheet = excelWBook.getSheet(sheetName);
		//System.out.println("Excel file set");
	}
	
	//------------------------------METHODS-----------------------------------//
}
