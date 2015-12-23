/**
 * 
 */
package seleniumTools.highcharts;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

/**
 * Class used to interact with HighCharts graphs. This includes Bar, line and column.
 * This a modified version of <a target='_blank' href='https://github.com/Ardesco/Powder-Monkey/blob/master/src/main/java/com/lazerycode/selenium/graphs/HighCharts.java'>link</a>
 * @author mubari
 */
public class HighCharts
{
	//-----------------------------VARIABLES----------------------------------//
	protected WebDriver 		driver;
	protected WebElement 		chart;
	protected WebDriverWait 	wait;
	protected Actions 			performAction;
	protected JavascriptLibrary javascript = new JavascriptLibrary();
	
	//variables related with the chart
	@FindBy(how = How.CSS, using = "g.highcharts-tooltip")
	private WebElement toolTip;
	@FindBy (how = How.CSS, using = "g.highcharts-legend")
	private WebElement legend;
	@FindBy(how = How.CSS, using = "g.highcharts-axis")
    private List<WebElement> axisLabels;
	
	
	@FindBy(how = How.CSS, using = "g.highcharts-series-group")
	private List<WebElement> xAxis;
	
	//----------------------------CONSTRUCTOR---------------------------------//
	
	/**
	 * Constructor sets the WebDriver and the WebElement that contains the chart
	 * @param driver - WebDriver
	 * @param chart - WebElement that has the chart
	 */
    public HighCharts(WebDriver driver, WebElement chart) {
        PageFactory.initElements(new DefaultElementLocatorFactory(chart), this);
        this.driver = driver;
        this.chart = chart;

        int waitTimeoutInSeconds = 15;
        wait = new WebDriverWait(driver, waitTimeoutInSeconds, 100);
        performAction = new Actions(driver);
        
    }//END CONSTRUCTOR(WebDriver, WebElement)
    
    //------------------------------GETTERS-----------------------------------//
    
    /**
     * Gets the content in the tool-tip
     * @param lineNo element value of the tool-tip. Starting at 1
     * @return String: containing tool-tip content
     * @throws NoSuchElementException - If tool-tip is not found
     */
    public String getToolTipLine(int lineNo) throws NoSuchElementException {
        List<String> lines = new ArrayList<String>();
        List<WebElement> toolTipLines = toolTip.findElements(By.cssSelector("text tspan"));
        for (WebElement toolTipLine : toolTipLines) {
            lines.add(toolTipLine.getText());
        }
        if (lineNo > lines.size()) {
            throw new NoSuchElementException("There is no line " + lineNo + "! There are only " + lines.size() + " lines in the tool tip");
        }
        //We return line - 1 because the lines Array starts a 0 not 1
        return lines.get(lineNo - 1);
        
    }//END METHOD getToolTipLine(int)
    
    /**
     * Gets the X axis label
     * @return WebElement that contains the x Axis
     */
    protected WebElement getXAxisLabels() {
        //return axisLabels.get(0);
    	return xAxis.get(0);
    	
    }//END METHOD getXAxisLabels()

