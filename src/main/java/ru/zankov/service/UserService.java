package ru.zankov.service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import ru.zankov.model.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static ru.zankov.EndPoints.AUTH;
import static ru.zankov.EndPoints.USERS;

public class UserService {

    {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().log(ALL).build();
    }

    public User signUp(String username, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        return given().body(body).when().post(USERS).then().statusCode(201).extract().body().as(User.class);
    }

    public User logIn(String username, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        return given().body(body).when().post(AUTH).then().statusCode(200).extract().body().as(User.class);
    }
}
