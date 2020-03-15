package main.java.com.kola.java.lesson13;

/**
 * 作用域：
 * 局部变量：作定义在方法内部--作用域在方法内部
 * 全局变量：定义在方法外部--作用域在整个类
 *
 * 局部变量特点：
 * 1，没有修饰符
 * 2，定义在函数内部
 * 3，使用前必须初始化
 * 4，作用域仅限于当前函数内部
 * 5，变量不能重名
 *
 * 总结：
 * 1，局部变量不能有修饰符，如public。全局变量可以有修饰符，如public,protetect,默认，private
 * 2，局部变量需要初始化，全局变量不需要初始化，给的值为默认值
 * 3，在一个作用域范围内，不能定义2个同名的变量
 * 4，全局变量和局部变量是可以同名的，但是局部变量会覆盖全局变量（不建议）
 *
 */
public class VariableZone {
    public class test {
        int jackAge = 4;//定义在方法内部：作用范围从定义处到方法结束前
    }

    public static void main(String[] args) {
        int age = 3;  //定义在方法内部：作用范围从定义处到方法结束前
        System.out.println(age);
        System.out.println(args);  // 定义在方法上或控制结构体上：作用域在方法内部
        for (int i = 1; i <= 12; i++) {
            System.out.println(i);  // 定义在方法上或控制结构体上：作用域在方法内部
            i++;
        }
    }

    {
        int tomAge = 4;  //定义在代码块中：作用范围在代码块内部
    }

}
