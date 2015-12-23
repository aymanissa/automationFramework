/**
 * 
 */
package seleniumTools.highcharts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.Color;

/**
 * Class used to hover over a column in the column graph.
 * @author mubari
 *
 */
public class ColumnChart extends HighCharts
{

	/**
	 * Constructor used to initialize the object.
	 * @param driver Web browser being used at the moment
	 * @param chart The WebElement that contains the graph
	 */
	public ColumnChart(WebDriver driver, WebElement chart)
	{
		super(driver, chart);
		
	}//END CONSTRUCTOR ColumnChart(WebDriver, WebElement)
	
	/**
     * @see seleniumTools.highcharts.HighCharts#hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
     * @param xAxisLabel .
     */
	public void hoverOverPrimarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(1, xAxisLabel);
        
    }//END METHOD hoverOverColumnOrBarChartSeriesAtXAxisPosition(String)

	/**
     * @see seleniumTools.highcharts.HighCharts#hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
     * @param xAxisLabel .
     */
    public void hoverOverSecondarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(2, xAxisLabel);
        
    }//END METHOD hoverOverColumnOrBarChartSeriesAtXAxisPosition(String)
    
    /**
     * Method used to hover over a Column in the graph
     * @param graphElement The column to hover over
     */
    public void hoverOver(WebElement graphElement) {
    	super.hoverOver(graphElement);
    	
    }//END METHOD hoverOver(WebElement)
    
    /*
    public Color getPrimarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(1, xAxisLabelValue);
    }

    public Color getSecondarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(2, xAxisLabelValue);
    }
	*/
    
}//END CLASS ColumnChart
