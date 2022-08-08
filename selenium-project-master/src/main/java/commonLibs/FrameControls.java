package commonLibs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameControls {

	private WebDriver driver;

	public FrameControls(WebDriver driver) {

		this.driver = driver;
	}

	public void switchToFrame(String nameOrId) {

		driver.switchTo().frame(nameOrId);

	}

	public void switchToFrame(WebElement element) {

		driver.switchTo().frame(element);

	}

	public void returnFromFrame() {

		driver.switchTo().defaultContent();

	}
}
