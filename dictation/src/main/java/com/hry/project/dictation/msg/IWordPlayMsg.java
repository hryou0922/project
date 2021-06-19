package com.hry.project.dictation.msg;

import com.hry.project.dictation.model.WordModel;

import java.util.Collection;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 19:21
 */
public interface IWordPlayMsg {

  /**
   * 播放录音
   * @param wordModels
   * @param groupId  组id，可为空
   */
  void play(Collection<WordModel> wordModels, Long groupId);

  void stop();
}
