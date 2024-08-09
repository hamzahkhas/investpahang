package Executive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignReviewer {

    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            driver.get("https://dev.suite.psk.gov.my");
            driver.manage().window().maximize();
    
            // login
            driver.findElement(By.id("email")).sendKeys("executive_officer@example.com");
            driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("M!rf@lah123");
            driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();
    
            // view new application submenu
            WebElement unitProcessMenu = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/a"));
            actions.moveToElement(unitProcessMenu).perform();
            unitProcessMenu.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/div/ul/li[3]/a"))).click();

            boolean applicationFound = false;
    
            // to find the required applications and click the invoice
            while (!applicationFound) {
                try {
                    // Try to locate the application by its XPath   //*[@id="338"]/td[7]/div/a //*[@id="282"]/td[7]/div/a //*[@id="280"]/td[7]/div/a[3]
                    WebElement applicationId = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"280\"]/td[7]/div/a[3]")));
                    applicationFound = true;
                    js.executeScript("arguments[0].click();", applicationId); // use this when nak click button yang di hujung

                    // applicationId.click();
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

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
