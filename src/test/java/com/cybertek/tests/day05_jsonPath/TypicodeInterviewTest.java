package com.cybertek.tests.day05_jsonPath;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class TypicodeInterviewTest {
    @BeforeAll
    public static void setUp() {
        baseURI = "https://jsonplaceholder.typicode.com";
    }
    @Test
    public void getUserTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/users/1");

        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        //convert response body to JasonPath

        JsonPath json = response.jsonPath();
        System.out.println("name = " + json.getString("name"));
        System.out.println("city = " + json.getString("address.city"));

        //print company name
        String companyName = json.getString("company.name");
        System.out.println("companyName = " + companyName);

        String lng = json.getString("address.geo.lng");
        System.out.println("lng = " + lng);

    }
}
