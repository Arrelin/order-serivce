package com.arrelin.order_serivce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class OrderApplicationTests {

	@Test
	void contextLoads() {
	}

}
