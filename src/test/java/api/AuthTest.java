package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest extends BaseTest {

    @Test
    public void buscarStatusDaAplicacao() {

        try {

            Response response = given()
                    .when()
                    .get("/test")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            String status = response.jsonPath().getString("status");
            assertEquals("ok", status, "O status da aplicação não é 'ok'!");

            String method = response.jsonPath().getString("method");
            assertEquals("GET", method, "O método da requisição não é 'GET'!");

            System.out.println("Status da aplicação: " + status);
            System.out.println("Método utilizado: " + method);
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao verificar o status da aplicação: " + e.getMessage());
        }
    }

    @Test
    public void buscarUsuarioParaAutenticacao() {

        try {
            given()
                    .when()
                    .get("/users/1") // Alterar conforme o ID que queira buscar
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(1))
                    .log().all();;
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao buscar usuário para autenticação: " + e.getMessage());
        }
    }

    @Test
    public void criarTokenAutenticacao() {

        try {
            String response =
                    given()
                            .contentType(ContentType.JSON)
                            .body("{ \"username\": \"kminchelle\", \"password\": \"0lelplR\" }")
                            .when()
                            .post("/auth/login")
                            .then()
                            .statusCode(200)
                            .body("token", notNullValue())
                            .extract()
                            .path("token");
            token = response;
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao criar token de autenticação: " + e.getMessage());
        }
    }
}

