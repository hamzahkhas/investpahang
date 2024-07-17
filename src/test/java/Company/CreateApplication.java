package Company;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;

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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String link = "https://dev.suite.psk.gov.my";


        driver.manage().window().maximize();
        driver.get(link);

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

        // collapse sidebar
        displaySidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button"));
        actions.moveToElement(displaySidebar).perform();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();

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
        WebElement uploadProjectImplementationSchedule = driver.findElement(By.cssSelector("#project_implementation_schedule"));
        uploadProjectImplementationSchedule.sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\Implementation Schedule.pdf"); 

        driver.findElement(By.xpath("//*[@id=\"form-step-1\"]/div/div[3]/div/div[5]/div[5]/button")).click();

        /* Tab 2 - application information b */
        driver.findElement(By.xpath("//*[@id=\"paidup_capital\"]")).sendKeys("1500000");
        driver.findElement(By.xpath("//*[@id=\"percent_bumi\"]")).sendKeys("10");  
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[1]/div/div/div[2]/input")).sendKeys("Hamzah Khas");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[2]/div/div/div[2]/input")).sendKeys("000900000000");
        driver.findElement(By.xpath("//*[@id=\"form-step-2\"]/div/div[2]/div[5]/div[3]/div/div/div[2]/input")).sendKeys("25");
        driver.findElement(By.xpath("//*[@id=\"app-info-b-next-btn\"]")).click();

        /* Tab 3 - project cost estimation */
        driver.findElement(By.xpath("//*[@id=\"land\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"building\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"source_raw_material\"]   ")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"tools\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"staff\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"management_market_expenses\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"capital_loan_cost\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"utility_cost\"]")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id=\"project-cost-est-next-button\"]")).click();
        
        /* Tab 4 -  project proposal */
        driver.findElement(By.cssSelector("#project_proposal_paper")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\Proposal.pdf");
        driver.findElement(By.xpath("//*[@id=\"form-step-4\"]/div/div[2]/div/div[2]/div/div/div[2]/button")).click();

        /* Tab 5 - company profile */
        driver.findElement(By.cssSelector("#company_profile")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\company profile.pdf");
        driver.findElement(By.xpath("//*[@id=\"ssm\"]")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\company profile.pdf");
        driver.findElement(By.xpath("//*[@id=\"form-step-5\"]/div[2]/div/div[3]/div/div/div[2]/button")).click();

        /* Tab 6 - company financial report */
        driver.findElement(By.cssSelector("#statement_account_1_value")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\financial report.pdf");
        driver.findElement(By.cssSelector("#statement_account_2_value")).sendKeys("C:\\\\Users\\hamza\\Desktop\\invest-pahang docs\\financial report.pdf");
        driver.findElement(By.cssSelector("#statement_account_3_value")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\financial report.pdf");
        driver.findElement(By.xpath("//*[@id=\"form-step-6\"]/div[2]/div/div[2]/div/div/div[2]/button")).click();

        /* tab 7 - plan */
        driver.findElement(By.xpath("//*[@id=\"site_plan\"]")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\site-plan-example.pdf");
        driver.findElement(By.xpath("//*[@id=\"topography_plan\"]")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\topography-plan-example.pdf");
        driver.findElement(By.xpath("//*[@id=\"site_zone\"]")).sendKeys("C:\\Users\\hamza\\Desktop\\invest-pahang docs\\site zone enquiry.pdf");
        // driver.findElement(By.xpath("//*[@id=\"property_rights_copy\"]")).sendKeys("");
        // driver.findElement(By.xpath("//*[@id=\"official_land_search\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"form-step-7\"]/div[2]/div/div[3]/div/div/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]")).click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.navigate().back();

        // try {
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }    

        js.executeScript("window.open('"+ link +"','_blank');");      
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));  

        
        displaySidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button"));
        actions.moveToElement(displaySidebar).perform();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/nav/div[1]/button")).click();

        WebElement viewapplication = driver.findElement(By.xpath("//*[@id=\"sidebar-investment-dropdown-list\"]/li[2]/a"));
        js.executeScript("arguments[0].click()", viewapplication);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   

        driver.quit();

    }
}
