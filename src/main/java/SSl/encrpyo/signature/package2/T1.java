package SSl.encrpyo.signature.package2;

import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/25
 * \* Time: 16:36
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Signature signature = Signature.getInstance("SHA256WithDSA");

        signature.initSign(keyPair.getPrivate(), secureRandom);

        byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
        signature.update(data);

        byte[] digitalSignature = signature.sign();


        Signature signature2 = Signature.getInstance("SHA256WithDSA");
        signature2.initVerify(keyPair.getPublic());

        signature2.update(data);

        boolean verified = signature2.verify(digitalSignature);

        System.out.println("verified = " + verified);
    }
}
