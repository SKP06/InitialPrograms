import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.builder.ToStringExclude;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUniDemo {

    @Test
    public void myTest(){
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

        try  
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        driver.close();
    }
}
