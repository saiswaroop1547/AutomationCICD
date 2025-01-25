package Selenuim.SeleniumFrameworkDesign.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.SeleniumFrameworkDesign.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

public class BaseTest {
	
	
	public WebDriver driver;
	public LoginPage lp;
	
	public WebDriver driverInitialization() throws IOException{
		
	Properties prop = new Properties();
	
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Selenium\\resources\\GloblData.properties");
	
	prop.load(fis);
	
	String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
	
	if(browserName.contains("chrome"))
	{
	ChromeOptions co = new ChromeOptions();
	WebDriverManager.chromedriver().setup();
	if(browserName.contains("headless"))
	{
	co.addArguments("headless");
	}
	driver = new ChromeDriver(co);
	//driver.manage().window().setSize(new Dimension(1400,900));
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		System.setProperty("webdriver.edge.driver","C:\\Users\\b85349\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		//System.setProperty("webdriver.gecko.driver","C:\\Users\\b85349\\Downloads\\geckodriver-v0.35.0-win-aarch64\\geckodriver.exe");
		//WebDriverManager.firefoxdriver().setup();
		FirefoxDriverManager.getInstance().setup();
		driver = new FirefoxDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	return driver;
}
	
public List<HashMap<String, String>> getDataToMap(String filePath) throws IOException{
		
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
	    ObjectMapper mapper = new ObjectMapper();
	    
	    List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
	    return data;
	}

public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	File dest = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
	FileUtils.copyFile(src, dest);
	return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchAppliction() throws IOException {
		
		driver = driverInitialization();
		lp = new LoginPage(driver);
		lp.goTo();
		return lp;
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void closeMeth() {
		driver.quit();
	}	
}