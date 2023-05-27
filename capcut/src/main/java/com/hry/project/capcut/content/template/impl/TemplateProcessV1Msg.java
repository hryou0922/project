package com.hry.project.capcut.content.template.impl;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.DraftContent;
import com.hry.project.capcut.content.enums.NodeEnum;
import com.hry.project.capcut.content.parser.MaterialsVideosParser;
import com.hry.project.capcut.content.parser.TracksParser;
import com.hry.project.capcut.content.template.BaseTemplateProcessMsg;
import com.hry.project.capcut.content.vo.MaterialsVideosVo;
import com.hry.project.capcut.content.vo.TracksVo;
import com.hry.project.capcut.content.vo.common.SourceTimerangeVo;
import com.hry.project.capcut.content.vo.common.TargetTimerangeVo;
import com.hry.project.capcut.pojo.TemplateConfigV1Vo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 版本1
 *
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 21:33
 */
@Component
@Slf4j
public class TemplateProcessV1Msg extends BaseTemplateProcessMsg<TemplateConfigV1Vo> {

    @Override
    protected JsonObject execute0(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {

        // 长度

        // 0:主轨道
        processTrack0(draftContent, templateConfigV1Vo);

        // TRACK
        int size = draftContent.getJsonArraySize(NodeEnum.TRACKS);


//        TracksParser p =  draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOS,0);
//
//        // 新的图片文件名称： pexels-pixabay-290548.jpg
//        String picName;
//        MaterialsAudiosParser p = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOS,0);
//        System.out.println(GsonBox.PUBLIC.toJson(p.getVo()));
//        p.saveVo(p.getVo());

        /**
         * 视频计划长度
         */
        long durationSecond;
        /**
         * 标题
         */
        String title;


        /**
         * mp3 名称： 小城故事邓丽君.mp3"
         */
        String mp3Name;
        /**
         * 副标题：这里是作者 薛明媛-完整版； 薛明媛-简化版
         */
        String subtitle;


        return draftContent.getRootJsonObject();
    }

    /**
     * 主轨道：图片
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack0(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理主轨道: 0");
        long mp3Duration = templateConfigV1Vo.getMp3Duration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 0);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        TargetTimerangeVo targetTimerange = segmentsBean.getTarget_timerange();
        targetTimerange.setDuration(mp3Duration);
        SourceTimerangeVo sourceTimerange = segmentsBean.getSource_timerange();
        sourceTimerange.setDuration(mp3Duration);
        tracksParser.saveVo(tracksVo);

        // 获取主轨道的材料
        MaterialsVideosParser materialsVideosParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOS, segmentMaterialId);
        MaterialsVideosVo materialsVideosVo = materialsVideosParser.getVo();
        materialsVideosVo.setDuration(mp3Duration);
        materialsVideosVo.setMaterial_name(templateConfigV1Vo.getPicName());
        materialsVideosVo.setPath(templateConfigV1Vo.getPicNameWithPath());
        materialsVideosParser.saveVo(materialsVideosVo);
    }
}
