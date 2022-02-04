package com.cybertek.tests.day06_deserialization;

import com.cybertek.tests.pojo.Spartan;
import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.SpartanSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanJsonToPojoTest extends SpartanTestBase {

    @Test
    public void spartanToPojoTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/24");

        Spartan spartan = response.as(Spartan.class);

        System.out.println(spartan.getId());
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getPhone());
    }


    @Test
    public void searchSpartansToPojoTest(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "ai")
                .and().queryParams("gender", "Female")
                .when().get("/api/spartans/search");

        System.out.println("status code = " + response.statusCode());
        //De-serialization. json body -->SpartanSearch java object
        SpartanSearch spartanSearch = response.as(SpartanSearch.class);
        System.out.println("total element = " + spartanSearch.getTotalElement() );
        System.out.println("each spartan information " + spartanSearch.getContent());
        System.out.println("spartan count = " + spartanSearch.getContent().size());

    }

}
