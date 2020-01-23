package com.example.novel;

import com.example.novel.dao.*;
import com.example.novel.po.Comment;
import com.example.novel.po.User;
import com.example.novel.service.NovelService;
import com.example.novel.utils.NovelAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.Date;

@SpringBootTest
class NovelApplicationTests {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    NovelMapper novelMapper;

    @Autowired
    NovelService novelService;

    @Autowired
    BillboardMapper billboardMapper;

    @Autowired
    BookListMapper bookListMapper;

    @Test
    public void hello(){
        Comment comment=new Comment();
        comment.setNovelId(Long.valueOf(1));
        comment.setContent("NiHao");
        comment.setCreateTime(new java.util.Date());
        commentMapper.insertComment(comment);
    }

    @Test
    public void Hello1(){
//        System.out.println(NovelAPI.getNovel("斗破苍穹"));
//        System.out.println(novelMapper.getNovelByUrl("https://www.xsbiquge.com/0_657/"));
//        System.out.println(novelService.getNovel("斗破苍穹"));
//        System.out.println(billboardMapper.getAllBillboard());
//        System.out.println(billboardMapper.getBillboard(Long.valueOf(1)));
//        System.out.println(bookListMapper.getAllBookList());
        System.out.println(bookListMapper.getOneBookList(Long.valueOf(1)));
    }
}
