package com.cybertek.tests.day04_ords_path;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.response.Response;

import java.util.List;


public class GetSpartanUsingPath extends SpartanTestBase {
    /**
     * Given accepts is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ------------------------------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @DisplayName("")
    @Test
    public void readJsonWithPathTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .and().when().get("/api/spartans/{id}");
        System.out.println("----------------------------------");
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        //print values from JSON
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        //store values from JSON to variables

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phoneNum = response.path("phone");

        System.out.println("PRINTING VARIABLES");
        System.out.println("id = " + id);
        System.out.println("phoneNum = " + phoneNum);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);


        //assertions

        assertEquals(13, id);
        assertEquals("Jaimie", name);
        assertEquals("Female", response.path("gender"));
        assertEquals(7842554879L, phoneNum);

    }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @Test
    public void readJsonArrayTest() {
    Response response = given().accept(ContentType.JSON)
            .when().get("/api/spartans");
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("content type = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id = " + response.path("id"));  //shows all IDs

        System.out.println("id of first spartan = " + response.path("[0].id"));     //will give us the id of the first spartan which is index 0

        System.out.println("First name of the second person = " + response.path("[1].name"));


        //print third person's id, name, gender, phone

        System.out.println("Third person id = " + response.path("[2].id"));
        System.out.println("Third person name = " + response.path("[2].name"));
        System.out.println("Third person gender = " + response.path("[2].gender"));
        System.out.println("Third person phone number = " + response.path("[2].phone"));

        System.out.println(response.path("id[2]").toString());

        //print all info for the 3rd person
        System.out.println("3rd Person's Info = " + response.path("[2]").toString());


        //print last person's info
        System.out.println("PRINT OUT THE LAST PERSON INFO");
        System.out.println("Third person id = " + response.path("id[-1]").toString());
        System.out.println("Third person name = " + response.path("name[-1]").toString());
        System.out.println("Third person gender = " + response.path("gender[-1]").toString());
        System.out.println("Third person phone number = " + response.path("phone[-1]").toString());


        //Store all first names into a list
        List<String> allNames = response.path("name");
        System.out.println("Names count = " + allNames.size());

        for(String each: allNames){
            System.out.println(each);
        }



    }


}
