package com.hry.javabasic;

import java.io.File;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangrongyou@yixin.im on 2018/6/11.
 */
public class TestJavaCommitTranslate {

    public static void main(String[] args) throws IOException {
        String saveFile = "d://tmp/commint.txt";
   //     String file = "D:\\eclipse_study\\source\\source\\java\\src\\main\\java\\java\\util\\concurrent\\locks\\AbstractQueuedSynchronizer.java";
        String file = "D:\\DEV_TOOLS\\java_src\\jdk-e03f9868f7df\\src\\share\\classes\\java\\util\\concurrent\\locks\\ReentrantReadWriteLock.java";


        // 写入文本
        final List<String> writeFileList = new ArrayList<>();

        List<String> segmentList = getLineFromFile(file);
        FileUtils.writeLines(new File(saveFile), segmentList);

//        segmentList.stream().forEach((a) -> {
//            Querier<AbstractTranslator> querierTrans = new Querier<>();                   // 获取查询器
//            querierTrans.setParams(LANG.EN, LANG.ZH, a);
//            querierTrans.attach(new GoogleTranslator());                                  // 向查询器中添加 Google 翻译器
//            List<String> result = querierTrans.execute();
//            writeFileList.add(a);
//            writeFileList.add(result.get(0));
//            System.out.println("test: " + a);
//            System.out.println("result: " + result);
//        });

        // 保存本地
    //    FileUtils.writeLines(new File(saveFile), writeFileList);
    }

    /**
     * 从文件中获取行
     *
     * @param file
     * @return
     * @throws IOException
     */
    private static List<String> getLineFromFile(String file) throws IOException {
        List<String> lineList = FileUtils.readLines(new File(file), "utf-8");

        List<String> segmentList = new ArrayList<>();
        StringBuilder lineSb = new StringBuilder();
        for(int i = 0; i < lineList.size(); i++){
            String line =  lineList.get(i);
            if(!line.contains("*")){
                segmentList.add(lineSb.toString());
                lineSb.delete(0,lineSb.length());
                continue;
            }
            line = line.replace("*","").trim();
            if(line.isEmpty()){
                segmentList.add(lineSb.toString());
                lineSb.delete(0,lineSb.length());
            }
            lineSb.append(line).append(" ");
        }
        if(lineSb.length() > 0){
            segmentList.add(lineSb.toString());
        }
        return segmentList;
    }

}
