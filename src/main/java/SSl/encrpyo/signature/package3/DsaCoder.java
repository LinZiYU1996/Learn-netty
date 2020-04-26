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
 * \* Time: 19:48
 * \* Description:
 * \
 */
public class DsaCoder {
    public static final String KEY_ALGORITHM = "DSA";

    public enum DsaTypeEn {
        MD5withDSA, SHA1withDSA
    }

    /**
     * DSA密钥长度默认1024位。 密钥长度必须是64的整数倍，范围在512~1024之间
     */
    private static final int KEY_SIZE = 1024;

    private KeyPair keyPair;

    public DsaCoder() throws Exception {
        keyPair = initKey();
    }

    public byte[] signature(byte[] data, byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey key =keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance(DsaTypeEn.SHA1withDSA.name());
        signature.initSign(key);
        signature.update(data);

        return signature.sign();
    }

    public boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey key =keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(DsaTypeEn.SHA1withDSA.name());
        signature.initVerify(key);
        signature.update(data);
        return signature.verify(sign);
    }

    private KeyPair initKey() throws Exception {
        // 初始化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 实例化密钥对生成器
        keyPairGen.initialize(KEY_SIZE);
        // 实例化密钥对
        return keyPairGen.genKeyPair();
    }

    public byte[] getPublicKey() {
        return keyPair.getPublic().getEncoded();
    }

    public byte[] getPrivateKey() {
        return keyPair.getPrivate().getEncoded();
    }

    public static void main(String[] args) throws Exception {
        String msg = "Hello,Where are you from ?";
        DsaCoder dsa = new DsaCoder();
        System.out.println("Bob发送消息:" + msg + "给Jack");

        byte[] sign = dsa.signature(msg.getBytes(), dsa.getPrivateKey());

        System.out.println("系统对消息进行计算，生成数字签名" + Base64.getEncoder().encodeToString(sign));

        boolean flag = dsa.verify(msg.getBytes(), dsa.getPublicKey(), sign);
        String result = flag ? "数字签名匹配" : "数字签名不匹配";
//        System.out.println("数字签名：" + Base64.encodeBase64URLSafeString(sign));
        System.out.println("验证结果：" + result);



    }
}
