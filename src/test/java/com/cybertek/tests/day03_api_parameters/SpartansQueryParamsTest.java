package com.cybertek.tests.day03_api_parameters;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class SpartansQueryParamsTest {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }
    /**
     * Given accept type is Json
     *         And query parameter values are:
     *         gender|Female
     *         nameContains|e
     *         When user sends GET request to /api/spartans/search
     *         Then response status code should be 200
     *         And response content-type: application/json
     *         And "Female" should be in response body
     *         And "Janette" should be in response body
     */

    @Test
    public void searchForSpartanQueryTest(){

        Response response =  given().accept(ContentType.JSON)
                          //  .queryParam("gender","Female", "nameContains", "e");
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println(response.asString());

        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));

    }


}
