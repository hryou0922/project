package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicRelationAuthor;

public interface ComicRelationAuthorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComicRelationAuthor record);

    int insertSelective(ComicRelationAuthor record);

    ComicRelationAuthor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComicRelationAuthor record);

    int updateByPrimaryKey(ComicRelationAuthor record);
}