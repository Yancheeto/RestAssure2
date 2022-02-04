package com.cybertek.tests.day07_deserialization_hamcrest;

import com.cybertek.tests.SpartanTestBase;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cybertek.tests.pojo.AllSpartans;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllSpartansToPojoTest extends SpartanTestBase {
    @Test
    public void getAllSpartansTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        System.out.println("status code = " +response.statusCode());

       // AllSpartans allSpartans1 = response.as(AllSpartans.class);
        List<Spartan> allSpartans = response.jsonPath().getList("", Spartan.class);
        System.out.println(allSpartans.get(0));
        System.out.println("total spartans count = " + allSpartans.size());

        System.out.println("-----------------------------------------------");
        //loop through the list and print each spartan info in separate line


        for(Spartan sp: allSpartans){
            if(sp.getGender().equals("Male")){
                System.out.println(sp.toString());
            }
        }
    }



}
