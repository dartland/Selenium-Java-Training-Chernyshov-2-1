package ru.st.selenium;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RemoveFilmTest extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void RemoveFilmTest() throws Exception {    
	
	//������������ �� ������ �������� �� ������� �������  
	driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
	WebElement FilmContainer = driver.findElement(By.id("results"));
	
	List<WebElement> Films = FilmContainer.findElements(By.tagName("a"));
    int FilmsSize = Films.size();
	if(FilmsSize>0) {
		Films.get(0).click();
    	driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
    	Thread.sleep(1000);
        assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
	}
	
	WebElement NewFilmContainer = driver.findElement(By.id("results"));
	List<WebElement> NewFilms = NewFilmContainer.findElements(By.tagName("a"));
	Assert.assertEquals((FilmsSize-NewFilms.size()),1);
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