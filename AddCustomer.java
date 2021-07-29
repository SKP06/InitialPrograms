import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddCustomer {

        @Test
        public void myTest() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.get("https://stock.scriptinglogic.net/");

            WebElement txtUser = driver.findElement(By.id("login-username"));
            txtUser.sendKeys("admin");

            WebElement txtPwd = driver.findElement(By.id("login-password"));
            txtPwd.sendKeys("admin");

            WebElement btnLogin = driver.findElement(By.name("submit"));
            btnLogin.click();

            WebElement lnkAddCustomer = driver.findElement(By.linkText("Add Customer"));
            lnkAddCustomer.click();

            WebElement txtName = driver.findElement(By.name("name"));
            txtName.sendKeys("Shalaka");

            WebElement txtaddr = driver.findElement(By.name("address"));
            txtaddr.sendKeys("Kandiwali");

            WebElement txtcnt1 = driver.findElement(By.name("contact1"));
            txtcnt1.sendKeys("98765");

            WebElement txtcnt2 = driver.findElement(By.name("contact2"));
            txtcnt2.sendKeys("43210");

            WebElement btnadd = driver.findElement(By.name("Submit"));
            btnadd.click();

            String expected = "Dublicat Entry. Please Verify";
            WebElement errorMsg = driver.findElement(By.cssSelector(".error-box"));
            String actual = errorMsg.getText();

            Assert.assertEquals("Incorrect error",expected,actual);
            try
            {
                Thread.sleep(5000);
            }
            catch(InterruptedException e)
            {
                // this part is executed when an exception (in this example InterruptedException) occurs
            }

            //driver.close();

        }
    }
