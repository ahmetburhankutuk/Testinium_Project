package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppTest
{
    @Test
    public void loginPageTest()
    {
        assertTrue( true );
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Ahmet\\Desktop\\Selenium_Project\\drivers\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver","C:\\Users\\Ahmet\\Desktop\\Selenium_Project\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        driver.get("https://www.trendyol.com/");

        String expected_title = "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da";
        String actual_title = driver.getTitle();
        if (expected_title.equals(actual_title)){
            System.out.println("Test Succesfull!");
        }else {
            System.out.println("Test Failed!");
        }

        driver.findElement(By.cssSelector(".navigation-icon-user")).click();

        driver.findElement(By.id("email")).click();

        driver.findElement(By.id("email")).sendKeys("ameliatan2355@gmail.com");

        driver.findElement(By.id("password")).click();

        driver.findElement(By.id("password")).sendKeys("test1234");
        driver.findElement(By.id("loginSubmit")).click();

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

        driver.get("https://www.trendyol.com/butik/liste/erkek");
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".search-box"))));
        search.click();
        WebElement pushitem = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".search-box"))));
        pushitem.sendKeys("bilgisayar");

        List<WebElement> list;
        list = Arrays.asList(driver.findElement(By.xpath("//*[@id=\"auto-complete-app\"]/div/div[2]/descendant::div[@class='suggestion-result']")));

        for (int i=0; i<list.size();i++){
            if (list.get(i).getText().contains("Bilgisayar")){
                list.get(i).click();
                break;
            }
        }

        WebElement chosen_product = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[2]/div[2]/div/div[5]/div[1]/a/div[1]/div/img"))));
        chosen_product.click();

        WebElement addedproduct = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[2]/div[1]/button[1]/div[1]"))));
        addedproduct.click();

        String basket_price = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/section/section[1]/div[2]/div/div/div[2]/div[2]/div[3]/div[2]")).getText();
        System.out.println(basket_price);

        // 7 | click | linkText=Sepete Git |
        driver.findElement(By.linkText("Sepete Git")).click();


    }
}