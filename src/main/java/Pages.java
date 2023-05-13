import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Pages {

    protected static WebDriver driver;

    protected Pages()
    {
        if(driver == null){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));}
    }

    public static void closeDriver()
    {
        if(driver != null){
            driver.close();
            driver = null;
        }
    }

    protected static final String url = "https://demo.seleniumeasy.com/";

    public void runFunction(String functionName)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(functionName);
    }

    public void scrollToElement(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView", element);
    }
    public void waitMethod(WebDriver driver, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String[] readFile(String fileName)
    {
        List<String> str = new ArrayList<>();
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                str.add(scanner.nextLine().trim());
            }
            scanner.close();

        } catch (Exception e)   {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        String[] result = str.toArray(new String[0]);
        return result;
    }

}
