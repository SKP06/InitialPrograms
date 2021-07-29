import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// Login with data provider
// This is a data driven framework
// This is my first TestNG program with 4 test cases using 2 dimensional array
/*
testNG 7.1.0 jars to be added to lib
Here @Test from testNG is used
Guava jars are repeated . del one of them
 */

public class LoginData {

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
    public Object[][] getData()
    {
        Object[][] data = new Object[4][2];
        data[0][0]="admin1";
        data[0][1]="pass1";
        data[1][0]="admin2";
        data[1][1]="pass2";
        data[2][0]="admin3";
        data[2][1]="pass3";
        data[3][0]="admin4";
        data[3][1]="pass4";

        return data;
    }

}

