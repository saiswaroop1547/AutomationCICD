package Selenium.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.Components.Components;

public class ProductCatalogue extends Components{

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement Loading;
	
	By productsBy = By.cssSelector(".mb-3");
	By addtoCart = By.cssSelector(".card-body button:last-of-type");
	By promessage = By.cssSelector(".toast-message");
		
	public List<WebElement> getProductList(){
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement pro = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return pro;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement pro = getProductByName(productName);
		pro.findElement(addtoCart).click();
		waitForElementToAppear(promessage); 
		waitForElementToDisappear(Loading);
	}

	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPwd;
	@FindBy(id="login")
	WebElement Login;
	
	public void test(String email, String pwd) {
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		Login.click();	
	}	
	}