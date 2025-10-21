package dev.msa.service;

import dev.msa.domain.Order;
import dev.msa.domain.OrderStatus;
import dev.msa.dto.OrderRequest;
import dev.msa.dto.PaymentRequest;
import dev.msa.dto.PaymentResponse;
import dev.msa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Value("${service.payment.url}")
    private String paymentServiceUrl;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = Order.builder()
                .itemName(request.itemName())
                .quantity(request.quantity())
                .price(request.price())
                .status(OrderStatus.PENDING)
                .build();

        order = orderRepository.save(order);

        // 결제 서비스 호출
        try {
            PaymentRequest paymentRequest = new PaymentRequest(
                    order.getId(),
                    order.getPrice() * order.getQuantity()
            );

            PaymentResponse paymentResponse = restTemplate.postForObject(
                    paymentServiceUrl,
                    paymentRequest,
                    PaymentResponse.class
            );

            if (paymentResponse != null && "SUCCESS".equals(paymentResponse.status())) {
                order.setStatus(OrderStatus.COMPLETED);
            } else {
                order.setStatus(OrderStatus.FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 결제 서비스 장애 시 주문은 생성되지만 PENDING 상태 유지
            // 실제 운영에서는 재시도 로직이나 이벤트 기반 처리 필요
        }

        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
