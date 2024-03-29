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
        if(this.jsonObject.size() == 0){
            simpeCopyJsonObject(newJsonObject, this.jsonObject);
        }else {
            saveVo0(newJsonObject, this.jsonObject);
        }
    }

    /**
     * 覆盖更新
     * @param vo
     */
    public void putVo(T vo){
        // 保存时，如果有些值没有被转，则不能简单的替换
        JsonObject newJsonObject = GsonBox.PUBLIC.toJsonTree(vo).getAsJsonObject();
        simpeCopyJsonObject(newJsonObject, this.jsonObject);
    }

    /**
     * 简单复制：
     *  将新的对象的属性简单复制到目标文件
     * @param newJsonObject
     * @param desJsonObject
     */
    private void simpeCopyJsonObject(JsonObject newJsonObject, JsonObject desJsonObject) {
        // 当在数组下新建时，json属性为空，则简单复制就可以
        for (Map.Entry<String, JsonElement> entry : newJsonObject.entrySet()) {
            desJsonObject.add(entry.getKey(), entry.getValue());
        }
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
//                log.debug("save key={} newJsonObject={}", key, tmpNewJsonObject);
                saveVo0(tmpNewJsonObject, desJsonObject.get(key).getAsJsonObject());
            }else if( val instanceof JsonArray){
                JsonArray jsonArray = (JsonArray) val;
                // 如果对象是 jsonObject，则循环进去，否则外部直接替换，如列表的内容是字符串 "111","122","333"
                // save key=extra_material_refs newJsonElemnt="0349B9B5-FB07-41be-AB53-4409C4FE102F" 元素不是jsonobject，直接跳出，在外部进行替换
                boolean isElementJsonObject = true;
                for(int i = 0; i < jsonArray.size(); i++){
                    JsonElement tmpJsonArrayElement = jsonArray.get(i);
//                    log.debug("save key={} newJsonElemnt={}", key, tmpJsonArrayElement);
                    if(tmpJsonArrayElement.isJsonObject()){
                        isElementJsonObject = true;
                        // 如果数组不相等：如数组有新增，则先创建
                        JsonArray desJsonArray = desJsonObject.get(key).getAsJsonArray();
                        if(i < desJsonArray.size()){
                            saveVo0(tmpJsonArrayElement.getAsJsonObject(), desJsonArray.get(i).getAsJsonObject());
                        }else {
                            // 在数组上创建一个新的json，然后将数据添加到上面
                            JsonObject desLastJsonObject = new JsonObject();
                            desJsonArray.add(desLastJsonObject);
                            // 当在数组下新建时，json属性为空，则简单复制就可以
                            simpeCopyJsonObject(tmpJsonArrayElement.getAsJsonObject(), desLastJsonObject);
                        }

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
