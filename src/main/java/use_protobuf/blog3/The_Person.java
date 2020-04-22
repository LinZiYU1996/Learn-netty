package use_protobuf.blog3;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:45
 * \* Description:
 * \
 */
public class The_Person implements Serializable {


    private static final long serialVersionUID = 4990283093307154827L;

    private int id;

    private String name;

    private String email;

    private PhoneNumber phoneNumber;

    enum PhoneType {
        MOBILE ,
        HOME,
        WORK ,
    }


    public static The_Person build(int id,String name,String email,PhoneNumber phoneNumber) {

        The_Person the_person = new The_Person();

        the_person.setId(id);

        the_person.setEmail(email);

        the_person.setName(name);

        the_person.setPhoneNumber(phoneNumber);
        return the_person;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
