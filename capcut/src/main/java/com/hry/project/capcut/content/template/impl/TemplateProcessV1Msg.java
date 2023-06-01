package com.hry.project.capcut.content.template.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.google.gson.JsonObject;
import com.hry.project.capcut.content.DraftContent;
import com.hry.project.capcut.content.enums.LyricTruncatedModeEnum;
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

        // 先处理歌词
        if(templateConfigV1Vo.isLyric()){
            // 6: 歌词
            processTrackLyric(draftContent, templateConfigV1Vo, 6);
        }
        // 主root信息
        processRoot(draftContent, templateConfigV1Vo);
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

        // 最后：mp3
        // mp3
        processTrackMp3(draftContent, templateConfigV1Vo, size - 1);
        return draftContent.getRootJsonObject();
    }

    /**
     * 处理root信息
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processRoot(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理root参数 : 0");
        long duration = templateConfigV1Vo.getDuration();
        SimpleRootParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.SIMPLE_ROOT, -1);
        SimpleRootVo tracksVo = tracksParser.getVo();
        tracksVo.setDuration(duration);
        tracksParser.saveVo(tracksVo);
    }


    /**
     * 主轨道：图片
     * @param draftContent
     * @param templateConfigV1Vo
     */
    private void processTrack0(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo) {
        log.debug("处理主轨道 图片 : 0");
        long duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, 0);
        TracksVo tracksVo = tracksParser.getVo();
        TracksVo.SegmentsBean segmentsBean = tracksVo.getSegments().get(0);
        String segmentMaterialId = segmentsBean.getMaterial_id();
        // 更新主轨道时长
        updateTimeRange(duration, segmentsBean);
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
        materialsVideosVo.setDuration(duration);
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

        // 副标题
        String newTile = templateConfigV1Vo.getAuthor() + " - " + templateConfigV1Vo.getMp3VersionTypeEnum().getName();
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
    private void processTrackLyric(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo, int index) {
        log.debug("处理轨道 歌词");


        long duration = templateConfigV1Vo.getDuration();
        TracksParser tracksParser = draftContent.getJsonArrayParser(NodeEnum.TRACKS, index);
        TracksVo tracksVo = tracksParser.getVo();


        // 处理歌词
        processLyric(draftContent, templateConfigV1Vo, tracksVo);

        // 获取首个歌词起始值
        long allLyricStart = getLyricStart(templateConfigV1Vo, tracksVo);

        // 获取截断的末尾的值
        long struncatedEnd = procesStruncated(templateConfigV1Vo, tracksVo);

        // 结算截断点
        log.info("当前截断点为:{}", struncatedEnd);

        // 设置总时长变短
        templateConfigV1Vo.setDuration(struncatedEnd - allLyricStart);
        templateConfigV1Vo.setAlllLricStart(allLyricStart);
        log.debug("视频总时长: {} ->{} 起始值:{}", duration, duration - allLyricStart, allLyricStart);

        // 歌词前部分截断
        for(TracksVo.SegmentsBean segmentsBean : tracksVo.getSegments()){
            // 歌词起始
            long lyricStart = segmentsBean.getTarget_timerange().getStart();
            segmentsBean.getTarget_timerange().setStart(lyricStart - allLyricStart);
        }
        // 覆盖配置
        tracksParser.putVo(tracksVo);
    }

    /**
     * 处理歌词，并去除要阶段的歌词
     * @param templateConfigV1Vo
     * @param tracksVo
     * @return 返回阶段的时间末位点
     */
    private long procesStruncated(TemplateConfigV1Vo templateConfigV1Vo, TracksVo tracksVo) {
        // 首个要阶段的起始值
        long struncatedEnd = templateConfigV1Vo.getDuration();
        if(templateConfigV1Vo.getLyricTruncatedModeEnum() == LyricTruncatedModeEnum.BEGIN_TRUNCATE_ONLY_FIRST_PART){
            // 上一个歌词结束的时间
            long lastLyricEnd = 0;
            // 截断处理后的列表
            List<TracksVo.SegmentsBean> newSegmentList = new ArrayList<>();
            boolean isFirstText = true;
            for(TracksVo.SegmentsBean segmentsBean : tracksVo.getSegments()){
                // 歌词起始
                long lyricStart = segmentsBean.getTarget_timerange().getStart();
                // 歌词持续信息
                long lyricDuration = segmentsBean.getTarget_timerange().getDuration();
                // 歌词
                if(lyricStart - lastLyricEnd > templateConfigV1Vo.getLyricDiscardThreshold()){
                    // 如果第一个文本大于阈值要忽略
                    if(!isFirstText){
                        // 加上gap值
                        struncatedEnd = lastLyricEnd + templateConfigV1Vo.getLyricGapTime();
                        log.info("立即进行截断的时间点： {} 和上一个值 {} 之差 {} > {}, 做为备选点"
                                , lyricStart, lastLyricEnd, (lyricStart - lastLyricEnd),  templateConfigV1Vo.getLyricDiscardThreshold());
                        break;
                    }
                }
                lastLyricEnd = lyricStart + lyricDuration;
                // 添加
                newSegmentList.add(segmentsBean);
                // 表示第一个已经处理
                if(!isFirstText){
                    isFirstText = false;
                }
            }
            // 进行替换
            tracksVo.setSegments(newSegmentList);
        }
        return struncatedEnd;
    }

    /**
     * 获取首个歌词起始值
     * @param templateConfigV1Vo
     * @param tracksVo
     * @return
     */
    private long getLyricStart(TemplateConfigV1Vo templateConfigV1Vo, TracksVo tracksVo) {

        if(templateConfigV1Vo.getLyricTruncatedModeEnum() == LyricTruncatedModeEnum.FULL){
            log.debug("当前是全量值，不执行首阶段");
            return 0;
        }

        long allLyricStart;
        TracksVo.SegmentsBean firstSegmentsBean = tracksVo.getSegments().get(0);
        // 歌词起始
        long lyricStart = firstSegmentsBean.getTarget_timerange().getStart();
        long lyricGapTime = templateConfigV1Vo.getLyricGapTime();
        if(lyricStart < lyricGapTime){
            log.debug("首个歌词的起始时间为{} < {}，忽略，不执行截断", lyricStart, lyricGapTime);
            allLyricStart = 0L;
        }else {
            // 首个歌词
            allLyricStart = lyricStart - lyricGapTime;
            log.debug("首个歌词的起始时间为:{}", lyricStart);
        }
        return allLyricStart;
    }

    /**
     * 处理歌词
     * @param draftContent
     * @param templateConfigV1Vo
     * @param tracksVo
     */
    private void processLyric(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo, TracksVo tracksVo) {
        double lyricTransformX = templateConfigV1Vo.getLyricTransformX();
        double lyricTransformY = templateConfigV1Vo.getLyricTransformY();
        for(TracksVo.SegmentsBean segmentsBean : tracksVo.getSegments()){
            // 歌词持续信息
            long lyricDuration = segmentsBean.getTarget_timerange().getDuration();
            // 歌词文本信息
            String materialId = segmentsBean.getMaterial_id();
            // 歌词动画
            String materialAnimationId = segmentsBean.getExtra_material_refs().get(0);
            // 设置歌词位置
            setLyricTransformLocation(lyricTransformX, lyricTransformY, segmentsBean);
            // 配置歌词文本字体信息
            configLyricTextVo(draftContent, templateConfigV1Vo, materialId);
            // 对应歌词动画处理
            configLyricLryicAnimationVo(draftContent, templateConfigV1Vo, lyricDuration, materialAnimationId);
        }
    }

    /**
     * 配置歌词动画效果
     * @param draftContent
     * @param templateConfigV1Vo
     * @param lyricDuration
     * @param materialId
     */
    private void configLyricLryicAnimationVo(DraftContent draftContent,  TemplateConfigV1Vo templateConfigV1Vo,
                                             long lyricDuration, String materialId) {
        MaterialsAnimationsParser materialsAnimationsParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_MATERIAL_ANIMATIONS, materialId);
        MaterialsAnimationsVo materialsAnimationsVo = materialsAnimationsParser.getVo();
        List<MaterialsAnimationsVo.AnimationsBean> animationsBeanList = materialsAnimationsVo.getAnimations();
        MaterialsAnimationsVo.AnimationsBean animationsBean = null;
        if(animationsBeanList != null || animationsBeanList.size() == 0){
            animationsBeanList = new ArrayList<>();
            materialsAnimationsVo.setAnimations(animationsBeanList);
            animationsBean = new MaterialsAnimationsVo.AnimationsBean();
            animationsBeanList.add(animationsBean);
        }else {
            // 默认获取首个值
            animationsBean = animationsBeanList.get(0);
        }
        // 设置动画效果： 音符弹跳
        String animationsId = UUID.randomUUID().toString();
        animationsBean.setCategory_id("");
        animationsBean.setCategory_name("");
        animationsBean.setDuration(lyricDuration);
        animationsBean.setId(animationsId);
        animationsBean.setMaterial_type("sticker");
        animationsBean.setName("音符弹跳");
        animationsBean.setPanel("");
        animationsBean.setPath("C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/1644336/86c6e9061b14fd43049b6232ffc113fa");
        animationsBean.setPlatform("all");
        animationsBean.setResource_id("6841115718172283406");
        animationsBean.setStart(0);
        animationsBean.setType("in");
        // 保存变化
        materialsAnimationsParser.saveVo(materialsAnimationsVo);
    }

    /**
     * 配置歌词文本字体信息
     * @param draftContent
     * @param templateConfigV1Vo
     * @param materialId
     */
    private void configLyricTextVo(DraftContent draftContent, TemplateConfigV1Vo templateConfigV1Vo, String materialId) {
        // 歌词大小
        int lyricFontSize = templateConfigV1Vo.getLyricFontSize();
        // 对应歌词文本处理
        MaterialsTextsParser materialsTextParser = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_TEXTS, materialId);
        MaterialsTextsVo materialsTextVo = materialsTextParser.getVo();
        // 设置文字大小
        // content : "<size=11><font id="" path="C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf">[oh嘿妈妈]</font></size>"
        // content : "<font id="" path="C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf"><color=(1.000000, 1.000000, 1.000000, 1.000000)><size=8.000000>[oh嘿妈妈]</size></color></font>"
        String content = materialsTextVo.getContent();
        content = "<size="+lyricFontSize+">" + content +  "</size>";
        materialsTextVo.setContent(content);
        materialsTextVo.setFont_size(lyricFontSize);
        materialsTextParser.saveVo(materialsTextVo);
    }

    /**
     * 设置歌词位置
     * @param lyricTransformX
     * @param lyricTransformY
     * @param segmentsBean
     */
    private void setLyricTransformLocation(double lyricTransformX, double lyricTransformY, TracksVo.SegmentsBean segmentsBean) {
        TracksVo.SegmentsBean.ClipBean clipBean = segmentsBean.getClip();
        if(clipBean == null){
            clipBean = new TracksVo.SegmentsBean.ClipBean();
            segmentsBean.setClip(clipBean);
        }
        TracksVo.SegmentsBean.ClipBean.TransformBean transformBean = new TracksVo.SegmentsBean.ClipBean.TransformBean();
        transformBean.setX(lyricTransformX);
        transformBean.setY(lyricTransformY);
        clipBean.setTransform(transformBean);
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
//        updateTimeRange(duration, segmentsBean);
        updateTimeRange(0L, duration, templateConfigV1Vo.getAlllLricStart(), duration, segmentsBean);
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

    private void updateTimeRange(Long targetStart, long targetDuration, Long sourceStart, long sourceDuration, TracksVo.SegmentsBean segmentsBean) {
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
