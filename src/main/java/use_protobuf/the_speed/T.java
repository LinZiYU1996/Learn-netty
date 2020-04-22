//package use_protobuf.the_speed;
//
//import com.google.protobuf.ByteString;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * \* Created with IntelliJ IDEA.
// * \* User: LinZiYu
// * \* Date: 2020/4/21
// * \* Time: 21:16
// * \* Description:
// * \
// */
//public class T {
//
//    private static MessageProtos.Message getProtobufBean() {
//        MessageProtos.Message.Builder messageBuilder = MessageProtos.Message.newBuilder();
//
//        messageBuilder.setStrObj("message");
//        messageBuilder.setFolatObj(1f);
//        messageBuilder.addDoubleObj(1d);
//        messageBuilder.addDoubleObj(2d);
//        messageBuilder.setBoolObj(true);
//
//        messageBuilder.setBytesObj(ByteString.copyFrom(new byte[] { 1, 2, 3 }));
//        messageBuilder.setInt32Obj(32);
//        messageBuilder.setInt64Obj(64l);
//        messageBuilder.setSint32Obj(232);
//        messageBuilder.setSint64Obj(264);
//        messageBuilder.setFixed32Obj(532);
//        messageBuilder.setFixed64Obj(564);
//        messageBuilder.setSfixed32Obj(2532);
//        messageBuilder.setSfixed64Obj(2564);
//        messageBuilder.setUint32Obj(632);
//        messageBuilder.setUint64Obj(664);
//
//        InnerMessageProtos.InnerMessage.Builder innerMessageBuilder = InnerMessageProtos.InnerMessage.newBuilder();
//        innerMessageBuilder.setId(1);
//        innerMessageBuilder.setName("inner");
//        innerMessageBuilder.setType(EnumTypeProtos.EnumType.PRODUCTS);
//
//        messageBuilder.setInnerMessage(innerMessageBuilder);
//
//        return messageBuilder.build();
//    }
//
//    private static MessagePojo getPojoBean() {
//        MessagePojo bean = new MessagePojo();
//
//        bean.setStrObj("message");
//        bean.setFolatObj(1f);
//        List<Double> doubleObj = new ArrayList<Double>();
//        doubleObj.add(1d);
//        doubleObj.add(2d);
//        bean.setDoubleObj(doubleObj);
//        bean.setBoolObj(true);
//
//        bean.setBytesObj(new byte[] { 1, 2, 3 });
//        bean.setInt32Obj(32);
//        bean.setInt64Obj(64l);
//        bean.setSint32Obj(232);
//        bean.setSint64Obj(264);
//        bean.setFixed32Obj(532);
//        bean.setFixed64Obj(564);
//        bean.setSfixed32Obj(2532);
//        bean.setSfixed64Obj(2564);
//        bean.setUint32Obj(632);
//        bean.setUint64Obj(664);
//
//        InnerMessagePojo innerMessagePojo = new InnerMessagePojo();
//        innerMessagePojo.setId(1);
//        innerMessagePojo.setName("inner");
//        innerMessagePojo.setType(EnumTypePojo.PRODUCTS);
//
//        bean.setInnerMessage(innerMessagePojo);
//
//        return bean;
//    }
//
//
//}
