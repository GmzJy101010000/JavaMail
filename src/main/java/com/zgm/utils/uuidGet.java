package com.zgm.utils;

import java.util.UUID;

public class uuidGet {

    public static String getCode() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
