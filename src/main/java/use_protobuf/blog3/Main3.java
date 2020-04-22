package use_protobuf.blog3;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:59
 * \* Description:
 * \
 */
public class Main3 {

    public static void main(String[] args) {
/*
所有的Builder类都提供了一个特殊的方法：mergeFrom(Message other)。

这个方法会：

对于单字段，会用other的对应字段覆盖原msg
对于复合字段，会进行融合
对于repeated字段，会拼接列表
 */
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
        AddressBookProtos.Person cai =
                AddressBookProtos.Person.newBuilder()
                        .setId(1234)
                        .setName("CAI")
                        .setEmail("CAI@test.com")
                        .addPhones(
                                AddressBookProtos.Person.PhoneNumber.newBuilder()
                                        .setNumber("12345678")
                                        .setType(AddressBookProtos.Person.PhoneType.HOME))
                        .build();
        AddressBookProtos.Person merge = john.toBuilder().mergeFrom(cai).build();
        System.out.println(merge);

    }
}
