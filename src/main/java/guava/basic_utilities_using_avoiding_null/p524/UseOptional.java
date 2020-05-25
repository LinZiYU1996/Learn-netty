package guava.basic_utilities_using_avoiding_null.p524;

import com.google.common.base.Optional;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/24
 * \* Time: 21:46
 * \* Description:
 *
 * Guava用Optional<T>表示可能为null的T类型引用。
 * 一个Optional实例可能包含非null的引用（我们称之为引用存在），
 * 也可能什么也不包括（称之为引用缺失）。它从不说包含的是null值
 * ，而是用存在或缺失来表示。但Optional从不会包含null值引用。
 * \
 */
public class UseOptional {

    private void t() {


        Optional<String> possbile = Optional.fromNullable("aa"); //将一个T的实例转换为Optional对象(T可以为空)
        boolean present = possbile.isPresent();          //若Optional包含的T实例不为null，则返回true；若T实例为null，返回false
        String t = possbile.get();                            //返回Optional包含的T实例，该T实例必须不为空；否则，抛出一个IllegalStateException异常
        System.out.println(t);
        Optional<Integer> possible = Optional.of(5);

        possible.isPresent(); // returns true

        possible.get(); // returns 5

        Optional<Double> doubleOptional = Optional.of(null);

        System.out.println(doubleOptional.get());
//boolean isPresent();              //若Optional包含的T实例不为null, 返回true; 否则, 返回false
//T get();                          //若Optional包含的T实例不为null, 返回T; 否则, 抛出IllegalStateException
//T or(T);                          //若Optional包含的T实例不为null, 返回T; 否则, 返回参数输入的T实例
//T orNull();                       //若Optional包含的T实例不为null, 返回T; 否则, 返回null
    }

    public static void main(String[] args) {

        new UseOptional().t();

    }
}
