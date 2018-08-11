package com.acfun.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成代码辅助类
 *
 * @author kwer
 * @Date 2018/6/16/016 12:17
 */
public class GeneratedCodeUtils {

    /**
     * 实体映射代码生成
     * kwer 自用
     */
    private static void generatedEntityMapping() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "bb1");
        map.put("aa2", "bb2");
        map.put("aa3", "bb3");
        map.put("aa5", "bb5");
        String entityName = "outputRoomFilesBO";
        String entityName2 = "bgyRoomTransactionFile";
        map.forEach((s, s2) -> {
            System.out.println(entityName + ".set" + s.substring(0, 1).toUpperCase() + s.substring(1) + "(" +
                    entityName2 + ".get" + s2.substring(0, 1).toUpperCase() + s2.substring(1) + ");");
        });
    }

    public static void main(String[] args) {
        generatedEntityMapping();
    }
}
