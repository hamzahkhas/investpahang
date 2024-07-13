package Company;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class CreateApplication {

    public static void main (String [] args) {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);      // to do actions such as hovering

        driver.get("https://dev.suite.psk.gov.my");

        // login
        driver.findElement(By.id("email")).sendKeys("company@example.com");
        driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();

        // reveal sidebar (on hover etc)
        WebElement displaySidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button"));
        actions.moveToElement(displaySidebar).perform();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();

        // create application submenu
        WebElement investmentMenu = driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[3]/a"));
        actions.moveToElement(investmentMenu).perform();
        driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"form-step-0\"]/div/div/div[2]/div/div/div[2]/button")).click();

        /*  tab 1 - application information a*/
        WebElement typeOfInvestment = driver.findElement(By.xpath("//*[@id=\"investment_type\"]"));
        Select investmentDropdown = new Select(typeOfInvestment);
        investmentDropdown.selectByValue("agriculture");

        driver.findElement(By.xpath("//*[@id=\"proposed_project\"]")).sendKeys("Test project");
        Select districtDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"region-select\"]")));
        districtDropdown.selectByValue("Bentong");

        Select provinceDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"district-select\"]")));
        provinceDropdown.selectByValue("Bentong");

        driver.findElement(By.xpath("//*[@id=\"area\"]")).sendKeys();


        driver.quit();
    }
}
