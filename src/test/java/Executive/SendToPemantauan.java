package Executive;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class SendToPemantauan {

    public void testSendToPemantauan (int fileNo) {

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
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[2]/div/ul/li[1]/div/ul/li[8]/a"))).click();
            

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
