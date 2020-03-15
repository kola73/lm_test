package main.java.com.kola.java.lesson23_2;

/*
访问修饰符
private:本类
default:本类，同包
protected:本类，同包，子类
public:任何地方
非访问修饰符：
static：
1，类在加载到内存的时候最先加载进来的是静态的成员
2，不需要依赖对象，可通过类名直接访问
3，静态成员在内存中只保存一份，生命周期跟类一致，所以静态成员是共享的
4，静态方法只能访问静态属性和方法
5，非静态属性方法通过实例化进行访问
final:不可改变
 */
public class AccessModifier {
    public String name;
    protected int id;
    String phone;
    private int age;
    // 常量：不允许修改
    public static final String PLANET = "Earth";

    // 访问常量：类名.方法名
    public static void main(String[] args) {
        System.out.println(AccessModifier.PLANET);
    }
}
