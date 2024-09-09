package Executive;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
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

public class GenerateLetter {

    @Test
    public void testGenerateLetter(int applicationNo, String fileNo) {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            driver.get("https://dev.suite.psk.gov.my");
            driver.manage().window().maximize();
    
            // login
            driver.findElement(By.id("email")).sendKeys("executive_officer@example.com");
            driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("M!rf@lah123");
            driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();
    
            // view new application submenu
            WebElement unitProcessMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/a")));
            actions.moveToElement(unitProcessMenu).perform();
            unitProcessMenu.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/div/ul/li[1]/a"))).click();

            driver.findElement(By.xpath("//*[@id=\"dataTable\"]/thead/tr/th[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"dataTable\"]/thead/tr/th[2]")).click();

            boolean applicationFound = false;
    
            // to find the required applications and click the invoice
            while (!applicationFound) {
                try {
                    // Try to locate the application by its XPath
                    WebElement applicationId = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\""+applicationNo+"\"]/td[7]/div/a[3]")));
                    applicationFound = true;
                    applicationId.click();
                    System.out.println("Application found and clicked.");
                } catch (NoSuchElementException e) {
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

            // input reference number
            driver.findElement(By.xpath("//*[@id=\"approve-proposal\"]/div/div/div/div[1]/div/input")).sendKeys(fileNo);
            driver.findElement(By.xpath("//*[@id=\"approve-proposal\"]/button")).click();
            
        } catch (Exception e) {
            takeErrorScreenshot(driver, "generateLetter");
            System.out.println("Something Wrong happened: "+e.getMessage());
        } finally {
            driver.quit();
        }
    }

    
    private static void takeErrorScreenshot(WebDriver driver, String fileName) {
        File errorScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(errorScreenshot, new File("C:\\Users\\hamza\\Downloads\\Error Screenshots\\"+fileName+".png"));

        } catch (IOException e) {
            System.out.println("Failed to save screenshot");

        }
    }
}
