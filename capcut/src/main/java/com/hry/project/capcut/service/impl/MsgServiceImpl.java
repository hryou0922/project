package com.hry.project.capcut.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.service.ContextService;
import com.hry.project.capcut.service.Mp3Service;
import com.hry.project.capcut.service.MsgService;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:38
 */
@Service
@Slf4j
public class MsgServiceImpl implements MsgService {
    @Autowired
    private Mp3Service mp3Service;
    @Autowired
    private ContextService contextService;
    @Autowired
    private MyConfig myConfig;

    @Override
    public FileMp3InfoVo execute(String mp3FileName, String picName){
        FileMp3InfoVo mp3InfoVo = mp3Service.parser(mp3FileName);
        // 语音时长
        long durationSecond = mp3InfoVo.getDurationSecond();
        // 文件原始名称
        String fileName = mp3InfoVo.getFileName();
        // 作者
        String author = mp3InfoVo.getAuthor();
        // 标题
        String title = mp3InfoVo.getTitle();

        String content = contextService.getContentFromFile();

        // 开始替换
        String newContent = content;
        // 替换文本名称
        newContent = newContent.replaceAll("pexels-pixabay-290548.jpg", picName);

        // 时长替换
        newContent = newContent.replaceAll("18533333", (durationSecond * 1000000L) + "");
        // mp3文件： 小城故事邓丽君.mp3 158066666
        newContent = newContent.replaceAll("小城故事邓丽君.mp3", mp3FileName);
        newContent = newContent.replaceAll("158066666",(durationSecond * 1000000L) + "");
        // 作者替换
        newContent = newContent.replaceAll("一字之差", title);
        // 标题替换
        newContent = newContent.replaceAll("薛明媛", author);

        // 替换
        contextService.saveContent2File(newContent);

        return mp3InfoVo;
    }

