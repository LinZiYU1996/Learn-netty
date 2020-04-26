package SSl.encrpyo.signature.package3;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/25
 * \* Time: 20:13
 * \* Description:
 * \
 */
public class RSASignatureUtil {

    public static final String KEY_ALGORITHM = "RSA";//密钥算法
    public static final String SIGN_ALGORITHM = "MD5withRSA";//签名算法：MD2withRSA,SHA1WithRSA,SHA256withRSA,SHA384withRSA,SHA512withRSA

    /**
     * 初始化RSA公钥私钥
     */
    public static KeyPair initKey() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 签名（原数据，私钥 2要素）
     */
    public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception{
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);//设置要计算的数据

        return signature.sign();
    }

    /**
     * 校验签名（元数据，公钥，签名 三要素）
     */
    public static boolean valid(byte[] data, byte[] publicKey, byte[] sign) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(sign);
    }

    public static void main(String[] args) throws Exception {
        String data = "123456";
        KeyPair keyPair = initKey();
        byte[] sign = sign(data.getBytes(),keyPair.getPrivate());
        boolean isValid = valid(data.getBytes(),keyPair.getPublic().getEncoded(),sign);
        System.out.println(isValid);
    }


}
