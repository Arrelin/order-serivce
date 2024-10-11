package com.arrelin.order_serivce;

import org.springframework.boot.SpringApplication;
import com.arrelin.order.OrderApplication;

public class TestOrderApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
