package com.cybertek.tests.day09_post_put;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.cybertek.tests.SpartanTestBase;
import io.restassured.response.Response.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SpartanPostRequestTest extends SpartanTestBase {
    /**
     * given accept is json
     * and content type is json
     * when I send POST request to /api/spartans
     * with {
     *   "gender": "Male",
     *   "name": "RestAssured Post",
     *   "phone": 2115552345
     * }
     * Then status code is 201
     * And "spartan is born" message should be displayed
     */

    @Test
    public void postSpartanTest(){
        String requestJson = "{\"gender\": \"Male\",\n" +
                "\"name\": \"RestAssuredPost\",\n" +
                "\"phone\": 2115552349}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestJson)
                .when().post("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertEquals(201, response.statusCode());
        //verify header
        assertThat(response.contentType(), is("application/json"));


        //verify json response body
        //and "A Spartan is Born!" message should be displayed
        System.out.println("message = " + response.path("success"));
        assertThat(response.path("success"), is(equalTo("A Spartan is Born!")));

    }

    @Test
    public void postSpartanWithMapTest(){
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name", "WoodenTester");
        requestMap.put("gender", "Female");
        requestMap.put("phone", 7986551422L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)         //serialization happening converting java object (map) to json
                .when().post("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        /**
         * Verify
         * status code is 201
         * content type is json
         * message is "A Spartan is Born!"
         * name is WoodenTester
         * gender is Female
         * Phone is 7986551422
         */

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        assertThat(response.path("success"), is(equalTo("A Spartan is Born!")));
        assertThat(response.path("data.name"), is(equalTo("WoodenTester")));
        assertThat(response.path("data.gender"), is(equalTo("Female")));
        assertThat(response.path("data.phone"), is(equalTo(7986551422L)));
        assertThat(response.path("data.id"), is(greaterThan(0)));

        System.out.println("________________same but with jsonPath___________________________________");
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        JsonPath json = response.jsonPath();
        assertThat(json.getString("success"), is("A Spartan is Born!"));
        assertThat(json.getString("data.name"), is("TestMapPost"));
        assertThat(json.getString("data.gender") , is("Female"));
        assertThat(json.getLong("data.phone") , is(3211231234L));
        assertThat(json.getInt("data.id"), greaterThan(0));

    }

    @Test
    public void postSpartanAndVerifyWithMapTest(){
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name", "WoodenSpoon");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 3455431244L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));

        Map<String, Object> responseMap = response.jsonPath().getMap("data");
        System.out.println("responseMap = " + responseMap);
        //compare response data is matching with request data
        assertThat(responseMap.get("name"), equalTo(requestMap.get("name")));
        assertThat(responseMap.get("gender"), equalTo(requestMap.get("gender")));
        assertThat(responseMap.get("phone") , equalTo(requestMap.get("phone")));
    }

    @Test
    public void postSpartanWithPojoTest() {
        //create object of spartan and set values
        Spartan reqSpartan = new Spartan();
        reqSpartan.setName("POJOpost");
        reqSpartan.setGender("Female");
        reqSpartan.setPhone(9877891234L);
        //serialization: java object => json
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(reqSpartan).log().all() //automatically convert spartan object to json
                .when().post("/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        //verify json body.
        //assign response spartan info into another Spartan object
        //then compare 2 spartan values
        //de-serialization = json => java object
        Spartan resSpartan = response.jsonPath().getObject("data",Spartan.class);

        //compare spartan values
        assertThat(resSpartan.getName(), equalTo(reqSpartan.getName()));
        assertThat(resSpartan.getGender(), equalTo(reqSpartan.getGender()));
        assertThat(resSpartan.getPhone() , equalTo(reqSpartan.getPhone()));
    }


}
