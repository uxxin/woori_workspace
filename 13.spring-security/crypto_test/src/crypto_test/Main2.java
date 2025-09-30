package crypto_test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

// 전자서명 방식 활용, 서명 검증 예제
public class Main2 {

    // 키 쌍을 클래스 레벨에서 생성하여 공유
    private static KeyPair keyPair;

    public static void main(String[] args) throws Exception {
        // 키 쌍 생성
        generateKeyPair();

        // 서명 생성 및 검증
        String signature = createSignature();
        validateSignature(signature);
    }

    // 키 쌍 생성 메서드
    public static void generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        keyPair = keyPairGen.generateKeyPair();
    }

    // 서명 생성
    public static String createSignature() throws Exception {
        PrivateKey privateKey = keyPair.getPrivate();

        // 서명할 메시지
        String message = "This is a confidential message.";

        // Signature 객체 초기화 및 데이터 서명
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();

        // 서명 결과를 Base64로 인코딩하여 출력
        String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
        System.out.println("Digital Signature: " + encodedSignature);

        return encodedSignature;
    }

    // 서명 검증
    public static void validateSignature(String signatureText) throws Exception {
        PublicKey publicKey = keyPair.getPublic();

        // 원본 메시지
        String message = "This is a confidential message.";

        // Base64로 인코딩된 서명을 디코딩
        byte[] digitalSignature = Base64.getDecoder().decode(signatureText);

        // Signature 객체 초기화 및 서명 검증
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());

        // 서명 검증 결과 출력
        boolean isVerified = signature.verify(digitalSignature);
        System.out.println("Signature Verified: " + isVerified);
    }
}
