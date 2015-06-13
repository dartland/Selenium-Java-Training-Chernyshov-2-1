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

public class AddNewFilmTestPositive extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void AddNewFilmTestPositive() throws Exception {    
	    // positive test
	    //driver.get(baseUrl + "/php4dvd/");
	    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
	    //обязательные поля
	    WebElement nameField = driver.findElement(By.name("name"));
		nameField.clear(); nameField.sendKeys("Selenium 2.0 + Java");
	    WebElement yearField = driver.findElement(By.name("year"));
		yearField.clear(); yearField.sendKeys("2013");
		//необязательные поля
	    addNotRequiredFields();
	    driver.findElement(By.id("submit")).click();
	    // на странице после успешного добавления фильма появляется единственный тэг h2, с названием фильма
	    assertEquals("Selenium 2.0 + Java (2013)", driver.findElement(By.xpath("//h2")).getText());  
	    driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();

    
	    Thread.sleep(1000);
  }


  public void addNotRequiredFields() {
		WebElement imdbField = driver.findElement(By.name("imdbid")); 
		imdbField.clear(); imdbField.sendKeys("12345");
		WebElement commentField = driver.findElement(By.name("aka"));
		commentField.clear(); commentField.sendKeys("занятия Selenium 2.0");
		WebElement durationField = driver.findElement(By.name("duration"));
		durationField.clear(); durationField.sendKeys("81");
		WebElement trailerField = driver.findElement(By.name("trailer"));
		trailerField.clear(); trailerField.sendKeys("http://software-testing.ru/lms/mod/url/view.php?id=16456");
		
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