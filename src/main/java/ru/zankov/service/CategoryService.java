package ru.zankov.service;

import ru.zankov.model.Category;
import ru.zankov.model.CategoryReq;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static ru.zankov.EndpointUrl.CATEGORIES;

public class CategoryService {

        public Category create(String name, String type, String token) {
            CategoryReq req = new CategoryReq(name, type);
            return given().body(req).auth().oauth2(token).
                    when().post(CATEGORIES).
                    then().statusCode(201).extract().body().as(Category.class);
        }

        public boolean isCategoryExists(String name, String token) {
            Category[] cats = given().auth().oauth2(token).get(CATEGORIES).as(Category[].class);
            return Stream.of(cats).anyMatch(c -> c.getName().equals(name));
        }
}
