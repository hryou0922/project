package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicLinks;

import java.util.List;

public interface ComicLinksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComicLinks record);

    int insertSelective(ComicLinks record);

    ComicLinks selectByPrimaryKey(Integer id);
    
    List<ComicLinks> selectAllWithUnDeal();

    int updateByPrimaryKeySelective(ComicLinks record);

    int updateByPrimaryKey(ComicLinks record);
}