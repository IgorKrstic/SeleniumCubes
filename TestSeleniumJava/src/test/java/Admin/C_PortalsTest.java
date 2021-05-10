package Admin;

import java.util.concurrent.TimeUnit;
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
import org.openqa.selenium.support.ui.Select;


public class C_PortalsTest {
    private static WebDriver driver;
    
    public C_PortalsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
        
        WebElement bv_username = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/input"));
        WebElement bv_password = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/input"));
        WebElement bv_login_button = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[4]/div/button"));
        
        bv_username.sendKeys("qa@cubes.rs");
        bv_password.sendKeys("cubesqa");
        bv_login_button.click();
        
        String admin_url = driver.getCurrentUrl();
        assertEquals("User is logged in!", admin_url, "http://bvtest.school.cubes.rs/admin");
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        String logged_user = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[2]/li/a")).getText();
        assertEquals("User account is incorrect!", logged_user, "Cubes QA");
        WebElement account_dropdown = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[2]/li/a"));
        account_dropdown.click();
        WebElement logout_link = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[2]/li/ul/li/a"));
        assertTrue("Logout not present", logout_link.isDisplayed());
        logout_link.click();
        String logout_url = driver.getCurrentUrl();
        assertEquals("Logout URL not confirmed!", logout_url, "http://bvtest.school.cubes.rs/");
        
        driver.quit();
    }
    
    @Before
    public void setUp() {
        WebElement portals_tab = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[1]/li[5]/a"));
        portals_tab.click();
        String portals_url = driver.getCurrentUrl();
        assertEquals("Portals page not reached!", portals_url, "http://bvtest.school.cubes.rs/admin/portals");
        
        //ovo je TC-10
    }
    
    @After
    public void tearDown() {
        driver.navigate().refresh();
    }

    @Test
    public void tc11() {
        WebElement add_portal_button = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[1]/div/div/div/div[1]/a"));
        add_portal_button.click();
        
        String portals_insert_url = driver.getCurrentUrl();
        assertEquals("Insert portal page not reached!", portals_insert_url, "http://bvtest.school.cubes.rs/admin/portals/insert");
        
        WebElement portals_insert_title = driver.findElement(By.id("title"));
        WebElement portals_insert_link = driver.findElement(By.id("url"));
        Select portals_insert_region = new Select(driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/fieldset/div[3]/div/select")));
        WebElement portals_insert_save = driver.findElement(By.id("save-portal-button"));
        
        portals_insert_title.sendKeys("QATest00001");
        portals_insert_link.sendKeys("http://qatest.com");
        portals_insert_region.selectByIndex(1);
        portals_insert_save.click();
        
        WebElement message = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[1]/div/div/div/div[2]/div"));
        
        assertTrue(message.isDisplayed());
        assertEquals("Incorrect message", message.getText(), "Portal \"QATest00001\" has been successfully saved!");
        
        WebElement target_portal = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[td[contains(text(),'QATest00001')]]"));
        
        assertTrue(target_portal.isDisplayed());
    }
    
    @Test
    public void tc12() {
        WebElement target_portal = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[td[contains(text(),'QATest00001')]]"));
        assertTrue(target_portal.isDisplayed());
        
        String target_row = target_portal.getAttribute("rowIndex");
        int target_row_num = Integer.parseInt(target_row);
        
        WebElement portal_edit_button = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[" + target_row_num + "]/td[5]/div/a"));
        portal_edit_button.click();
        
        String portals_edit_page = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[1]")).getText();
        assertEquals(portals_edit_page, "Portals | Edit Portal");
        
        WebElement portals_edit_title = driver.findElement(By.id("title"));
        WebElement portals_edit_link = driver.findElement(By.id("url"));
        Select portals_edit_region = new Select(driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/fieldset/div[3]/div/select")));
        WebElement portals_edit_save = driver.findElement(By.id("save-portal-button"));
        
        portals_edit_title.clear();
        portals_edit_title.sendKeys("QATest00002");
        portals_edit_link.clear();
        portals_edit_link.sendKeys("http://qatest2.com");
        portals_edit_region.selectByIndex(0);
        portals_edit_save.click();
        
        WebElement message_edit = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[1]/div/div/div/div[2]/div"));
        
        assertTrue(message_edit.isDisplayed());
        assertEquals("Incorrect message", message_edit.getText(), "Portal \"QATest00002\" has been successfully saved!");
        
        WebElement target_edit = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[td[contains(text(),'QATest00002')]]"));
        
        assertTrue(target_edit.isDisplayed());
    }
    
    @Test
    public void tc13() {
        WebElement target_portal2 = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[td[contains(text(),'QATest00002')]]"));
        assertTrue(target_portal2.isDisplayed());
        
        String target_row2 = target_portal2.getAttribute("rowIndex");
        int target_row2_num = Integer.parseInt(target_row2);
        
        WebElement portal_dissable_button = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[" + target_row2_num + "]/td[5]/div/button[1]"));
        portal_dissable_button.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement disable_dialogue_button = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"));
        disable_dialogue_button.click();
        
        String disable_confirm = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[1]/div/div/div/div[2]/div")).getText();
        
        assertEquals(disable_confirm, "Portal \"QATest00002\" has been disabled");
        
        String disable_icon = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[" + target_row2_num + "]/td[4]/span")).getText();
        assertEquals(disable_icon, "D");
    }
    
    @Test
    public void tc14() {
        WebElement target_portal3 = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[td[contains(text(),'QATest00002')]]"));
        assertTrue(target_portal3.isDisplayed());
        
        String target_row3 = target_portal3.getAttribute("rowIndex");
        int target_row3_num = Integer.parseInt(target_row3);
        
        WebElement portal_delete_button = driver.findElement(By.xpath("//*[@id=\"portalsTable\"]/tbody/tr[" + target_row3_num + "]/td[5]/div/button[2]"));
        portal_delete_button.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Ovo staviti svuda gde se neki element prvo pronalazi pa onda interact sa njim
        
        WebElement delete_dialogue_button = driver.findElement(By.xpath("//*[@id=\"portalDeleteDialog\"]/div/div/div[3]/button[2]"));
        delete_dialogue_button.click();
        
        String delete_confirm = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[1]/div/div/div/div[2]/div")).getText();
        
        assertEquals(delete_confirm, "Portal \"QATest00002\" has been successfully deleted!");
    }
}
