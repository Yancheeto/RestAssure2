package com.cybertek.tests.office_hours;
import com.cybertek.tests.pojo.zipcode.Place;
import com.cybertek.tests.pojo.zipcode.ZipInfo;
import io.restassured.http.ContentType;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ZipCodeMorePracticeTest {
    @BeforeAll
    public static void setUp(){
        baseURI = "http://api.zippopotam.us";
    }

    //pojo class
    @Test
    public void zipCodePojoTest(){
        Response response = (Response) given().accept(ContentType.JSON)
                .when().get("/us/12189");

       assertThat(response.statusCode(), is(200));
       //de-serialization of json response body. Jackson json parser library

        ZipInfo zipInfo = response.as(ZipInfo.class);

        System.out.println("zip info = " + zipInfo);

        assertThat(zipInfo.getPostCode(), equalTo("12189"));
        assertThat(zipInfo.getCountry(), equalTo("United States"));
        assertThat(zipInfo.getCountryAbbreviation(), is(equalTo("US")));

        zipInfo.getPlaces().get(0).getPlaceName();

        Place place = zipInfo.getPlaces().get(0);

        assertThat(place.getPlaceName(), equalTo("Watervliet"));
        assertThat(place.getState(), equalTo("New York"));
        assertThat(place.getLatitude(), equalTo("42.7298"));
        assertThat(place.getStateAbbreviation(), equalTo("NY"));
    }
    @Test
    public void zipcodeHamcrestChainingTest(){
        given().accept(ContentType.JSON)
                .when().get("/us/20171")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("'post code'", is("20171"),
                                "country", equalTo("United States"),
                                "'country abbreviation'", equalTo("US"),
                                "places[0].'place name'", equalTo("Herndon"),
                                "places[0].longitude", equalTo("-77.3928"));

}

}
