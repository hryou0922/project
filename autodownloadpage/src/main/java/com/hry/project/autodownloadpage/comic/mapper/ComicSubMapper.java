package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicSub;

public interface ComicSubMapper {
    int deleteByPrimaryKey(Integer comicsId);

    int insert(ComicSub record);

    int insertSelective(ComicSub record);

    ComicSub selectByPrimaryKey(Integer comicsId);

    int updateByPrimaryKeySelective(ComicSub record);

    int updateByPrimaryKey(ComicSub record);
}