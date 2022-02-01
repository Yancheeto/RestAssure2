package com.cybertek.tests.office_hours;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class PetStoreGetRequestsTest {

    @BeforeAll
    public static void setUp(){
    baseURI = "https://petstore.swagger.io/v2";
    }

    /**
     * accept type is json
     * get request to /store/inventory
     * then status code 200
     * and content type is json
     * and available is more than 500
     */
    @Test
    public void getInventoryTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/store/inventory");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode()); //the same as the one above

        System.out.println("Content type = " + response.contentType());
        System.out.println("Date = " + response.getHeader("Date"));

        assertEquals("application/json", response.contentType());
        //verify if the date is exicting
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

        JsonPath json = response.jsonPath();

        //print available count
        System.out.println("available = " + json.getInt("available"));

        //available is more than 500
        assertEquals(Boolean.TRUE, json.getInt("available")>500);

        assertTrue((json.getInt("available")>=500));
    }


    /**
     * accept type is json
     * order id is 2
     * get request to store/order/2
     * Then status code is 200
     * And content type is json
     * id is 2
     * pet id is 20
     * status is "placed"
     * complete is true
     * */

    @Test
    public void getOrderPathParamTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("orderId" , 2)
                .when().get("/store/order/{orderId}");
        //.when().get("/store/order/2");        --> same thing as the one above

        System.out.println("response type = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type = " + response.contentType());
        assertEquals("application/json", response.getContentType());

        JsonPath json = response.jsonPath();
        int orderID = json.getInt("id");
        int petId = json.getInt("petId");
        String status = json.getString("status");
        boolean complete=json.getBoolean("complete");

        assertEquals(2, orderID);
        assertEquals(20, petId);
        assertEquals("placed", status);
        assertEquals(Boolean.TRUE, complete);

    }
    /**
     * accept type is json
     * query param status is "available"
     * get request to /pet/findByStatus
     * Then status code is 200
     * And content type is json
     * And see all pet names
     * And all status values should be "available"
     * */
    @Test
        public void searchPetsByStatusTest() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("status", "available");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("status", "available")
                .and().when().get("/pet/findByStatus");
        //.and().queryParams(paramMap);

        System.out.println("status code " + response.statusCode());
        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        JsonPath json = response.jsonPath();

        List<String> allNames = json.getList("name");
        System.out.println("allNames = " + allNames);
        System.out.println("count = " + allNames.size());

        List <String> allStatus = json.getList("status");
        System.out.println("allStatus = " + allStatus);


        //verify all are available

        for(String eachStatus: allStatus){
            assertEquals("available", eachStatus);
        }
    //verify all are available with lamda expression
        allStatus.forEach(eachStatus->assertEquals("available", eachStatus));



}
}
