package org.example;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppTest
{
    @Test
    public void loginPageTest() throws InterruptedException {
        assertTrue( true );

        //Chrome browser.
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Ahmet\\Desktop\\Selenium_Project\\drivers\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();

        //Firefox browser.
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Ahmet\\Desktop\\Selenium_Project\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        //This is the base url that i worked on.
        driver.get("https://www.trendyol.com/");

        //The part where I check that the main page is opened.
        String expected_title = "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da";
        String actual_title = driver.getTitle();
        if (expected_title.equals(actual_title)){
            System.out.println("Test Succesfull!");
        }else {
            System.out.println("Test Failed!");
        }

        //Processing part continuous below.
        driver.findElement(By.cssSelector(".navigation-icon-user")).click();

        driver.findElement(By.id("email")).click();

        //USername
        driver.findElement(By.id("email")).sendKeys("Write a Username Here!");

        driver.findElement(By.id("password")).click();

        //Password
        driver.findElement(By.id("password")).sendKeys("Write a Password Here!");
        driver.findElement(By.id("loginSubmit")).click();


        //The part where I check whether login passed or failed.
        String actualUrl="https://www.trendyol.com/";
        String expectedUrl= driver.getCurrentUrl();

        if(actualUrl.equalsIgnoreCase(expectedUrl))
        {
            System.out.println("Login Successful!");
        }
        else
        {
            System.out.println("Login Failed!");
        }


        //The part that i searched a word in searching box.
        driver.get("https://www.trendyol.com/butik/liste/erkek");
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".search-box"))));
        search.click();
        WebElement pushitem = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".search-box"))));
        pushitem.sendKeys("bilgisayar");

        //I put the result of the search in a list.
        List<WebElement> list;
        list = Arrays.asList(driver.findElement(By.xpath("//*[@id=\"auto-complete-app\"]/div/div[2]/descendant::div[@class='suggestion-result']")));

        //Choosing a category from the options that contains "Bilgisayar".
        for (int i=0; i<list.size();i++){
            if (list.get(i).getText().contains("Bilgisayar")){
                list.get(i).click();
                break;
            }
        }

        //Choosed a product from the website.
        WebElement chosen_product = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//img[@src='https://cdn.dsmcdn.com//ty5/product/media/images/20200716/13/4607280/77062627/0/0_org.jpg']"))));
        chosen_product.click();

        //The part where the product is thrown into the basket
        WebElement addedproduct = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/button[1]/div[1]"))));
        addedproduct.click();

        //Going to the basket.
        driver.findElement(By.linkText("Sepete Git")).click();

    }
}