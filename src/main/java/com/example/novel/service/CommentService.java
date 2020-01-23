package com.example.novel.service;

import com.example.novel.po.Comment;

import java.util.List;

public interface CommentService {

    public int insertComment(Comment comment);

    public List<Comment> getComments(Long id);
}
