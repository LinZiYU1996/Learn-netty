package SSl.encrpyo.signature.package1;

import java.security.*;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 11:47
 * \* Description:
 * \
 */
public class T1 {


    final static String TEXT = "I am Java Coder";

    public static void testSignature() throws Exception {

        Signature signature = Signature.getInstance("NONEwithRSA");

        //KeyPairGenerator生成公钥和私钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //用私钥初始化signature
        signature.initSign(privateKey);
        //更新原始字符串
        signature.update(TEXT.getBytes());
        byte[] bytes = signature.sign();
        String sign = Base64.getEncoder().encodeToString(bytes);
        System.out.println("数字签名: " + sign);


        //用公钥初始化signature
        signature.initVerify(publicKey);
        //更新原始字符串
        signature.update(TEXT.getBytes());
        //校验签名是否正确
        boolean result = signature.verify(Base64.getDecoder().decode(sign));

        System.out.println("签名校验结果: " + result);
    }


    public static void main(String[] args) throws Exception {

        testSignature();

    }

}
