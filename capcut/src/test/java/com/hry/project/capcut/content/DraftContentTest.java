package com.hry.project.capcut.content;

import com.google.gson.JsonObject;
import com.hry.project.capcut.TestBase;
import com.hry.project.capcut.content.enums.NodeEnum;
import com.hry.project.capcut.content.parser.MaterialsAudiosParser;
import com.hry.project.capcut.content.parser.SimpleRootParser;
import com.hry.project.capcut.content.parser.TracksParser;
import com.hry.project.capcut.content.vo.SimpleRootVo;
import com.hry.project.capcut.content.vo.TracksVo;
import com.hry.project.capcut.service.ContextService;
import com.hry.project.capcut.utils.GsonBox;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 19:35
 */
public class DraftContentTest extends TestBase {
    @Autowired
    private ContextService contextService;

    @Test
    public void draftContent(){
        JsonObject root = contextService.getJsonObjectContentFromFile();
        DraftContent draftContent = new DraftContent(root);
        MaterialsAudiosParser p = draftContent.getJsonArrayParser(NodeEnum.MATERIALS_AUDIOS,0);
        System.out.println(GsonBox.PUBLIC.toJson(p.getVo()));
        p.saveVo(p.getVo());
        System.out.println(NodeEnum.MATERIALS_AUDIOS.getName() + " " + draftContent.getJsonArraySize(NodeEnum.MATERIALS_AUDIOS));
    }


    @Test
    public void draftContent_tracks(){
        JsonObject root = contextService.getJsonObjectContentFromFile();
        String oldOrgContent = GsonBox.PUBLIC.toJson(root);
        System.out.println(oldOrgContent);

        DraftContent draftContent = new DraftContent(root);
        TracksParser p = draftContent.getJsonArrayParser(NodeEnum.TRACKS,0);
        TracksVo tracksVo = p.getVo();
        System.out.println(GsonBox.PUBLIC.toJson(tracksVo));
        p.saveVo(tracksVo);

        String newOrgContent = GsonBox.PUBLIC.toJson(root);
        System.out.println(newOrgContent);

        Assert.assertEquals(oldOrgContent, newOrgContent);
    }

    @Test
    public void draftContent_root(){
        JsonObject root = contextService.getJsonObjectContentFromFile();
        String oldOrgContent = GsonBox.PUBLIC.toJson(root);
        System.out.println(oldOrgContent);

        DraftContent draftContent = new DraftContent(root);
        SimpleRootParser p = draftContent.getJsonArrayParser(NodeEnum.SIMPLE_ROOT,0);
        SimpleRootVo vo = p.getVo();
        System.out.println(GsonBox.PUBLIC.toJson(vo));
//        vo.setDuration(12345);
        p.saveVo(vo);
        String newOrgContent = GsonBox.PUBLIC.toJson(root);
        System.out.println(newOrgContent);

        Assert.assertEquals(oldOrgContent, newOrgContent);
    }
}
