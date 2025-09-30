package dev.rest.config;

import dev.rest.model.Product;
import dev.rest.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// 더미데이터 초기화용 클래스
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            ProductRepository productRepository
            // TODO: UserRepository는 인증기능 구현 후 추가 예정
    ) {
        return args -> {
            List<Product> products = List.of(
                    new Product("맥북 프로 16인치", "고성능 M칩을 탑재한 전문가용 노트북", 3500000, 5, "전자제품"),
                    new Product("에어팟 프로", "액티브 노이즈 캔슬링이 탑재된 무선 이어폰", 300000, 10, "전자제품"),
                    new Product("애플워치 시리즈 9", "헬스케어 기능이 강화된 최신 스마트워치", 500000, 2, "웨어러블"),
                    new Product("아이패드 에어", "얇고 가벼운 디자인의 고성능 태블릿", 800000, 0, "태블릿"),
                    new Product("갤럭시 버즈2", "선명한 사운드를 자랑하는 무선 이어폰", 150000, 8, "전자제품"),
                    new Product("갤럭시 워치6", "삼성의 최신 스마트워치", 400000, 4, "웨어러블"),
                    new Product("LG 울트라 노트북", "가벼운 무게와 고성능의 조화", 1200000, 7, "전자제품"),
                    new Product("닌텐도 스위치", "휴대용 게임기로 다양한 게임 플레이", 360000, 12, "게임기"),
                    new Product("PS5 디스크 버전", "차세대 콘솔 게임기", 680000, 3, "게임기"),
                    new Product("아이폰 15", "최신 A17 칩셋 탑재 스마트폰", 1450000, 9, "스마트폰"),
                    new Product("갤럭시 S24", "삼성 플래그십 스마트폰", 1390000, 6, "스마트폰"),
                    new Product("샤오미 미 밴드 8", "저렴하고 기능 많은 스마트밴드", 55000, 20, "웨어러블")
            );

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                productRepository.save(product); // DB에 저장
            }
        };
    }
}
