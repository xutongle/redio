package cn.redio.util.QCloud;

import org.junit.Test;

import java.util.TreeMap;

/**
 * Created by XIAOYAO on 2016/11/23 17:24.
 */
public class VodUtilsTest {

//    @Test
    public void createClassTest() {
        //77279
        //77314
        //77316
        System.out.println(VodUtils.createClass("demo3", 0));
    }

    @Test
    public void describeAllClassTest() {
        System.out.println("获取用户所有分类层级：" + VodUtils.describeAllClass());
    }

    @Test
    public void describeClassTest() {
        System.out.println("获取视频分类列表：" + VodUtils.describeClass());
    }

    @Test
    public void modifyClassTest() {
        System.out.println("修改分类名：" + VodUtils.modifyClass(77316, "测试3"));
    }

//    @Test
    public void deleteClassTest() {
        System.out.println("删除视频分类：" + VodUtils.deleteClass(77314));
    }

//    @Test
    public void multipartUploadVodFileTest() {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("fileName", "F:\\Warcraft3_End.avi");
        params.put("classId", 77279);
        System.out.println("视频上传：" + VodUtils.multipartUploadVodFile(params));
    }

    @Test
    public void describeVodInfoTest() {
        System.out.println("获取视频信息列表：" + VodUtils.describeVodInfo(77279));
    }

    @Test
    public void describeVodPlayInfoTest() {
        System.out.println("获取视频播放信息列表：" + VodUtils.describeVodPlayInfo("Warcraft3_End"));
    }

    @Test
    public void describeVodPlayUrlsTest() {
        System.out.println("获取视频详细信息：" + VodUtils.describeVodPlayUrls("14651978969456454658"));
    }

    @Test
    public void modifyVodInfoTest() {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("fileId", "14651978969456454658");
        params.put("fileName", "Warcraft3_End");
        params.put("fileIntro", "文件描述是什么");
        params.put("classId", 77279);
        System.out.println("修改视频信息:" + VodUtils.modifyVodInfo(params));
    }

    @Test
    public void setVodPlayStatusTest() {
        System.out.println(VodUtils.setVodPlayStatus("14651978969456454658", 0));
    }

    @Test
    public void multiSetVodPlayStatusTest() {
        System.out.println("批量修改视频发布和分发状态:" + VodUtils.multiSetVodPlayStatus("14651978969456454658", 1, 1));
    }

    @Test
    public void createScreenShotTest() {
        System.out.println("批量获取视频截图地址:" + VodUtils.createScreenShot("14651978969456454658"));
    }

    @Test
    public void describeScreenShotTest() {
        System.out.println("获取播放器时间轴批量缩略图:" + VodUtils.describeScreenShot("14651978969456454658"));
    }

    @Test
    public void describeVodCoverTest() {
        System.out.println("为视频设置显示封面:" + VodUtils.describeVodCover("14651978969456454658", 1, "aaa", "aaa"));
    }

    @Test
    public void convertVodFileTest() {
        System.out.println("对视频文件转码:" + VodUtils.convertVodFile("14651978969456454658", 1, 0));
    }

    @Test
    public void describeAutoScreenShotTest() {
        System.out.println("批量获取转码时产生的截图:" + VodUtils.describeAutoScreenShot("14651978969456454658"));
    }

    @Test
    public void createVodTagsTest() {
        System.out.println("增加视频标签:" + VodUtils.createVodTags("14651978969456454658", "标签是什么"));
    }

    @Test
    public void deleteVodTagsTest() {
        System.out.println("删除视频标签:" + VodUtils.deleteVodTags("14651978969456454658", "标签是什么"));
    }

    @Test
    public void deleteVodFileTest() {
        System.out.println("删除视频文件:" + VodUtils.deleteVodFile("14651978969456447102", 1));
    }

}