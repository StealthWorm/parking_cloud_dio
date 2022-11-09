package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/*
   usar o "RANDOM_PORT" é uma boa prática, pois nao interfere na porta utilizada no build, muito importante para testar aplicações que envolvem Integração Continua
*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setupTest() {
        // rest-assured é uma biblioteca para testar API restful programaticamente
        // se nao fizer essa config. ele não acha a aplicação
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("user", "dio@12345678")
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().body().prettyPrint();
//                .body("license[0]", Matchers.equalTo("WRT-5555"));
    }

    @Test
    void whenFindOneByIdThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("user", "dio@12345678")
                .when()
                .pathParam("id", "991e8dbaabcb4a01ac5c104a47ea1eec")
                .when()
                .get("/parking/{id}")
                .then()
//                .statusCode(HttpStatus.OK.value())
                .extract().response().body().prettyPrint();
//                .body("license[0]", Matchers.equalTo("WRT-5555"));
    }

    @Test
    void whenCreateThenCheckCreated() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("RS");

        RestAssured.given()
                .auth()
                .basic("user", "dio@12345678")
                .when()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("WRT-5555"))
                .body("color", Matchers.equalTo("AMARELO"));
    }
}