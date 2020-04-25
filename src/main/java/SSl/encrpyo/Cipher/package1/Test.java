package SSl.encrpyo.Cipher.package1;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 11:04
 * \* Description:
 * \
 */
public class Test {

    /**
     * content: 加密内容
     * slatKey: 加密的盐，16位字符串
     * vectorKey: 加密的向量，16位字符串
     */
    public static String encrypt(String content, String slatKey, String vectorKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(slatKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * content: 解密内容(base64编码格式)
     * slatKey: 加密时使用的盐，16位字符串
     * vectorKey: 加密时使用的向量，16位字符串
     */
    public static String decrypt(String base64Content, String slatKey, String vectorKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(slatKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] content = Base64.decodeBase64(base64Content);
        byte[] encrypted = cipher.doFinal(content);
        return new String(encrypted);
    }

    final static java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    final static java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();


    public static void testCipherAES() throws Exception {




        //指定使用AES加密
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //使用KeyGenerator生成key，参数与获取cipher对象的algorithm必须相同
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        //指定生成的密钥长度为128
        keyGenerator.init(128);
        Key key = keyGenerator.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal("helloworld".getBytes());
//        System.out.println("AES加密： " + Base64.encodeBase64String(encodeToString(bytes)).encodeToString(bytes));

        System.out.println("AES加密 : ");
        System.out.println(encoder.encodeToString(bytes));


        //由于AES加密在CBC模式下是需要有一个初始向量数组byte[] initializeVector ,
        // 而解密的时候也需要同样的初始向量，因此需要使用加密时的参数初始化解密的cipher，否则会出错
        byte[] initializeVector = cipher.getIV();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializeVector);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        //上面三步操作可以用此操作代替   cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
        bytes = cipher.doFinal(bytes);
        System.out.println("AES解密： " + new String(bytes));
    }

    public static void testCipherDES() throws Exception {
        //指定使用DES加密
        Cipher cipher = Cipher.getInstance("DES");
        //使用KeyGenerator生成key，参数与获取cipher对象的algorithm必须相同
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        //DES的秘钥长度必须是56位
        keyGenerator.init(56);
        Key key = keyGenerator.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal("helloworld".getBytes());
//        System.out.println("DES加密： " + Base64.getEncoder().encodeToString(bytes));
        System.out.println("DES加密 : ");

        System.out.println(encoder.encodeToString(bytes));



        //与AES不同，由于DES并不需要初始向量，因此解密的时候不需要第三个参数
        cipher.init(Cipher.DECRYPT_MODE, key);
        bytes = cipher.doFinal(bytes);
        System.out.println("DES解密： " + new String(bytes));
    }


    public static void testCipherRSA() throws Exception {
        //获取cipher对象
        Cipher cipher = Cipher.getInstance("RSA");
        //通过KeyPairGenerator来生成公钥和私钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();//公钥
        PrivateKey privateKey = keyPair.getPrivate();//私钥

        /*加密*/
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        final String TEXT = "hello world !";
        byte[] bytes = cipher.doFinal(TEXT.getBytes());
        final String encryptText = encoder.encodeToString(bytes);
        System.out.println("RSA公钥加密：" + encryptText);

        /*解密*/
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        bytes = cipher.doFinal(decoder.decode(encryptText));
        System.out.println("RSA解密：" + new String(bytes));
    }

    public static void main(String[] args) throws Exception {

        testCipherAES();

        System.out.println("**************************");

        testCipherDES();

        System.out.println("**************************");

        testCipherRSA();


    }
}
