package Admin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class A_SanityCheck {
    private static WebDriver driver;
    
    public A_SanityCheck() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
    }
    
    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tc01() {
        WebElement bv_logo = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/nav/div/div[1]/a"));
        WebElement bv_username = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/input"));
        WebElement bv_password = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/input"));
        WebElement bv_login_button = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[4]/div/button"));
        
        assertTrue("BV Logo not present!", bv_logo.isDisplayed());
        assertTrue("BV Username not present!", bv_username.isDisplayed());
        assertTrue("BV Password not present!", bv_password.isDisplayed());
        assertTrue("BV Login Button not present!", bv_login_button.isDisplayed());
    }
}
