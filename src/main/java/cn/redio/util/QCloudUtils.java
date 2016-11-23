package cn.redio.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcloud.Module.Vod;
import com.qcloud.QcloudApiModuleCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeMap;

/**
 * Created by XIAOYAO on 2016/11/23 16:44.
 * 腾讯云视频点播操作
 */
public class QCloudUtils {

    private static final String SECRET_ID = "AKIDd9U6asMMecUAewtuaMxGNI1JWWKFpe3H";

    private static final String SECRET_KEY = "ACEE5kIgreO2UxmV2iIepnZAcsSwOky0";

    private static Logger logger = LoggerFactory.getLogger(QCloudUtils.class);

    /**
     * 创建视频分类
     * @param className 分类名称
     * @param parentId 父分类id号
     * @return 返回信息
     */
    public static TreeMap<String, Object> createClass(String className, int parentId) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> params = new TreeMap<>();
        params.put("className", className); //分类信息，分类名称
        if (0 != parentId) {
            params.put("parentId", parentId); //父分类的id号，若不填，默认生成一级分类
        }

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            String result = module.call("CreateClass", params);

            logger.info("创建视频分类：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 获取用户所有分类层级
     * @return 用户所有分类层级
     */
    public static TreeMap<String, Object> describeAllClass() {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            String result = module.call("DescribeAllClass", new TreeMap<>());

            logger.info("获取用户所有分类层级：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 获取视频分类列表
     * @return 视频分类列表
     */
    public static TreeMap<String, Object> describeClass() {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            String result = module.call("DescribeClass", new TreeMap<>());

            logger.info("获取视频分类列表：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 修改分类名
     * @param classId 待修改的分类id
     * @param className 新的分类名
     * @return 返回信息
     */
    public static TreeMap<String, Object> modifyClass(int classId, String className) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            TreeMap<String, Object> params = new TreeMap<>();
            params.put("classId", classId);
            params.put("className", className);
            String result = module.call("ModifyClass", params);

            logger.info("修改分类名：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 修改视频分类
     * @param fileId 视频id
     * @param classId 分类id
     * @return 返回信息
     */
    public static TreeMap<String, Object> modifyVodClass(String fileId, int classId) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            TreeMap<String, Object> params = new TreeMap<>();
            params.put("fileId", fileId);
            params.put("classId", classId);
            String result = module.call("ModifyVodClass", params);

            logger.info("修改视频分类：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
