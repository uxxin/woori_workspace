package dev.msa.service;

import dev.msa.domain.Order;
import dev.msa.domain.OrderStatus;
import dev.msa.domain.Payment;
import dev.msa.domain.PaymentStatus;
import dev.msa.dto.OrderRequest;
import dev.msa.repository.OrderRepository;
import dev.msa.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final Random random = new Random();

    @Value("${simulation.payment.success-rate:100}")
    private int paymentSuccessRate;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = Order.builder()
                .itemName(request.itemName())
                .quantity(request.quantity())
                .price(request.price())
                .status(OrderStatus.PENDING)
                .build();

        order = orderRepository.save(order);

        // 결제 서비스 장애 시뮬레이션
        PaymentStatus paymentStatus = simulatePaymentFailure();

        Payment payment = Payment.builder()
                .orderId(order.getId())
                .amount(request.price() * request.quantity())
                .status(paymentStatus)
                .paidAt(paymentStatus == PaymentStatus.SUCCESS ? LocalDateTime.now() : null)
                .build();

        paymentRepository.save(payment);

        // 결제 실패 시 주문도 실패 처리
        OrderStatus orderStatus = paymentStatus == PaymentStatus.SUCCESS ? OrderStatus.COMPLETED : OrderStatus.FAILED;
        order.setStatus(orderStatus);

        return orderRepository.save(order);
    }

    /**
     * 결제 서비스 장애 시뮬레이션
     * 설정된 성공 확률에 따라 SUCCESS 또는 FAILED 반환
     */
    private PaymentStatus simulatePaymentFailure() {
        int randomValue = random.nextInt(100);
        return randomValue < paymentSuccessRate ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
