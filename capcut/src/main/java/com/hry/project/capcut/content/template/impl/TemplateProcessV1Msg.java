package com.hry.project.capcut.content.template.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.google.gson.JsonObject;
import com.hry.project.capcut.content.DraftContent;
import com.hry.project.capcut.content.enums.NodeEnum;
import com.hry.project.capcut.content.parser.*;
import com.hry.project.capcut.content.template.BaseTemplateProcessMsg;
import com.hry.project.capcut.content.vo.*;
import com.hry.project.capcut.content.vo.common.SourceTimerangeVo;
import com.hry.project.capcut.content.vo.common.TargetTimerangeVo;
import com.hry.project.capcut.pojo.TemplateConfigV1Vo;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        int size = draftContent.getJsonArraySize(NodeEnum.TRACKS);

        // 0:主轨道
        processTrack0(draftContent, templateConfigV1Vo);
        // 1：音乐进度条
        processTrack1(draftContent, templateConfigV1Vo);
        // 2：下雨效果
        processTrack2(draftContent, templateConfigV1Vo);
        // 3: 倘若你喜欢音乐，而我刚好有
        processTrack3(draftContent, templateConfigV1Vo);
        // 4: 副标题-作者
        processTrack4(draftContent, templateConfigV1Vo);
        // 5: 主标题-歌名
        processTrack5(draftContent, templateConfigV1Vo);

        // 歌词处理
        if(templateConfigV1Vo.isLyric() && size >= 7){
            // 6: 歌词
            processTrackLyric(draftContent, templateConfigV1Vo);
        }else {
            log.info("没有个歌词，不进行处理");
        }
        // 最后：mp3
        // mp3
        processTrackMp3(draftContent, templateConfigV1Vo, size - 1);
        return draftContent.getRootJsonObject();
    }



    /**
     * 主轨道：图片
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack0(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理主轨道 图片 : 0");
        long mp3Duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 0);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        updateTimeRange(mp3Duration, segmentsBean);
        // 处理关键帧：使用第2帧
        String keyframeId = segmentsBean.getKeyframe_refs().get(0);
        // 获取第一个帧模板获取，其他帧重新创建
        KeyframesVideosParser keyframesVideosParser = draftContent.getJsonArrayParser(NodeEnum.KEYFRAMES_VIDEOS, keyframeId);
        KeyframesVideosVo keyframesVideosVo = keyframesVideosParser.getVo();

        // 重新创建帧列表
        List<String> newKeyframeIdList = new ArrayList<>();
        newKeyframeIdList.add(keyframeId);
        segmentsBean.setKeyframe_refs(newKeyframeIdList);
        long fullDuration = templateConfigV1Vo.getDuration();
        // 每段长度
        long partDuraton = 30 * 1000000L;
        long newTimeoffset = partDuraton;
        while(true){
            // 创建新帧
            String newKeyframeId = UUID.randomUUID().toString();
            newKeyframeIdList.add(newKeyframeId);
            // 更新帧新值
            keyframesVideosVo.setId(newKeyframeId);
            keyframesVideosVo.setTime_offset(newTimeoffset);
            // 实现循环左右
            keyframesVideosVo.getPosition().setX(keyframesVideosVo.getPosition().getX() * -1);
            KeyframesVideosParser newKeyframesVideosParser = draftContent.createJsonArrayParser(NodeEnum.KEYFRAMES_VIDEOS);
            newKeyframesVideosParser.saveVo(keyframesVideosVo);

            if(newTimeoffset >= fullDuration){
                break;
            }
            // 更新值
            newTimeoffset = (newTimeoffset + partDuraton) < fullDuration ? (newTimeoffset + partDuraton) : fullDuration;
        }
        // 保存
        tracksParser.saveVo(tracksVo);

        // 主轨道的材料的图片信息更新
        MaterialsVideosParser materialsVideosParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOS, segmentMaterialId);
        MaterialsVideosVo materialsVideosVo = materialsVideosParser.getVo();
        materialsVideosVo.setDuration(mp3Duration);
        materialsVideosVo.setMaterial_name(templateConfigV1Vo.getPicName());
        materialsVideosVo.setPath(templateConfigV1Vo.getPicNameWithPath());
        materialsVideosParser.saveVo(materialsVideosVo);
    }

    /**
     * 音乐进度条
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack1(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理轨道 音乐进度条 : 1");
        long mp3Duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 1);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        updateTimeRange(mp3Duration, segmentsBean);
        tracksParser.saveVo(tracksVo);

        // 主轨道的材料的 音乐进度条
        MaterialsVideosParser materialsVideosParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOS, segmentMaterialId);
        MaterialsVideosVo materialsVideosVo = materialsVideosParser.getVo();
        materialsVideosVo.setDuration(mp3Duration);
        materialsVideosParser.saveVo(materialsVideosVo);
    }

    /**
     * 下雨效果
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack2(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理轨道 下雨效果 : 2");
        long duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 2);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        updateTimeRange(duration, segmentsBean);
        tracksParser.saveVo(tracksVo);

        // 主轨道的材料的 下雨效果: TODO 这里可以根据配置，未来使用不同的配置
//        MaterialsVideoEffectsParser materialsVideoEffectsParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOEFFECTS, segmentMaterialId);
//        MaterialsVideoEffectsVo materialsVideosVo = materialsVideoEffectsParser.getVo();
//        materialsVideoEffectsParser.saveVo(materialsVideosVo);
    }

    /**
     *  3: 倘若你喜欢音乐，而我刚好有
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack3(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        // 什么也不做
    }

    /**
     * 4: 副标题-作者
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack4(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("副标题-作者 : 4");
        long duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 4);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        updateTimeRange(duration, segmentsBean);
        tracksParser.saveVo(tracksVo);

        // 轨道的材料的
        MaterialsTextsParser materialsTextParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_TEXTS, segmentMaterialId);
        MaterialsTextsVo materialsTextVo = materialsTextParser.getVo();

        // 副标题 TODO，可根据配置，作者后面的值是 完整版
        String newTile = templateConfigV1Vo.getAuthor() + " - 完整版";
        saveContentText(newTile, materialsTextVo);

        materialsTextParser.saveVo(materialsTextVo);
    }



    /**
     *  5: 主标题-歌名
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack5(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理轨道 主标题-歌名 : 5");
        long duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 5);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        updateTimeRange(duration, segmentsBean);
        tracksParser.saveVo(tracksVo);

        // 轨道的材料的
        MaterialsTextsParser materialsTextParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_TEXTS, segmentMaterialId);
        MaterialsTextsVo materialsTextVo = materialsTextParser.getVo();
        // 主标题
        String newTitle = FileNameUtil.mainName(templateConfigV1Vo.getTitle());
        saveContentText(newTitle, materialsTextVo);
        materialsTextParser.saveVo(materialsTextVo);
    }

    /**
     *  6: 歌词
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrackLyric(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理轨道 歌词 : 6");
//        long mp3Duration = templateConfigV1Vo.getDuration();
//        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 6);
//        TracksVo tracksVo = tracksParser.getVo();
//        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
//        String segmentMaterialId = segmentsBean.getMaterial_id();
//        // 更新主轨道时长
//        updateTimeRange(mp3Duration, segmentsBean);
//        tracksParser.saveVo(tracksVo);
//
//        // 轨道的材料的
//        MaterialsVideosParser materialsVideosParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_VIDEOS, segmentMaterialId);
//        MaterialsVideosVo materialsVideosVo = materialsVideosParser.getVo();
//        materialsVideosVo.setDuration(mp3Duration);
//        materialsVideosParser.saveVo(materialsVideosVo);
    }

    /**
     *  7: mp3
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrackMp3(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo, int trackIndex) {
        log.debug("处理轨道 mp3 : {}", trackIndex);
        long duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, trackIndex);
        TracksVo tracksVo = tracksParser.getVo();
        String type = tracksVo.getType();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // TODO 这里可配置切除头，去除尾部，不切除
        // 更新主轨道时长
        updateTimeRange(duration, segmentsBean);
        tracksParser.saveVo(tracksVo);

        // 轨道的材料的： mp3
        MaterialsAudiosParser materialsAudioParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_AUDIOS, segmentMaterialId);
        MaterialsAudiosVo materialsAudioVo = materialsAudioParser.getVo();
        // TODO 截断后，这里duration 也需要打断 ?
        materialsAudioVo.setDuration(duration);
        materialsAudioVo.setPath(templateConfigV1Vo.getMp3NameWithPath());
        materialsAudioVo.setName(templateConfigV1Vo.getMp3Name());

        materialsAudioParser.saveVo(materialsAudioVo);

    }

    /**
     * 保存文件内容
     * @param newText
     * @param materialsTextVo
     */
    private void saveContentText(String newText, MaterialsTextsVo materialsTextVo) {
        newText = "[" + newText + "]";
        // "<useLetterColor><outline color=(0,0,0,1) width=0.08><size=14><font id=6740439840254333443 path=C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/349329/92988aefce8a45c8fd7fe58a60fc72cc/特黑体-思源黑体1号.otf>[王蓉 - 完整版]</font></size></outline></useLetterColor>"
        String content = materialsTextVo.getContent();
        String newContent = content.replaceAll("\\[.*\\]", newText);
        log.debug("new title = {}", newContent);
        materialsTextVo.setContent(newContent);
    }

    private void updateTimeRange(long duration, TracksVo.SegmentsBean segmentsBean) {
        updateTimeRange(null, duration, null, duration, segmentsBean);
    }

    private void updateTimeRange(Integer targetStart, long targetDuration, Integer sourceStart, long sourceDuration, TracksVo.SegmentsBean segmentsBean) {
        TargetTimerangeVo targetTimerange = segmentsBean.getTarget_timerange();
        if(targetTimerange == null){
            log.info("没有找到 targetTimerange, {}", GsonBox.PUBLIC.toJson(segmentsBean));
        }else {
            targetTimerange.setDuration(targetDuration);
            if (targetStart != null) {
                targetTimerange.setStart(targetStart);
            }
        }

        SourceTimerangeVo sourceTimerange = segmentsBean.getSource_timerange();
        if(sourceTimerange == null){
            log.info("没有找到 sourceTimerange, {}", GsonBox.PUBLIC.toJson(segmentsBean));
        }else {
            sourceTimerange.setDuration(sourceDuration);
            if (sourceStart != null) {
                sourceTimerange.setStart(sourceStart);
            }
        }
    }
}
