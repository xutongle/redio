package com.redio.util;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by XIAOYAO on 2016/10/21.
 * 视频点播接口测试
 */
public class VideoPointAPITest {

    /**
     * 视频点播接口测试
     */
    @Test
    public void videoPointTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        VideoPointAPI.videoPoint();
        String url = VideoPointAPI.computing();
        System.out.println(url);
        try {
            HttpSend.sendGet(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
