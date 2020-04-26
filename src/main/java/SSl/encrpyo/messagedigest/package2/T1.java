package SSl.encrpyo.messagedigest.package2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/25
 * \* Time: 16:33
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] data1 = "0123456789".getBytes("UTF-8");
        byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");

        messageDigest.update(data1);
        messageDigest.update(data2);

        byte[] digest = messageDigest.digest();

        System.out.println(Base64.getEncoder().encodeToString(digest));

    }
}
