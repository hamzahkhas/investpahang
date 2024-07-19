package Company;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadInvoice {
    
    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
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


    }

}
