package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicAuthor;

public interface ComicAuthorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComicAuthor record);

    int insertSelective(ComicAuthor record);

    ComicAuthor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComicAuthor record);

    int updateByPrimaryKey(ComicAuthor record);
}