package cn.redio.util;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.TreeMap;

/**
 * Created by XIAOYAO on 2016/11/23 17:24.
 */
public class QCloudUtilsTest {

//    @Test
    public void createClassTest() {
        //77279
        //77314
        //77316
        System.out.println(QCloudUtils.createClass("demo3", 0));
    }

    @Test
    public void describeAllClassTest() {
        System.out.println("获取用户所有分类层级：" + QCloudUtils.describeAllClass());
    }

    @Test
    public void describeClassTest() {
        System.out.println("获取视频分类列表：" + QCloudUtils.describeClass());
    }

    @Test
    public void modifyClassTest() {
        System.out.println("修改分类名：" + QCloudUtils.modifyClass(77316, "测试3"));
    }

//    @Test
    public void deleteClassTest() {
        System.out.println("删除视频分类：" + QCloudUtils.deleteClass(77314));
    }

//    @Test
    public void multipartUploadVodFileTest() {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("fileName", "F:\\Warcraft3_End.avi");
        params.put("classId", 77279);
        System.out.println("视频上传：" + QCloudUtils.multipartUploadVodFile(params));
    }

    @Test
    public void describeVodInfoTest() {
        System.out.println("获取视频信息列表：" + QCloudUtils.describeVodInfo(77279));
    }

    @Test
    public void describeVodPlayInfoTest() {
        System.out.println("获取视频播放信息列表：" + QCloudUtils.describeVodPlayInfo("Warcraft3_End"));
    }

    @Test
    public void describeVodPlayUrlsTest() {
        System.out.println("获取视频详细信息：" + QCloudUtils.describeVodPlayUrls("14651978969456454658"));
    }

    @Test
    public void modifyVodInfoTest() {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("fileId", "14651978969456454658");
        params.put("fileName", "Warcraft3_End");
        params.put("fileIntro", "文件描述是什么");
        params.put("classId", 77279);
        System.out.println("修改视频信息:" + QCloudUtils.modifyVodInfo(params));
    }

    @Test
    public void setVodPlayStatusTest() {
        System.out.println(QCloudUtils.setVodPlayStatus("14651978969456454658", 0));
    }

    @Test
    public void multiSetVodPlayStatusTest() {
        System.out.println("批量修改视频发布和分发状态:" + QCloudUtils.multiSetVodPlayStatus("14651978969456454658", 1, 1));
    }

    @Test
    public void createScreenShotTest() {
        System.out.println("批量获取视频截图地址:" + QCloudUtils.createScreenShot("14651978969456454658"));
    }

    @Test
    public void describeScreenShotTest() {
        System.out.println("获取播放器时间轴批量缩略图:" + QCloudUtils.describeScreenShot("14651978969456454658"));
    }

    @Test
    public void describeVodCoverTest() {
        System.out.println("为视频设置显示封面:" + QCloudUtils.describeVodCover("14651978969456454658", 1, "aaa", "aaa"));
    }

    @Test
    public void convertVodFileTest() {
        System.out.println("对视频文件转码:" + QCloudUtils.convertVodFile("14651978969456454658", 1, 0));
    }

    @Test
    public void describeAutoScreenShotTest() {
        System.out.println("批量获取转码时产生的截图:" + QCloudUtils.describeAutoScreenShot("14651978969456454658"));
    }

    @Test
    public void createVodTagsTest() {
        System.out.println("增加视频标签:" + QCloudUtils.createVodTags("14651978969456454658", "标签是什么"));
    }

    @Test
    public void deleteVodTagsTest() {
        System.out.println("删除视频标签:" + QCloudUtils.deleteVodTags("14651978969456454658", "标签是什么"));
    }

    @Test
    public void deleteVodFileTest() {
        System.out.println("删除视频文件:" + QCloudUtils.deleteVodFile("14651978969456447102", 1));
    }

}