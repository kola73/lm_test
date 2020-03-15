package main.java.com.kola.java.lesson23_2;

/*
重写和重载：
重写：发生在继承关系中，一般是子类重写父类方法（比如toString方法）， 返回值和形参都不能改变。即外壳不变，核心重写！
1，参数列表必须完全与被重写方法的相同。
2，返回类型与被重写方法的返回类型可以不相同，但是必须是父类返回值的派生类
3，访问权限不能比父类中被重写的方法的访问权限更低。例如：如果父类的一个方法被声明为 public，那么在子类中重写该方法就不能声明为 protected。
4，父类的成员方法只能被它的子类重写。
重载：方法名相同（不管它是什么类型的，只要方法名一样），参数不同（个数或者类型不一样）
1，被重载的方法必须改变参数列表(参数个数或类型不一样)；
2，被重载的方法可以改变返回类型；
3，被重载的方法可以改变访问修饰符；
4，被重载的方法可以声明新的或更广的检查异常；
5，方法能够在同一个类中或者在一个子类中被重载。
6，无法以返回值类型作为重载函数的区分标准。
 */
public class OverrideAndOverload {
    private int id;
    private String name;

    // 重写：
    static class Animal {
        public void move() {
            System.out.println("动物可以移动");
        }
    }

    static class Dog extends Animal {
        public void move() {
            System.out.println("狗可以跑和走");
        }
    }

    public static void main(String args[]) {
        Animal a = new Animal(); // Animal 对象
        Animal b = new Dog(); // Dog 对象

        a.move();// 执行 Animal 类的方法

        b.move();//执行 Dog 类的方法=
    }


    // 重载
    public OverrideAndOverload() {


    }

    public OverrideAndOverload(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
