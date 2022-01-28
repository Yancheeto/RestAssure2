package com.cybertek.tests.day01_intro_to_api;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReqResGetUsersTest {
    /**
     *Send get request to API Endpoint:
     *     "https://reqres.in/api/users"
     * Response status code should be 200
     * Response body should contain user info "George"
     *
     */

    @Test
    public void getAllUsersApiTest(){
      Response response = RestAssured.get(ConfigurationReader.getProperty("regres.users.api.url"));
        System.out.println("Status code = " + response.statusCode());       //will show 200 which is the status code
        // * Response status code should be 200
        Assertions.assertEquals(200, response.statusCode());

        response.prettyPrint();
        System.out.println("Response body = " + response.body().asString()); //the same as response.prettyPrint();

        //* Response body should contain user info "George"
        Assertions.assertTrue(response.asString().contains("George"));


    }


    @Test
    public void getSingleUserApiTest(){
        /**
         * Send get request to API Endpoint:
         * "https://reqres.in/api/users/5"
         * Response status code should be 200
         * Response body should contain user info "Charles"
         *
         */

        Response response = RestAssured.get(ConfigurationReader.getProperty("regres.users.api.url") +"/5");
        int statusCode = response.statusCode();
        System.out.println("StatusCode = " + statusCode);

        Assertions.assertEquals(200, statusCode);


        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("Charles"));

    }


   /** Send get request to API Endpoint:
            "https://reqres.in/api/users/50"
    Response status code should be 404
    Response body should contain "{}"
*/
    @Test
    public void getSingleUserNegativeApiTest() {

        Response response = RestAssured.get(ConfigurationReader.getProperty("regres.users.api.url") +"/50");

        System.out.println("Status Code = " + response.statusCode());
        Assertions.assertEquals(404, response.statusCode());
        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("{\n" +
                "    \n" +
                "}"));
    }






}
