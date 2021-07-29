import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

// Login with data provider using excel sheet
// This is a data driven framework
// This is my first TestNG program with test cases using excel
/*
testNG 7.1.0 jars to be added to lib
Here @Test from testNG is used
Guava jars are repeated . del one of them
 */

public class LoginDataExcelxlsx {

    @Test (dataProvider = "getData")
    public void myTest(String username, String password){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://stock.scriptinglogic.net/");

        WebElement txtUser = driver.findElement(By.id("login-username"));
        txtUser.sendKeys(username);

        WebElement txtPwd = driver.findElement(By.id("login-password"));
        txtPwd.sendKeys(password);

        WebElement btnLogin = driver.findElement(By.name("submit"));
        btnLogin.click();

        String expected = "https://stock.scriptinglogic.net/index.php?msg=Wrong%20Username%20or%20Password&type=error";
        String actual = driver.getCurrentUrl();
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        driver.close();
        Assert.assertEquals("We are not on login page", expected,actual);
    }

    @DataProvider
    public Object[][] getData() throws IOException
    {
      /* For excel download POI library add to lib folder
        Read excel using fileinputstream
        If file extension is xlsx then just replace H with X everywhere in HSSF
        Convert file object into workbook object
        choose the sheet in excel
        find the no of active rows */

        FileInputStream fp = new FileInputStream("Data\\dataxlsx.xlsx");
        XSSFWorkbook workbook= new XSSFWorkbook(fp);
        XSSFSheet sheet = workbook.getSheet("Sheet2");

        int rowCount =sheet.getPhysicalNumberOfRows();

        Object[][] data = new Object[rowCount-1][2];

        for(int i =0; i< rowCount-1; i++)
        {
            XSSFRow row = sheet.getRow(i+1);

            XSSFCell username = row.getCell(0);
            data[i][0] = username.toString().trim();

            XSSFCell password = row.getCell(1);
            data[i][1] = password.toString().trim();

        }

        /*
        Object[][] data = new Object[rowCount][2];

         for(int i =0; i< rowCount; i++)      for direct records  - no header
        {
            XSSFRow row = sheet.getRow(i);

            XSSFCell username = row.getCell(0);
            data[i][0] = username.toString().trim();

            XSSFCell password = row.getCell(1);
            data[i][1] = password.toString().trim();

        } */
        return data;
    }

}

