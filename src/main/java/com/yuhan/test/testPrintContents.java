package com.yuhan.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class testPrintContents {
    /**
     * 检查响应状态码,并打印日志到控制台
     */
    @Test
    public void testPrintContents(){
        given().
                get("http://192.168.2.60:10090/api/config/location/getAreaByStationId?param=1%E5%8F%B7%E4%B8%BB%E5%8F%98%E5%AE%A4&stationId=ebf4cee3857e4e148fb53a21aaf0f023\n").
                then().statusCode(401).log().all();
    }
}
