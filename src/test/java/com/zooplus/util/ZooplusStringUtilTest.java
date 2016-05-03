package com.zooplus.util;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class ZooplusStringUtilTest {

	@Test
	public void testEncription() {
		String password = ZooplusStringUtil.encryptPassword("12");
		assertEquals("7b52009b64fd0a2a49e6d8a939753077792b0554", password);
	}

	@Test
	public void testDateFormat() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 3);
		
		assertEquals("2016-05-03", ZooplusStringUtil.formatDate(calendar.getTime()));
	}

}
