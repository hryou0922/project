package com.swjtu;


import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;

import java.util.List;

/**
 * Created by huangrongyou@yixin.im on 2018/6/11.
 */
public class TestGoogle {
    public static void main(String[] args){
        System.out.println(System.getProperty("user.dir"));
        Querier<AbstractTranslator> querierTrans = new Querier<>();                   // 获取查询器

 //       querierTrans.setParams(LANG.ZH, LANG.EN, "如果这都不算爱，我有什么好悲哀！");    // 设置参数
  //      querierTrans.setParams(LANG.EN, LANG.ZH, "If this is not love, what am I sorrowful!");

        querierTrans.setParams(LANG.EN, LANG.ZH,"Because checks in acquire are invoked before enqueuing, a newly acquiring thread may ahead of others that are blocked and queued. However, you can, if desired, define {@code tryAcquire} and/or {@code tryAcquireShared} to disable barging by internally invoking one or more of the inspection methods, thereby providing a <em>fair</em> FIFO acquisition order.In particular, most fair synchronizers can define {@code tryAcquire} to return {@code false} if {@link #hasQueuedPredecessors} (a method specifically designed to be used by fair synchronizers) returns {@code true}.  Other variations are possible.");


        querierTrans.attach(new GoogleTranslator());                                  // 向查询器中添加 Google 翻译器

        List<String> result = querierTrans.execute();                                 // 执行查询并接收查询结果

        for (String str : result) {
            System.out.println(str);
        }
    }
}
