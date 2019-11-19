package com.atmecs.demo.testbase;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.atmecs.demo.constants.ConstantPaths;
import com.atmecs.demo.helpers.ReadPropertiesFile;
import com.atmecs.demo.helpers.ValidateTestResult;
import com.atmecs.demo.reports.LogReports;

public class BaseClass {

	protected static ReadPropertiesFile read = new ReadPropertiesFile();
	public WebDriver driver;
	public Properties property;
	public static LogReports log = new LogReports();
	public ValidateTestResult validate = new ValidateTestResult();

	public static String Xpath;

	/**
	 * select driver class type
	 * 
	 * @param browserType
	 * @throws IOException
	 */
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void openBrowser(String browserType, String url) throws IOException {

		switch (browserType) {

		case "chrome":
			ChromeOptions optionschrome = new ChromeOptions();
			optionschrome.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", ConstantPaths.CHROME_FILE);
			driver = new ChromeDriver(optionschrome);
			log.info("Chrome browser invoked");
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", ConstantPaths.FIREFOX_FILE);
			driver = new FirefoxDriver();
			log.info("Firefox browser invoked");
			break;

		case "ie":
			System.setProperty("webdriver.ie.driver", ConstantPaths.IE_FILE);
			driver = new InternetExplorerDriver();
			log.info("IE browser invoked");
			break;

		}

		driver.manage().window().maximize();
		driver.get(url);

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
