package UnitProcess;

import java.time.Duration;
import java.io.*;
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

public class ApproveFirstLayer {

    public static void main (String [] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        try {
            driver.get("https://dev.suite.psk.gov.my");
        
        // login
        driver.findElement(By.id("email")).sendKeys("unitprocess01@example.com");
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

        driver.findElement(By.xpath("//*[@id=\"form-step-0\"]/div/div/div/div/div/div/div[2]/button")).click();
        
        WebElement sectOnePass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-one-radio-2\"]")));
        sectOnePass.click();
        WebElement sectOneSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-one\"]/div/div[4]/div[2]/button")));
        sectOneSaveNSubmit.click();
        
        WebElement sectTwoPass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-two-radio-2\"]")));
        sectTwoPass.click();
        WebElement sectTwoSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-two\"]/div/div[4]/div[2]/button")));
        sectTwoSaveNSubmit.click();
        
        try {
            WebElement sectThreePass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-three-radio-2\"]")));
            actions.moveToElement(sectThreePass).perform();
            sectThreePass.click();
    
        } catch (Exception e) {
            System.out.println("Failed to click Sect 3 Pass "+ e);
        }
        WebElement sectThreeSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-three\"]/div/div[4]/div[2]/button")));
        sectThreeSaveNSubmit.click();

        try {
            WebElement sectFourPass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-four-radio-2\"]")));
            actions.moveToElement(sectFourPass).perform();
            sectFourPass.click();
    
        } catch (Exception e) {
            System.out.println("Failed to click sect 4 pass "+e);        
        }
        WebElement sectFourSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-four\"]/div/div[4]/div[2]/button")));
        sectFourSaveNSubmit.click();
        
        try {
            WebElement sectFivePass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-five-radio-2\"]")));
            actions.moveToElement(sectFivePass).perform();
            sectFivePass.click();
    
        } catch (Exception e) {
            System.out.println("Failed to click sect 5 pass "+e);
        }
        WebElement sectFiveSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-five\"]/div/div[4]/div[2]/button")));
        sectFiveSaveNSubmit.click();

        try {
            WebElement sectSixPass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-six-radio-2\"]")));
            actions.moveToElement(sectSixPass).perform();
            sectSixPass.click();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement sectSixSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-six\"]/div/div[4]/div[2]/button")));
        sectSixSaveNSubmit.click();

        try {
            WebElement sectSevenPass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section-seven-radio-2\"]")));
            actions.moveToElement(sectSevenPass).perform();
            sectSevenPass.click();
    
        } catch (Exception e) {
            System.out.println("Failed to click sect 7 pass "+e);
        }
        WebElement sectSevenSaveNSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"feedback-form-section-seven\"]/div/div[4]/div[2]/button")));
        sectSevenSaveNSubmit.click();

        // approve and notify in charge officer
        try {
            WebElement approveNotifyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"company-proposal\"]/div/div[1]/div/div/form/button")));
            actions.moveToElement(approveNotifyButton).perform();
            approveNotifyButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();

        } catch (Exception e) {
            System.out.println("Unable to click approve button " + e);
        }


        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.navigate().back();

        } catch (Exception e) {
            takeErrorScreenshot(driver, "unitprocess_firstlayer");
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
            System.out.println("Failed to take screenshot: "+e);
        }
    }
}