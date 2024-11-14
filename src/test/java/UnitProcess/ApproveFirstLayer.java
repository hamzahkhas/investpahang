package UnitProcess;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

public class ApproveFirstLayer {

    @Test
    public void testApproveFirstLayer(int applicationNo) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        try {
            driver.get("https://dev.suite.psk.gov.my");

            // check if page successfully open
            // Assertions.assertTrue(driver.getTitle().contains("Invest Pahang"), "Title does not contain 'Invest Pahang'");

            // login
            driver.findElement(By.id("email")).sendKeys("unitprocess01@example.com");
            driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("M!rf@lah123");
            driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();

            // check if successful login 
            // Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/div/div/div"))).isDisplayed());

            // view new application submenu
            WebElement unitProcessMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[4]/a")));
            actions.moveToElement(unitProcessMenu).perform();
            unitProcessMenu.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[4]/div/ul/li[1]/a"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[4]/div/ul/li[1]/div/ul/li[1]/a"))).click();

            // check if successful navigation
            // Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/div/div/div/h1"))).isDisplayed());

            boolean applicationFound = false;

            // cari the application
            while (!applicationFound) {
                try {
                    // Try to locate the application by its XPath
                    WebElement applicationId = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\""+applicationNo+"\"]/td[7]/div/a[1]")));
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

            driver.findElement(By.xpath("//*[@id=\"form-step-0\"]/div/div/div/div/div/div/div[2]/button")).click();

            // check if application is open
            // Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/main/div[2]/div[2]/div[1]/h1"))).isDisplayed());
            
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
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            takeErrorScreenshot(driver, "unitprocess_firstlayer");
            System.out.println("Something went wrong: "+e.getMessage());
            // Assertions.fail("Test failed due to an exception "+e.getMessage());

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