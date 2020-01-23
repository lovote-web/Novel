package com.example.novel.controller;

import com.example.novel.dao.NovelMapper;
import com.example.novel.po.Chapter;
import com.example.novel.po.Comment;
import com.example.novel.po.Novel;
import com.example.novel.service.CommentService;
import com.example.novel.service.NovelService;
import com.example.novel.utils.NovelAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *小说
 */

@RestController
public class NovelController {

    @Autowired
    NovelService novelService;

    @Autowired
    CommentService commentService;

    /**
     * t:
     * 1--->按推荐
     * 2--->按访问量
     * 3--->按评分
     * 4--->按完结
     * 按分类和t找到所属书籍的部分内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/Type/{id}/{t}",method = RequestMethod.GET)
    public List<Novel> Novel(@PathVariable Long id,@PathVariable int t) {
        return novelService.OrderNovel(id,t);
    }

    /**
     * 获取小说的章节
     * @param id
     * @return
     */
    @RequestMapping(value = "/Chapter/{id}",method = RequestMethod.GET)
    public Map<String,Object> NovelChapter(@PathVariable Long id){
        String url=novelService.UrlNovel(id);
        List<Chapter> chapters= NovelAPI.getChapter(url);
        Map<String,Object> map=new HashMap<>();
        if (chapters==null){
            map.put("code",404);
            map.put("message","Not found");
        }else {
            map.put("code",200);
            map.put("message","success");
            map.put("chapter",chapters);
        }
        return map;
    }

    /**
     * 通过url 返回章节z
     * @param url
     * @return
     */
    @RequestMapping(value = "/Chapter",method = RequestMethod.POST)
    public Map<String,Object> NovelChapter2(@RequestParam String url){
        List<Chapter> chapters= NovelAPI.getChapter(url);
        Map<String,Object> map=new HashMap<>();
        if (chapters == null){
            map.put("code",404);
            map.put("message","没有相关章节");
        }else {
            map.put("code",200);
            map.put("message","success");
            map.put("chapter",chapters);
        }
        return map;
    }

    @RequestMapping(value = "/Novel/{id}",method = RequestMethod.GET)
    public Novel novel(@PathVariable Long id){
        return novelService.getByNovelId(id);
    }


    /**
     * 搜索小说
     * @param name
     * @return
     */
    @RequestMapping(value = "/Search/{name}",method = RequestMethod.GET)
    public Map<String,Object> SearchNovel(@PathVariable String name){
        List<Novel> list=novelService.getNovel(name);
        Map<String,Object> map=new HashMap<>();
        if(list==null){
            map.put("code","404");
            map.put("message","没找到");
        }else {
            map.put("code","200");
            map.put("novel",list);
            map.put("message","成功");
        }
        return map;
    }

    /**
     * 推荐榜单前十
     * @return
     */
    @RequestMapping(value = "/Top10",method = RequestMethod.GET)
    public Map<String,Object> TopNovel() {
        Map<String, Object> map = new HashMap<>();
        List<Novel> list = novelService.getTopNovels();
        map.put("topNovels", list);
        return map;
    }

    /**
     * 发表评论
     * @param comment
     * @return
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Map<String, Object> setComment(Comment comment){
        Map<String,Object> map=new HashMap<>();
        comment.setId(null);
        comment.setCreateTime(new Date());
        if (commentService.insertComment(comment)==1){
            map.put("code","200");
            map.put("message","操作成功");
        }else {
            map.put("code","400");
            map.put("message","操作失败");
        }
        return map;
    }

    /**
     * 获取comment 通过novelId
     * @param novelId
     * @return
     */
    @RequestMapping(value = "/comment/{novelId}",method = RequestMethod.GET)
    public Map<String ,Object> getComment(@PathVariable Long novelId){
        Map<String,Object> map=new HashMap<>();
        List<Comment> comments=commentService.getComments(novelId);
        map.put("code","200");
        map.put("message","success");
        map.put("comments",comments);
        return map;
    }
}
