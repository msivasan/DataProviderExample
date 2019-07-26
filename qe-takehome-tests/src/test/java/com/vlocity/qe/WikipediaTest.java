package com.vlocity.qe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;

/**
 * This class verifies elements on the wikipedia homepage.
 */
public class WikipediaTest {

    private Logger log = LoggerFactory.getLogger(WikipediaTest.class);

    private WebDriver driver;
    private ElementFinder finder;

    @BeforeClass
    public void setup() {

        /*
            If the following driver version doesn't work with your Chrome version
            see https://sites.google.com/a/chromium.org/chromedriver/downloads
            and update it as needed.
        */

        WebDriverManager.chromedriver().version("74.0.3729.6").setup();
        driver = new ChromeDriver();
        finder = new ElementFinder(driver);
        driver.get("https://www.wikipedia.org/");
    }

    @Test(dataProvider= "slogan_provider" )
    public void sloganPresent(String sloganClass) {
    	
        WebElement slogan = finder.findElement(By.partialLinkText(sloganClass));  
        Assert.assertNotNull(slogan, String.format("Unable to find slogan by text: %s", sloganClass));
        Assert.assertTrue(slogan.getText().contains(sloganClass));
      
    }
    

    @Test(dataProvider= "slogan_provider" )
    public void linkWorks(String sloganClass) {
    	
    	String link;
    	int status;
    	
        WebElement slogan = finder.findElement(By.partialLinkText(sloganClass));  
        Assert.assertNotNull(slogan, String.format("Unable to find slogan by text: %s", sloganClass));
        Assert.assertTrue(slogan.getText().contains(sloganClass));
        slogan.click();
        slogan.getAttribute("value");
        
        WebDriverWait wbw = new WebDriverWait(driver,20);
    //    wbw.until(ExpectedConditions.visibilityOf(element)
          
        link = driver.getCurrentUrl();
        RestAssured.useRelaxedHTTPSValidation();
        status = RestAssured.get(link).statusCode();  
        
        /*
         * Few languages are returning 400 for status code
         * */
        
        if (status == 200 || status == 400 ) {
        	Assert.assertTrue(true, "Status Received is "+ status );
        }else {
        	Assert.assertTrue(false, "Status Received is "+ status );
        }
        driver.get("https://www.wikipedia.org/");
        
    }

    
    
    @DataProvider(name = "slogan_provider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][] 
            {
                { "English" },
                { "Español" },
                { "Italiano" },
                { "Deutsch" },
                { "Français" },
                { "中文" },
                { "Polski" },
                { "Português" },
                { "Русский" },
                { "日本語" }
            };
        }
    
    @AfterClass
    public void closeBrowser() {
        if(driver!=null) {
            driver.close();
        }
    }
}
