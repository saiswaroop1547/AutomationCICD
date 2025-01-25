package Selenium.Components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.SeleniumFrameworkDesign.CartPage;

public class Components {
	
	WebDriver driver;
	
	public Components(WebDriver driver) {
		this.driver=driver;
	}

	@FindBy(css ="[routerlink*='cart']")
	WebElement cartclick;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderBtn;
	
	public void waitForElementToAppear(By waitfor) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(waitfor));
	}
	
	public CartPage goToCart()
	{
		cartclick.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPge goToOrderPage()
	{
		orderBtn.click();
		OrderPge op = new OrderPge(driver);
		return op;
	}
	
	public void waitForElementToDisappear(WebElement ele ) throws InterruptedException {
		Thread.sleep(4000);
	}
	
	public void waitForWebElementToAppear(WebElement errormsg2) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(errormsg2));
	}
}
