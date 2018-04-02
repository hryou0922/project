package com.hry.project.autodownloadpage.comic.mapper;


import com.hry.project.autodownloadpage.comic.model.ComicRelationTag;

public interface ComicRelationTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComicRelationTag record);

    int insertSelective(ComicRelationTag record);

    ComicRelationTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComicRelationTag record);

    int updateByPrimaryKey(ComicRelationTag record);
}