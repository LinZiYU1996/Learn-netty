package proxy.blog1.api;

import java.lang.reflect.Field;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:35
 * \* Description:
 * \
 */
public class ReflectFieldDemo {

    class FieldSpy<T> {
        public Boolean[][] b = {{false, false}, {true, true}};
        public String name = "Alice";
        public List<Integer> list;
        public T val;
    }
    public static void main(String[] args) throws NoSuchFieldException {
        Field f1 = FieldSpy.class.getField("b");
        System.out.format("Type: %s%n", f1.getType());
        Field f2 = FieldSpy.class.getField("name");
        System.out.format("Type: %s%n", f2.getType());
        Field f3 = FieldSpy.class.getField("list");
        System.out.format("Type: %s%n", f3.getType());
        Field f4 = FieldSpy.class.getField("val");
        System.out.format("Type: %s%n", f4.getType());
    }
/*Type: class [[Ljava.lang.Boolean;
Type: class java.lang.String
Type: interface java.util.List
Type: class java.lang.Object
Method

Class 对象提供以下方法获取对象的方法（Method）：

getMethod - 返回类或接口的特定方法。其中第一个参数为方法名称，后面的参数为方法参数对应 Class 的对象。
getDeclaredMethod - 返回类或接口的特定声明方法。其中第一个参数为方法名称，后面的参数为方法参数对应 Class 的对象。
getMethods - 返回类或接口的所有 public 方法，包括其父类的 public 方法。
getDeclaredMethods - 返回类或接口声明的所有方法，包括 public、protected、默认（包）访问和 private 方法，但不包括继承的方法。
获取一个 Method 对象后，可以用 invoke 方法来调用这个方法。
 */

}
