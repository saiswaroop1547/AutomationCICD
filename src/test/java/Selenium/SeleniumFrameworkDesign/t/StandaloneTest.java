package Selenium.SeleniumFrameworkDesign.t;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.Components.OrderPge;
import Selenium.SeleniumFrameworkDesign.CartPage;
import Selenium.SeleniumFrameworkDesign.ProductCatalogue;
import Selenium.SeleniumFrameworkDesign.checkOutPage;
import Selenium.SeleniumFrameworkDesign.confirmationPage;
import Selenuim.SeleniumFrameworkDesign.test.BaseTest;

public class StandaloneTest extends  BaseTest{

	String ProName = "IPHONE 13 PRO";
	String Country = "India";
		
		@Test(dataProvider = "getData", groups = {"OrderAProduct"})
		public void Order(HashMap<String, String> Input) throws InterruptedException, IOException{
		
		ProductCatalogue productCtalogue = lp.test(Input.get("email"),Input.get("password"));
		List<WebElement> products = productCtalogue.getProductList();
		productCtalogue.addProductToCart(Input.get("ProName"));
		CartPage cp = productCtalogue.goToCart();
		Boolean match = cp.verifyTheProduct(Input.get("ProName"));
		Assert.assertTrue(match);
		checkOutPage  chkout = cp.goToCheckout();
		
		chkout.selectCountry(Country);
	
		confirmationPage cnf = chkout.clickCheckout();
		
		String msg = cnf.confirmationmsg();
		
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
		}
		
		@Test(dependsOnMethods = {"Order"})
		public void OrderHistoryCheck()
		{
			ProductCatalogue productCtalogue = lp.test("swaroop@gmail.com","Test@123");
			OrderPge op = productCtalogue.goToOrderPage();
			Assert.assertTrue(op.verifyTheOrderedProduct(ProName));
		}
		
//		@DataProvider
//		public Object[][] getData()
//		{
//			return new Object [][] {{"swaroop@gmail.com","Test@123","IPHONE 13 PRO"},{"saiswaroop@gmail.com","Test@123","QWERTY"}};
//		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("email", "swaroop@gmail.com");
//			map.put("password", "Test@123");
//			map.put("ProName", "IPHONE 13 PRO");
//			
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("email", "saiswaroop@gmail.com");
//			map1.put("password", "Test@123");
//			map1.put("ProName", "QWERTY")

			List<HashMap<String, String>> data = getDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\SeleniumFrameworkDesign\\Data\\PurchaseDetails.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
		}
		
}