package Company;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
        investmentMenu.click();
        driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[1]/a")).click();

        // click next on checklist page
        driver.findElement(By.xpath("//*[@id=\"form-step-0\"]/div/div/div[2]/div/div/div[2]/button")).click();

        /*  tab 1 - application information a*/
        WebElement typeOfInvestment = driver.findElement(By.xpath("//*[@id=\"investment_type\"]"));     // investment type
        Select investmentDropdown = new Select(typeOfInvestment);
        investmentDropdown.selectByValue("agriculture");
        
        driver.findElement(By.xpath("//*[@id=\"proposed_project\"]")).sendKeys("Test project");     // proposed project

        Select districtDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"region-select\"]")));       // select district
        districtDropdown.selectByValue("Bentong");

        Select provinceDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"district-select\"]")));     // select province
        provinceDropdown.selectByValue("Bentong");

        driver.findElement(By.xpath("//*[@id=\"area\"]")).sendKeys("test");         // location
        driver.findElement(By.xpath("//*[@id=\"wide_acres\"]")).sendKeys("100");    // land area
        driver.findElement(By.id("gross_development_cost")).sendKeys("1500000");            // gross development cost
        
        // upload file
        WebElement uploadFileButton = driver.findElement(By.cssSelector("#project_implementation_schedule"));
        uploadFileButton.sendKeys("C:\\Users\\USER\\Downloads\\Telegram Desktop\\invest-pahang docs\\Implementation Schedule.pdf"); 

        driver.findElement(By.xpath("//*[@id=\"form-step-1\"]/div/div[3]/div/div[5]/div[5]/button")).click();

        /* Tab 2 - application information b */
        driver.findElement(By.xpath("//*[@id=\"paidup_capital\"]")).sendKeys("1500000");
        driver.findElement(By.xpath("//*[@id=\"percent_bumi\"]")).sendKeys("10");  
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[1]/div/div/div[2]/input")).sendKeys("Hamzah Khas");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[2]/div/div/div[2]/input")).sendKeys("000900000000");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[3]/div/div/div[2]/input")).sendKeys("25");

        /* saving as draft */
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement draftButton = driver.findElement(By.id("draft-button"));
        js.executeScript("arguments[0].click();", draftButton);
        driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]")).click();
        // need to add element to wait for it to fully load

        // reveal sidebar (on hover etc)
        actions.moveToElement(displaySidebar).perform();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();

        // create application submenu
        actions.moveToElement(investmentMenu).perform();
        investmentMenu.click();
        driver.findElement(By.xpath("//*[@id=\"adminmenu\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[2]/a")).click();

        driver.quit();

    }
}
