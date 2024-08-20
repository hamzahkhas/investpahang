package Company;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateApplication {

    @Test
    public void testCreateApplication() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);      // to do actions such as hovering
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String link = "https://dev.suite.psk.gov.my";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        driver.manage().window().maximize();

        try {
            
        // login
        driver.get(link);
        driver.findElement(By.id("email")).sendKeys("company@example.com");
        driver.findElement(By.xpath("//*[@id=\"passwordGroup\"]/div/input")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/form/button")).click();

        // create application submenu
        WebElement investmentMenu = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[3]/a"));
        actions.moveToElement(investmentMenu).perform();
        investmentMenu.click();
        driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[3]/div/ul/li[1]/a/span[2]")).click();

        // click next on checklist page
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#form-step-0 > div > div > div:nth-child(2) > div > div > div:nth-child(2) > button"))).click();;

        /*  tab 1 - application information a*/
        WebElement typeOfInvestment = driver.findElement(By.xpath("//*[@id=\"investment_type\"]"));     // investment type
        Select investmentDropdown = new Select(typeOfInvestment);
        investmentDropdown.selectByValue("agriculture");
        
        driver.findElement(By.xpath("//*[@id=\"proposed_project\"]")).sendKeys("CADANGAN PAJAKAN TANAH PROJEK TANAMAN KELAPA");     // proposed project

        Select districtDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"region-select\"]")));       // select district
        districtDropdown.selectByValue("Rompin");

        Select provinceDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"district-select\"]")));     // select province
        provinceDropdown.selectByValue("Rompin");

        driver.findElement(By.xpath("//*[@id=\"area\"]")).sendKeys("HSD 5739 NO PT 8198 KAMPUNG DERAMAN");         // location
        driver.findElement(By.xpath("//*[@id=\"wide_acres\"]")).sendKeys("550");    // land area
        driver.findElement(By.id("gross_development_cost")).sendKeys("1100000");            // gross development cost
        
        // upload file
        WebElement uploadProjectImplementationSchedule = driver.findElement(By.cssSelector("#project_implementation_schedule"));
        uploadProjectImplementationSchedule.sendKeys("C:\\Users\\hamza\\Downloads\\Implementation Schedule.pdf"); 
        driver.findElement(By.xpath("//*[@id=\"form-step-1\"]/div/div[3]/div/div[5]/div[5]/button")).click();

        /* Tab 2 - application information b */
        driver.findElement(By.xpath("//*[@id=\"paidup_capital\"]")).sendKeys("1000000");
        driver.findElement(By.xpath("//*[@id=\"percent_bumi\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"percent_bumi\"]")).sendKeys("30");  
        driver.findElement(By.xpath("//*[@id=\"percent_nonbumi\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"percent_nonbumi\"]")).sendKeys("70");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[1]/div/div/div[2]/input")).sendKeys("Hamzah Khas");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[2]/div/div/div[2]/input")).sendKeys("000900000000");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[3]/div/div/div[2]/input")).sendKeys("25");
        driver.findElement(By.xpath("//*[@id=\"app-info-b-next-btn\"]")).click();

        /* Tab 3 - project cost estimation */
        driver.findElement(By.xpath("//*[@id=\"land\"]")).sendKeys("1430000");
        driver.findElement(By.xpath("//*[@id=\"building\"]")).sendKeys("0");
        driver.findElement(By.xpath("//*[@id=\"source_raw_material\"]   ")).sendKeys("1980000");
        driver.findElement(By.xpath("//*[@id=\"tools\"]")).sendKeys("715000");
        driver.findElement(By.xpath("//*[@id=\"staff\"]")).sendKeys("1375000");
        driver.findElement(By.xpath("//*[@id=\"management_market_expenses\"]")).sendKeys("0");
        driver.findElement(By.xpath("//*[@id=\"capital_loan_cost\"]")).sendKeys("0");
        driver.findElement(By.xpath("//*[@id=\"utility_cost\"]")).sendKeys("0");
        driver.findElement(By.xpath("//*[@id=\"project-cost-est-next-button\"]")).click();
        
        /* Tab 4 -  project proposal */
        driver.findElement(By.cssSelector("#project_proposal_paper")).sendKeys("C:\\Users\\hamza\\Downloads\\Kertas Cadangan.pdf");
        driver.findElement(By.xpath("//*[@id=\"form-step-4\"]/div/div[2]/div/div[2]/div/div/div[2]/button")).click();

        /* Tab 5 - company profile */
        driver.findElement(By.cssSelector("#company_profile")).sendKeys("C:\\Users\\hamza\\Downloads\\Company Profile.pdf");
        driver.findElement(By.xpath("//*[@id=\"ssm\"]")).sendKeys("C:\\Users\\hamza\\Downloads\\SSM.pdf");
        driver.findElement(By.xpath("//*[@id=\"form-step-5\"]/div[2]/div/div[3]/div/div/div[2]/button")).click();

        /* Tab 6 - company financial report */
        driver.findElement(By.cssSelector("#statement_account_1_value")).sendKeys("C:\\Users\\hamza\\Downloads\\Financial Report 1.pdf");
        driver.findElement(By.cssSelector("#statement_account_2_value")).sendKeys("C:\\Users\\hamza\\Downloads\\Financial Report 2.pdf");
        driver.findElement(By.cssSelector("#statement_account_3_value")).sendKeys("C:\\Users\\hamza\\Downloads\\Financial Report 3.pdf");
        driver.findElement(By.xpath("//*[@id=\"form-step-6\"]/div[2]/div/div[2]/div/div/div[2]/button")).click();

        /* tab 7 - plan */
        driver.findElement(By.xpath("//*[@id=\"site_plan\"]")).sendKeys("C:\\Users\\hamza\\Downloads\\Site Plan.pdf");
        driver.findElement(By.xpath("//*[@id=\"topography_plan\"]")).sendKeys("C:\\Users\\hamza\\Downloads\\Topo Plan.pdf");
        driver.findElement(By.xpath("//*[@id=\"site_zone\"]")).sendKeys("C:\\Users\\hamza\\Downloads\\Site Zone.pdf");
        // driver.findElement(By.xpath("//*[@id=\"property_rights_copy\"]")).sendKeys("");
        // driver.findElement(By.xpath("//*[@id=\"official_land_search\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"form-step-7\"]/div[2]/div/div[3]/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // open new page
        js.executeScript("window.open('"+ link +"','_blank');");      
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));  

        // view project progress
        WebElement investMenu = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[3]/a"));
        actions.moveToElement(investMenu).perform();
        investMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]/div/div[2]/nav/ul/li[3]/div/ul/li[6]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dataTable\"]/thead/tr/th[2]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dataTable\"]/thead/tr/th[2]"))).click();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   

        } catch (Exception e) {
            takeErrorScreenshot(driver, "Apply Error");

        } finally {
            driver.quit();
        }

    }

    // fnction to capture ss
    private static void takeErrorScreenshot(WebDriver driver, String fileName) {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshot, new File("C:\\Users\\hamza\\Downloads\\Error Screenshots\\"+fileName+".png"));
        } catch (IOException exception) {
            System.out.println("Failed to save screenshot: "+exception);
        }
    }
}
