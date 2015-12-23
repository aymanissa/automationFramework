/**
 * 
 */
package seleniumTools.highcharts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.Color;

/**
 * Class used to hover over a bar in the bar graph.
 * @author mubari
 *
 */
public class BarChart extends HighCharts
{

	/**
	 * Constructor used to initialize the object.
	 * @param driver Web browser being used at the moment
	 * @param chart The WebElement that contains the graph
	 */
	public BarChart(WebDriver driver, WebElement chart)
	{
		super(driver, chart);
		
	}//END CONSTRUCTOR BarChart(WebDriver, WebElement)
	
	/**
     * @see seleniumTools.highcharts.HighCharts#hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
     */
	public void hoverOverPrimarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(2, xAxisLabel);
        
    }//END METHOD hoverOverPrimarySeriesAtXAxisLabel(String)
	
	/**
     * @see seleniumTools.highcharts.HighCharts#hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
     */
    public void hoverOverSecondarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(1, xAxisLabel);
        
    }//END METHOD hoverOverPrimarySeriesAtXAxisLabel(String)
    
    /**
     * Method used to hover over a bar in the graph
     * @param graphElement The bar to hover over.
     */
    public void hoverOver(WebElement graphElement) {
    	super.hoverOver(graphElement);
    	
    }//END METHOD hoverOver(WebElement)
    
    /*
    public Color getPrimarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(2, xAxisLabelValue);
    }

    public Color getSecondarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(1, xAxisLabelValue);
    }
    */

}//END CLASS BarChart
