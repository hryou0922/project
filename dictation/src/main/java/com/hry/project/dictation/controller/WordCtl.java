package com.hry.project.dictation.controller;


import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/word")
public class WordCtl {
    @Autowired
    private WordService wordService;

    @RequestMapping(value = "list")
    public List<WordModel> list(){
        return wordService.list();
    }

}

