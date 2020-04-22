package use_protobuf.blog3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:54
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) throws IOException {

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("5656-445");
        phoneNumber.setPhoneType(The_Person.PhoneType.MOBILE);
        The_Person person = The_Person.build(123,"miki","XXXX@789.com",phoneNumber);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ObjectOutputStream ojb = new ObjectOutputStream(out);

        ojb.writeObject(person);

        ojb.flush();;


        byte[] bytes = out.toByteArray();

        System.out.println(bytes.length);


    }
}
