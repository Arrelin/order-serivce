package com.arrelin.order_serivce;

import com.arrelin.order.model.Order;
import com.arrelin.order.repository.OrderRepository;
import com.arrelin.order_serivce.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(classes = com.arrelin.order.OrderApplication.class
		, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
class OrderApplicationTests {

	@LocalServerPort
	private Integer port;

	@MockBean
	private OrderRepository orderRepository;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;

		Mockito.when(orderRepository.save(Mockito.any())).thenReturn(new Order());
	}

	@Test
	void shouldCreateOrder() {
		String orderRequest = """
                {
                    "skuCode": "Iph133264",
                    "price": 1000.00,
                    "quantity": 2
                }""";

		//utilizing useless mockito tests
		InventoryClientStub.stubInventoryCall("Iph133264", 2);

		var response = RestAssured.given()
				.contentType("application/json")
				.body(orderRequest)
				.when()
				.post("/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		assertThat(response, Matchers.is("Order placed successfully"));
	}
}
