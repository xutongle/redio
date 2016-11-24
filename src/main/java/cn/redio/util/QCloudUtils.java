package cn.redio.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcloud.Module.Vod;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Utilities.Json.JSONObject;
import com.qcloud.Utilities.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.TreeMap;

/**
 * Created by XIAOYAO on 2016/11/23 16:44.
 * 腾讯云视频点播操作
 */
public class QCloudUtils {

    private static final String SECRET_ID = "你的SECRET_ID";

    private static final String SECRET_KEY = "你的SECRET_KEY";

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

    /**
     * 删除视频分类
     * @param classId 分类id
     * @return 返回信息
     */
    public static TreeMap<String, Object> deleteClass(int classId) {
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
            String result = module.call("DeleteClass", params);

            logger.info("删除视频分类：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 视频上传
     * @param params 需要传入fileName文件路径，classId视频的分类ID
     * @return 返回信息
     */
    public static TreeMap<String, Object> multipartUploadVodFile(TreeMap<String, Object> params) {
        TreeMap<String, Object> config = new TreeMap<>();
        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz");
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        try {
            System.out.println("开始上传视频...");
            File fileName = new File(String.valueOf(params.get("fileName")));
            long fileSize = fileName.length();
            String fileSHA1 = SHA1.fileNameToSHA(fileName.getPath());

            int fixDataSize = 1024 * 1024 * 50;  //每次上传字节数，可自定义
            int firstDataSize = 1024 * 100;    //切片上传：最小片字节数（默认不变）,如果：dataSize + offset > fileSize,把这个值变小即可
            int tmpDataSize = firstDataSize;
            long remainderSize = fileSize;
            int tmpOffset = 0;
            int code;
            int flag;
            String fileId;
            String result = null;

            if (remainderSize <= 0) {
                System.out.println("wrong file path...");
            }
            while (remainderSize > 0) {
                //视频文件本地名称，如果包含中文空格，则需要使用rawurlencode编码，长度在40个字符以内，不得包含\ / : * ? “ < > | 等字符
                params.put("fileName", fileName.getName().substring(0, fileName.getName().lastIndexOf(".")));
                //视频文件的sha，采用SHA-1计算文件内容
                params.put("fileSha", fileSHA1);
                //视频文件的总大小，单位字节Byte
                params.put("fileSize", fileSize);
                //本次上传的分片大小，单位字节Byte。dataSize值取值范围[524288~5242880]
                params.put("dataSize", tmpDataSize);
                //本次上传分片在视频文件的偏移量，第一片为0，后续偏移量可按照上一次操作的返回
                params.put("offset", tmpOffset);
                //视频文件的类型，根据后缀区分
                params.put("fileType", fileName.getName().substring(fileName.getName().lastIndexOf(".") + 1));
                params.put("file", fileName.getPath());
                //是否转码，0：否，1：是，默认为0；如果不执行转码，可在上传后，在管理控制台视频文件管理进行转码；
                params.put("isTranscode", 0);
                //是否截图，0：否，1：是，默认为0
                params.put("isScreenshot", 1);
                //是否打水印，0：否，1：是，默认为0；如果选择打水印，请务必在管理控制台提前完成水印文件选择和位置设定，否则可能导致上传失败
                params.put("isWatermark", 0);

                result = module.call("MultipartUploadVodFile", params);
                System.out.println(result);
                JSONObject json_result = new JSONObject(result);
                code = json_result.getInt("code");
                if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=10K,满足大多数视频的上传)
                    tmpDataSize = firstDataSize;
                    tmpOffset = 0;
                    continue;
                } else if (code != 0) {
                    return null;
                }
                flag = json_result.getInt("flag");
                if (flag == 1) {
                    fileId = json_result.getString("fileId");
                    break;
                } else {
                    tmpOffset = Integer.parseInt(json_result.getString("offset"));
                }
                remainderSize = fileSize - tmpOffset;
                if (fixDataSize < remainderSize) {
                    tmpDataSize = fixDataSize;
                } else {
                    tmpDataSize = (int) remainderSize;
                }
            }
            System.out.println("end...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error..." + e.toString());
        }
        return null;
    }

