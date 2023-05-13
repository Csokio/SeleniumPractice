



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.http.HttpResponse;

import java.io.File;
import java.util.Scanner;

public class DemoTest {

    @AfterEach
    public void closeTheDriver()
    {
        Pages.closeDriver();
    }
    @Test
    public void testCountRowInTables()
    {
        TablePage tablePage = new TablePage();
        tablePage.goToPaginationTable();
        Assertions.assertEquals(13, tablePage.countTableRows());
    }

    @Test
    public void testClickCheckBoxes()
    {
        InputForms inputForms = new InputForms();
        inputForms.goToCheckBox();
        Assertions.assertTrue(inputForms.clickCheckBox());
    }

    @Test
    public void testRadioButtons()
    {
        InputForms inputForms = new InputForms();


        Object[] actualObject = inputForms.getRadioButtonValue().entrySet().toArray();
        String[] actual = new String[actualObject.length];
        for (int i = 0; i < actualObject.length; i++) {
            actual[i] = actualObject[i].toString().trim();
        }

        String[] expected = inputForms.readFile("ages_list.txt");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testApiStatus()
    {

        Response response = RestAssured
                .given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        int actual = response.statusCode();

        Assertions.assertEquals(200,actual);
    }
    @Test
    public void testStatusWebsite()
    {
        int actual = RestAssured.get("https://automationexercise.com").statusCode();
        Assertions.assertEquals(200,actual);
    }

}
