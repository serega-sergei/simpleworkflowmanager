package com.ssiryk.workflow.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;

/**
 * Just a basic integration test.
 * TODO: Move constants to corresponding files, move base url to application.properties, ...
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkflowControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    private static String payload = "{\n" +
            "    \"name\": \"X\",\n" +
            "    \"jobs\": {\n" +
            "        \"A\": null,\n" +
            "        \"B\": [\n" +
            "            {\n" +
            "                \"name\": \"A\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"C\": [\n" +
            "            {\n" +
            "                \"name\": \"A\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"B\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    private static String response = "{\n" +
            "    \"workflowName\": \"X\",\n" +
            "    \"jobs\": [\n" +
            "        {\n" +
            "            \"name\": \"A\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"B\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"C\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void execute() {
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("simpleworkflowmanager/api/workflow/execute")
                .then()
                .statusCode(200)
                .extract()
                .response().equals(response);
    }
}