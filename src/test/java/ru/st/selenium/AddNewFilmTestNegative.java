package ru.st.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddNewFilmTestNegative extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();


  @Test
  public void AddNewFilmTestNegative() throws Exception {    
	    //возврат на нужную страницу (в случае падения предыдущего теста) 
	    driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
	    //не заполнено поле year
	    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
	    // negative test  поля By.name("name")  и By.name("year") обязательные
	    //обязательные поля
	    WebElement nameField = driver.findElement(By.name("name"));
		nameField.clear(); nameField.sendKeys("Selenium 2.0 + Java");
		WebElement yearField = driver.findElement(By.name("year"));
		yearField.clear(); //yearField.sendKeys("2013"); // забыли заполнить поле year 
		driver.findElement(By.id("submit")).click();
	    // на странице, в случае незаполненного поля year, должна выводиться метка и она должна быть isDisplayed() 
		WebElement labelErrorYear = driver.findElement(By.xpath(".//label[@for='year']"));
		Assert.assertTrue(labelErrorYear.isDisplayed(), "Bug, label yaer not found");
		assertEquals("This field is required", labelErrorYear.getText());
		
	    //не заполнено поле name
	    nameField.clear(); //nameField.sendKeys("Selenium 2.0 + Java");// забыли заполнить поле name 
	    yearField.clear(); yearField.sendKeys("2013");
		driver.findElement(By.id("submit")).click();
	    // на странице, в случае незаполненного поля name, должна выводиться метка и она должна быть isDisplayed() 
		WebElement labelErrorName = driver.findElement(By.xpath(".//label[@for='name']"));
		Assert.assertTrue(labelErrorName.isDisplayed(), "Bug, label name not found");
		assertEquals("This field is required", labelErrorName.getText());		


		
  }
  
  
  

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}