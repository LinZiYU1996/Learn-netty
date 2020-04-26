package SSl.encrpyo.messagedigest.package3;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/25
 * \* Time: 19:46
 * \* Description:
 * \
 */
public class HmacCoder {

    /**
     * JDK支持HmacMD5, HmacSHA1,HmacSHA256, HmacSHA384, HmacSHA512
     */
    public enum HmacTypeEn {
        HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512;
    }

    public static byte[] encode(byte[] plaintext, byte[] secretKey, HmacTypeEn type) throws NoSuchAlgorithmException, InvalidKeyException,Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, type.name());
        Mac mac = Mac.getInstance(keySpec.getAlgorithm());
        mac.init(keySpec);
        return mac.doFinal(plaintext);
    }

    public static void main(String[] args) throws Exception {
        String msg = "Hello World!";
        byte[] secretKey = "Secret_Key".getBytes("UTF8");
        byte[] digest = HmacCoder.encode(msg.getBytes(), secretKey, HmacTypeEn.HmacSHA256);
        System.out.println("原文: " + msg);
        System.out.println("摘要: " + Base64.encodeBase64URLSafeString(digest));
    }
}
