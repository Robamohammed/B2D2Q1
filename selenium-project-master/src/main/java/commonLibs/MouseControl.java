package commonLibs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseControl {

	Actions action;

	public MouseControl(WebDriver driver) {

		action = new Actions(driver);

	}

	public void mouseHover(WebElement element) {

		action.moveToElement(element).build().perform();

	}

	public void mouseHoverAndClick(WebElement element) {

		action.moveToElement(element).click(element).build().perform();

	}

	public void dragAndDrop(WebElement source, WebElement target) {

		action.dragAndDrop(source, target).build().perform();

	}

	public void doubleClick(WebElement element) {

		action.doubleClick(element).build().perform();

	}

	public void rightClick(WebElement element) {

		action.contextClick(element).build().perform();

	}

}
