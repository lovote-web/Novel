package com.example.novel.dao;

import com.example.novel.po.Billboard;
import com.example.novel.po.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillboardMapper {

    //通过id获取榜单的novel信息
    public Billboard getBillboard(Long id);

    public List<Billboard> getAllBillboard();

    @Select("select n.* from novel n,billboard_novel t where t.id=#{id} and t.novel_id=n.id")
    public List<Novel> getBillboardNovels(Long id);

    @Select("select n.* from novel n,billboard_novel t where t.id=#{id} and t.novel_id=n.id limit 3")
    public List<Novel> getBillboardNovelTop3(Long id);

}