    /**
     * 获取视频信息列表
     * @param classId 视频分类ID，过滤使用
     * @return 视频信息列表
     */
    public static TreeMap<String, Object> describeVodInfo(int classId) {
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
            String result = module.call("DescribeVodInfo", params);

            logger.info("视频分类ID：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 获取视频播放信息列表
     * @param fileName 视频名称（前缀匹配）
     * @return 视频播放信息列表
     */
    public static TreeMap<String, Object> describeVodPlayInfo(String fileName) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            TreeMap<String, Object> params = new TreeMap<>();
            params.put("fileName", fileName);
            String result = module.call("DescribeVodPlayInfo", params);

            logger.info("获取视频播放信息列表：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 获取视频详细信息
     * @param fileId 希望获取的视频的ID
     * @return 视频详细信息
     */
    public static TreeMap<String, Object> describeVodPlayUrls(String fileId) {
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
            String result = module.call("DescribeVodPlayUrls", params);

            logger.info("获取视频详细信息：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 修改视频信息
     * @param params fileId文件id，fileName文件名称，fileIntro文件描述，classId分类id（int）
     * @return 返回信息
     */
    public static TreeMap<String, Object> modifyVodInfo(TreeMap<String, Object> params) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            String result = module.call("ModifyVodInfo", params);

            logger.info("修改视频信息：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 修改视频发布状态
     * 视频暂停或者恢复播放
     * @param fileId 视频ID
     * @param playStatus 视频播放状态，0为暂停，1为恢复
     * @return
     */
    public static TreeMap<String, Object> setVodPlayStatus(String fileId, int playStatus) {
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
            params.put("playStatus", playStatus);

            String result = module.call("SetVodPlayStatus", params);

            logger.info("修改视频发布状态：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 批量修改视频发布和分发状态
     * @param fileId 文件id
     * @param playStatus 视频播放状态，0为暂停，1为恢复
     * @param isPushCDN 是否发布cdn，0不发布，1发布
     * @return 返回信息
     */
    public static TreeMap<String, Object> multiSetVodPlayStatus(String fileId, int playStatus, int isPushCDN) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            TreeMap<String, Object> params = new TreeMap<>();
            params.put("pullset.0.fileId", fileId);
            params.put("pullset.0.playStatus", playStatus);
            params.put("pullset.0.isPushCDN", isPushCDN);

            String result = module.call("MultiSetVodPlayStatus", params);

            logger.info("批量修改视频发布和分发状态：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 批量获取视频截图地址
     * 针对具体文件，获取其不同尺寸下多张截图URL地址。
     * 文件按照id顺序排列，分别对应时间轴0%位置、10%位置，
     * 20%位置，至90%位置的截图。
     * @param fileId 文件id
     * @return 返回信息
     */
    public static TreeMap<String, Object> createScreenShot(String fileId) {
        TreeMap<String, Object> config = new TreeMap<>();

        config.put("SecretId", SECRET_ID);
        config.put("SecretKey", SECRET_KEY);
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz"); //默认区域，广州

        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);

        TreeMap<String, Object> resultMap = new TreeMap<>();

        try {
            TreeMap<String, Object> params = new TreeMap<>();
            params.put("pullset.0.fileId", fileId);

            String result = module.call("CreateScreenShot", params);

            logger.info("批量获取视频截图地址：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 获取播放器时间轴批量缩略图
     * 用于获取用于播放器时间轴上的缩略图。指定大小后按照以10秒/张
     * 的频率按照指定高宽截取。截取后的图片，将按照每100张组成为一
     * 张大图片的方式生成数个固定格式地址，用户可在操作后按照固定格式依次获取。
     * @param fileId 视频文件的id号
     * @return 返回信息
     */
    public static TreeMap<String, Object> describeScreenShot(String fileId) {
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
            params.put("width", 300);
            params.put("height", 300);

            String result = module.call("DescribeScreenShot", params);

            logger.info("获取播放器时间轴批量缩略图：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 为视频设置显示封面
     * @param fileId 视频文件的id
     * @param type 封面设置方法（1：使用截图url, 非1：上传本地图片 ）
     * @param para type值为1：para就是截图url，type非1值 ，para就是本地图片的本机路径）
     * @param imageData 若type其他值，表示用本地图片作为封面，则本参数表示该图片的base64字符串数据（只有在type值不为1时才有效)
     * @return 返回信息
     */
    public static TreeMap<String, Object> describeVodCover(String fileId, int type, String para, String imageData) {
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
            params.put("type", type);
            params.put("para", para);
            params.put("imageData", imageData);

            String result = module.call("DescribeVodCover", params);

            logger.info("为视频设置显示封面：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 对视频文件转码
     * 用于对已经上传的视频进行转码和添加水印。
     * 转码的具体配置和水印配置按照管理控制台的配置参数进行。
     * @param fileId 文件id
     * @param isScreenshot 是否截图，0不需要，1需要
     * @param isWatermark 是否添加水印，0不需要，1需要
     * @return 返回信息
     */
    public static TreeMap<String, Object> convertVodFile(String fileId, int isScreenshot, int isWatermark) {
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
            params.put("isScreenshot", isScreenshot);
            params.put("isWatermark", isWatermark);

            String result = module.call("ConvertVodFile", params);

            logger.info("对视频文件转码：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 批量获取转码时产生的截图
     * @param fileId 视频文件的id号
     * @return 视频截图信息
     */
    public static TreeMap<String, Object> describeAutoScreenShot(String fileId) {
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

            String result = module.call("DescribeAutoScreenShot", params);

            logger.info("批量获取转码时产生的截图：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 增加视频标签
     * @param fileId 视频的ID
     * @param tags 标签列表，字母数字和汉字，单个标签限长8个字
     * @return 返回信息
     */
    public static TreeMap<String, Object> createVodTags(String fileId, String tags) {
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
            params.put("tags.1", tags);

            String result = module.call("CreateVodTags", params);

            logger.info("增加视频标签：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 删除视频标签
     * @param fileId 视频的ID
     * @param tags 标签列表，字母数字和汉字，单个标签限长8个字
     * @return 返回信息
     */
    public static TreeMap<String, Object> deleteVodTags(String fileId, String tags) {
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
            params.put("tags.1", tags);

            String result = module.call("DeleteVodTags", params);

            logger.info("删除视频标签：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 删除视频文件
     * @param fileId 视频id
     * @param priority 可填0；优先级0:中 1：高 2：低
     * @return 返回信息
     */
    public static TreeMap<String, Object> deleteVodFile(String fileId, int priority) {
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
            params.put("priority", priority);

            String result = module.call("DeleteVodFile", params);

            logger.info("删除视频文件：" + result);

            resultMap = new ObjectMapper().readValue(result, TreeMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

}
