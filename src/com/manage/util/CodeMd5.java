package com.manage.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * ����MD5���м���
 * 
 * @param �����ܵ��ַ���
 * @return ���ܺ���ַ���
 * @throws NoSuchAlgorithmException
 * @throws UnsupportedEncodingException
 */
public class CodeMd5 {
	public String EncoderByMd5(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		// ȷ�����㷽��
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// ���ܺ���ַ���
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
}
