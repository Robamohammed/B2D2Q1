package samplePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.ElementControl;

public class HomePage {
	
	
	ElementControl elementControl;
	
	@FindBy(xpath = "//h2")
	public WebElement textMessage;

	
	public HomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
		elementControl = new ElementControl();
		
	
	}
	
	public String getMessageFromThePage() {
		return elementControl.getText(textMessage);
	}

}
