package UnitKewangan;

import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;

public class ApproveInvoiceFirstLevel {

    @Test
    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://dev.suite.psk.gov.my");
            driver.manage().window().maximize();
    
            // login
            driver.findElement(By.id("email")).sendKeys("finance01@example.com");
            driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("M!rf@lah123");
            driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();
    
            // view new application submenu
            WebElement unitProcessMenu = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/a"));
            actions.moveToElement(unitProcessMenu).perform();
            unitProcessMenu.click();
            driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/div/ul/li[1]/a")).click();
    
            boolean applicationFound = false;
    
            // to find the required applications and click the invoice
            while (!applicationFound) {
                try {
                    // Try to locate the application by its XPath
                    WebElement applicationId = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"339\"]/td[7]/div/a[2]")));
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
    
            // click view invoice 
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/div/div/a"))).click();
            
            // open invoice in new tab
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/div[2]/div/div/table[1]/tbody/tr[2]/td[5]/a"))).sendKeys(Keys.CONTROL,Keys.ENTER);

            // switch tabs
            ArrayList<String> windowTabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(windowTabs.get(1));
            System.out.println("Title of the new tab: " + driver.getTitle());

            Thread.sleep(5000);

            driver.switchTo().window(windowTabs.get(0));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form-transaction-update-136\"]/button"))).click();    // click approve
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();

            driver.navigate().back();
    
        } catch (Exception e) {
            takeErrorScreenshot(driver, "approveInvoiceFirstLayer");
            System.out.println("An error occurred: "+e.getMessage());

        } finally {
            driver.quit();
        }
    }

    // fnction to capture ss
    private static void takeErrorScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Users\\hamza\\Downloads\\Error Screenshots\\"+fileName+".png"));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot");
        }
    }
}
