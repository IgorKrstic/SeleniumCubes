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


public class B_LoginTest { // Ovo su variable za celu klasu
    private static WebDriver driver;
    private static WebElement bv_logo;
    private static WebElement bv_username;
    private static WebElement bv_password;
    private static WebElement bv_login_button;
    
    public B_LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() { // Ovo se izvrsava samo na pocetku skripte i samo jednom za celu skriptu
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
    }
    
    @AfterClass
    public static void tearDownClass() { // Ovo se izvrsava samo na kraju skripte i samo jednom
        driver.quit();
    }
    
    @Before
    public void setUp() { // Ovo se izvrsava pre svakog testa, za svaki test
        bv_logo = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/nav/div/div[1]/a"));
        bv_username = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/input"));
        bv_password = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/input"));
        bv_login_button = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[4]/div/button"));
        
        assertTrue("BV Logo not present!", bv_logo.isDisplayed());
        assertTrue("BV Username not present!", bv_username.isDisplayed());
        assertTrue("BV Password not present!", bv_password.isDisplayed());
        assertTrue("BV Login Button not present!", bv_login_button.isDisplayed());
    }
    
    @After
    public void tearDown() { // Ovo se izvrsava posle svakog testa
        driver.navigate().refresh();
    }

    @Test
    public void tc02() {     
        bv_login_button.click();
        
        assertTrue("Username error message is not present!", driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/span/strong")).isDisplayed());
        assertTrue("Password error message is not present!", driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/span/strong")).isDisplayed());
        
        String un_error = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/span/strong")).getText();
        String pw_error = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/span/strong")).getText();
               
        assertEquals("Username error message is incorrect!", un_error, "The email field is required.");
        assertEquals("Password error message is incorrect!", pw_error, "The password field is required.");    
    }
    
    @Test
    public void tc03() {        
        bv_username.sendKeys("qatester@gmail.com");
        bv_password.sendKeys("testpass123");
        bv_login_button.click();
        
        assertTrue("Username error message is not present!", driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/span/strong")).isDisplayed());
        
        String un_invalid = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/span/strong")).getText();
        
        assertEquals("Username error message is incorrect!", un_invalid, "These credentials do not match our records.");
    }
    
    @Test
    public void tc04() {
        bv_username.sendKeys("qa@cubes.rs");
        bv_password.sendKeys("cubesqa");
        bv_login_button.click();
        
        String admin_url = driver.getCurrentUrl();
        
        assertEquals("URL is incorrect!", admin_url, "http://bvtest.school.cubes.rs/admin");
        
        String logged_user = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[2]/li/a")).getText();
        
        assertEquals("User account is incorrect!", logged_user, "Cubes QA");
        
        WebElement account_dropdown = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[2]/li/a"));
        account_dropdown.click();
        
        WebElement logout_link = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[2]/li/ul/li/a"));
        
        assertTrue("Logout not present", logout_link.isDisplayed());
        logout_link.click();
        
        String logout_url = driver.getCurrentUrl();
        
        assertEquals("Logout URL not confirmed!", logout_url, "http://bvtest.school.cubes.rs/");
        
    }

}
