package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicTag;

public interface ComicTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComicTag record);

    int insertSelective(ComicTag record);

    ComicTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComicTag record);

    int updateByPrimaryKey(ComicTag record);
}