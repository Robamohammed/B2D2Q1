package commonLibs;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertControl {

	private WebDriver driver;

	public AlertControl(WebDriver driver) {

		this.driver = driver;

	}

	private Alert getAlert() {

		return driver.switchTo().alert();

	}

	public void acceptAlert() {

		getAlert().accept();

	}

	public void rejectAlert() {

		getAlert().dismiss();

	}

	public String getTextFromAlert() {

		return getAlert().getText();

	}

}
