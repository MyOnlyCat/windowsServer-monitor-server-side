package com.monitor.serverside.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

public class ServerUtil {

    /**
     * 请求腾讯的开源项目地址获取外网IP
     *
     * @return ip地址 string
     */
    public static String getIp() {
        String ip = "https://checkip.map.qq.com/";
        String inputLine = "";
        String read = "";
        try {
            URL url = new URL(ip);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((read = in.readLine()) != null) {
                inputLine += read;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputLine;
    }

    /**
     * 获取计算机名称
     *
     * @return 计算机名称
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }

    public static String getOsName() {
        Properties props = System.getProperties();
        return props.getProperty("os.name");
    }
}
