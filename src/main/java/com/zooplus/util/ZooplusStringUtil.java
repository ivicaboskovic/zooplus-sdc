package com.zooplus.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZooplusStringUtil {

	private final static String algorithm = "SHA";

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static final String encryptPassword(String password) {
		byte[] plainText = password.getBytes();
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}

		md.reset();
		md.update(plainText);
		byte[] encodedPassword = md.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				sb.append("0");
			}

			sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return sb.toString();
	}

	public static String formatDate(Date date) {
		return df.format(date);
	}	

}
