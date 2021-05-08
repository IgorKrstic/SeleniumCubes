package com.mycompany.cubestest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    
    public static void main (String[] args){
        // Initiate Webdriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
        
        
        
        
        // Find elements
        WebElement bv_username = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[1]/div/input"));
        WebElement bv_password = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[2]/div/input"));
        WebElement bv_login_button = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div/div/div/div/div[2]/form/div[4]/div/button"));
 
        bv_username.sendKeys("qa@cubes.rs");
        bv_password.sendKeys("cubesqa");
        bv_login_button.click();
    }
}
