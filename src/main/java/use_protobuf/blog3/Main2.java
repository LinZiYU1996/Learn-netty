package use_protobuf.blog3;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:41
 * \* Description:
 * \
 */
public class Main2 {

    /*
    序列化：

    byte[] toByteArray()：生成字节数组
    void writeTo(OutputStream output)：序列化并写入到指定的输出流中
    反序列化：

    static Person parseFrom(byte[] data)：解析二进制数组，反序列化出指定对象
    static Person parseFrom(InputStream input)：解析输入流，反序列化出指定对象

     */
    public static void main(String[] args) throws InvalidProtocolBufferException {

        AddressBookProtos.Person john =
                AddressBookProtos.Person.newBuilder()
                        .setId(123)
                        .setName("john doll")
                        .setEmail("XXXX@555.com")
                        .addPhones(

                                AddressBookProtos.Person.PhoneNumber.newBuilder()
                                        .setNumber("555-66666")
                                        .setType(AddressBookProtos.Person.PhoneType.HOME)

                        )
                        .build();



        byte[] bytes = john.toByteArray();

        for(byte b : bytes) {

            System.out.print(b);
        }

        System.out.println("");

        System.out.println(bytes.length);

        System.out.println(AddressBookProtos.Person.parseFrom(bytes));

    }
}
