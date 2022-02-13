package com.cybertek.tests.day13_bookit;

import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParameterizedDemoTest {

    public static List<String> getFriendNames(){
        List<String>friendNames= Arrays.asList("Kalys", "Tach", "Dzhamilya", "Naserahmad", "Fahima", "Jama", "Meer");
        return friendNames;
    }

        @ParameterizedTest
        @MethodSource("getFriendNames")
        public void friendNamesTest(String name){
            System.out.println("name = " + name);

        }

    /**
     * Read all credetioals from BookIQA3.xlsx file
     * and return as list of Maps
     * Map will have 2 keys, email and password
     */
    public static List<Map<String, String>>getAllUserCredentials(){
        //open excel file and worksheet QA3
        ExcelUtil excelUtil = new ExcelUtil("BookItQa3.xlsx", "QA3");
        //returns excel data as list of maps
       return excelUtil.getDataList();

    }

    @ParameterizedTest
    @MethodSource("getAllUserCredentials")
    public void bookItUsersTest(Map<String, String> credential){
        System.out.println("Credentials = " + credential);
    }

    @ParameterizedTest
    //csv - coma separated values
    @CsvSource({
            "1, 4",
            "10, 3",
            "5, 9",
            "44, 33"
    })
    public void addNumbers(int num1, int num2){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " +(num1+num2));


    }

}
