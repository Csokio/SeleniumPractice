import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TablePage extends Pages{

    public TablePage()
    {
        super();
    }


    private final By BUTTON_TABLE = By.xpath("//ul[@id='treemenu']//li/a[text()='Table']");

    private final By SUB_BUTTON_TABLE = By.xpath("//ul[@id='treemenu']//li/a[contains(text(), 'Pagin')]");

    public void goToPaginationTable()
    {
        driver.navigate().to(Pages.url);
        waitMethod(driver, driver.findElement(BUTTON_TABLE));
        driver.findElement(BUTTON_TABLE).click();
        driver.findElement(SUB_BUTTON_TABLE).click();
    }

    private final By ROWS = By.xpath("//section[@class='content']//tbody/tr");

    private final By BUTTON_NEXT = By.xpath("//ul[@id='myPager']//a[@class='next_link']");

    public boolean clickNextButton()
    {
        scrollToElement(driver.findElement(BUTTON_NEXT));
        if (driver.findElement(BUTTON_NEXT).isDisplayed()){
            driver.findElement(BUTTON_NEXT).click();
            return true;
        }
        return false;
    }

    public int countTableRows()
    {
        int count = 0;
        List<WebElement> rowList = driver.findElements(ROWS);
        do  {
            for(WebElement row: rowList){

                String style = row.getAttribute("style");
                if(style.equals("display: table-row;")){
                    count++;
                }
            }
        } while (clickNextButton());



        return count;
    }
}
