/*
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2021-12-01 16:27:29
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-12-01 17:32:16
 */
package com.geekfanfan.think.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
	final static Base64.Decoder decoder = Base64.getDecoder();
	final static Base64.Encoder encoder = Base64.getEncoder();

	/**
	 * 编码
	 * 
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		String encodedText = "";
		try {
			byte[] textByte = text.getBytes("UTF-8");
			encodedText = encoder.encodeToString(textByte);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 编码
		return encodedText;
	}

	/**
	 * 解码
	 * 
	 * @param encodedText
	 */
	public static String decode(String encodedText) {
		String text = "";
		try {
			text = new String(decoder.decode(encodedText), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;

	}

	public static void main(String[] args) {

		System.out.println(Base64Util.encode("西安"));
	}
}
