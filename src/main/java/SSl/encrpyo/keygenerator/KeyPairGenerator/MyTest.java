package SSl.encrpyo.keygenerator.KeyPairGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 16:40
 * \* Description:
 * \
 */
public class MyTest {

    final static java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    final static java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    final static String TEXT = "hello world!";

    // 简单测试，生成密钥对
    public static void test_KeyPairGenerator() throws NoSuchAlgorithmException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey oldPbk = keyPair.getPublic();
        PrivateKey oldPrk = keyPair.getPrivate();

        byte[] public_Key = oldPbk.getEncoded();

        byte[] private_Key = oldPrk.getEncoded();

        // 进行base64编码

        System.out.println(encoder.encodeToString(public_Key));

        System.out.println(encoder.encodeToString(private_Key));

    }


    // 使用私钥进行加密措施
    public static byte[] use_privateKey_ENCRYPT(PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(TEXT.getBytes());
        // base64编码
        System.out.println("私钥加密： " + Base64.getEncoder().encodeToString(bytes));
        return bytes;
    }

    // 公钥加密
    public static byte[] use_publicKey_ENCRYPT(PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(TEXT.getBytes());
        // base64编码
        System.out.println("公钥加密： " + Base64.getEncoder().encodeToString(bytes));
        return bytes;
    }


    // 公钥解密  对应私钥加密
    public static void use_publicKey_DECRYPT(byte[] encrypt_MSG,PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("RSA");
        byte[] msg;
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        msg = cipher.doFinal(encrypt_MSG);
        System.out.println("公钥解密");
        System.out.println(new String(msg));

    }


    // 私钥解密  对应公钥加密
    public static void use_privateKey_DECRYPT(byte[] encrypt_MSG, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] msg;
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        msg = cipher.doFinal(encrypt_MSG);
        System.out.println("私钥解密");
        System.out.println(new String(msg));
    }




    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

//        use_publicKey_DECRYPT(use_privateKey_ENCRYPT(privateKey),publicKey);

        use_privateKey_DECRYPT(use_publicKey_ENCRYPT(publicKey),privateKey);


    }
}