    /**
     * Gets the text contained within the X axis label
     * @return .
     */
    public List<String> getXAxisLabelsText() {
        List<String> labels = new ArrayList<String>();
        List<WebElement> xAxisLabels = getXAxisLabels().findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));

        for (WebElement xAxisLabel : xAxisLabels) {
        	labels.add(xAxisLabel.getText());

        }
        return labels;
        
    }//END METHOD getXAxisLabelsText()
    
    /**
     * Return a list of X axis labels as WebElements
     * @return .
     */
    public List<WebElement> getXAxisLabelsWebElementList(){
    	return getXAxisLabels().findElements(By.cssSelector("g.highcharts-series.highcharts-tracker > rect"));
    	
    }
    
    /**
     * Gets the X axis label in an array
     * @return .
     */
    public String[] getXAxisLabelsAsArray() {
        List<String> xAxisLabels = getXAxisLabelsText();
        return xAxisLabels.toArray(new String[xAxisLabels.size()]);
        
    }//END METHOD getXAxisLabelsAsArray()
    
    /**
     * Gets Y axis label
     * @return Y axis label as a WebElement
     */
    protected WebElement getYAxisLabels() {
        return axisLabels.get(1);
    }//END METHOD getYAxisLabels()

    /**
     * Gets the Y axis label as a string
     * @return .
     */
    public List<String> getYAxisLabelsText() {
        List<String> labels = new ArrayList<String>();
        List<WebElement> yAxisLabels = getYAxisLabels().findElements(By.cssSelector("text"));
        for (WebElement yAxisLabel : yAxisLabels) {
            labels.add(yAxisLabel.getText());
        }//END for (WebElement yAxisLabel : yAxisLabels)
        return labels;
    }//END METHOD getYAxisLabelsText()

    /**
     * Gets the Y axis label in an array
     * @return .
     */
    public String[] getYAxisLabelsAsArray() {
        List<String> yAxisLabels = getYAxisLabelsText();
        return yAxisLabels.toArray(new String[yAxisLabels.size()]);
    }//END METHOD getYAxisLabelsAsArray

	//------------------------------SETTERS-----------------------------------//
    /*
    public void setChart(WebElement chart)
    {
    	this.chart = chart;
    	
    }//END METHOD setChart(WebElement)
    */
	
	//------------------------------METHODS-----------------------------------//
    
    /**
     * Checks if the chart is displayed
     * @return true if the chart is displayed. false otherwise
     */
    public boolean isChartDisplayed() {
    	boolean chartDisplayed;
    	try
    	{
    		chartDisplayed = wait.until(visibilityOf(this.chart)) != null;
    		
    	}
    	
    	catch(WebDriverException e)
    	{
    		chartDisplayed = false;
    		
    	}
    	
        return chartDisplayed;
        
    }//END METHOD isChartDisplayed()

    /**
     * Checks if the legend is displayed
     * @return true if the legend is displayed. false otherwise
     */
    public boolean isLegendDisplayed() {
    	boolean legendDisplayed;
    	try
    	{
    		legendDisplayed = wait.until(visibilityOf(this.chart)) != null;
    		
    	}
    	
    	catch(WebDriverException e)
    	{
    		legendDisplayed = false;
    		
    	}
    	
        return legendDisplayed;
        
    }//END METHOD isLegendDisplayed()

    /**
     * Checks if tool-tip is displayed
     * @return true if tool-tip is displayed. false otherwise
     */
    public boolean isTooltipDisplayed() {
    	boolean toolTipDisplayed;
    	try
    	{
    		toolTipDisplayed = wait.until(visibilityOf(toolTip)) != null;
    		
    	}
        catch(WebDriverException e)
        {
        	toolTipDisplayed = false;
        	
        }
    	
    	return toolTipDisplayed;
    	
    }//END METHOD isTooltipDisplayed()
    
    /**
     * Hover over a column or bar chart 
     * @param series .
     * @param xAxisLabel .
     */
    protected void hoverOverColumnOrBarChartSeriesAtXAxisPosition(int series, String xAxisLabel) {
        int barNumber = getXAxisLabelsText().indexOf(xAxisLabel);
        WebElement pointToHoverOver = chart.findElements(By.cssSelector("g.highcharts-tracker > g:nth-of-type(" + series + ") > rect")).get(barNumber);

        //For browsers not supporting native events
        javascript.callEmbeddedSelenium(driver, "triggerEvent", pointToHoverOver, "mouseover");
        //For browsers supporting native events
        performAction.moveToElement(pointToHoverOver).perform();
        
    }//END METHOD hoverOverColumnOrBarChartSeriesAtXAxisPosition(int, String)
    
    /**
     * Method used to hover over a column based on the WebElement. The WebElement must contain a <b><mark><code>rect</code></mark></b> tagname. 
     * The idea for how it works is the bar gets hovered over by the elements present in a tagname <b><mark><code>g</code></mark></b> with the class 
     * <b><mark><code>highcharts-series</code></mark></b> and <b><mark><code>highchart-tracker</code></mark></b>. 
     * This is contained within <b><mark><code>svg</code></mark></b> tagname (this should be found when constructing this class.).
     * The caller will have to get the WebElement that points to the column in the chart.<br><br> 
     * 
     * NOTE: this method must <b>NOT</b> be called directly. It should called from it's subclass. This is made public so it can be used for 
     * polymorphism, specifically used in {@link graphValidator.GraphValidator#validateGraph(WebDriver, HighCharts, String, String, Object[], int, int, int, String, long)} 
     * to reduce repeating code.
     * 
     * @see seleniumTools.highcharts.HighCharts#getXAxisLabelsWebElementList()
     * @param graphElement .
     */
    public void hoverOver(WebElement graphElement)
    {
    	//javascript.callEmbeddedHtmlUtils(driver, "triggerEvent", column, "mouseOver");
    	performAction.moveToElement(graphElement).perform();
    	
    }//END METHOD hoverOver(WebElement)

    @SuppressWarnings("unused")
	private static ExpectedCondition<Boolean> attributeIsEqualTo(final WebElement element, final String attribute, final String attributeValue) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return element.getAttribute(attribute).equals(attributeValue);
            }
        };
    }
    
}//END CLASS HighCharts
