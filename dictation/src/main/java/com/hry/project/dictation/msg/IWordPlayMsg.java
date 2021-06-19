package com.hry.project.dictation.msg;

import com.hry.project.dictation.model.WordModel;

import java.util.Collection;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 19:21
 */
public interface IWordPlayMsg {

  void play(Collection<WordModel> wordModels);

  void stop();
}
