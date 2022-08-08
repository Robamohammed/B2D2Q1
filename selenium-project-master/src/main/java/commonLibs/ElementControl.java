package commonLibs;

import org.openqa.selenium.WebElement;

public class ElementControl {

	public void clickElement(WebElement element) {

		element.click();
	}

	public void sendText(WebElement element, String text) {

		element.sendKeys(text);
	}

	public void clearText(WebElement element) {
		element.clear();
	}

	public String getText(WebElement element) {

		return element.getText();
	}

	public boolean isElementPresent(WebElement element) {
		return element.isDisplayed();
	}

	public boolean isElementEnable(WebElement element) {
		return element.isEnabled();
	}

}
