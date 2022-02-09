package com.cybertek.tests.day10_post_put_delete;
import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ORDSPostPutDeleteRegionTest extends ORDSTestBase {
    /**
     * given accepts Json
     * and content type is json
     * when I send post request to "/regions"
     * with json:
     * {
     *     "region_id": 100,
     *     "region_name": "Test Region"
     * }
     * then status code is 201
     * content type is json
     * region_id is 100
     * region_name is "Test Region"
     */
    @Test
    public void postARegionTest(){
        int regionId = 122;

        //delete region by Id before posting
        deleteRegion(regionId);

        Map<String, Object> regionRequestMap = new LinkedHashMap<>();
        regionRequestMap.put("region_id", regionId);
        regionRequestMap.put("region_name", "Test Region");
        
       Map<String, Object> regionResponseMap = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
               .and().body(regionRequestMap)
                .when().post("/regions/")
                .then().assertThat().statusCode(201)    //verify 201 created status code
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Map.class);  //convert json response to Map

        //compare request map values match the response map values
        System.out.println("regionResponseMap = " + regionResponseMap);

        //assertions
        assertEquals(regionRequestMap.get("region_id"), regionResponseMap.get("region_id"));
        assertEquals(regionRequestMap.get("region_name"), regionResponseMap.get("region_name"));


//send a get request with region_id and verify it matches the post request map data
        Map<String, Object> getRequestMap = given().accept(ContentType.JSON)
                .when().get("/regions/"+regionId)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().extract().body().as(Map.class);

//verify getRequestMap details match regionRequestMap of post
        //verify getRequestMap details match regionRequestMap of post
        System.out.println("getRequestMap = " + getRequestMap);
        assertEquals(regionRequestMap.get("region_id"), getRequestMap.get("region_id"));
        assertEquals(regionRequestMap.get("region_name"), getRequestMap.get("region_name"));



    }
    
    public static void deleteRegion(int regionId){

        delete("/regions/" + regionId);

    }
    
}
