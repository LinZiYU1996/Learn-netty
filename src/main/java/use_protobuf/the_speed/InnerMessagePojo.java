package use_protobuf.the_speed;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/22
 * \* Time: 9:40
 * \* Description:
 * \
 */
public class InnerMessagePojo implements Serializable {

    private static final long serialVersionUID = 8148713905399476658L;
    private String name ;
    private int id;
    private EnumType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumType getType() {
        return type;
    }

    public void setType(EnumType type) {
        this.type = type;
    }
}
