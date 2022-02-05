package com.cybertek.tests.day08_hamcrest_post;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanHamcrestTest extends SpartanTestBase {

    @Test
    public void singleSpartanTest(){
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/24")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().body("id", is(24), "name", is("Julio"), "gender", is("Male"), "phone", is(9393139934L))
                .log().all();
    }

    @Test
    public void getMapTest(){
        /**
         * In below example:
         * send get request
         * verify status code and header
         * then convert json body to Map object and return
         * De-serialization
         */

       Map<String, Object> spartanMap = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json").log().all()
                .and().extract().body().as(Map.class);  //convert json body to Map object and return
        System.out.println("spartanMap = " +spartanMap);
        //read id value from map then assert it is 24
        assertThat(spartanMap.get("id"), equalTo(24));

        //check the keys of json response
        assertThat(spartanMap.keySet(),containsInAnyOrder("id", "name","gender","phone"));

        //check all values of json
        assertThat(spartanMap.values(), hasItems(24, "Julio", "Male", 9393139934L ));

    }

    /**
     * given accept type is Json
     * get request to /api/spartans
     * the status code is 200
     * and content type is jason
     * then extract names of spartans into a List<String>
     */
    @Test
    public void getSpartanTest(){
      List<String> names =  given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().jsonPath().getList("name");
        //.and().extract().body().path("name");

        System.out.println("name = " + names);
        assertThat(names, hasSize(115));
        assertThat(names, hasItems("Julio", "Lorenza", "Nona"));

    }
    /**
     * given accept type is Json
     * name contains "v"
     * gender is "Male
     *  get request to /api/spartans/search
     *  the status code is 200
     *  and content type is json
     *  and return totalElement value as an int
     *  int totalElement = given ..
     *
     */
    @Test
    public void getTotalElementTest(){
   int totalElement =  given().accept(ContentType.JSON)
            .and().queryParam("nameContains", "v")
            .and().queryParam("gender", "Male")
            .when().get("/api/spartans/search")
            .then().assertThat().statusCode(200)
            .and().contentType(ContentType.JSON).log().all()
            .and().extract().path("totalElement");
   //.and().extract().body().jsonPath().getInt("totalElement"); --> the same thing as the line above 

        System.out.println("totalElement = " + totalElement);
        assertThat(totalElement, is(equalTo(4)));
    }

    /**
     * get /api/spartans/2400
     * in singe statement, verify status is "not found"
     *
     */
    @Test
    public void invalidSpartanTest(){
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/2400")
                .then().assertThat().statusCode(404)
                .and().assertThat(). body("error", equalTo("Not Found"));

    }


}
