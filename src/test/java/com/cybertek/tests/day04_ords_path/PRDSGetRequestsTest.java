package com.cybertek.tests.day04_ords_path;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class PRDSGetRequestsTest {

    @BeforeAll
    public static void setUp(){
    baseURI = ConfigurationReader.getProperty("ords.url");

    }

    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */
    @Test
    public void getAllRegionsTest(){



    }

}
