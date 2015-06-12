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

public class LoginTest extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testUntitled() throws Exception {    
	driver.get(baseUrl + "/php4dvd/");
    driver.manage().window().maximize();
    WebElement usernameField = driver.findElement(By.id("username"));
	usernameField.clear();
    usernameField.sendKeys("admin");
    WebElement passwordField = driver.findElement(By.name("password"));
	passwordField.clear();
    passwordField.sendKeys("admin");
    driver.findElement(By.name("submit")).click();
    //add new user
    driver.findElement(By.xpath("//a[contains(text(),'User management')]")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("dartland");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("dartland@rambler.ru");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("12345");
    driver.findElement(By.id("password2")).clear();
    driver.findElement(By.id("password2")).sendKeys("12345");
    new Select(driver.findElement(By.name("permission"))).selectByVisibleText("Admin");
    driver.findElement(By.cssSelector("option[value=\"2\"]")).click();
    driver.findElement(By.name("submit")).click();
    //remove new user
    driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
    Thread.sleep(3000);
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