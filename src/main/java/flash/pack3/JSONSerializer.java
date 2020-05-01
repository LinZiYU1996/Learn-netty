package flash.pack3;

import com.alibaba.fastjson.JSON;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 19:59
 * \* Description:
 * \+
 */
public class JSONSerializer implements Serializer{

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }


    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }

}
