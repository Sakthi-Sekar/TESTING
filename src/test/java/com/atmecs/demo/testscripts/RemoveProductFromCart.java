package com.atmecs.demo.testscripts;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.atmecs.demo.constants.ConstantPaths;
import com.atmecs.demo.helpers.PageActions;
import com.atmecs.demo.helpers.ReadExcelData;
import com.atmecs.demo.helpers.ReadPropertiesFile;
import com.atmecs.demo.helpers.ValidateTestResult;
import com.atmecs.demo.reports.LogReports;

public class RemoveProductFromCart extends AddCart {
	public static WebDriver driver;
	Properties config = new Properties();
	protected ReadPropertiesFile read = new ReadPropertiesFile();
	public static LogReports log = new LogReports();
	public static ValidateTestResult validate = new ValidateTestResult();

	String Xpath, exptd;
	WebElement Css, inputElement;

	@Test(priority = 4)
	public void clcikCartAndRemoveProduct() {
		Xpath = read.readPropertiesFile("loc.button.cart.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);

		Xpath = read.readPropertiesFile("loc.remove.cart.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);
	}
}
