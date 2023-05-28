package com.hry.project.capcut.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hry.project.capcut.content.enums.NodeEnum;
import com.hry.project.capcut.content.parser.BaseParser;

import java.lang.reflect.Constructor;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 19:15
 */
public class DraftContent {

    /**
     * root元素
     */
    private JsonObject rootJsonObject;

    /**
     * tracks 数组
     */
    private JsonArray trackJsonArray;

    /**
     * materials - videos
     */
    private JsonArray materialsVideosJsonArray;

    /**
     * materials - video_effects
     */
    private JsonArray materialsVideoEffectsJsonArray;

    /**
     * materials - texts
     */
    private JsonArray materialsTextsJsonArray;

    /**
     * materials - audios
     */
    private JsonArray materialsAudiosJsonArray;

    /**
     * materials - material_animations
     */
    private JsonArray materialsMaterialAnimationsJsonArray;

    /**
     * keyframes - videos
     */
    private JsonArray keyframesVideosJsonArray;


    public DraftContent(JsonElement contentJsonElement){
        this.rootJsonObject = contentJsonElement.getAsJsonObject();
        this.trackJsonArray = rootJsonObject.get("tracks").getAsJsonArray();
        // materials
        JsonObject materialsJsonObject = rootJsonObject.get("materials").getAsJsonObject();
        this.materialsVideosJsonArray = materialsJsonObject.get("videos").getAsJsonArray();
        this.materialsVideoEffectsJsonArray = materialsJsonObject.get("video_effects").getAsJsonArray();
        this.materialsTextsJsonArray = materialsJsonObject.get("texts").getAsJsonArray();
        this.materialsAudiosJsonArray = materialsJsonObject.get("audios").getAsJsonArray();
        this.materialsMaterialAnimationsJsonArray = materialsJsonObject.get("material_animations").getAsJsonArray();
        // keyframes
        JsonObject keyframesJsonObject = rootJsonObject.get("keyframes").getAsJsonObject();
        this.keyframesVideosJsonArray = keyframesJsonObject.get("videos").getAsJsonArray();

    }

    /**
     * 获取 jsonArray
     * @param nodeEnum
     * @return
     */
    private JsonArray getJsonArray(NodeEnum nodeEnum){
        JsonArray jsonArray = null;
        switch (nodeEnum){
            case TRACKS: jsonArray = trackJsonArray; break;
            case MATERIALS_TEXTS: jsonArray = materialsTextsJsonArray; break;
            case MATERIALS_VIDEOEFFECTS: jsonArray = materialsVideoEffectsJsonArray; break;
            case MATERIALS_VIDEOS: jsonArray = materialsVideosJsonArray; break;
            case MATERIALS_AUDIOS: jsonArray = materialsAudiosJsonArray; break;
            case MATERIALS_MATERIAL_ANIMATIONS: jsonArray = materialsMaterialAnimationsJsonArray; break;
            case KEYFRAMES_VIDEOS: jsonArray = this.keyframesVideosJsonArray; break;
            default:
                throw new RuntimeException("不认识的枚举的类型: " + nodeEnum);
        }
        return jsonArray;
    }



    /**
     * 获取数组 解析类 BaseParser
     * @param nodeEnum
     * @param index
     * @param <V>
     * @return
     */
    public <V extends BaseParser> V getJsonArrayParser(NodeEnum nodeEnum, int index){
        JsonObject jsonObject = getJsonObject(nodeEnum, index);
        Constructor<V> constructor = null;
        try {
            constructor = nodeEnum.getParserClass().getConstructor(JsonObject.class);
            // materialsAudiosJsonArray.get(index).getAsJsonObject()
            return constructor.newInstance(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取数组 解析类 BaseParser
     * @param nodeEnum
     * @param id
     * @param <V>
     * @return
     */
    public <V extends BaseParser> V getJsonArrayParser(NodeEnum nodeEnum, String id){
        JsonObject jsonObject = getJsonObject(nodeEnum, id);
        Constructor<V> constructor = null;
        try {
            constructor = nodeEnum.getParserClass().getConstructor(JsonObject.class);
            // materialsAudiosJsonArray.get(index).getAsJsonObject()
            return constructor.newInstance(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取数组 解析类 BaseParser
     * @param nodeEnum
     * @param <V>
     * @return
     */
    public <V extends BaseParser> V createJsonArrayParser(NodeEnum nodeEnum){
        JsonObject jsonObject = createJsonObject(nodeEnum);
        Constructor<V> constructor = null;
        try {
            constructor = nodeEnum.getParserClass().getConstructor(JsonObject.class);
            // materialsAudiosJsonArray.get(index).getAsJsonObject()
            return constructor.newInstance(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查询
     * @param nodeEnum
     * @return
     */
    private JsonObject createJsonObject(NodeEnum nodeEnum){
        JsonObject jsonObject = null;
        switch (nodeEnum){
            // root 返回root值
            case SIMPLE_ROOT: throw new RuntimeException("root 不支持id查询");
            default:
                JsonArray jsonArray = getJsonArray(nodeEnum);
                JsonObject newJsonObject = new JsonObject();
                jsonArray.add(newJsonObject);
                jsonObject = newJsonObject;
        }
        return jsonObject;
    }

    /**
     * 获取 长度
     * @return
     */
    public int getJsonArraySize(NodeEnum nodeEnum){
        return getJsonArray(nodeEnum).size();
    }

    /**
     * 获取数组里的一个对象
     * @param nodeEnum
     * @param index
     * @return
     */
    private JsonObject getJsonObject(NodeEnum nodeEnum, int index){
        JsonObject jsonObject = null;
        switch (nodeEnum){
            // root 返回root值
            case SIMPLE_ROOT: jsonObject = this.rootJsonObject; break;
            default:
                return getJsonArray(nodeEnum).get(index).getAsJsonObject();
        }
        return jsonObject;
    }

    /**
     * 根据id查询
     * @param nodeEnum
     * @param id
     * @return
     */
    private JsonObject getJsonObject(NodeEnum nodeEnum, String id){
        JsonObject jsonObject = null;
        switch (nodeEnum){
            // root 返回root值
            case SIMPLE_ROOT: throw new RuntimeException("root 不支持id查询");
            default:
                JsonArray jsonArray = getJsonArray(nodeEnum);
                for(int i = 0; i < jsonArray.size(); i++){
                    JsonObject tmpJsonObject = jsonArray.get(i).getAsJsonObject();
                    if(id.equalsIgnoreCase(tmpJsonObject.get("id").getAsString())){
                        jsonObject = tmpJsonObject;
                        break;
                    }
                }
        }
        return jsonObject;
    }

    public JsonObject getRootJsonObject() {
        return rootJsonObject;
    }
}
