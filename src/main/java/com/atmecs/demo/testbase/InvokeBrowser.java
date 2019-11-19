package com.atmecs.demo.testbase;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atmecs.demo.constants.ConstantPaths;
import com.atmecs.demo.helpers.ReadPropertiesFile;
import com.atmecs.demo.helpers.ValidateTestResult;
import com.atmecs.demo.reports.LogReports;

public class InvokeBrowser {

	public WebDriver driver;
	public LogReports log = new LogReports();
	public ReadPropertiesFile read = new ReadPropertiesFile();
	public ValidateTestResult validate = new ValidateTestResult();
	String Url;
	String base;
	public String Xpath;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void invokeBrowser() throws IOException {

		Url = read.readPropertiesFile("url", ConstantPaths.CONFIG_FILE);
// String base = read.readPropertiesFile("browser", ConstantPaths.CONFIG_FILE);
		//base=System.getenv(browsername);

		if (base.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ConstantPaths.CHROME_FILE);
			driver = new ChromeDriver();
			log.info("Chrome browser launched...");

		} else if (base.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ConstantPaths.FIREFOX_FILE);
			driver = new FirefoxDriver();
			log.info("Firefox browser launched...");

		} else if (base.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", ConstantPaths.IE_FILE);
			DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
			ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
			driver = new InternetExplorerDriver(ieCaps);
			log.info("IE browser started...");

		}
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		log.info("Application is open...");

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}