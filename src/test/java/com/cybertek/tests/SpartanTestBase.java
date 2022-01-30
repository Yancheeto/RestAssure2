package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanTestBase {

    @BeforeAll  //runs ones before all tests
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.url");

    }

}
