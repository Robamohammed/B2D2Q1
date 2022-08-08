package commonLibs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdownControl {

	private Select initSelect(WebElement element) {

		return new Select(element);

	}

	public void selectViaVisibleText(WebElement element, String text) {

		initSelect(element).selectByVisibleText(text);

	}

	public void selectViaValue(WebElement element, String value) {

		initSelect(element).selectByValue(value);

	}

	public void selectViaIndex(WebElement element, int index) {

		initSelect(element).selectByIndex(index);

	}
}
