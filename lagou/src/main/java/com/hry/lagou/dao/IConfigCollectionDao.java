package com.hry.lagou.dao;

import com.hry.lagou.model.ConfigCollectionModel;

import java.util.List;

public interface IConfigCollectionDao {

    void insert(ConfigCollectionModel configCollectionModel);

    ConfigCollectionModel queryByCity(String city);

    void updateConfig(String city, String kds, int pageNum);

    void updateLastQueryTime(String city, List<ConfigCollectionModel.ConfigModel> list);
}