    @Override
    public FileMp3InfoVo execute(String configFileName) {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(myConfig.getConfigDir() + File.separator + configFileName));
        List<CsvRow> rows = data.getRows();
        //遍历行
//        for (CsvRow csvRow : rows) {
//            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
//            Console.log(csvRow.getRawList());
//        }
        // 当前值读取第一行
        CsvRow firsRow = rows.get(1);
        log.info(GsonBox.PUBLIC.toJson(firsRow));
        String mp3FileName = firsRow.get(0);
        String picName = firsRow.get(1);
        return execute(mp3FileName, picName);
    }

    @Override
    public void exeucteGeCi(int tracksGeCiIndex) {
        double transformX = 0;
        double transformY = -0.2593902439024389;
        JsonObject contentJsonObject = contextService.getJsonObjectContentFromFile();
        // 歌词 tracks[6].segments[]数组
//        JsonArray segmentArray = contentJsonObject.getAsJsonArray("tracks").get(tracksGeCiIndex).getAsJsonObject().getAsJsonArray();
        JsonArray segmentArray = contentJsonObject.getAsJsonArray("tracks").get(tracksGeCiIndex).getAsJsonObject().getAsJsonArray("segments");
        for(int i= 0; i < segmentArray.size(); i++){
            // 歌词
            JsonObject segmentJsonObject = segmentArray.get(i).getAsJsonObject();
            System.out.println(segmentJsonObject);
            // 歌词id
            String materialId = segmentJsonObject.get("material_id").getAsString();
            // 动画
            JsonArray extraMaterialRefsJsonArray = segmentJsonObject.get("extra_material_refs").getAsJsonArray();
            // target_timerange
            JsonObject targetTimerangeJsonObject = segmentJsonObject.get("target_timerange").getAsJsonObject();
            // duration
            int duration = targetTimerangeJsonObject.get("duration").getAsInt();
            // start
            int start = targetTimerangeJsonObject.get("start").getAsInt();

            // 设置固定值
            // clip - transform - x
            segmentJsonObject.getAsJsonObject("clip").getAsJsonObject("transform").addProperty("x", transformX);
            // clip - transform - y
            segmentJsonObject.getAsJsonObject("clip").getAsJsonObject("transform").addProperty("y", transformY);

            for(int j=0; j < extraMaterialRefsJsonArray.size(); j++){
                // 文本
                updateMaterialTextInfo(contentJsonObject,materialId);
                // 文本动画
                updateMaterialAnTextInfo(contentJsonObject, extraMaterialRefsJsonArray.get(j).getAsString(), duration);
            }

//            System.out.println(materialId);
//            System.out.println(extraMaterialRefsJsonArray);
        }

        // 替换
        contextService.saveJsonObjectContentFromFile(contentJsonObject);
    }

    private void updateMaterialAnTextInfo( JsonObject contentJsonObject, String extraMaterialRefId, int duration){
        JsonArray materialAnimationsArray = contentJsonObject.getAsJsonObject("materials").getAsJsonArray("material_animations");

        long animationsJsonArrayId = System.currentTimeMillis()/1000;
        int start = 0;
        for(int i= 0; i < materialAnimationsArray.size(); i++){
            // 歌词动画
            JsonObject animationsJsonObject = materialAnimationsArray.get(i).getAsJsonObject();
            // id
            String id = animationsJsonObject.get("id").getAsString();
            if(extraMaterialRefId.equalsIgnoreCase(id)){
                JsonArray  animationsJsonArray = animationsJsonObject.getAsJsonArray("animations");
//                System.out.println("== "+animationsJsonArray);
                /**
                 * {
                 *     "category_id":"",
                 *     "category_name":"",
                 *     "duration":3766666,
                 *     "id":"1644336",
                 *     "material_type":"sticker",
                 *     "name":"音符弹跳",
                 *     "panel":"",
                 *     "path":"C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/1644336/86c6e9061b14fd43049b6232ffc113fa",
                 *     "platform":"all",
                 *     "resource_id":"6841115718172283406",
                 *     "start":0,
                 *     "type":"in"
                 * }
                 */
                JsonObject jsonObject;
                if(animationsJsonArray.size() == 0){
                    jsonObject = new JsonObject();
                    animationsJsonArray.add(jsonObject);
                }else {
                    jsonObject = animationsJsonArray.get(0).getAsJsonObject();
                }
                jsonObject.addProperty("category_id", "");
                jsonObject.addProperty("category_name", "");
                jsonObject.addProperty("duration", duration);
                jsonObject.addProperty("id", animationsJsonArrayId);
                jsonObject.addProperty("material_type", "sticker");
                jsonObject.addProperty("name", "音符弹跳");
                jsonObject.addProperty("panel", "");
                jsonObject.addProperty("path", "C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/1644336/86c6e9061b14fd43049b6232ffc113fa");
                jsonObject.addProperty("platform", "all");
                jsonObject.addProperty("resource_id", "6841115718172283406");
                jsonObject.addProperty("start", start);
                jsonObject.addProperty("type", "in");
            }
        }


    }

    /**
     * 更新歌词文本 - 暂时不改
     * @param contentJsonObject
     * @param materialId
     */
    private void updateMaterialTextInfo( JsonObject contentJsonObject, String materialId){
        JsonArray segmentArray = contentJsonObject.getAsJsonObject("materials").getAsJsonArray("texts");
        int foneSize = 11;
        for(int i= 0; i < segmentArray.size(); i++){
            // 歌词文本
            JsonObject textJsonObject = segmentArray.get(i).getAsJsonObject();
            // id
            String id = textJsonObject.get("id").getAsString();
            if(materialId.equalsIgnoreCase(id)){
                // 内容
                String content = textJsonObject.get("content").getAsString();
                // 替换size
                content = content.replaceAll("size=8.000000", "size="+foneSize);

                // content : "<size=11><font id="" path="C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf">[oh嘿妈妈]</font></size>"
                // content : "<font id="" path="C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf"><color=(1.000000, 1.000000, 1.000000, 1.000000)><size=8.000000>[oh嘿妈妈]</size></color></font>"

                // font_path : "C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf"

                // font_size : 8

                textJsonObject.remove("content");
                textJsonObject.addProperty("content", content);
                textJsonObject.addProperty("font_size", foneSize);
                System.out.println("== " + textJsonObject.toString());
            }

        }

    }
}
