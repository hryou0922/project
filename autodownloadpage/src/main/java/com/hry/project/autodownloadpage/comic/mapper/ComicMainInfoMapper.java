package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicMainInfo;

public interface ComicMainInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComicMainInfo record);

    int insertSelective(ComicMainInfo record);

    ComicMainInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComicMainInfo record);

    int updateByPrimaryKey(ComicMainInfo record);
}