/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-01 16:18:08
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-01 16:18:30
 */
package com.geekfanfan.think.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
	private String clientId;
	private String base64Secret;
	private String name;
	private int expiresSecond;
}