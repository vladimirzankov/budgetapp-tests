package ru.zankov.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class BaseTest {

    @BeforeAll
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().log(ALL).build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
