package com.cybertek.tests.day05_jsonPath;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import com.cybertek.tests.ORDSTestBase;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ORDSGetEmployeesPathTest extends ORDSTestBase {

    @Test
    public void getAllITProgrammersTest() {

    //query parameter with HashMap
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("q", "{\"job_id\":\"IT_PROG\"}");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramMap)
                .when().get("/employees");

        System.out.println("Status code = " + response.statusCode());
        response.prettyPrint();

        //print first employees emp id, first name, email

        System.out.println("first emp id = " + response.path("items[0].employee_id"));     //items is the name of our file
        System.out.println("first emp first name = " +response.path("items[0].first_name"));
        System.out.println("first emp last name = " +response.path("items[0].last_name"));
        System.out.println("first emp email = " +response.path("items[0].email"));



    }


}
