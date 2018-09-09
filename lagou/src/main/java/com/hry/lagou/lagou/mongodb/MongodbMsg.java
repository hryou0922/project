package com.hry.lagou.lagou.mongodb;

import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class MongodbMsg {

    private final MongoDbFactory mongo;

    @Autowired
    public MongodbMsg(MongoDbFactory mongo) {
        this.mongo = mongo;
    }

    public void example() {
        MongoDatabase db = mongo.getDb();
        db.createCollection("abc");


    }


}
