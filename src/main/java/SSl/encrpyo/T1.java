package SSl.encrpyo;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import static SSl.encrpyo.RSATest.ALGORITHM_RSA;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 10:10
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) throws Exception {

        generatorKeyPair();
    }

    static void generatorKeyPair() throws Exception {
        // 获取 RSA算法密钥对的生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);

        keyPairGen.initialize(1024);

        // 获取密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 获取 RSA 公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        // 获取公钥的内容(字节数组的形式)
        byte[] keyBs = rsaPublicKey.getEncoded();
        System.out.println(keyBs.length);
        for (byte b : keyBs) {
            System.out.print(b);
        }

        System.out.println("-------------------------------------");

        PublicKey gets = getPublicKey(keyBs);

        byte[] bytes = gets.getEncoded();


        for (byte b : bytes) {
            System.out.print(b);
        }

        System.out.println("********************************************");
//        publicKey = encodeBase64(keyBs);
//        System.out.println("生成的公钥：\r\n" + publicKey);

        // 获取RSA 私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 获取私钥的内容(字节数组的形式)
        keyBs = rsaPrivateKey.getEncoded();
        for (byte b : keyBs) {
            System.out.print(b);
        }
//        privateKey = encodeBase64(keyBs);
//        System.out.println("生成的私钥：\r\n" + privateKey);
    }


    static PublicKey getPublicKey(byte[] publicKey) throws Exception {
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(publicKeySpec);
    }




}
