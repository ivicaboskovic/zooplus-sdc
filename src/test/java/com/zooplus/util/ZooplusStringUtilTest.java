package com.zooplus.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZooplusStringUtilTest {

	@Test
	public void test() {
		String password = ZooplusStringUtil.encryptPassword("12");
		assertEquals("7b52009b64fd0a2a49e6d8a939753077792b0554", password);
	}

}
