package Executive;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
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

public class MoveToJKT {

    public void testMoveToJKT (int fileNo) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
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
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/div/ul/li[3]/a"))).click();
            
            boolean applicationFound = false;

            // to find the required appications and click the invoice
            while (!applicationFound) {
                try {
                    // locate the application by its xpath
                    WebElement noFail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\""+fileNo+"\"]/td[7]/div/a[2]")));//*[@id="1171"]/td[7]/div/a[2]
                    applicationFound = true;
                    noFail.click();
                    System.out.println("Application found and moved.");
                } catch (NoSuchElementException e) {
                    try {
                        // click next
                        WebElement nextPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dataTable_next\"]/a")));
                        nextPage.click();

                        // wait new page to load
                        wait.until(ExpectedConditions.stalenessOf(nextPage));
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='dataTable']")));
                        
                    } catch (NoSuchElementException ex) {
                        // break loop if next button not found or timeout
                        System.out.println("Next button not found or timeout: "+ ex);
                    }
                }
            }

            // teruskan
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();
           
           actions.moveToElement(unitProcessMenu).perform();
            unitProcessMenu.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/div/ul/li[3]/a"))).click();
            

        } catch (Exception e) {
            takeErrorScreenshot(driver, "moveToJKT");
            System.out.println("Something went wrong: "+e.getMessage());
            Assertions.fail("Test failed due to an exception "+e.getMessage());

        } finally {
            driver.quit();
        }
    }

     // function to take ss
     private static void takeErrorScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Users\\hamza\\Downloads\\Error Screenshots\\"+fileName+".png"));
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: "+e);
        }
    }
}
