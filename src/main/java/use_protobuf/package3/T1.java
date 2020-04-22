package use_protobuf.package3;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 10:34
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) {

        Info.Baz.Builder builder = Info.Baz.newBuilder();

        builder.getBarBuilder().getFooBuilder().setVal(100);

        Info.Baz baz = builder.build();

        System.out.println(baz);

    }
}
