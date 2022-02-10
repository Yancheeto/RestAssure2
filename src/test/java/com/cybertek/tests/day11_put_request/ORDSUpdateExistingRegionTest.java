package com.cybertek.tests.day11_put_request;
import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.Region;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ORDSUpdateExistingRegionTest extends ORDSTestBase {

    /**
     * /**
     * * Given accept type is Json
     * * And content type is json
     * * When i send put request to /regions/122
     * * With json body:
     * *        {
     * *         "region_id": 122,
     * *         "region_name": "Wooden Region"
     * *    }
     * * Then status code is 200
     * * And content type is json
     * * And json response should contain updated values
     */

    @Test
    public void updateExistingRegionTest() {
        //target region id to be updated
        int regionId = 122;

        //create pojo instance for put request
        Region reqRegion = new Region();
        reqRegion.setRegionId(regionId);
        reqRegion.setRegionName("Wooden Region");

      Region resRegion =  given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(reqRegion)          //serialization - Java object to Json
                .when().put("/regions/" + regionId)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Region.class);

      //verify json response should contain updated values
        assertThat(reqRegion.getRegionId(), is(equalTo(resRegion.getRegionId())));
        assertThat(reqRegion.getRegionName(), is(equalTo(resRegion.getRegionName())));

    }
}
