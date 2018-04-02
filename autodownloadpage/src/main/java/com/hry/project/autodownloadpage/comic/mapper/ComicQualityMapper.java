package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicQuality;

public interface ComicQualityMapper {
    int deleteByPrimaryKey(Integer comic);

    int insert(ComicQuality record);

    int insertSelective(ComicQuality record);

    ComicQuality selectByPrimaryKey(Integer comic);

    int updateByPrimaryKeySelective(ComicQuality record);

    int updateByPrimaryKey(ComicQuality record);
}