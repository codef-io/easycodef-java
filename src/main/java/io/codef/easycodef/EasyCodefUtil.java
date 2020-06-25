package io.codef.easycodef;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;

/**
 * FileName : EasyCodefUtil.java
 * Comment  : 쉬운 코드에프 유틸 클래스
 * @version : 1.0.1
 * @author  : notfound404
 * @date    : Apr 2, 2020
 */
public class EasyCodefUtil {

	/**
	 * Comment  : RSA암호화 수행 
	 * @version : 1.0.1
	 * @tags    : @param plainText
	 * @tags    : @return
	 * @tags    : @throws NoSuchAlgorithmException
	 * @tags    : @throws InvalidKeySpecException
	 * @tags    : @throws NoSuchPaddingException
	 * @tags    : @throws InvalidKeyException
	 * @tags    : @throws IllegalBlockSizeException
	 * @tags    : @throws BadPaddingException
	 * @date    : Mar 30, 2020
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
	 * Comment  : byte배열로 추출한 파일 정보를 BASE64 문자열로 인코딩
	 * @version : 1.0.1
	 * @tags    : @param filePath
	 * @tags    : @return
	 * @tags    : @throws IOException
	 * @date    : Apr 2, 2020
	 */
	public static String encodeToFileString(String filePath) throws IOException {
		File file = new File(filePath);
		
		byte[] fileContent = FileUtils.readFileToByteArray(file);
		String fileString = Base64.getEncoder().encodeToString(fileContent);
		
		return fileString;
	}
}
