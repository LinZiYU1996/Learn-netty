package SSl.encrpyo.keys.T1;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/25
 * \* Time: 16:30
 * \* Description:
 * \
 */
public class T {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();;
        int keyBitSize = 256;

        generator.init(keyBitSize,secureRandom);

        SecretKey secretKey = generator.generateKey();

        System.out.println(secretKey.getEncoded());

    }




}
