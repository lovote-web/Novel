package com.example.novel.service;

import com.example.novel.po.Novel;

import java.util.List;

public interface NovelService {

    public List<Novel> getByNovelTypeId(Long id);

    public List<Novel> OrderNovel(Long id,int t);

    public String UrlNovel(Long id);

    public Novel getByNovelId(Long id);

    public List<Novel> getTopNovels();

    public Novel getNovelByUrl(String url);

    public int insertNovel(Novel novel);

    public List<Novel> getNovel(String name);
}
