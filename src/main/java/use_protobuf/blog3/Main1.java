package use_protobuf.blog3;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:24
 * \* Description:
 * \
 */
public class Main1 {

    public static void main(String[] args) {

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


        System.out.println(john);


        /*
        同时需要注意到Builder和Msg提供的API的差异：

         */


        //Msg只提供了get和has方法
        String email = john.getEmail();
        boolean hasEmail = john.hasEmail();

//而builder提供了add/set、get、has和clear方法
        AddressBookProtos.Person.Builder builder = AddressBookProtos.Person.newBuilder();
        builder.setEmail("jdoe@example.com");
        boolean hasEmail1 = builder.hasEmail();
        String email1 = builder.getEmail();
        builder.clearEmail();



    }
}
