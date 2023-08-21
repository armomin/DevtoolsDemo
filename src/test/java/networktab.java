import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.v111.network.Network;
import org.openqa.selenium.devtools.v111.network.model.RequestId;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class networktab {
	ChromeDriver driver = new ChromeDriver();
	DevTools devTools = ((ChromeDriver) driver).getDevTools();

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\12343\\eclipse-workspace\\DevtoolsDemo\\resources\\chromedriver.exe");

	}

	@Test
	public void mainTest() {

		

		devTools.createSession();
		devTools.send(Network.clearBrowserCache());
		devTools.send(Network.setCacheDisabled(true));
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		devTools.addListener(Network.responseReceived(), responseReceived -> {

			RequestId requestId = responseReceived.getRequestId();

			try {
				Command<Network.GetResponseBodyResponse> getBody = Network.getResponseBody(requestId);
				Network.GetResponseBodyResponse response = devTools.send(getBody);
				
				System.out.println("Get Body : " + getBody);
				System.out.println("response : " + response);

				
			} catch (DevToolsException e) {
				e.printStackTrace();
			}
			

		});
		driver.get("https://www.twitch.tv/thebausffs");
		
		
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
