package commonLibs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavascriptControl {

	private JavascriptExecutor jsExecutor;

	public JavascriptControl(WebDriver driver) {

		jsExecutor = (JavascriptExecutor) driver;

	}

	public void executeJsMethod(String command) throws Exception {

		jsExecutor.executeScript(command);

	}

	public void scrollOperation(int xLocation, int yLocation) throws Exception {
		String command = String.format("window.scrollBy(%d,%d)", xLocation, yLocation);

		executeJsMethod(command);
	}

}
