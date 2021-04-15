package com.test;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class SeleniumCode {

    @Test(groups = {"sanity", "otherGroupNames"})
    public void testMethod() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.get("https://www.google.com/");

        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).sendKeys("lion");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        driver.switchTo().frame(0);
        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("")).sendKeys(Keys.ENTER);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.contextClick().build().perform();
    }

    @Parameters({"browser", "username", "password"})
    @Test(groups = {"smoke"})
    public void anotherTest(String browser, String username, String password) {
        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
             driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        System.out.println("this i s a test ");
    }




   /* @DataProvider(name = "apiData")
    public Object[][] apiData() {

        int totalRow = getTotalRowNum(CommonVariables.sourceFile, "ApiData");
        int totalClmn = getTotalClmnNum(CommonVariables.sourceFile, "ApiData");

        Object[][] data = new Object[totalRow][totalClmn];

        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalClmn; j++) {

                String firstName = excelGetCellData(CommonVariables.sourceFile, "ApiData", "firstName", i);
                String lastName = excelGetCellData(CommonVariables.sourceFile, "ApiData", "lastName", i);
                String roleId = excelGetCellData(CommonVariables.sourceFile, "ApiData", "roleId", i);

                if (j == 0) {
                    data[i][j] = firstName;
                } else if (j == 1) {
                    data[i][j] = lastName;
                } else {
                    data[i][j] = roleId;
                }

            }
        }

        return data;
    }
*/

    public int getTotalRowNum(String filePath, String sheetName) {

        int totalRowNum = 0;

        try {

            FileInputStream fls = new FileInputStream(new File(filePath));
            Workbook wb = new XSSFWorkbook(fls);
            Sheet sh = wb.getSheet(sheetName);
            //Row row = sh.getRow(sh.getFirstRowNum());

            totalRowNum = sh.getLastRowNum();

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return totalRowNum;
    }
}
