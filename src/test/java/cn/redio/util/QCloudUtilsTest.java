package cn.redio.util;

import org.junit.Test;

/**
 * Created by XIAOYAO on 2016/11/23 17:24.
 */
public class QCloudUtilsTest {

//    @Test
    public void createClass() {
        //77279
        //77314
        //77316
        System.out.println(QCloudUtils.createClass("demo3", 0));
    }

    @Test
    public void describeAllClass() {
        System.out.println("获取用户所有分类层级：" + QCloudUtils.describeAllClass());
    }

    @Test
    public void describeClass() {
        System.out.println("获取视频分类列表：" + QCloudUtils.describeClass());
    }

    @Test
    public void modifyClass() {
        System.out.println("修改分类名：" + QCloudUtils.modifyClass(77316, "测试3"));
    }

}
