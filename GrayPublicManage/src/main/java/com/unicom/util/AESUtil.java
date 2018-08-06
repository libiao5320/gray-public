package com.unicom.util;


import static com.unicom.constant.Constants.AES_SECKEY;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author
 * @create 2018-07-16 14:23
 **/
public class AESUtil {


  /**
   *
   * @param clearPwd 明文字符串
   * @return 密文字节数组
   */
  public static byte[] encrypt(String clearPwd) {
    try {
      SecretKeySpec secretKeySpec = new SecretKeySpec(getRawKey(AES_SECKEY), "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      byte[] encypted = cipher.doFinal(clearPwd.getBytes());
      return encypted;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * @param encrypted 密文字节数组
   * @param rawKey 密钥
   * @return 解密后的字符串
   */
  public static String decrypt(byte[] encrypted) {
    try {
      SecretKeySpec secretKeySpec = new SecretKeySpec(getRawKey(AES_SECKEY), "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
      byte[] decrypted = cipher.doFinal(encrypted);
      return new String(decrypted);
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @return 密钥数据
   */
  private static byte[] getRawKey(byte[] seed) {
    byte[] rawKey = null;
    try {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(seed);
      // AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
      kgen.init(128, secureRandom);
      SecretKey secretKey = kgen.generateKey();
      rawKey = secretKey.getEncoded();
    } catch (NoSuchAlgorithmException e) {
    }
    return rawKey;
  }
}
