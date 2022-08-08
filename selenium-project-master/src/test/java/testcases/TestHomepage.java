package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHomepage extends BaseTest {
	
	
	@Test
	public void verifyHomepageText() {
		
		
		String message = homepage.getMessageFromThePage();
		
		Assert.assertEquals(message, "Hello World!");
	}

}
