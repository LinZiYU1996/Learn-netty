package SSl.encrpyo.messagedigest.package1;

import java.security.MessageDigest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 11:50
 * \* Description:
 * \
 */
public class T1 {

//    public void testMessageDigest() throws Exception {
//        //参数可以是 MD5,MD2,MD5,SHA-1,SHA-224,SHA-256,SHA-384,SHA-512
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        byte[] bytes = messageDigest.digest("helloworld".getBytes());
//        //将二进制数组转成16进制字符串输出
//        System.out.println("MD5哈希变换：" + HexUtil.toHexString(bytes));
//
//
//        messageDigest = MessageDigest.getInstance("SHA-1");
//        bytes = messageDigest.digest("helloworld".getBytes());
//        System.out.println("SHA1哈希变换：" + HexUtil.toHexString(bytes));
//    }

    //HexUtil
    public static String toHexString(byte[] data) {
        StringBuilder builder = new StringBuilder();
        int len = data.length;
        String hex;
        for (int i = 0; i < len; i++) {
            hex = Integer.toHexString(data[i] & 0xFF);
            if (hex.length() == 1) {
                builder.append("0");
            }
            builder.append(hex);
        }
        return builder.toString();


    }
}