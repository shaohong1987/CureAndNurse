package com.shaohong.cureandnurse.utils;

import java.util.HashMap;

/**
 * Created by shaohong on 2017/5/2.
 */

public class ConstantUtils {
    public static final String UPDATE_URL="http://192.168.1.121:8080/update.json";
    public static final String PACKAGE_NAME="com.shaohong.cureandnurse";
    public static final String APK_VERSION="version";
    public static final String APK_NAME="name";
    public static final String APK_URL="url";

    public static HashMap<Integer,String> EXAM_OPTIONS() {
        HashMap<Integer, String> examOptions = new HashMap<>();
        examOptions.put(0, "全院考试");
        examOptions.put(1, "科室考试");
        examOptions.put(2, "专科考试");
        return examOptions;
    }
}
