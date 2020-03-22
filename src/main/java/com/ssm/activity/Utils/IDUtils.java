package com.ssm.activity.Utils;

import java.util.UUID;

/**
 * ID生产器 UUID生成
 */
public class IDUtils {
    public static String getId(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    public static String getCode(int index) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, index);
        return uuid;
    }
}
