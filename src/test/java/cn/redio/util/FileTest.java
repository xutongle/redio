package cn.redio.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by XIAOYAO on 2016/11/24 9:59.
 */
public class FileTest {

    @Test
    public void filePathTest() throws IOException {
        File file = new File("F:\\60318.mp4");
        System.out.println("path:" + file.getPath());
        System.out.println("fileName:" + file.getName());
        System.out.println("文件后缀：" + file.getName().substring(file.getName().lastIndexOf(".") + 1));
        System.out.println("文件名：" + file.getName().substring(0, file.getName().lastIndexOf(".")));
    }
}
