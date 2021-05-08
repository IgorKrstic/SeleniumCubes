package com.mycompany.cubestest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    
    public static void main (String[] args){
        WebDriver driver = new ChromeDriver();
        driver.get("http://bvtest.school.cubes.rs/login");
        
        WebElement bv_username = driver.findElement(By.xpath("//*[@id='app-layout']/div/div/div/div/div[2]/form/div[1]/div/input"));
        bv_username.sendKeys("Zdravo Jogi");
    }
}
