package com.redio.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;

/**
 * Created by XIAOYAO on 2016/10/21.
 * 视频点播服务接口调用
 */
public class VideoPointAPI {

    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final String ENCODE_TYPE = "UTF-8";

    private static final String HTTP_METHOD = "GET";

    private static final String ALGORITHM = "HmacSHA1";

    private static final String ENCODING = "UTF-8";

    private static final String secret = "youkey"; //此处请替换成您的AccessKeySecret

    private static final String SEPARATOR = "&";

    private static final String EQUAL = "=";

    private static Map<String, String> parameterMap = new HashMap<>();

    private static StringBuilder stringToSign = new StringBuilder();

    public static void videoPoint() throws UnsupportedEncodingException {
        //加入请求公共参数
        parameterMap.put("Action", "QueryMediaList");
        parameterMap.put("Version", "2014-06-18");
        parameterMap.put("AccessKeyId", "testId"); //此处替换成自己的AccessKeyId
        parameterMap.put("Timestamp", formatIso8601Date(new Date()));
        parameterMap.put("SignatureMethod", "HMAC-SHA1");
        parameterMap.put("SignatureVersion", "1.0");
        parameterMap.put("SignatureNonce", UUID.randomUUID().toString());
        parameterMap.put("Format", "XML");

        //加入方法特有参数
        parameterMap.put("MediaIds", "68a4d2629a339db3207963ac073a88cd");

        //对参数进行排序
        List<String> sortedKeys = new ArrayList<>(parameterMap.keySet());
        Collections.sort(sortedKeys);

        //生成stringToSign字符
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);

        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            //此处需要对key和value进行编码
            String value = parameterMap.get(key);
            canonicalizedQueryString.append(SEPARATOR).append(percentEncode(key)).append(EQUAL).append(percentEncode(value));
        }

        //此处需要对canonicalizedQueryString进行编码
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));
    }

    /**
     * 生成规范的Timestamp字符串
     *
     * @param date 日期
     * @return 规范后的日期
     */
    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    /**
     * 生成规范请求字符串，以及stringToSign时，都需要进行必要的编码
     *
     * @param value 需要规范的字符串
     * @return 规范后的字符串
     * @throws UnsupportedEncodingException 字符处理异常
     */
    private static String percentEncode(String value) throws UnsupportedEncodingException {
        if (value == null) return null;
        return URLEncoder.encode(value, ENCODE_TYPE).replace("+", "%20").replace("%7E", "~");
    }

    /**
     * 计算签名
     *
     * @throws InvalidKeyException          异常
     * @throws NoSuchAlgorithmException     异常
     * @throws UnsupportedEncodingException 异常
     */
    public static String computing() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        SecretKey key = new SecretKeySpec((secret + SEPARATOR).getBytes(ENCODE_TYPE), SignatureMethod.HMAC_SHA1);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(key);

        String signatrue = URLEncoder.encode(new String(new Base64().encode(mac.doFinal(stringToSign.toString().getBytes(ENCODE_TYPE))), ENCODE_TYPE), ENCODE_TYPE);

        //生成请求URL
        StringBuilder requestURL;
        requestURL = new StringBuilder("http://mts.aliyuncs.com?");
        requestURL.append(URLEncoder.encode("Signature", ENCODE_TYPE)).append("=").append(signatrue);
        for (Map.Entry<String, String> e : parameterMap.entrySet()) {
            requestURL.append("&").append(percentEncode(e.getKey())).append("=").append(percentEncode(e.getValue()));
        }

        return requestURL.toString();
    }

}
