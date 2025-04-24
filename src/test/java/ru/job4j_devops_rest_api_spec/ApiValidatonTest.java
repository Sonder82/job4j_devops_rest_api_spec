package ru.job4j_devops_rest_api_spec;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class ApiValidatonTest {

    private static final OpenApiValidationFilter validationFilter =
            new OpenApiValidationFilter(
                    ApiValidatonTest.class.getClassLoader().getResource("swagger.yaml").toString()
            );

    @Test
    public void testGetAllResultEndpoint() {
        RestAssured.given()
                .filter(validationFilter) // Применяем фильтр для проверки соответствия спецификации
                .when()
                .get("http://localhost:8080/calc/")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testSummariseEndpoint() {
        RestAssured.given()
                .filter(validationFilter)
                .contentType("application/json")
                .body("{\"first\": 5, \"second\": 7}")
                .when()
                .post("http://localhost:8080/calc/summarise")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
