package com.atmecs.demo.testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.atmecs.demo.constants.ConstantPaths;
import com.atmecs.demo.helpers.PageActions;
import com.atmecs.demo.helpers.ReadExcelData;
import com.atmecs.demo.helpers.ValidateTestResult;
import com.atmecs.demo.testbase.InvokeBrowser;

public class AddCart extends Homepage {
	String Xpath, exptd;
	String Css;
	WebElement inputElement;

	@Test(priority = 3, dataProvider = "data", dataProviderClass = ReadExcelData.class)
	public void enterData(String[] in) {

		// clear the search field
		Css = read.readPropertiesFile("loc.clearsearch.css", ConstantPaths.LOCATORS_FILE);
		inputElement = driver.findElement(By.cssSelector(Css));
		inputElement.clear();

		// pass value through data provider
		Xpath = read.readPropertiesFile("loc.search.xpath", ConstantPaths.DATA_FILE);
		driver.findElement(By.xpath(Xpath)).sendKeys(in[0]);

		// hit search
		Xpath = read.readPropertiesFile("loc.button.search.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);

		// click on iphone link

		Css = read.readPropertiesFile("loc.clickimg.css", ConstantPaths.LOCATORS_FILE);
		PageActions.clickElement(driver, Css);

		// validate availability

		Css = read.readPropertiesFile("loc.iphone.availability.css", ConstantPaths.LOCATORS_FILE);
		String actavailabilty = driver.findElement(By.cssSelector(Css)).getText();
		validate.validateData(actavailabilty, in[1], "No match");
		log.info("Availability:In stock");

		// validate price
		Css = read.readPropertiesFile("loc.iphone.price.css", ConstantPaths.LOCATORS_FILE);
		String actprice = driver.findElement(By.cssSelector(Css)).getText();
		validate.validateData(actprice, in[2], "No match");
		log.info("Product price");

		// validate Ex.Tax
		Css = read.readPropertiesFile("loc.iphone.extax.css", ConstantPaths.LOCATORS_FILE);
		String acttax = driver.findElement(By.cssSelector(Css)).getText();
		validate.validateData(acttax, in[3], "No match");
		log.info("Ex Tax");

//		// validate description
		// Css = read.readPropertiesFile("loc.description.cs",
		// ConstantPaths.LOCATORS_FILE);
		String actintro = driver.findElement(By.cssSelector("div.tab-content div.tab-pane.active")).getText();
		System.out.println(actintro);
		validate.validateData(actintro, in[4], "No match");
		log.info("Description");

		// increase QTY
		Xpath = read.readPropertiesFile("loc.click.qty.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.ClickElement(driver, Xpath);
		driver.findElement(By.xpath(Xpath)).clear();
		driver.findElement(By.xpath(Xpath)).sendKeys(in[5]);
		Xpath = read.readPropertiesFile("loc.button.click.addtocart.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickOnElement(driver, Xpath);

	}

	@Test(priority = 4)
	public void validateCartAfterAdd() {
		// click on cart symbol
		Css = read.readPropertiesFile("loc.validate.clickoncart.afteradd.css", ConstantPaths.LOCATORS_FILE);
		PageActions.clickElement(driver, Css);
		// click view cart
		Css = read.readPropertiesFile("loc.viewcartbtn.click.css", ConstantPaths.LOCATORS_FILE);
		PageActions.clickElement(driver, Css);
		// validate each cart items-product 1
		Xpath = read.readPropertiesFile("loc.verify.nameaftradd.iphone.xpath", ConstantPaths.LOCATORS_FILE);
		String actproduct1 = driver.findElement(By.xpath(Xpath)).getText();
		String exptproduct1 = read.readPropertiesFile("exppro1", ConstantPaths.LOCATORS_FILE);
		validate.validateData(actproduct1, exptproduct1, "No match");
		log.info("Product1");
		// product 2
		Xpath = read.readPropertiesFile("loc.verify.nameaftradd.mac.xpath", ConstantPaths.LOCATORS_FILE);
		String actproduct2 = driver.findElement(By.xpath(Xpath)).getText();
		String exptproduct2 = read.readPropertiesFile("exppro2", ConstantPaths.LOCATORS_FILE);
		validate.validateData(actproduct2, exptproduct2, "No match");
		log.info("Product2");
		// grand total after adding products
		Xpath = read.readPropertiesFile("loc.verify.grandtotal.aftradd.xpath", ConstantPaths.LOCATORS_FILE);
		String actgrandadd = driver.findElement(By.xpath(Xpath)).getText();
		String exptgrandadd = read.readPropertiesFile("expgrandadd", ConstantPaths.LOCATORS_FILE);
		validate.validateData(actgrandadd, exptgrandadd, "No match");
		log.info("Grand total after adding products");

	}

	@Test(priority = 5)
	public void validateAfterRemove() {
		Css = read.readPropertiesFile("removebutton", ConstantPaths.LOCATORS_FILE);
		PageActions.clickElement(driver, Css);

		// validate grand after removal of one product
		Xpath = read.readPropertiesFile("loc.verify.grandtotal.aftrremove.xpath", ConstantPaths.LOCATORS_FILE);
		String actgrandremove = driver.findElement(By.xpath(Xpath)).getText();
		String exptgrandremove = read.readPropertiesFile("expgrandremove", ConstantPaths.LOCATORS_FILE);
		validate.validateData(actgrandremove, exptgrandremove, "No match");
		log.info("Grand total after removal of one product");
	}

	@Test(priority = 6)
	public void negativeTestCase() {
		Xpath = read.readPropertiesFile("loc.search.xpath", ConstantPaths.LOCATORS_FILE);
		PageActions.clickElement(driver, Css);
		String invalidinput = read.readPropertiesFile("invalidname", ConstantPaths.LOCATORS_FILE);
		driver.findElement(By.xpath(Xpath)).sendKeys(invalidinput);
		System.out.println(invalidinput);
		// validate negative test case output

		Xpath = read.readPropertiesFile("loc.negoutput.xpath", ConstantPaths.LOCATORS_FILE);
		String actoutput = driver.findElement(By.xpath(Xpath)).getText();
		String expoutput = read.readPropertiesFile("invalidout", ConstantPaths.LOCATORS_FILE);
		validate.validateData(actoutput, expoutput, "No match");
		System.out.println(actoutput);

	}

}
