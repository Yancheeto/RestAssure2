package com.cybertek.tests.day11_put_request;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
import com.cybertek.utilities.RestUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateSpartanTest extends SpartanTestBase {
    /**
     * Given accept type is Json
     * And content type is json
     * When i send put request to /api/spartans/15
     * With json body:
     * {
     *   "gender": "Female",
     *   "name": "Changed",
     *   "phone": 1234567890
     * }
     * Then status code is 204
     * And content type is json
     * And json response should contain updated values
     */
@Test
    public void updateSpartanTest(){

    Map<String, Object> spartanMap = new LinkedHashMap<>();
    spartanMap.put("gender", "Female");
    spartanMap.put("name", "Changed");
    spartanMap.put("phone", 1234567890);

    given().contentType(ContentType.JSON).and().body(spartanMap).when().put("/api/spartans/15")
            .then().assertThat().statusCode(204);

}

@Test
    public void patchSpartanTest(){
    Map<String, Object>spartanMap = new LinkedHashMap<>();
    spartanMap.put("phone", 5555555555L);

    given().contentType(ContentType.JSON)
            .and().pathParams("id", 15)
            .and().body(spartanMap)
            .when().patch("/api/spartans/{id}")
            .then().assertThat().statusCode(204);

    //send get request with some spartan id and verify phone number update
    Spartan spartan = RestUtils.getSpartan(15);
    assertThat(spartan.getPhone(), is(spartanMap.get("phone")));
}

}
