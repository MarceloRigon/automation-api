package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductTest extends BaseTest {

    @Test
    public void buscarProdutosComAutenticacao() {
        try {
            given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(200)
                    .body("products", not(empty()))
                    .body("total", greaterThan(0))
                    .log().all();
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao buscar produtos com autenticação: " + e.getMessage());
        }
    }

    @Test
    public void criarProduto() {
        String novoProdutoJson = "{ \"title\": \"Novo Produto\", \"price\": 99.99, \"category\": \"eletrônicos\" }";
        try {
            given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(novoProdutoJson)
                    .when()
                    .post("/products/add")
                    .then()
                    .statusCode(201)
                    .body("title", equalTo("Novo Produto"))
                    .body("price", equalTo(99.99F))
                    .body("category", equalTo("eletrônicos"))
                    .body("id", notNullValue())
                    .log().all();
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao criar produto: " + e.getMessage());
        }
    }

    @Test
    public void buscarTodosOsProdutos() {
        try {
            given()
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(200)
                    .body("products", not(empty()))
                    .body("total", greaterThan(0))
                    .log().all();
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao buscar todos os produtos: " + e.getMessage());
        }
    }

    @Test
    public void buscarProdutoPorId() {
        try {
            given()
                    .when()
                    .get("/products/1")
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(1))
                    .log().all();
        } catch (AssertionError e) {
            throw new ApiTestException("Erro ao buscar produto por ID: " + e.getMessage());
        }
    }
}
