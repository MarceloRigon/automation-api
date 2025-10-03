package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static String BASE_URL = "https://dummyjson.com";
    protected static String token;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
