/**
 * 
 */
package seleniumTools.highcharts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author mubari
 *
 */
public class LineChart extends HighCharts
{

	/**
	 * @param driver
	 * @param chart
	 */
	public LineChart(WebDriver driver, WebElement chart)
	{
		super(driver, chart);
		// TODO Auto-generated constructor stub
	}
	
	public void hoverOverPrimarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(2, xAxisLabel);
    }

    public void hoverOverSecondarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(1, xAxisLabel);
    }
    
    public void hoverOverColumn(WebElement column) {
    	//System.out.println("Inside ColumnChart.hoverOverColumn(WebElement)");
    	super.hoverOver(column);
    	//System.out.println("Exiting ColumnChart.hoverOverColumn(WebElement)");
    	
    }
    
    /*
    public Color getPrimarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(2, xAxisLabelValue);
    }

    public Color getSecondarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(1, xAxisLabelValue);
    }
    */
	
}
