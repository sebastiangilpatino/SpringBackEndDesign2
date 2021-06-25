import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import mvc.domain.Account;
import mvc.domain.Transacctions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class BankRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetAccount() {

        given()
                .when().post("/bank?accountNumber=667&accountHolder='Joe Smith'").then()
                .statusCode(200);
        // test getting the contact
        given()
                .when()
                .get("/bank/667")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("accountNumber", equalTo(667))
                .body("accountHolder", equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("/bank/667");
    }

    @Test
    public void testCreateAccount() {

        given()
                .when().post("/bank?accountNumber=667&accountHolder='Joe Smith'").then()
                .statusCode(200);

        // get the contact and verify
        given()
                .when()
                .get("bank/667")
                .then()
                .statusCode(200)
                .and()
                .body("accountNumber", equalTo(667))
                .body("accountHolder", equalTo("Joe Smith"))
                .body("balance",equalTo(0.0f));

        //cleanup
        given()
                .when()
                .delete("bank/667");
    }

    @Test
    public void testDeposit() {
        // creat the account to deposit to
        given()
                .when().post("/bank?accountNumber=667&accountHolder='Joe Smith'").then()
                .statusCode(200);
        //deposit to this account
        given()
                .when().post("/bank?accountNumber=667&operation='deposit'&amount=122.25").then()
                .statusCode(200);
        //verify balance
        given()
                .when()
                .get("bank/667")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("accountNumber",equalTo(667))
                .body("accountHolder",equalTo("Joe Smith"))
                .body("balance",equalTo(122.25f));
        //cleanup
        given()
                .when()
                .delete("bank/667");

    }

    @Test
    public void testWithDraw() {
        // creat the account to deposit to
        given()
                .when().post("/bank?accountNumber=667&accountHolder='Joe Smith'").then()
                .statusCode(200);
        //deposit to this account
        given()
                .when().post("/bank?accountNumber=667&operation='withdraw'&amount=122.25").then()
                .statusCode(200);
        //verify balance
        given()
                .when()
                .get("bank/667")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("accountNumber",equalTo(667))
                .body("accountHolder",equalTo("Joe Smith"))
                .body("balance",equalTo(-122.25f));
        //cleanup
        given()
                .when()
                .delete("bank/667");
    }
    @Test
    public void testDeleteAccount() {
        // creat the account to deposit to
        given()
                .when().post("/bank?accountNumber=667&accountHolder='Joe Smith'").then()
                .statusCode(200);

        given()
                .when()
                .delete("bank/667");

        given()
                .when()
                .get("bank/667")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage",equalTo("AccountNumber # 667 is not available"));
    }
}
