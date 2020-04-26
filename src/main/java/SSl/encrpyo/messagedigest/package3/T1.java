package SSl.encrpyo.messagedigest.package3;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/25
 * \* Time: 19:45
 * \* Description:
 * \
 */
public class T1 {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        String msg = "Hello World!";

        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        // 更新要计算的内容
        md5Digest.update(msg.getBytes());
        // 完成哈希计算，得到摘要
        byte[] md5Encoded = md5Digest.digest();

        MessageDigest shaDigest = MessageDigest.getInstance("SHA1");
        // 更新要计算的内容
        shaDigest.update(msg.getBytes());
        // 完成哈希计算，得到摘要
        byte[] shaEncoded = shaDigest.digest();

        System.out.println("原文: " + msg);
        System.out.println("MD5摘要: " + Base64.encodeBase64URLSafeString(md5Encoded));
        System.out.println("SHA摘要: " + Base64.encodeBase64URLSafeString(shaEncoded));

    }
}
