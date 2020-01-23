package com.example.novel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.novel.dao.NovelMapper;
import com.example.novel.po.Novel;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class NovelServiceImpl implements NovelService {

    @Autowired
    NovelMapper novelMapper;


    @Override
    public List<Novel> getByNovelTypeId(Long id) {
        return novelMapper.findByTypeId(id);
    }

    @Override
    public List<Novel> OrderNovel(Long id,int t){
        List<Novel> novelList=getByNovelTypeId(id);
        if(novelList != null){
            switch (t){
                case 1: break;
                case 2: getHotNovel(novelList);break;
                case 3: getMarkNovel(novelList);break;
                case 4: getEndNovel(novelList);break;
            }
        }
        return novelList;
    }

    @Override
    public String UrlNovel(Long id) {
        return novelMapper.getNovelUrl(id);
    }

    @Override
    public Novel getByNovelId(Long id) {
        return novelMapper.getNovelById(id);
    }

    @Override
    public List<Novel> getTopNovels() {
        return novelMapper.getNovelOrderByTop();
    }

    @Override
    public Novel getNovelByUrl(String url) {
        return novelMapper.getNovelByUrl(url);
    }

    @Override
    public int insertNovel(Novel novel) {
        return novelMapper.insertNovel(novel);
    }


    public List<Novel> getHotNovel(List<Novel> novelList) {
        Collections.sort(novelList, new Comparator<Novel>() {
            @Override
            public int compare(Novel o1, Novel o2) {
                if(o1.getCollectionNumber()<o2.getCollectionNumber()){
                    return 1;
                }
                return -1;
            }
        });
        return novelList;
    }

    public List<Novel> getMarkNovel(List<Novel> novelList){
        Collections.sort(novelList, new Comparator<Novel>() {
            @Override
            public int compare(Novel o1, Novel o2) {
                if(o1.getMark()<o2.getMark()){
                    return 1;
                }
                return -1;
            }
        });
        return novelList;
    }

    public List<Novel> getEndNovel(List<Novel> novelList){
        for (int i=0;i<novelList.size();i++){
            if(!novelList.get(i).getIsEnd()){
                novelList.remove(i);
            }
        }
        return novelList;
    }

    @Override
    public List<Novel> getNovel(String name) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://api.pingcc.cn/?xsname="+name);
        CloseableHttpResponse response = null;
        List<Novel> list1=new ArrayList<>();
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String en= EntityUtils.toString(entity, "utf-8");// 可以指定编码格式（中文：utf-8或者GBK）
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
                        default:novel.setTypeId(Long.valueOf((int)(Math.random()*8+1)));break;
                    }
                    Novel novel1=novelMapper.getNovelByUrl(novel.getUrl());
                    if(novel1 == null){
                        novelMapper.insertNovel(novel);
                        novel1=novelMapper.getNovelByUrl(novel.getUrl());
                    }
                    novel.setId(novel1.getId());
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
}
