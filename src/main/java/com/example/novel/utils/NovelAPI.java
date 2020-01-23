package com.example.novel.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.novel.dao.NovelMapper;
import com.example.novel.po.Chapter;
import com.example.novel.po.Novel;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class NovelAPI {
    // main Alt+?
    public static List<Novel> getNovel(String name) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://api.pingcc.cn/?xsname="+name);
        CloseableHttpResponse response = null;
        List<Novel> list1=new ArrayList<>();
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String en=EntityUtils.toString(entity, "utf-8");// 可以指定编码格式（中文：utf-8或者GBK）
            JSONObject jsonObject = JSON.parseObject(en);
            Map<String,Object> map=(Map<String, Object>)JSONObject.parse(String.valueOf(jsonObject));
            if(map.get("code").equals(0)){
                JSONArray array= (JSONArray) map.get("list");
                List list= (List) JSONArray.parse(String.valueOf(array));
                for (int i=0;i<list.size();i++){
                    JSONObject jsonObject1= (JSONObject) list.get(i);
                    Novel novel=new Novel();
                    novel.setNPhoto(jsonObject1.getString("cover"));
                    novel.setDescription(jsonObject1.getString("introduce"));
                    novel.setNAnthor(jsonObject1.getString("author"));
                    novel.setNName(jsonObject1.getString("name"));
                    novel.setUrl(jsonObject1.getString("url"));
                    if("连载中".equals(jsonObject1.getString("status"))){
                        novel.setIsEnd(Boolean.FALSE);
                    }else {
                        novel.setIsEnd(Boolean.TRUE);
                    }
                    switch (jsonObject1.getString("tag")){
                        case ("玄幻奇幻"): novel.setTypeId(Long.valueOf(1));break;
                        case ("武侠仙侠"): novel.setTypeId(Long.valueOf(2));break;
                        case ("都市言情"): novel.setTypeId(Long.valueOf(3));break;
                        case ("历史军事"): novel.setTypeId(Long.valueOf(4));break;
                        case ("科幻灵异"): novel.setTypeId(Long.valueOf(5));break;
                        case ("网游竞技"): novel.setTypeId(Long.valueOf(6));break;
                        case ("女生频道"): novel.setTypeId(Long.valueOf(7));break;
                        case ("同人小说"): novel.setTypeId(Long.valueOf(8));break;
                        default:novel.setTypeId(Long.valueOf((int)(Math.random()*8+1)));
                    }
                    list1.add(novel);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list1;
    }

    //获取小说章节
    public static List<Chapter> getChapter(String url){
        CloseableHttpClient client = HttpClients.createDefault();
        // 2.创建一个get请求方法
        HttpGet get = new HttpGet("http://api.pingcc.cn/?xsurl1="+url);
        CloseableHttpResponse response = null;
        List<Chapter> list=null;
        try {
            // 3.执行请求，获取到响应
            response = client.execute(get);
            // 实体
            HttpEntity entity = response.getEntity();
            // EntityUtils实体类的工具包 ，将实体对象转成Stirng或者byte
            String en=EntityUtils.toString(entity, "utf-8");// 可以指定编码格式（中文：utf-8或者GBK）
            JSONObject jsonObject = JSON.parseObject(en);
            Map<String,Object> map=(Map<String, Object>)JSONObject.parse(String.valueOf(jsonObject));
            JSONArray array= (JSONArray) map.get("list");
            list= (List<Chapter>) JSONArray.parse(String.valueOf(array));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
}