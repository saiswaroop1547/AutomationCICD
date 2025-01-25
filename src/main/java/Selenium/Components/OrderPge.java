package Selenium.Components;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.SeleniumFrameworkDesign.CartPage;

public class OrderPge extends CartPage {

	
	WebDriver driver;
	
	public OrderPge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
		
	public boolean verifyTheOrderedProduct(String proName) {
		// TODO Auto-generated method stub
		boolean match = productNames.stream().anyMatch(cpro -> cpro.getText().equalsIgnoreCase(proName));
		return match;
	}
	
}
