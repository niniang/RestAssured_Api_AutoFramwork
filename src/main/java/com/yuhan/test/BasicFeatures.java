package com.yuhan.test;


import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicFeatures {

    /**
     * 检查响应状态码
     */

    @Test
    public void testStatusCode(){

        //given().get("https://www.baidu.com").then().statusCode(200).log().all();
        given().
                get("http://jsonplaceholder.typicode.com/posts/1").
                then().
                    body("id",equalTo(1),"title",equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));

    }

    @Test
    public void testParameters(){
        Map<String,String> parameters = new HashMap<String, String>();
        parameters.put("userId","2");
        parameters.put("id","14");

        given().params(parameters).when().get("http://jsonplaceholder.typicode.com/posts").then().statusCode(200).log().all();
    }

    @Test
    public void testHeaders(){
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("accept-encoding","gzip,deflate");
        headers.put("accept-language", "zh-CN");

        given().headers(headers).when().log().all().get("http://jsonplaceholder.typicode.com/posts").then().statusCode(200).log().all();
    }

    @Test
    public void testSingleXMLContents() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
                then().
                body("CUSTOMER.ID", equalTo("10")).
                body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
                body("CUSTOMER.LASTNAME", equalTo("Fuller")).
                body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
                body("CUSTOMER.CITY", equalTo("Dallas")).
                log().all();
    }

    @Test
    public void findValueByXMLXpath(){
        given().get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
                then().body(hasXPath("/CUSTOMER/FIRSTNAME"),containsString("Sue"));
    }

    @Test
    public void findValueByXMLXpath2() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/INVOICE/").
                then().
                body(hasXPath("/INVOICEList/INVOICE[text()='20']")).
                log().all();
    }

    @Test
    public void testPost(){
        given().
                param("name","yuhan").
                param("job","tester").
                header("Content-Type","text/html").
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).log().all();
    }

    @Test
    public void getResponseAsString(){
        String responseAsString = get("https://reqres.in/api/users/2").asString();
        System.out.println(responseAsString);
    }
}
