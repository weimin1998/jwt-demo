package com.example.jwtdemo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    // 获取 cipher
    private static Cipher getCipher(byte[] key, int model) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        return cipher;
    }

    // AES加密
    // key长度16的字符串
    public static String encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }

    // AES解密
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(Base64.getDecoder().decode(data));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("{\"name\":\"weimin\",\"age\":18}".getBytes(), "weimin1234567890".getBytes()));

        System.out.println(new String(decrypt("uu/zFBEkqghmTN18rnrg2c5Vgl8R9N6uh/3fDU4Wyih12o6XZPtMR1iSGHQkA90m".getBytes(), "weimin1234567890".getBytes())));
    }
}