package com.mycompany.testseleniumjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    
    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
        
        WebElement bv_username = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/input"));
        WebElement bv_password = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/input"));
        WebElement bv_login_button = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[4]/div/button"));
        
        bv_username.sendKeys("qa@cubes.rs");
        bv_password.sendKeys("cubesqa");
        bv_login_button.click();
        
        WebElement cat_nav = driver.findElement(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[1]/li[3]/a"));
        cat_nav.click();
        
        String target_row = driver.findElement(By.xpath("//*[@id=\"categoriesTable\"]/tbody/tr[td[contains(text(),'QACatTest')]]")).getAttribute("rowIndex");
        
        int target = Integer.parseInt(target_row);
        
        WebElement targetDisable = driver.findElement(By.xpath("//*[@id=\"categoriesTable\"]/tbody/tr[" + target + "]/td[5]/div/button[1]"));
        targetDisable.click();
        
        
    }
}
