package com.jack.mobliesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	public static String encode(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bytes = digest.digest(password.getBytes());// ���м�������,���ؼ��ܺ���ֽ�����

			StringBuffer sb = new StringBuffer();
			for (byte b : bytes) {
				int i = b & 0xff;
				String hexString = Integer.toHexString(i);
				// System.out.println(hexString);
				if (hexString.length() == 1) {
					hexString = "0" + hexString;
				}

				sb.append(hexString);
			}

			String md5 = sb.toString();

			return md5;
		} catch (NoSuchAlgorithmException e) {
			// û�д��㷨�쳣
			e.printStackTrace();
		}

		return null;
	}
}