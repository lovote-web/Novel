package com.example.novel.dao;

import com.example.novel.po.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("Select * from comment where novel_id=#{id}")
    public List<Comment> getByNovelId(Long id);

    @Insert("insert into comment(content,create_time,novel_id,parent_comment_id,goods) values(#{content},#{createTime},#{novelId},#{parentCommentId},#{goods})")
    public int insertComment(Comment comment);
}
