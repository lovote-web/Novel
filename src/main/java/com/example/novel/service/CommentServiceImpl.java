package com.example.novel.service;

import com.example.novel.dao.CommentMapper;
import com.example.novel.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getComments(Long id) {
        return commentMapper.getByNovelId(id);
    }
}
