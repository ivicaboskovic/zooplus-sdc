package com.zooplus.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

public class ZooplusStringUtil {

	private final static String algorithm = "SHA";

	private static final Pattern phoneNumberPattern = Pattern.compile("^((\\+)(968))?(\\d{8})$");
	
	private static final Pattern passwordPattern = Pattern.compile("^((?=.*\\d)(?=.*[a-zA-Z]).{6,25})$");

	private static Pattern crIdMatcher = Pattern.compile("\\d*");
	private static Pattern idMatcher = Pattern.compile("\\d*");
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

	public static final String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	public static String getPriceString(Double price) {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(Locale
				.forLanguageTag("sr"));
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", dfs);
		if (price != null)
			return decimalFormat.format(price);
		else
			return "";
	}

/*	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
*/
	public static boolean isValidMobileNumber(String phoneNumber) {
		Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidPassword(String password) {
		Matcher matcher = passwordPattern.matcher(password);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String formatDate(Date date) {
		return df.format(date);
	}
	
	public static final String stringToHex(String text) {
		char[] chars = text.toCharArray();
        String next;
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            next = Integer.toHexString((int)chars[i]);
            // Unfortunately, toHexString doesn't pad with zeroes, so we have to.
            for (int j = 0; j < (4-next.length()); j++)  {
                output.append("0");
            }
            output.append(next);
        }
        return output.toString();
	}
	
public static Calendar getCalendarFromISO(String datestring) {
	    
	    
	    Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()) ;
	    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HHmmss'Z'", Locale.getDefault());
	    try {
	      Date date = dateformat.parse(datestring);
	      date.setHours(date.getHours()-1); 
	      calendar.setTime(date);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    
	    
	    return calendar;
	  }
}
