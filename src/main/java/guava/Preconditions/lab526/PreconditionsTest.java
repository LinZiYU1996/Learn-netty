package guava.Preconditions.lab526;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:26
 * \* Description:
 *
 * 　　在日常开发中，我们经常会对方法的输入参数做一些数据格式上的验证
 * ，以便保证方法能够按照正常流程执行下去。对于可预知的一些数据上的错误，
 * 我们一定要做事前检测和判断，来避免程序流程出错，而不是完全通过错误处理来保证流程正确执行，
 * 毕竟错误处理是比较消耗资源的方式。在平常情况下我们对参数的判断都需要自己来逐个写方法判断，
 * 代码量不少并且复用性不高，如下所示：
 *
 *
 * \
 */
public class PreconditionsTest {


    @Test
    public void Preconditions() throws Exception {

        getPerson(8,"peida");

        getPerson(-9,"peida");

        getPerson(8,"");

        getPerson(8,null);
    }
    public static void getPerson(int age,String neme)throws Exception{
        if(age>0&&neme!=null&&neme.isEmpty()!=true){
            System.out.println("a person age:"+age+",neme:"+neme);
        }else{
            System.out.println("参数输入有误！");
        }
    }

    //说明：参数验证，我们每次都要添加if语句来做判断, 重复的工作会做好多次。getPerson方法只有2个参数，验证规则也不是很复杂，如果参数过度，验证规则复杂后，上面代码的可读性都会很差的，复用性就更谈不上了。
    //
    //　　Guava类库中提供了一个作参数检查的工具类--Preconditions类， 该类可以大大地简化我们代码中对于参数的预判断和处理，让我们对方法输入参数的验证实现起来更加简单优雅，下面我们看看Preconditions类的使用实例：　

    @Test
    public void Preconditions1() throws Exception {

        getPersonByPrecondition(8,"peida");

        try {
            getPersonByPrecondition(-9,"peida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            getPersonByPrecondition(8,"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            getPersonByPrecondition(8,null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//a person age:8,neme:peida
//age 必须大于0
//neme为''
//neme为null
    public static void getPersonByPrecondition(int age,String neme)throws Exception{
        Preconditions.checkNotNull(neme, "neme为null");
        Preconditions.checkArgument(neme.length()>0, "neme为\'\'");
        Preconditions.checkArgument(age>0, "age 必须大于0");
        System.out.println("a person age:"+age+",neme:"+neme);
    }


    /*

    　Preconditions里面的方法：

　　1 .checkArgument(boolean) ：
　　功能描述：检查boolean是否为真。 用作方法中检查参数
　　失败时抛出的异常类型: IllegalArgumentException

　　2.checkNotNull(T)：
　　功能描述：检查value不为null， 直接返回value；
　　失败时抛出的异常类型：NullPointerException

　　3.checkState(boolean)：
　　功能描述：检查对象的一些状态，不依赖方法参数。 例如， Iterator可以用来next是否在remove之前被调用。
　　失败时抛出的异常类型：IllegalStateException

　　4.checkElementIndex(int index, int size)：
　　功能描述：检查index是否为在一个长度为size的list， string或array合法的范围。 index的范围区间是[0, size)(包含0不包含size)。无需直接传入list， string或array， 只需传入大小。返回index。
　　失败时抛出的异常类型：IndexOutOfBoundsException


　　5.checkPositionIndex(int index, int size)：
　　功能描述：检查位置index是否为在一个长度为size的list， string或array合法的范围。 index的范围区间是[0， size)(包含0不包含size)。无需直接传入list， string或array， 只需传入大小。返回index。
　　失败时抛出的异常类型：IndexOutOfBoundsException

　　6.checkPositionIndexes(int start, int end, int size)：
　　功能描述：检查[start, end)是一个长度为size的list， string或array合法的范围子集。伴随着错误信息。
　　失败时抛出的异常类型：IndexOutOfBoundsException


     */
}
