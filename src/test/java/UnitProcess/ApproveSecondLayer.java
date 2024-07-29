package UnitProcess;

import java.io.*;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ApproveSecondLayer {

    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
       
        try {
            driver.get("https://dev.suite.psk.gov.my");
        
            // login
            driver.findElement(By.id("email")).sendKeys("officer.unitprocess@example.com");
            driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("M!rf@lah123");
            driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();
    
            // reveal sidebar (on hover etc)
            WebElement displaySidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button"));
            actions.moveToElement(displaySidebar).perform();
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();
    
            // view application submenu
            WebElement investmentMenu = driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[2]/a"));
            actions.moveToElement(investmentMenu).perform();
            investmentMenu.click();
    
            driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[1]/a")).click();
    
            // view new application
            try {
                // Wait for the element to be visible
                WebElement viewApplication = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"298\"]/td[7]/div/a[1]")));
    
                // Move to the element and click it
                actions.moveToElement(viewApplication).perform();
                viewApplication.click();
    
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"approve-button\"]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click(); // submit
            // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[3]"))).click(); // cancel
    
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            driver.navigate().back();
    
        } catch (Exception e) {
            takeErrorScreenshot(driver, "unitprocess_secondlayer");
            System.out.println("Something went wrong: "+e.getMessage());
            
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
            System.out.println("Failed to save screenshot: "+e);
        }
    }

}
