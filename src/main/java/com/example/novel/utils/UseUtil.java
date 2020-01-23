package com.example.novel.utils;

import java.util.Map;

public class UseUtil {

    public static Map<String,Object> mapAdd(Map<String,Object> map,Boolean b){
        if(b){
            map.put("code","404");
            map.put("message","没找到");
        }else {
            map.put("code","200");
            map.put("message","成功");
        }
        return map;
    }
}
