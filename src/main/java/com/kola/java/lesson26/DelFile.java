package main.java.com.kola.java.lesson26;

import java.io.File;

/*
递归所具备的条件：
1，子问题与原始问题为同样的事，且更为简单
2，不能无限循环的调用本身，需要有一个出口，化简为非递归状况处理
 */
public class DelFile {
    // 运用递归算法删除文件
    public static void delFiles(String path) {
        File file = new File(path);
        File[] allFile = file.listFiles();  //列出所有文件
        for (File files : allFile) {    //循环遍历，是空目录或者文件，直接删除
            if (files.isFile()) {
                files.delete();
            } else {
                delFiles(files.getAbsolutePath()); // 如果不是的话，得到里面的文件，然后递归删除
            }
        }
        file.delete();  //删除空目录
    }

    public static void main(String[] args) {
        delFiles("C:\\Users\\YH\\Desktop\\hello");
    }
}
