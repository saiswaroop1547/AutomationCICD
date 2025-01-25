package Selenium.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.Components.Components;

public class CartPage extends Components {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement clickBtn;
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartHeader;

	public boolean verifyTheProduct(String proName) {
		// TODO Auto-generated method stub
		boolean match = cartHeader.stream().anyMatch(cpro -> cpro.getText().equalsIgnoreCase(proName));
		return match;
	}
	
	public checkOutPage goToCheckout() {
		clickBtn.click();
		return new checkOutPage(driver);
	}
	
}
