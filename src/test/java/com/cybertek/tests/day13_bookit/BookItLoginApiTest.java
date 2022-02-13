package com.cybertek.tests.day13_bookit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookItLoginApiTest {
    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }

    /**
     * Given accepts type is jason
     * and query params: email=lfinnisz@yolasite.com
     * password=lissiefinnis
     * When I send get request to /sign
     * Then status code is 200
     * and response json body contains the token
     */
    @Test
    public void loginAsTeamLeaderTest(){
        given().accept(ContentType.JSON)
                .and().queryParams("email", "lfinnisz@yolasite.com",
                        "password", "lissiefinnis")
                .when().get("/sign")
                .then().assertThat().statusCode(200)
                .and().body("accessToken", not(emptyString()))
                .log().all();



    }




}
