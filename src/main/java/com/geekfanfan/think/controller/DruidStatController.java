/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-23 16:53:20
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-23 16:53:45
 */
package com.geekfanfan.think.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/druid")
public class DruidStatController {

	@GetMapping("/stat")
	public Object druidStat() {
		// 获取数据源的监控数据
		return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
	}
}