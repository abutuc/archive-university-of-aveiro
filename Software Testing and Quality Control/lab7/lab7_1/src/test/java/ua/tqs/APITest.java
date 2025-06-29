package ua.tqs;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class APITest {

    @Test
    void whenGetUrl_thenCheckStatus(){
        given().when().get("https://jsonplaceholder.typicode.com/todos")
                .then().statusCode(200);
    }

    @Test
    void whenSpecificTodo_thenCheckTitle(){
        given().when().get("https://jsonplaceholder.typicode.com/todos/4")
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("title", equalTo("et porro tempora"));
    }

    @Test
    void whenListAllTodos_thenCheckIfSpecificTodosArePresent(){
        given().when().get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(200)
                .body("id", hasItems(198, 199));
    }

    @Test
    void whenListAllTodos_thenCheckSpeed(){
        given().when().get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .time(lessThan(2000L));
    }




}