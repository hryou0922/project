package com.hry.project.capcut.ctl;

import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.service.CapCutConfigFileService;
import com.hry.project.capcut.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/12 23:16
 */
@Slf4j
@RequestMapping("/api/")
@RestController
public class ApiCtl {
    @Autowired
    private MsgService msgService;
    @Autowired
    private CapCutConfigFileService capCutConfigFileService;

    /**
     * 生成 内容
     * @param configName
     * @return
     */
    @GetMapping(value = "generate")
    public String generate(@RequestParam("name") String configName){
        log.info("configName={}", configName);
        FileMp3InfoVo fileMp3InfoVo = msgService.execute(configName);

        StringBuilder sb = new StringBuilder();
        sb.append("#").append(fileMp3InfoVo.getAuthor()).append(" ")
                .append("#").append(fileMp3InfoVo.getTitle()).append(" ")
                .append("#音乐 #经典老歌 #怀旧金曲");
        return sb.toString();
    }

    /**
     * 生成 内容
     * @return
     */
    @GetMapping(value = "generateText")
    public String generateText(){
        int tracksGeCiIndex = 6;
        msgService.exeucteGeCi(tracksGeCiIndex);
        return "ok";
    }

    /**
     * 生成 配置文件
     * @return
     */
    @GetMapping(value = "generateConfigFile")
    public String generateConfigFile(){
        capCutConfigFileService.execute();
        return "ok";
    }

}
