package com.example.novel.dao;

import com.example.novel.po.Novel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NovelMapper {

    /**
     * 通过分类Id找到所属小说部分内容
     * @param id
     * @return
     */
    @Select("select id,n_name,n_anthor,description,n_photo,mark,isEnd from novel where type_id=#{id}")
    public List<Novel> findByTypeId(Long id);

    @Select("select url from novel where id=#{id}")
    public String getNovelUrl(Long id);

    /**
     * 通过小说id获得所有内容
     * @param id
     * @return
     */
    public Novel getNovelById(Long id);

    @Select("SELECT id,n_name,n_anthor,description,n_photo,mark FROM novel ORDER BY mark DESC LIMIT 10")
    public List<Novel> getNovelOrderByTop();

    @Select("Select * from novel where n_name=#{name}")
    public Novel getNovelByName(String name);

    @Select("select * from novel where url=#{url}")
    public Novel getNovelByUrl(String url);

    @Insert("insert into novel(n_name,n_anthor,description,n_photo,type_id,collection_number,mark,isEnd,url) values(#{nName},#{nAnthor},#{description},#{nPhoto},#{typeId},#{collectionNumber},#{mark},#{isEnd},#{url})")
    public int insertNovel(Novel novel);

}
