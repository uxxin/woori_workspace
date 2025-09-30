package crypto_test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

// 비대칭 암호화 방식의 메시지 복호화
public class Main {

	public static void main(String[] args) throws Exception {
        // 키 쌍 생성(공개 키 & 개인 키)
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // 파라미터로 전달된 알고리즘으로 키 쌍을 생성해주는 객체 생성
        keyPairGenerator.initialize(2048); // 생성할 키 쌍의 키의 크기를 2048비트로 지정, 키의 크기가 작을수록 보안 수준이 낮아짐
        // 일반적으로 키의 사이즈가 커지면 커질수록 암호화는 더 안전해지지만, 처리 속도는 느려짐
        // 2048비트는 보안과 성능의 균형이 각각 고려된 통상적인 크기
        
        
        KeyPair keyPair = keyPairGenerator.generateKeyPair(); // 키 페어 생성기가 실제 키 페어 생성
        PublicKey publicKey = keyPair.getPublic(); // 생성된 키 페어 중 공개 키 얻기
        PrivateKey privateKey = keyPair.getPrivate(); // 생성된 키 페어 중 비밀 키 얻기

        // 암호화할 예시 메시지
        String originalMessage = "Hello, this is a secret message!";
//        String originalMessage = "하이루?";

        // 암호화
        Cipher encryptCipher = Cipher.getInstance("RSA"); // 암호화, 복호화 역할을 수행하는 Cipher 클래스 생성
        /*
           1. 암호화:
			데이터를 특정 알고리즘을 사용해 암호화하여, 원본 데이터를 보호된 형태(암호문)로 변환
			예를 들어, 평문 데이터를 RSA 알고리즘을 통해 암호화하면, 이를 타인이 쉽게 읽을 수 없는 형태로 변환함
			
		   2. 복호화:
			암호화된 데이터를 원래의 평문으로 되돌리는 작업 수행
			이는 암호화에 사용된 알고리즘과 키에 따라 복호화가 가능하며, 데이터의 기밀성을 유지하면서 적절한 키를 가진 사용자가만 접근할 수 있게 함
			
		   3. 암호화 모드 및 패딩 설정:
			암호화 작업이 단순히 데이터를 변환하는 것뿐만 아니라, 블록 암호의 경우 암호화 모드(예: ECB, CBC 등)와 패딩(데이터 블록 크기 맞추기)을 지정할 수 있음
			이 설정은 Cipher.getInstance() 메서드에 전달하는 문자열 파라미터로 지정됨
         */
        
        // 암호화 작업 수행 준비
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey); // Cipher 객체를 암호화 모드로 초기화, 암호화 작업에 사용할 Key 지정(상대방의 공개 키)
        
        // doFinal() - 실제 암호화 작업 수행, 암호화(or 복호화)된 결과 반환
        byte[] encryptedBytes = encryptCipher.doFinal(originalMessage.getBytes()); // getBytes() - 암호화할 메시지를 바이트 배열로 전달, 대부분의 암호화 알고리즘은 바이트 배열을 입력으로 받아 처리하기 때문(오버로딩된 doFinal()메서드 파라미터 타입 확인해보기)
        
        
        // 바이너리를 텍스트 형태로 변환 - 암호화된 데이터는 바이너리 형식으로 존재하지만, 텍스트 기반의 시스템(JSON, 이메일) 등에서 안전하게 전송하기 위해서는 텍스트 형식으로 변환해야 함
        // Base64 인코딩은 바이너리 데이터를 ASCII 텍스트로 변환하여 이런 시스템에서 전송할 수 있도록 함
        // 그 외 가독성, 데이터 손상 방지 등의 이유로 Base64 인코딩 활용
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Message: " + encryptedMessage);
        

        // 복호화
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey); // publicKey를 넣을 경우, BadPaddingException: Padding error in verification
        
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage = new String(decryptedBytes); // 파라미터로 byte[]을 전달하면 디코딩하여 문자열로 변환  
        // Constructs a new String by decoding the specified array of bytes using the platform's default charset.
        System.out.println("Decrypted Message: " + decryptedMessage);
        
    }

}
