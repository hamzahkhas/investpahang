package Company;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadInvoice {

    @Test
    public static void main(String[] args) throws TimeoutException {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().window().maximize();
        
        try {
            
            driver.get("https://dev.suite.psk.gov.my");

        // login
        driver.findElement(By.id("email")).sendKeys("company@example.com");
        driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();

        // open project progress submenu
        WebElement investMenu = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[3]/a"));
        actions.moveToElement(investMenu).perform();
        investMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[3]/div/ul/li[6]/a"))).click();
       
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/thead/tr/th[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/thead/tr/th[2]")).click();

        boolean applicationFound = false;

        // to find the required applications
        while (!applicationFound) {
            try {
                // Try to locate the application by its XPath

                WebElement applicationId = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"339\"]/td[8]/div/a[3]")));
                applicationFound = true;
                js.executeScript("arguments[0].click();", applicationId); // use this when nak click button yang di hujung
                System.out.println("Application found and clicked.");

            } catch (org.openqa.selenium.TimeoutException e) {

                try {
                    // Look for the "Next" button and click it
                    WebElement nextPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dataTable_next\"]/a")));
                    nextPage.click();

                    // Wait for the new page to load
                    wait.until(ExpectedConditions.stalenessOf(nextPage)); // Wait for the page to refresh
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='dataTable']"))); // Wait for the table to be visible

                } catch (NoSuchElementException ex) {
                    // Break the loop if "Next" button is not found or a timeout occurs
                    System.out.println("Next button not found or timeout occurred: " + ex);
                    break;
                }
            }
        }

        // upload invoice
        Thread.sleep(5000);
        WebElement uploadInvoice = driver.findElement(By.xpath("//*[@id=\"proof_of_payment\"]"));   // not catered for different payment types yet due to some bullshit
        uploadInvoice.sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\Implementation Schedule.pdf"); 
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/div[2]/div/div/div/div/div[2]/button")).click();

        // switch to confirmation alert
        Alert confirmationAlert = driver.switchTo().alert();
        confirmationAlert.accept();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        } catch (Exception e) {
            takeErrorScreenshot(driver, "upload_invoice");
            System.out.println("Something went wrong: "+e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // fnction to capture ss
    private static void takeErrorScreenshot(WebDriver driver, String fileName) {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Users\\hamza\\Downloads\\Error Screenshots\\"+fileName+".png"));
        } catch (Exception e) {
            System.out.println("Failed to save screenshot: "+e);
        }
    }

}
