package com.qingqing.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
		exclude = {
				RabbitAutoConfiguration.class,
				DataSourceAutoConfiguration.class,
				RabbitAutoConfiguration.class,
		},
		scanBasePackages = {
				"com.qingqing.test"
		}
)
@EnableFeignClients
@EnableCircuitBreaker
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE - 1)
@EnableScheduling
public class TestApiApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(TestApiApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8090);
		container.setContextPath("/apiTest");
	}
}
