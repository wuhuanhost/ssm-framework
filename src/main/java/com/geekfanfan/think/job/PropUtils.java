/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 11:02:34
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-19 14:40:43
 */
package com.geekfanfan.think.job;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description 配置文件操作类
 * @Date 2019/12/19 20:09
 * @Created by Jason
 */
public class PropUtils {

    private static String propPath;

    private static Properties properties;

    /**
     * 读取配置文件
     * 
     * @param path
     * @throws IOException
     */
    private static void read(String path) throws IOException {
        if (path != null && !"".equals(path)) {
            if (properties == null) {
                // 可以读取任意路径的文件
                properties = new Properties();
                // 使用InPutStream流读取properties文件
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

                properties.load(bufferedReader);
            }

        }

        propPath = path;
    }

    /**
     * 获取配置属性
     * 
     * @param propPath
     * @param key
     * @return
     */
    public static String getPropValue(String propPath, String key) {
        try {
            read(propPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(">>>>>>>> 读取配置文件时发生异常!");
        }

        return properties.getProperty(key);
    }

}