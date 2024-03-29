package br.ce.wcaquino.rest.tests.refac.suite;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.rest.core.BaseTest;
import br.ce.wcaquino.rest.tests.refac.AuthTest;
import br.ce.wcaquino.rest.tests.refac.ConatasTest;
import br.ce.wcaquino.rest.tests.refac.MovimentacaoTest;
import br.ce.wcaquino.rest.tests.refac.SaldoTest;
import io.restassured.RestAssured;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	ConatasTest.class,
	MovimentacaoTest.class,
	SaldoTest.class,
    AuthTest.class
})
public class Suite extends BaseTest {
	
	@BeforeClass
	public static void login() {
		Map<String, String> login = new HashMap<>();
		login.put("email", "diogenes.jj@hotmail.com");
		login.put("senha", "dIOGENES@1989");
		
		
		String TOKEN = given()
		     .body(login)
		.when()
		     .post("/signin")
		.then()
		     .statusCode(200)
		     .extract().path("token");
		     
		     
		RestAssured.requestSpecification .header("Authorization", "JWT " + TOKEN);
		     
		RestAssured.get("/reset").then().statusCode(200);
	}

}
