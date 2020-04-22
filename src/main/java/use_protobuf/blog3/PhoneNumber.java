package use_protobuf.blog3;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:52
 * \* Description:
 * \
 */
public class PhoneNumber implements Serializable {


    private static final long serialVersionUID = 1103624462642362997L;
    private String number;

    private The_Person.PhoneType phoneType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public The_Person.PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(The_Person.PhoneType phoneType) {
        this.phoneType = phoneType;
    }



}
