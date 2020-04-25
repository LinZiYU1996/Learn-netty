package SSl.encrpyo.keygenerator.KeyPairGenerator;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 11:54
 * \* Description:
 * \
 */
public class T1 {

    /*
    KeyPairGenerator用于生成一对密钥对，用于做非对称加密操作。KeyPairGenerator.getInstance(String alorithm)的可用参数为：

    DSA
RSA
EC

分别保存公钥和私钥的二进制编码

     */
    public static void testSaveKeyPair2() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey oldPbk = keyPair.getPublic();
        PrivateKey oldPrk = keyPair.getPrivate();

        String oldPbk_S = new String(oldPbk.getEncoded());

        Cipher cipher = Cipher.getInstance("RSA");
        /*============使用原始私钥加密，重新生成的公钥解密===============*/
        cipher.init(Cipher.ENCRYPT_MODE, oldPrk);
        byte[] bytes = cipher.doFinal("helloworld".getBytes());
        System.out.println("原始私钥加密： " + Base64.getEncoder().encodeToString(bytes));

        /*提取公钥的比特编码经过Base64转换后保存到文件，注意公钥的比特编码是X.509格式*/
        byte[] pbks = Base64.getEncoder().encode(oldPbk.getEncoded());
        String before = new String(pbks);
        File file = new File("../public.key");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(pbks);
        fos.flush();
        fos.close();

        /*从文件中提取公钥比特编码并恢复成公钥*/
        file = new File("../public.key");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            bos.write(buffer, 0, len);
        }
        pbks = Base64.getDecoder().decode(bos.toByteArray());
        System.out.println(oldPbk_S.equals(new String(pbks))+ "*****************");
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(pbks);
        //重新得到公钥
        PublicKey newPbk = KeyFactory.getInstance("RSA").generatePublic(encodedKeySpec);


        cipher.init(Cipher.DECRYPT_MODE, newPbk);
        bytes = cipher.doFinal(bytes);
        System.out.println("新的公钥解密： " + new String(bytes));

        /*============使用原始公钥加密，重新生成的私钥解密===============*/
        cipher.init(Cipher.ENCRYPT_MODE, oldPbk);
        bytes = cipher.doFinal("helloworld".getBytes());
        System.out.println("原始私钥加密： " + Base64.getEncoder().encodeToString(bytes));


        /*省略了文件存取操作，与公钥相同*/

        byte[] prks = oldPrk.getEncoded();
        /*私钥的比特编码是pkcs8格式*/
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(prks);
        PrivateKey newPrk = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, newPrk);
        bytes = cipher.doFinal(bytes);
        System.out.println("新的私钥解密： " + new String(bytes));
    }

    public static void main(String[] args) throws Exception {

        testSaveKeyPair2();
    }
}
