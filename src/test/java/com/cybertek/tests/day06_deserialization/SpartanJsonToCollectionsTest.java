package com.cybertek.tests.day06_deserialization;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import java.util.Map;

public class SpartanJsonToCollectionsTest extends SpartanTestBase {
    /**
     * given accept type is json
     *      * when I send get request to /api/spartans/24
     *      * Then status code is 200
     *      * And content type is json
     *      * And id is 24, name is Julio, gender is Male, phone is 9393139934
     *
     */
    @Test
    public void singeSpartanToMapTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24");

//convert json response to Map Object key+value
        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " +spartanMap);


    }


}
