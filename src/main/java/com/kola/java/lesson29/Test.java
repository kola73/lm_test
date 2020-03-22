package com.kola.java.lesson29;

import com.kola.java.lesson28.Student;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static List<Student> readXml() {
        Student student = new Student();
        // 存储学生对象
        List<Student> studentList = null;
        // 获得类的字节码对象
        Class clazz = Student.class;
        try {
            studentList = new ArrayList<>();
            // 创建解析器
            SAXReader saxReader = new SAXReader();
            // 读取文件
            Document document = saxReader.read(Test.class.getResourceAsStream("/students.xml"));
            // 获得文件的根节点
            Element element = document.getRootElement();
            // 获得根节点的子节点
            List<Element> elements = element.elements();
            for (Element allEles : elements) {
                Attribute attribute = allEles.attribute("id");
                String attValue = attribute.getValue();   //获得它的id属性
                student.setId(attValue);
                List<Element> subEles = allEles.elements();
                // 获得子节点的key，value
                for (Element allSubEles : subEles) {
                    String key = allSubEles.getName();
                    String value = allSubEles.getStringValue();
                    String setMethod = "set" + (key.charAt(0) + "").toUpperCase() + key.substring(1);
                    Method method = clazz.getMethod(setMethod, String.class);
                    method.invoke(student, value);
                }
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public static void main(String[] args) {
        List<Student> students = readXml();
        for (Student allStus : students) {
            System.out.println(allStus);
        }
    }
}
