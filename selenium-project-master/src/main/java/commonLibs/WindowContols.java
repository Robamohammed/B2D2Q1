package commonLibs;

import org.openqa.selenium.WebDriver;

public class WindowContols {

	WebDriver driver;

	public WindowContols(WebDriver driver) {

		this.driver = driver;

	}

	public void switchToChildWindow(String WindowHandle) {

		driver.switchTo().window(WindowHandle);

	}

	public void switchToNthWindow(int nthWindow) {

		String windowHandle = driver.getWindowHandles().toArray()[nthWindow - 1].toString();

		switchToChildWindow(windowHandle);

	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

}
