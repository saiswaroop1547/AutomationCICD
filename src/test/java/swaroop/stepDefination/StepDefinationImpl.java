package swaroop.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Selenium.SeleniumFrameworkDesign.CartPage;
import Selenium.SeleniumFrameworkDesign.LoginPage;
import Selenium.SeleniumFrameworkDesign.ProductCatalogue;
import Selenium.SeleniumFrameworkDesign.checkOutPage;
import Selenium.SeleniumFrameworkDesign.confirmationPage;
import Selenuim.SeleniumFrameworkDesign.test.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest{
	
	public LoginPage lp;
	public ProductCatalogue productCtalogue;
	public confirmationPage cnf;
	
	
	@Given("Launch the Application")
	public void Launch_the_Application() throws IOException
	{
		lp = launchAppliction();
	}
	
	@Given("^Login with Username (.+) and Password (.+)$")
	public void Login_with_Username_and_Password(String username, String password) {
		  productCtalogue = lp.test(username,password);
	}

	@When("^Added the Product (.+) to Cart$")
	public void Added_the_Product_to_Cart(String ProductName) throws InterruptedException
	{
		List<WebElement> products = productCtalogue.getProductList();
		productCtalogue.addProductToCart(ProductName);
	}
	
	@And("^Submit the Order and Checkeout (.+) Country (.+)$")
	public void Submit_the_Order_and_Checkeout(String ProductName, String Country) {
		CartPage cp = productCtalogue.goToCart();
		Boolean match = cp.verifyTheProduct(ProductName);
		Assert.assertTrue(match);
		checkOutPage  chkout = cp.goToCheckout();
		chkout.selectCountry(Country);
		cnf = chkout.clickCheckout();
	}
	
	@Then("{string} message is Displayed in ConfirmationPage")
	public void message_is_Displayed_in_ConfirmationPage(String string) {
		String msg = cnf.confirmationmsg();
		Assert.assertTrue(msg.equalsIgnoreCase(string));	
		driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string)
	{
		Assert.assertEquals(string, lp.getErrMsg());
		driver.quit();
	}
}




