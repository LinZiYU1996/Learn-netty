package use_protobuf.blog3;

import java.io.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 21:00
 * \* Description:
 * \
 */
public class Main4 {

    public static void main(String[] args) throws IOException {

        //使用builder()Msg类
        AddressBookProtos.Person john =
                AddressBookProtos.Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                AddressBookProtos.Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(AddressBookProtos.Person.PhoneType.HOME))
                        .build();
        File file = new File("../persons.data");
        OutputStream outputStream = null;

        outputStream = new FileOutputStream(file);
        for (int i = 0; i < 100; i++) {
            john.writeDelimitedTo(outputStream);
        }

        outputStream.close();

        InputStream inputStream;

        inputStream = new FileInputStream(file);
        AddressBookProtos.Person person = AddressBookProtos.Person.parseDelimitedFrom(inputStream);
        System.out.println(person);
        int count = 1;
        while (inputStream.available()!=0){
            person = AddressBookProtos.Person.parseDelimitedFrom(inputStream);
            count++;
        }
        System.out.println(count);
        inputStream.close();
    }
}
