package Company;

import java.io.*;
import java.time.Duration;
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
import dev.failsafe.TimeoutExceededException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadInvoice {
    
    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        
        try {
            
            driver.get("https://dev.suite.psk.gov.my");

        // login
        driver.findElement(By.id("email")).sendKeys("company@example.com");
        driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();

        // reveal sidebar (on hover etc)
        WebElement displaySidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button"));
        actions.moveToElement(displaySidebar).perform();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();

        // view application submenu
        WebElement investmentMenu = driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[3]/a"));
        actions.moveToElement(investmentMenu).perform();
        investmentMenu.click();
        WebElement viewApplication = driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[2]/a"));
        js.executeScript("arguments[0].click()", viewApplication);

        boolean applicationFound = false;

        // to find the required applications
        while (!applicationFound) {
            try {
                WebElement applicationId = driver.findElement(By.xpath("//*[@id=\"298\"]/td[8]/div/a[3]"));
                applicationFound = true;
                applicationId.click();

            } catch (Exception e) {
                try {
                    WebElement nextPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dataTable_next\"]/a")));
                    nextPage.click();
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"298\"]/td[8]/div/a[3]"))); // wait for an element on the next page to ensure the page is loaded

                } catch (NoSuchElementException ex) {
                    System.out.println("Next button not found "+ex);
                    break;
                } catch (TimeoutExceededException te) {
                    System.out.println("timeout wait " +te);
                    break;
                }
            }
        }

        // upload invoice
        WebElement uploadProjectImplementationSchedule = driver.findElement(By.cssSelector("#proof_of_payment"));
        uploadProjectImplementationSchedule.sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\Implementation Schedule.pdf"); 
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[2]/div/div[3]/div[2]/div/div/div/div/div[2]/button")).click();

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
