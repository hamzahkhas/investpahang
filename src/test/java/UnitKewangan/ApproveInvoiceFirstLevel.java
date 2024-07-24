package UnitKewangan;

import org.apache.commons.io.FileUtils;
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
import dev.failsafe.TimeoutExceededException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;



public class ApproveInvoiceFirstLevel {

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
    
            // reveal sidebar
            WebElement displaySidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button"));
            actions.moveToElement(displaySidebar).perform();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();
    
            // view application submenu
            WebElement investmentMenu = driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[2]/a"));
            actions.moveToElement(investmentMenu).perform();
            investmentMenu.click();
    
            driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[1]/a")).click();
    
            boolean applicationFound = false;
    
            // to find the required applications and click the invoice
            while (!applicationFound) {
                try {
                    WebElement applicationId = driver.findElement(By.xpath("//*[@id=\"298\"]/td[7]/div/a[2]"));
                    applicationFound = true;
                    applicationId.click();
    
                } catch (Exception e) {
                    try {
                        WebElement nextPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dataTable_next\"]/a")));
                        nextPage.click();
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"298\"]/td[7]/div/a[2]"))); // wait for an element on the next page to ensure the page is loaded
    
                    } catch (NoSuchElementException ex) {
                        System.out.println("Next button not found "+ex);
                        break;
                    } catch (TimeoutExceededException te) {
                        System.out.println("timeout wait " +te);
                        break;
                    }
                }
            }
    
            // click view invoice 
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/div/div[3]/div/div/div/table/tbody/tr[2]/td[6]/div/div/a")).click();
    
    
        } catch (Exception e) {
            takeErrorScreenshot(driver, "error_screenshot");
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
