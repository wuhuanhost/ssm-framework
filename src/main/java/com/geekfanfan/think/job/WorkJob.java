/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 10:59:23
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2020-11-19 13:59:24
 */
package com.geekfanfan.think.job;

import org.quartz.*;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 任务作业
 * @Date 2019/12/19 19:26
 * @Created by Jason
 */
// 保证任务一个接着一个执行
@Slf4j
@DisallowConcurrentExecution
public class WorkJob implements Job {

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println(" ===== 开始执行作业! ======");

		JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
		String command = (String) dataMap.get("command"); // 接收JobDetail 传递过来的参数
		executeWork(command); // 若需要个性化定制任务, 再这里写你的逻辑就行啦
	}

	// 执行具体作业
	private void executeWork(String command) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		log.info("定时任务执行时间：" + sdf.format(date));
		try {
			// 可以执行,windows / linux 命令, 脚本等
			System.out.println(command);
			Process exec = Runtime.getRuntime().exec("cmd.exe /c " + command);
			BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			String readLine = br.readLine();
			while (readLine != null) {
				readLine = br.readLine();
				System.out.println(readLine);
			}
			if (br != null) {
				br.close();
			}
			exec.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}