package SSl.encrpyo.keygenerator.KeyPairGenerator;

import java.io.*;
import java.util.Base64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/24
 * \* Time: 20:03
 * \* Description:
 * \
 */
public class MyTest1 {

    public static void main(String[] args) throws IOException {

        String s = "abcdefg";

        byte[] datas = s.getBytes();

        byte[] buffer = Base64.getEncoder().encode(datas);

        System.out.println(new String(buffer));

        File file;
        FileOutputStream fileOutputStream = new FileOutputStream(new File("../ts.key"));
        fileOutputStream.write(buffer);
        fileOutputStream.flush();;
        fileOutputStream.close();

//        byte[] bytes;
        FileInputStream fileInputStream = new FileInputStream(new File("../ts.key"));

        byte[] bytes = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = fileInputStream.read(bytes)) > 0) {
            bos.write(bytes, 0, len);
        }
        buffer = bos.toByteArray();
        System.out.println(new String(buffer));

    }
}
