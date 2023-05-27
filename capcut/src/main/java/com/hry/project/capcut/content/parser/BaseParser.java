package com.hry.project.capcut.content.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.BaseElementVo;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 基础类
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 22:31
 */
@Slf4j
public class BaseParser<T extends BaseElementVo> {
    protected JsonObject jsonObject;
    protected Class<T> baseContentVo;


    public T getVo(){
        return GsonBox.PUBLIC.fromJson(this.jsonObject, baseContentVo);
    }

    public void saveVo(T vo){
        // 保存时，如果有些值没有被转，则不能简单的替换
        JsonObject newJsonObject = GsonBox.PUBLIC.toJsonTree(vo).getAsJsonObject();
        saveVo0(newJsonObject, this.jsonObject);
    }

    /**
     *
     * @param newJsonObject 源，修改后的值
     * @param desJsonObject 目的，准备保存文件
     */
    private void saveVo0(JsonObject newJsonObject, JsonObject desJsonObject){
        for(Map.Entry<String, JsonElement> entry : newJsonObject.entrySet()){
            String key = entry.getKey();
            JsonElement val = entry.getValue();
            if(val instanceof JsonObject){
                JsonObject tmpNewJsonObject = (JsonObject) val;
                log.debug("save key={} newJsonObject={}", key, tmpNewJsonObject);
                saveVo0(tmpNewJsonObject, desJsonObject.get(key).getAsJsonObject());
            }else if( val instanceof JsonArray){
                JsonArray jsonArray = (JsonArray) val;
                // 如果对象是 jsonObject，则循环进去，否则外部直接替换
                boolean isElementJsonObject = true;
                for(int i = 0; i < jsonArray.size(); i++){
                    JsonElement tmpJsonArrayElement = jsonArray.get(i);
                    log.debug("save key={} newJsonElemnt={}", key, tmpJsonArrayElement);
                    if(tmpJsonArrayElement.isJsonObject()){
                        isElementJsonObject = true;
                        saveVo0(tmpJsonArrayElement.getAsJsonObject(), desJsonObject.get(key).getAsJsonArray().get(i).getAsJsonObject());
                    }else {
                        log.info("save key={} newJsonElemnt={} 元素不是jsonobject，直接跳出，在外部进行替换", key, tmpJsonArrayElement);
                        isElementJsonObject = false;
                        break;
                    }
                }
                if(!isElementJsonObject){
                    desJsonObject.add(key, val);
                }
            }else {
                desJsonObject.add(key, val);
            }
        }
    }

    public String getId(){
        return getString("id");
    }

    public void putId(String id){
        putString("id", id);
    }

    public String getString(String name){
        return jsonObject.get(name).getAsString();
    }

    public void putString(String name, String value){
        jsonObject.addProperty(name, value);
    }

    public long getLong(String name){
        return jsonObject.get(name).getAsLong();
    }

    public void putLong(String name, long value){
        jsonObject.addProperty(name, value);
    }

    public int getInt(String name){
        return jsonObject.get(name).getAsInt();
    }

    public void putInt(String name, int value){
        jsonObject.addProperty(name, value);
    }

}
