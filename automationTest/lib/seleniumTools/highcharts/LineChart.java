/**
 * 
 */
package seleniumTools.highcharts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.Color;

/**
 * Class used to hover a point in the Line graph. <br>
 * <b>NOTE: </b>at the moment, hovering over a point in the Line graph doesn't work.
 * check <a target='_blank' href='https://github.com/Ardesco/Powder-Monkey/blob/master/src/main/java/com/lazerycode/selenium/graphs/LineChart.java'>link</a>
 * for some help.
 * @author mubari
 *
 */
public class LineChart extends HighCharts
{

	/**
	 * Constructor used to initialize the object.
	 * @param driver Web browser being used at the moment
	 * @param chart The WebElement that contains the graph
	 */
	public LineChart(WebDriver driver, WebElement chart)
	{
		super(driver, chart);
		
	}//END CONSTRUCTOR (WebDriver, WebElement)
	
	/**
     * @see seleniumTools.highcharts.HighCharts#hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
     */
	public void hoverOverPrimarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(2, xAxisLabel);
    }
	
	/**
     * @see seleniumTools.highcharts.HighCharts#hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
     */
    public void hoverOverSecondarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(1, xAxisLabel);
    }
	
    /**
     * Method used to hover over a point in the Line graph
     * @param graphElement The point in the graph to hover over
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
	
}//END CLASS LineChart
