package Selenium.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.Components.Components;

public class confirmationPage extends Components {

	WebDriver driver ;

	public confirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".hero-primary")
	WebElement cnfMsg;

	public String confirmationmsg() {
		return cnfMsg.getText();
	}
	
}
