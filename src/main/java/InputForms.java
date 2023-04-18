import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputForms extends Pages{

    public InputForms()
    {
        super();
    }


    private final By BUTTON_INPUT_FORMS = By.xpath("//a[text()='All Examples']/following::a[text()='Input Forms']");
    private final By PAGE_CHECKBOX = By.xpath("//ul[@id='treemenu']//a[contains(text(), 'Che')]");
    public void goToCheckBox()
    {
        driver.navigate().to(Pages.url);
        driver.findElement(BUTTON_INPUT_FORMS).click();
        driver.findElement(PAGE_CHECKBOX).click();
    }

    private final By CHECKBOX = By.xpath("//div[text()=\"Multiple Checkbox Demo\"]//following::input[@type=\"checkbox\"]");

    public boolean clickCheckBox()
    {
        List<WebElement> boxes = driver.findElements(CHECKBOX).subList(1,3);

        for(WebElement box: boxes){
            if(!box.isSelected()){
                box.click();
            }
        }

        for(int i = 0; i < boxes.size(); i++){
            if(boxes.get(i).isSelected()){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    //TODO Radio Buttons

    private final By PAGE_RADIO = By.xpath("//a[text()='All Examples']/following::a[text()='Radio Buttons Demo']");
    private final By BUTTON_FEMALE = By.xpath("//h4[text()='Sex : ']/following::input[@value='Female']");
    private final By BUTTON_MALE = By.xpath("//h4[text()='Sex : ']/following::input[@value='Male']");
    private final By BUTTON_AGES = By.xpath("//input[@name='ageGroup']");
    private final By TEXT_AGES = By.xpath("//p[@class='groupradiobutton']");

    public HashMap<String, String> getRadioButtonValue()
    {
        driver.navigate().to(Pages.url);
        driver.findElement(BUTTON_INPUT_FORMS).click();
        driver.findElement(PAGE_RADIO).click();
        driver.findElement(BUTTON_MALE).click();

        List<WebElement> ages = driver.findElements(BUTTON_AGES);
        for(WebElement age: ages){
            age.click();
        }
        runFunction("getValues();");

        String result = driver.findElement(TEXT_AGES).getText();
        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.insert(11, ",");

        HashMap<String, String> mapAges = new HashMap<>();
        String[] pairs = stringBuilder.toString().split(",");
        for(String pair: pairs){
            String[] mapValue = pair.split(":");
            mapAges.put(mapValue[0], mapValue[1]);
        }

        return mapAges;

    }
}
