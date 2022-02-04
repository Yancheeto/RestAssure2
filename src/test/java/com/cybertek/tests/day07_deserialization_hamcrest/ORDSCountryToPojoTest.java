package com.cybertek.tests.day07_deserialization_hamcrest;
import com.cybertek.tests.pojo.Country;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import com.cybertek.tests.ORDSTestBase;

public class ORDSCountryToPojoTest extends ORDSTestBase {

    @Test
    public void countryToPojoTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/countries/US");

        System.out.println("status code = " + response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        Country country = response.body().as(Country.class);

        System.out.println(country.getCountryId());
        System.out.println(country.getCountryName());
        System.out.println(country.getRegionId());
        System.out.println(country.getLinks());

    }


}
