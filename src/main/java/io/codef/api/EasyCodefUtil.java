package io.codef.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

/**
 * 쉬운 코드에프 유틸 클래스
 */
public class EasyCodefUtil {

    /**
     * RSA암호화
     */
    public static String encryptRSA(String plainText, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] bytePublicKey = Base64.getDecoder().decode(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(bytePublicKey));

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytePlain = cipher.doFinal(plainText.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(bytePlain);

        return encrypted;
    }

    /**
     * byte배열로 추출한 파일 정보를 BASE64 문자열로 인코딩
     */
    public static String encodeToFileString(String filePath) throws IOException {
        File file = new File(filePath);

        byte[] fileContent = FileUtils.readFileToByteArray(file);
        String fileString = Base64.getEncoder().encodeToString(fileContent);

        return fileString;
    }

    /**
     * 토큰 맵 변환
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> getTokenMap(String token) throws IOException {

        /** 클라이언트 식별 값, 요청 식별 값 추출을 위한 디코드 */
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        String tokenBody = new String(Base64.getDecoder().decode(base64EncodedBody));

        /** 맵 변환 */
        return new ObjectMapper().readValue(tokenBody, HashMap.class);
    }

    /**
     * 요청 토큰 정합성 체크
     */
    public static boolean checkValidity(int expInt) {
        long now = new Date().getTime();
        String expStr = expInt + "000";    // 현재 시간 타임스탬프와 자리수 맞추기(13자리)
        long exp = Long.parseLong(expStr);
        // 유효기간 확인::유효기간이 지났거나 한시간 이내로 만료되는 경우
        return now <= exp && (exp - now >= 3600000);
    }
}
