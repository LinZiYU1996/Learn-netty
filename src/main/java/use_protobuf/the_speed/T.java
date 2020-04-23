package use_protobuf.the_speed;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 21:16
 * \* Description:
 * \
 */
public class T {

    private static MessageProtos.Message getProtobufBean() {
        MessageProtos.Message.Builder messageBuilder = MessageProtos.Message.newBuilder();

        messageBuilder.setStrObj("message");
        messageBuilder.setFolatObj(1f);
        messageBuilder.addDoubleObj(1d);
        messageBuilder.addDoubleObj(2d);
        messageBuilder.setBoolObj(true);

        messageBuilder.setBytesObj(ByteString.copyFrom(new byte[] { 1, 2, 3 }));
        messageBuilder.setInt32Obj(32);
        messageBuilder.setInt64Obj(64l);
        messageBuilder.setSint32Obj(232);
        messageBuilder.setSint64Obj(264);
        messageBuilder.setFixed32Obj(532);
        messageBuilder.setFixed64Obj(564);
        messageBuilder.setSfixed32Obj(2532);
        messageBuilder.setSfixed64Obj(2564);
        messageBuilder.setUint32Obj(632);
        messageBuilder.setUint64Obj(664);

        InnerMessageProtos.InnerMessage.Builder innerMessageBuilder = InnerMessageProtos.InnerMessage.newBuilder();
        innerMessageBuilder.setId(1);
        innerMessageBuilder.setName("inner");
        innerMessageBuilder.setType(EnumTypeProtos.EnumType.PRODUCTS);

        messageBuilder.setInnerMessage(innerMessageBuilder);

        return messageBuilder.build();
    }

    private static MessagePojo getPojoBean() {
        MessagePojo bean = new MessagePojo();

        bean.setStrObj("message");
        bean.setFolatObj(1f);
        List<Double> doubleObj = new ArrayList<Double>();
        doubleObj.add(1d);
        doubleObj.add(2d);
        bean.setDoubleObj(doubleObj);
        bean.setBoolObj(true);

        bean.setBytesObj(new byte[] { 1, 2, 3 });
        bean.setInt32Obj(32);
        bean.setInt64Obj(64l);
        bean.setSint32Obj(232);
        bean.setSint64Obj(264);
        bean.setFixed32Obj(532);
        bean.setFixed64Obj(564);
        bean.setSfixed32Obj(2532);
        bean.setSfixed64Obj(2564);
        bean.setUint32Obj(632);
        bean.setUint64Obj(664);

        InnerMessagePojo innerMessagePojo = new InnerMessagePojo();
        innerMessagePojo.setId(1);
        innerMessagePojo.setName("inner");
        innerMessagePojo.setType(EnumType.PRODUCTS);

        bean.setInnerMessage(innerMessagePojo);

        return bean;
    }

    private static void testTemplate(TestCallback callback, Object source, int count) {
        int warmup = 10;
        // 先进行预热，加载一些类，避免影响测试
        for (int i = 0; i < warmup; i++) {
            byte[] bytes = callback.writeObject(source);
            callback.readObject(bytes);
        }
        restoreJvm(); // 进行GC回收
        // 进行测试
        long start = System.nanoTime();
        long size = 0l;
        for (int i = 0; i < count; i++) {
            byte[] bytes = callback.writeObject(source);
            size = size + bytes.length;
            callback.readObject(bytes);
            // System.out.println(callback.readObject(bytes));
            bytes = null;
        }
        long nscost = (System.nanoTime() - start);
        System.out.println(callback.getName() + " total cost=" + nscost + "ns , each cost="
                + (nscost / count) + "ns , and byte sizes = " + size / count);
        restoreJvm();// 进行GC回收

    }

    private static void restoreJvm() {
        int maxRestoreJvmLoops = 10;
        long memUsedPrev = memoryUsed();
        for (int i = 0; i < maxRestoreJvmLoops; i++) {
            System.runFinalization();
            System.gc();

            long memUsedNow = memoryUsed();
            // break early if have no more finalization and get constant mem used
            if ((ManagementFactory.getMemoryMXBean().getObjectPendingFinalizationCount() == 0)
                    && (memUsedNow >= memUsedPrev)) {
                break;
            } else {
                memUsedPrev = memUsedNow;
            }
        }
    }

    private static long memoryUsed() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }


    public static void main(String[] args) {


        final int testCount = 1000 * 500;
        final MessageProtos.Message protoObj = getProtobufBean();
        final MessagePojo pojoOBj = getPojoBean();

        System.out.println(pojoOBj.getBytesObj());
// Serializable测试
        testTemplate(new TestCallback() {

            public String getName() {
                return "Serializable Test";
            }

            @Override
            public byte[] writeObject(Object source) {
                try {
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    ObjectOutputStream output = new ObjectOutputStream(bout);
                    output.writeObject(source);
                    return bout.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Object readObject(byte[] bytes) {
                try {
                    ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
                    ObjectInputStream input = new ObjectInputStream(bin);
                    return input.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, pojoOBj, testCount);


        System.out.println("--------------------------------------------------");


        // protobuf测试
        testTemplate(new TestCallback() {

            public String getName() {
                return "protobuf test";
            }

            @Override
            public byte[] writeObject(Object source) {
                if (source instanceof MessageProtos.Message) {
                    MessageProtos.Message message = (MessageProtos.Message) source;
                    return message.toByteArray();
                }

                return null;
            }

            @Override
            public Object readObject(byte[] bytes) {
                try {
                    return MessageProtos.Message.parseFrom(bytes);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, protoObj, testCount);
    }


}
