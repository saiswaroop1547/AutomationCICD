package Selenium.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.Components.Components;

public class LoginPage extends Components{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPwd;
	
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errormsg;
	
	public ProductCatalogue test(String email, String pwd) {
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		Login.click();
		return  new ProductCatalogue(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrMsg() {
		waitForWebElementToAppear(errormsg); 
		String msg = errormsg.getText();
		return msg;
	}
	
	}