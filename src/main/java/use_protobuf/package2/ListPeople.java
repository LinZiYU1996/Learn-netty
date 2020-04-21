//package use_protobuf.package2;
//
//import java.io.FileInputStream;
//
///**
// * \* Created with IntelliJ IDEA.
// * \* User: LinZiYu
// * \* Date: 2020/4/20
// * \* Time: 20:02
// * \* Description:
// * \
// */
//public class ListPeople {
//
//    // Iterates though all people in the AddressBook and prints info about them.
//    static void Print(AddressBookProtos.AddressBook addressBook) {
//        for (AddressBookProtos.Person person: addressBook.getPeopleList()) {
//            System.out.println("Person ID: " + person.getId());
//            System.out.println("  Name: " + person.getName());
//            if (person.hasEmail()) {
//                System.out.println("  E-mail address: " + person.getEmail());
//            }
//
//            for (AddressBookProtos.Person.PhoneNumber phoneNumber : person.getPhonesList()) {
//                switch (phoneNumber.getType()) {
//                    case MOBILE:
//                        System.out.print("  Mobile phone #: ");
//                        break;
//                    case HOME:
//                        System.out.print("  Home phone #: ");
//                        break;
//                    case WORK:
//                        System.out.print("  Work phone #: ");
//                        break;
//                }
//                System.out.println(phoneNumber.getNumber());
//            }
//        }
//    }
//
//    // Main function:  Reads the entire address book from a file and prints all
//    //   the information inside.
//    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
//            System.exit(-1);
//        }
//
//        // Read the existing address book.
//        AddressBookProtos.AddressBook addressBook =
//                AddressBookProtos.AddressBook.parseFrom(new FileInputStream(args[0]));
//
//        Print(addressBook);
//    }
//}
