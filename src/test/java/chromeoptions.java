import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class chromeoptions {
	ChromeDriver driver = new ChromeDriver();
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\12343\\eclipse-workspace\\DevtoolsDemo\\resources\\chromedriver.exe");

	}
	@Test
	public void captureRequestSelenium() {

		 // specify the path of the chromdriver binary that you have downloaded (see point 2)
	   
	    ChromeOptions options = new ChromeOptions();
	    // if you like to specify another profile
	    options.addArguments("user-data-dir=/root/Downloads/aaa"); 
	    options.addArguments("start-maximized");
	    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    WebDriver driver = new ChromeDriver(capabilities);
	    driver.get("http://www.google.com");
	    String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
	    String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();

	}
	
	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
