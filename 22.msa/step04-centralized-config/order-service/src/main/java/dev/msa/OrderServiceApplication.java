package dev.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// Eureka Server 내 레지스트리에 하나의 클라이언트로 등록되도록 적용
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
    @LoadBalanced // Spring Cloud LoadBalancer 모듈에서 제공하는 옵션
    // 서비스 이름으로 다른 마이크로서비스를 호출할 수 있게 적용하는 역할
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
