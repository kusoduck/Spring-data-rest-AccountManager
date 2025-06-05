package com.kusoduck.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {
	// AES 密鑰（必須固定且安全保存）
    private static final String SECRET_KEY = "mySuperSecretKey123"; // 長度至少 16 字元

    // 使用 AES 加密密碼
    public static String encryptPassword(String plainPassword) throws Exception  {
    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // 指定填充模式
        SecretKeySpec keySpec = getKeySpec(SECRET_KEY);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 使用 AES 解密密碼
    public static String decryptPassword(String encryptedPassword) throws Exception {
    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // 指定填充模式
        SecretKeySpec keySpec = getKeySpec(SECRET_KEY);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)); // Base64 解碼
        return new String(decryptedBytes, "UTF-8");
    }

    // 產生 AES 密鑰規格
    private static SecretKeySpec getKeySpec(String key) throws Exception {
        byte[] keyBytes = key.getBytes("UTF-8");
        byte[] keyBytesPadded = new byte[16]; // AES 密鑰固定長度為 16 bytes
        System.arraycopy(keyBytes, 0, keyBytesPadded, 0, Math.min(keyBytes.length, keyBytesPadded.length));
        return new SecretKeySpec(keyBytesPadded, "AES");
    }
}
