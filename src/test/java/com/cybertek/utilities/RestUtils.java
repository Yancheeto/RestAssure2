package com.cybertek.utilities;
import static io.restassured.RestAssured.*;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;

public class RestUtils {

     //accepts spartan ID and returns Pojo object containing spartan info

    public static Spartan getSpartan(int spartanId){
       Spartan spartan=  given().accept(ContentType.JSON)
                .and().pathParams("id", spartanId)
                .when().get(ConfigurationReader.getProperty("spartan.url")+"/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().extract().body().as(Spartan.class);

       return spartan;
    }

}
