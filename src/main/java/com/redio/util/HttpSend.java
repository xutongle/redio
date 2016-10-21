package com.redio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by XIAOYAO on 2016/10/21.
 * 发送http请求工具
 * 用来发送get，post请求，可以模拟一切常见的http请求。也可以用来获取JSON数据等
 */
public class HttpSend {

    /**
     * 发送get请求
     * @param url 请求地址
     * @param list 请求参数
     * @return 请求结果
     * @throws IOException 抛出IO异常
     */
    public static String sendGet(String url, List<HTTPParam> list) throws IOException {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URL httpUrl; //HTTP URL类 用这个类来创建连接
        URLConnection connection; //创建的http连接
        BufferedReader bufferedReader; //接受连接的参数
        //如果存在参数，我们才需要拼接参数 类似于localhost/index.html?a=a&b=b
        if (list != null && list.size() > 0) {
            for (int i = 0, z = list.size(); i < z; i++) {
                buffer.append(list.get(i).getKey()).append("=").append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));
                //如果不是最后一个参数，不需要添加&
                if ((i + 1) < list.size()) {
                    buffer.append("&");
                }
            }
            url = url + "?" + buffer.toString();
        }

        //创建URL
        httpUrl = new URL(url);
        //建立连接
        connection = httpUrl.openConnection();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        connection.connect();

        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        bufferedReader.close();
        return result.toString();
    }

    /**
     * 发送post请求
     * @param url 请求地址
     * @param list 请求参数
     * @return 请求结果
     * @throws IOException 抛出IO异常
     */
    public static String sendPost(String url, List<HTTPParam> list) throws IOException {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URL httpUrl; //HTTP URL类 用这个类来创建连接
        URLConnection connection; //创建的http连接
        PrintWriter printWriter;
        BufferedReader bufferedReader; //接受连接的参数
        //创建URL
        httpUrl = new URL(url);
        //建立连接
        connection = httpUrl.openConnection();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        printWriter = new PrintWriter(connection.getOutputStream());

        if (list != null && list.size() > 0) {
            for (int i = 0, z = list.size(); i < z; i++) {
                buffer.append(list.get(i).getKey()).append("=").append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));
                //如果不是最后一个参数，不需要添加&
                if ((i + 1) < list.size()) {
                    buffer.append("&");
                }
            }
        }

        printWriter.print(buffer.toString());
        printWriter.flush();
        connection.connect();
        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }

}
