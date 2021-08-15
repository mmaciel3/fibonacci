package com.task.fibonacci.api;

import com.task.fibonacci.FibonacciApplication;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
		classes = FibonacciApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DisplayName("Fibonacci API")
public class FibonacciApiTest {
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup() {
		RestAssured.port = port;
		RestAssured.requestSpecification = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.build();
	}

	@Nested
	@DisplayName("When N is missing")
	class whenNisMissing {
		@Test
		@DisplayName("Should return HTTP 400 with Missing Parameter message")
		public void shouldReturn400() {
			given().get("/fib")
					.then()
					.assertThat()
					.statusCode(400)
					.body("message", equalTo("parameter n is required"));
		}
	}

	@Nested
	@DisplayName("When N is blank")
	class whenNisBlank {
		@Test
		@DisplayName("Should return HTTP 400 with Missing Parameter message")
		public void shouldReturn400() {
			given().get("/fib?n=")
					.then()
					.assertThat()
					.statusCode(400)
					.body("message", equalTo("n must be a non-negative integer"));
		}
	}

	@Nested
	@DisplayName("When N is not a number")
	class whenNisNotANumber {
		@Test
		@DisplayName("Should return HTTP 400 with Invalid Number message")
		public void shouldReturn400() {
			given().get("/fib?n=abc")
					.then()
					.assertThat()
					.statusCode(400)
					.body("message", equalTo("n must be a non-negative integer"));
		}
	}

	@Nested
	@DisplayName("When N is a negative number")
	class whenNisNegative {
		@Test
		@DisplayName("Should return HTTP 400 with Invalid Number message")
		public void shouldReturn400() {
			given().get("/fib?n=-1")
					.then()
					.assertThat()
					.statusCode(400)
					.body("message", equalTo("n must be a non-negative integer"));
		}
	}

	@Nested
	@DisplayName("When N is a non-negative number")
	class whenNisNonNegative {

		@Nested
		@DisplayName("When N is 0")
		class when0 {
			@Test
			@DisplayName("Should return HTTP 200 and result 0")
			public void shouldReturn200AndResult() {
				given().get("/fib?n=0")
						.then()
						.assertThat()
						.statusCode(200)
						.body("result", equalTo(0));
			}
		}

		@Nested
		@DisplayName("When N is 1")
		class when1 {
			@Test
			@DisplayName("Should return HTTP 200 and result 1")
			public void shouldReturn200AndResult() {
				given().get("/fib?n=1")
						.then()
						.assertThat()
						.statusCode(200)
						.body("result", equalTo(1));
			}
		}

		@Nested
		@DisplayName("When N is 2")
		class when2 {
			@Test
			@DisplayName("Should return HTTP 200 and result 1")
			public void shouldReturn200AndResult() {
				given().get("/fib?n=2")
						.then()
						.assertThat()
						.statusCode(200)
						.body("result", equalTo(1));
			}
		}

		@Nested
		@DisplayName("When N is 10")
		class when10 {
			@Test
			@DisplayName("Should return HTTP 200 and result 55")
			public void shouldReturn200AndResult() {
				given().get("/fib?n=10")
						.then()
						.assertThat()
						.statusCode(200)
						.body("result", equalTo(55));
			}
		}

		@Nested
		@DisplayName("When N is 72")
		class when72 {
			@Test
			@DisplayName("Should return HTTP 200 and result 498454011879264")
			public void shouldReturn200AndResult() {
				given().get("/fib?n=72")
						.then()
						.assertThat()
						.statusCode(200)
						.body("result", equalTo(498454011879264L));
			}
		}

		@Nested
		@DisplayName("When N is 1000")
		class when1000 {
			@Test
			@DisplayName("Should return HTTP 200 and result 43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875")
			public void shouldReturn200AndResult() {
				BigInteger result = given().get("/fib?n=1000")
						.then()
						.assertThat()
						.statusCode(200)
						.extract()
						.path("result");

				BigInteger expected = new BigInteger(
						"43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875");

				assertThat(expected.compareTo(result), equalTo(0));
			}
		}

		@Nested
		@DisplayName("When N is 2000")
		class when2000 {
			@Test
			@DisplayName("Should return HTTP 200 and result 4224696333392304878706725602341482782579852840250681098010280137314308584370130707224123599639141511088446087538909603607640194711643596029271983312598737326253555802606991585915229492453904998722256795316982874482472992263901833716778060607011615497886719879858311468870876264597369086722884023654422295243347964480139515349562972087652656069529806499841977448720155612802665404554171717881930324025204312082516817125")
			public void shouldReturn200AndResult() {
				BigInteger result = given().get("/fib?n=2000")
						.then()
						.assertThat()
						.statusCode(200)
						.extract()
						.path("result");

				BigInteger expected = new BigInteger(
						"4224696333392304878706725602341482782579852840250681098010280137314308584370130707224123599639141511088446087538909603607640194711643596029271983312598737326253555802606991585915229492453904998722256795316982874482472992263901833716778060607011615497886719879858311468870876264597369086722884023654422295243347964480139515349562972087652656069529806499841977448720155612802665404554171717881930324025204312082516817125");

				assertThat(expected, comparesEqualTo(result));
			}
		}
	}
}