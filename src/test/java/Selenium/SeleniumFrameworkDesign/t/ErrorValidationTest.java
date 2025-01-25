package Selenium.SeleniumFrameworkDesign.t;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import Selenium.SeleniumFrameworkDesign.CartPage;
import Selenium.SeleniumFrameworkDesign.ProductCatalogue;
import Selenuim.SeleniumFrameworkDesign.test.BaseTest;
import Selenuim.SeleniumFrameworkDesign.test.RetryListner;

public class ErrorValidationTest extends  BaseTest{

		
		@Test(groups = {"ErrorHandling"}, retryAnalyzer = RetryListner.class)
		public void loginErrorValid() throws InterruptedException, IOException{
		
		
		ProductCatalogue productCtalogue = lp.test("swaroop@gmail.com","Test@1");
		Assert.assertEquals("Incorrect email password.", lp.getErrMsg());
		}
		
		@Test
		public void prodErrValid() throws InterruptedException, IOException{
		
		String ProName = "IPHONE 13 PRO";
		String Country = "India";
		ProductCatalogue productCtalogue = lp.test("swaroop@gmail.com","Test@123");
		List<WebElement> products = productCtalogue.getProductList();
		productCtalogue.addProductToCart(ProName);
		CartPage cp = productCtalogue.goToCart();
		Boolean match = cp.verifyTheProduct("IPHONE");
		Assert.assertFalse(match);
		
		}
}