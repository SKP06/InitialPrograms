import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotDemo {

    @Test
    public void myTest() throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://stock.scriptinglogic.com/");

        // 1 create an object reference of takesscreenshotas
        // typecast driver into takescreenshot

        TakesScreenshot ts = (TakesScreenshot) driver;

        // 2 call the method getScreenshotAs() and assign it to a file

        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        // create a real image
        Date date = new Date();
        String timestamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(date);
        String fileName = "IMG"+timestamp+".png";

        FileUtils.copyFile(srcFile,new File("C:\\Users\\Venky\\Downloads\\Movies\\"+fileName));


    }



}
