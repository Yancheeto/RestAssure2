package com.cybertek.tests.day06_deserialization;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import java.util.HashMap;
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

        System.out.println("id = " + spartanMap.get("id"));
        assertEquals(24, spartanMap.get("id"));
        System.out.println("name = "+ spartanMap.get("name"));
        assertEquals("Julio", spartanMap.get("name"));
        System.out.println("gender = "+ spartanMap.get("gender"));
        assertEquals("Male", spartanMap.get("gender"));
        System.out.println("phone = " +spartanMap.get("phone"));
        assertEquals(9393139934L, spartanMap.get("phone"));

        //create new map with expected values and compare 2 maps
        Map<String, Object> expected = new HashMap<>();
        expected.put("id", 24);
        expected.put("name", "Julio");
        expected.put("gender", "Male");
        expected.put("phone", 9393139934L);

            assertEquals(expected, spartanMap);
    }


}
