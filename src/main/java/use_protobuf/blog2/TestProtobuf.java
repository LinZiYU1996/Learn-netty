package use_protobuf.blog2;

import com.sun.media.sound.SoftTuning;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 20:05
 * \* Description:
 * \
 */
public class TestProtobuf {


    public static void main(String[] args) {

        Test.A.B1.C cObject = Test.A.B1.C.newBuilder().setC("c").build();
        Test.A.B1 b1Object = Test.A.B1.newBuilder().setB("b").setC(cObject).build();
        Test.A.B2 b2Object = Test.A.B2.newBuilder().setB(1).build();
        Test.A aObject = Test.A.newBuilder().setA("a").setB1(b1Object).setB2(b2Object).build();

//        method1(aObject);

        System.out.println("***************************");

        method2(aObject,b1Object);

    }

    private static void method1(Test.A aObject) {

        System.out.println("A = " + aObject);

        System.out.println("------------------------");

        System.out.println("A.isInitialized() = " + aObject.isInitialized());

        System.out.println("------------------------");

        System.out.println("A.getSerializedSize() = " + aObject.getSerializedSize());

        System.out.println("------------------------");

        System.out.println("A.Builder = " + aObject.toBuilder());

        System.out.println("------------------------");

        System.out.println("A.getB1OrBuilder() = " + aObject.getB1OrBuilder());

        System.out.println("------------------------");


        System.out.println("A.b1 = " + aObject.getB1());



    }


    private static void method2(Test.A aObject, Test.A.B1 b1Object) {

        // builder
        Test.A.Builder aBuilder = Test.A.newBuilder();

        // merge from a existing object
        aBuilder.mergeFrom(aObject);
        System.out.println(aBuilder);
        System.out.println("------------------------");

        // change a structure in object
        aBuilder.mergeB1(b1Object.toBuilder().setB("change_a_structure").build());
        System.out.println(aBuilder);
        System.out.println("------------------------");

        // 用builder去get builder，需要修改值直接set就可以了
        aBuilder.getB1Builder().getCBuilder().setC("builder_get_builder").build();
        System.out.println(aBuilder);
        System.out.println("------------------------");

        // 用object去toBuilder，再set值，那么原本的object不会变
        aBuilder.getB1().toBuilder().setB("no_change_happens");
        System.out.println(aBuilder);
        System.out.println("------------------------");

        // 除非再set回去
        Test.A.B1.Builder b1Builder = aBuilder.getB1().toBuilder().setB("change_only_if_set_again");
        aBuilder.setB1(b1Builder); // 使用b1或b1Builder都行，有重载方法
        System.out.println(aBuilder);
        System.out.println("------------------------");

        // buildPartial() vs. build()
        aBuilder.clearA().getB1Builder().getCBuilder().setC("2333"); // 现在已经是CBuilder对象了，不再是ABuilder，所以调用build()也是CBuilder的方法
        // safe
        System.out.println(aBuilder.buildPartial());
        System.out.println("------------------------");

        aBuilder.clearA();
        // Exception in thread "main" com.google.protobuf.UninitializedMessageException: Message missing required fields: a
        // System.out.println(aBuilder.build(



    }
}
