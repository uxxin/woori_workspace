package dev.msa.service;

import dev.msa.domain.Payment;
import dev.msa.domain.PaymentStatus;
import dev.msa.dto.PaymentRequest;
import dev.msa.dto.PaymentResponse;
import dev.msa.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final Random random = new Random();

    @Value("${simulation.payment.success-rate:100}")
    private int paymentSuccessRate;

    @Value("${server.port}")
    private String serverPort;  // 현재 실행 중인 포트 번호 주입

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // 결제 서비스 장애 시뮬레이션
        PaymentStatus paymentStatus = simulatePaymentFailure();

        // 로그 추가
        log.info("[{}] Processing payment for orderId={}, amount={}, status={}",
                serverPort, request.orderId(), request.amount(), paymentStatus);

        Payment payment = Payment.builder()
                .orderId(request.orderId())
                .amount(request.amount())
                .status(paymentStatus)
                .paidAt(paymentStatus == PaymentStatus.SUCCESS ? LocalDateTime.now() : null)
                .build();

        payment = paymentRepository.save(payment);

        log.info("[{}] Payment saved with id={}, status={}", serverPort, payment.getId(), payment.getStatus());

        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus().toString(),
                payment.getPaidAt()
        );
    }

    public PaymentResponse getPaymentById(Long id) {
        log.info("[{}] Fetching payment info for id={}", serverPort, id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        log.info("[{}] Found payment id={}, orderId={}, status={}",
                serverPort, payment.getId(), payment.getOrderId(), payment.getStatus());

        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus().toString(),
                payment.getPaidAt()
        );
    }

    /**
     * 결제 서비스 장애 시뮬레이션
     * 설정된 성공 확률에 따라 SUCCESS 또는 FAILED 반환
     */
    private PaymentStatus simulatePaymentFailure() {
        int randomValue = random.nextInt(100);
        return randomValue < paymentSuccessRate ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }
}
