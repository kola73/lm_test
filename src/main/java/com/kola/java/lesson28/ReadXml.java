package com.kola.java.lesson28;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

/*
xml:extensible markup language 可拓展标记语言
1，特点：可拓展性，在遵循xml语法的前提下支持自定义和修改
2，标记语言，具有结构性，意味着也是以类似于html中的标签来定义文档
3，xml在项目中多以数据载体的形式出现，xml和json都是一种数据交互格式
4，非常适合万维网传输，提供统一的方法描述和交换结构化数据
 */
public class ReadXml {
    public static List<Student> readXml() {
        //创建一个容器保存students对象
        List<Student> studentList = null;
        // 创建解析器
        SAXReader saxReader = new SAXReader();
        try {
            studentList = new ArrayList<>();
            // 读取students.xml文件
            Document document = saxReader.read(ReadXml.class.getResourceAsStream("/students.xml"));
            // 获得根节点(students)
            Element element = document.getRootElement();
            // 循环遍历，获得根节点下面的子节点(student)
            List<Element> elements = element.elements();
            for (Element listElements : elements) {
                Student student = new Student();
                // 获得student子元素的id属性
                Attribute attribute = listElements.attribute("id");
                // 获得id属性对应的值
                student.setId(attribute.getValue());
//                System.out.println(attribute.getName() + "-" + attribute.getValue());
                // 循环遍历，获得里面具体的值
                List<Element> allElements = listElements.elements();
                for (Element subElements : allElements) {
                    String eleName = subElements.getName();
                    String eleValue = subElements.getStringValue();
//                    System.out.println(eleName + "-" + eleValue);
                    // 写equals方法的时候，字面值放前面可以避免空指针
                    if ("name".equals(eleName)) {
                        student.setName(eleValue);
                    } else if ("age".equals(eleName)) {
                        student.setAge(eleValue);
                    } else if ("gender".equals(eleName)) {
                        student.setGender(eleValue);
                    }
                }
                studentList.add(student);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static void main(String[] args) {
        List<Student> lists = readXml();
        for (Student students : lists) {
            System.out.println(students);
        }
    }
}
