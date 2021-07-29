
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MultipleDataExcelAddCustomerDemo {

    WebDriver driver;

        @BeforeClass
        public void doLogin()
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.get("https://stock.scriptinglogic.net/");

            WebElement txtUser = driver.findElement(By.id("login-username"));
            txtUser.sendKeys("admin");

            WebElement txtPwd = driver.findElement(By.id("login-password"));
            txtPwd.sendKeys("admin");

            WebElement btnLogin = driver.findElement(By.name("submit"));
            btnLogin.click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        }

bhunjgdfff vvfftsdd rrrrrr

    @Test(dataProvider = "getData")
    public void addCustomerTest(String name, String addr, String ct1, String ct2)
    {

        driver.findElement(By.xpath("//a[normalize-space()='Add Customer']")).click();

        WebElement txtName = driver.findElement(By.xpath("//input[@id='name']"));
        txtName.sendKeys(name);

        WebElement txtAddr = driver.findElement(By.xpath("//textarea[@placeholder='ENTER YOUR ADDRESS']"));
        txtAddr.sendKeys(addr);

        WebElement contact1 = driver.findElement(By.xpath("//input[@id='buyingrate']"));
        contact1.sendKeys(ct1);

        WebElement contact2 = driver.findElement(By.xpath("//input[@id='sellingrate']"));
        contact2.sendKeys(ct2);

        WebElement btnAdd = driver.findElement(By.xpath("//input[@id='Add']"));
        btnAdd.click();

    }


    @DataProvider
    public Object[][] getData() throws IOException {
        FileInputStream fp = new FileInputStream("Data\\dataxlsx.xlsx");
        XSSFWorkbook workbook= new XSSFWorkbook(fp);
        XSSFSheet sheet = workbook.getSheet("Sheet3");

        int rowCount = sheet.getPhysicalNumberOfRows();

        Object[][] data = new Object[rowCount-1][4];

        for(int i =0; i< rowCount-1; i++)
        {
            XSSFRow row= sheet.getRow(i+1);

            for (int j=0;j<4;j++)
            {
                XSSFCell cell =  row.getCell(j);
                data[i][j] = cell.toString().trim();
            }
        }
        return data;
    }

}
