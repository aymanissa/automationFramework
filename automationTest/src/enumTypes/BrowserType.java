package enumTypes;

/**
 * Enum used for instantiating browsers in {@link seleniumTools.WebDriverTools#instantiateBrowser(BrowserType)}. <br>
 * If more browser types are to be added, add them in this enum and make the necassary changes in 
 * {@link seleniumTools.WebDriverTools#getWebDriver(BrowserType) getWebDriver}.
 * @author mubari
 *
 */
public enum BrowserType 
{
	FIREFOX, IE, CHROME
}
