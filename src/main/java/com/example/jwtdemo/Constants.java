package com.example.jwtdemo;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static Map<String, String> mockDB_name_password = new HashMap<>();
    public static Map<String, String> mockDB_id_name = new HashMap<>();
    public static String secret = "weimin";

    public static String key = "weimin1234567890";

    static {
        mockDB_name_password.put("tom", "tom");
        mockDB_name_password.put("jerry", "jerry");

        mockDB_id_name.put("tom", "1");
        mockDB_id_name.put("jerry", "2");
    }
}
