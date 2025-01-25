package Selenium.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.Components.Components;

public class checkOutPage extends Components{
	
	WebDriver driver;
	
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".ta-results button")
	List<WebElement> selectCountry; 
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	By countries = By.cssSelector(".ta-results");

	public void selectCountry(String country2) {
		// TODO Auto-generated method stub
		Actions a = new Actions(driver);
		a.sendKeys(country, "india").build().perform();
		waitForElementToAppear(countries);
		WebElement drop = selectCountry.stream().filter(dpv->dpv.getText().equals(country2)).findFirst().orElse(null);
		drop.click();
	}
	

	public confirmationPage clickCheckout() {
		// TODO Auto-generated method stub
		submit.click();
		return new confirmationPage(driver);
	}
	
}
