package UnitProcess;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class ApproveFirstLayer {

    public static void main (String [] args) {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
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
            WebElement viewApplication = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"296\"]/td[7]/div/a[1]")));

            // Move to the element and click it
            actions.moveToElement(viewApplication).perform();
            viewApplication.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//*[@id=\"form-step-0\"]/div/div/div/div/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-one-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-one\"]/div/div[4]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-two-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-two\"]/div/div[4]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-three-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-three\"]/div/div[4]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-four-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-four\"]/div/div[4]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-five-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-five\"]/div/div[4]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-six-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-six\"]/div/div[4]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"section-seven-radio-2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"feedback-form-section-seven\"]/div/div[4]/div[2]/button")).click();


    }
}