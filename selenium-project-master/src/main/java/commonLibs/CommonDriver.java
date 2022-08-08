package commonLibs;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonDriver {

	private WebDriver driver;

	private String projectDirectory;

	private int elementDetectionTimeout;

	private int pageloadTimeout;

	public WebDriver getDriver() {
		return driver;
	}

	public CommonDriver(String browserType) throws Exception {

		elementDetectionTimeout = 10;

		pageloadTimeout = 10;

		browserType = browserType.trim();

		projectDirectory = System.getProperty("user.dir");

		if (browserType.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", projectDirectory + "/driver/chromedriver");

			driver = new ChromeDriver();

		} else if (browserType.equalsIgnoreCase("chrome-headless")) {

			System.setProperty("webdriver.chrome.driver", projectDirectory + "/driver/chromedriver");
			
			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--headless"); 
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--disable-gpu");
			
			driver = new ChromeDriver(chromeOptions);

		} else if (browserType.equalsIgnoreCase("chrome-grid")) {

			System.setProperty("webdriver.chrome.driver", projectDirectory + "/driver/chromedriver");
			
			ChromeOptions chromeOptions = new ChromeOptions();
			
			chromeOptions.setCapability("browserVersion", "103");
			chromeOptions.setCapability("platformName", "Mac");
			
			// Showing a test name instead of the session id in the Grid UI
			chromeOptions.setCapability("se:name", "My simple test"); 
			
			
			driver = new RemoteWebDriver(new URL("http://10.10.50.40:4444"), chromeOptions);

		} else if (browserType.equalsIgnoreCase("firefox-grid")) {

			System.setProperty("webdriver.chrome.driver", projectDirectory + "/driver/chromedriver");
			
			FirefoxOptions ffOptions = new FirefoxOptions();
			
			ffOptions.setCapability("browserVersion", "103");
			ffOptions.setCapability("platformName", "Mac");
			
			// Showing a test name instead of the session id in the Grid UI
			ffOptions.setCapability("se:name", "My simple test"); 
			
			
			driver = new RemoteWebDriver(new URL("http://10.10.50.40:4444"), ffOptions);

		}
		
		
		else if (browserType.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", projectDirectory + "/driver/geckodriver");

			driver = new FirefoxDriver();

		} else if (browserType.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.gecko.driver", projectDirectory + "/driver/MicrosoftWebDriver");

			driver = new EdgeDriver();

		} else {
			throw new Exception("Invalid Broser type" + browserType);
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}

	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public void navigateToUrl(String url) {

		url = url.trim();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageloadTimeout));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(elementDetectionTimeout));

		driver.get(url);

	}

	public String getTitle() throws Exception {
		return driver.getTitle();
	}

	public String getCurrentUrl() throws Exception {
		return driver.getCurrentUrl();
	}

	public void closeBrowser() throws Exception {

		driver.close();

	}

	public void closeAllBrowser() throws Exception {

		driver.quit();

	}

}
