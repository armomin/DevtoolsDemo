
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v111.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(Listener.class)
public class devtools

{
	ChromeDriver driver = new ChromeDriver();

	DevTools devTool = driver.getDevTools();
	SoftAssert s = new SoftAssert();

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\12343\\eclipse-workspace\\DevtoolsDemo\\resources\\chromedriver.exe");

	}
	

	@Test
	public void captureRequestSelenium() {

		devTool.createSession();

		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		devTool.addListener(Network.requestWillBeSent(), requestSent -> {

			System.out.println("Request URL => " + requestSent.getRequest().getUrl());

			System.out.println("Request Method => " + requestSent.getRequest().getMethod());

			System.out.println("Request Headers => " + requestSent.getRequest().getHeaders().toString());

			System.out.println("Request URL => " + requestSent.getRequest().getUrl());

			System.out.println("------------------------------------------------------");

		});

		driver.get(
				"https://www.flipkart.com/camera-clp-store?otracker=nmenu_sub_Electronics_0_Camera&fm=neo%2Fmerchandising&iid=M_1344ea03-8bbb-443b-b0f9-9ca0b12d9a0e_1_372UD5BXDFYS_MC.JR4C1KAN1IAQ&otracker=hp_rich_navigation_3_1.navigationCard.RICH_NAVIGATION_Electronics~Cameras%2B%2526%2BAccessories_JR4C1KAN1IAQ&otracker1=hp_rich_navigation_PINNED_neo%2Fmerchandising_NA_NAV_EXPANDABLE_navigationCard_cc_3_L1_view-all&cid=JR4C1KAN1IAQ");

	}

	@Test

	public void captureResponseSelenium() {

		devTool.createSession();

		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		devTool.addListener(Network.responseReceived(), responseReceieved -> {
			if (responseReceieved.getResponse().getUrl().toString()
					.contains("https://b.px-cdn.net/api/v1/PXgNtTli3A/d/p")) {

				System.out.println("Response Url => " + responseReceieved.getResponse().getUrl());

				System.out.println("Response Status => " + responseReceieved.getResponse().getStatus());

				System.out.println("Response Headers => " + responseReceieved.getResponse().getHeaders().toString());

				System.out.println("Response Headers => " + responseReceieved.getResponse().toString());

				System.out.println("Response MIME Type => " + responseReceieved.getResponse().getMimeType().toString());

				System.out.println("------------------------------------------------------");

				s.assertAll();
			}
		});

		driver.get(
				"https://www.flipkart.com/camera-clp-store?otracker=nmenu_sub_Electronics_0_Camera&fm=neo%2Fmerchandising&iid=M_1344ea03-8bbb-443b-b0f9-9ca0b12d9a0e_1_372UD5BXDFYS_MC.JR4C1KAN1IAQ&otracker=hp_rich_navigation_3_1.navigationCard.RICH_NAVIGATION_Electronics~Cameras%2B%2526%2BAccessories_JR4C1KAN1IAQ&otracker1=hp_rich_navigation_PINNED_neo%2Fmerchandising_NA_NAV_EXPANDABLE_navigationCard_cc_3_L1_view-all&cid=JR4C1KAN1IAQ");

	}

	@Test

	public void captureResponse2() {

		devTool.createSession();

		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		devTool.addListener(Network.responseReceived(), responseReceieved -> {
			// if(responseReceieved.getResponse().getUrl().toString().contains("https://hrmax.myadrenalin.com/AdrenalinMAX/Landing/GetCelebrations"))
			// {

			System.out.println("Response Url => " + responseReceieved.getResponse().getUrl());

			System.out.println("Response Status => " + responseReceieved.getResponse().getStatus());

			System.out.println("Response Headers => " + responseReceieved.getResponse().getHeaders().toString());

			System.out.println("Response MIME Type => " + responseReceieved.getResponse().getMimeType().toString());

			System.out.println("------------------------------------------------------");

			s.assertAll();
			// }
		});

		driver.get("https://www.twitch.tv/thebausffs");

	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

}
