package com.hry.zhihu;

import com.hry.zhihu.util.Char2ImgUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args){

        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://www.zhihu.com/api/v4/questions/294088771/answers?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&offset=0&limit=120&sort_by=created";
        // 名称 pageId
//        String name = "你听过哪些不反智的鸡汤";
//        String pageId = "294088771";

        String name = "男生对女朋友可以心机深到什么程度-tmp";
        String pageId = "47517195";

        String pngPathRoot = "C:\\Users\\hry\\Desktop\\tmp\\other\\char2img\\"+pageId+"\\"+name + "\\";
        new File(pngPathRoot).mkdirs();

        String url = url(pageId, 0);
        ResponseEntity<Answer> responseEntity = restTemplate.getForEntity(url, Answer.class);
        // 总数
        int total = responseEntity.getBody().getPaging().getTotals();
        // offset
        int offset = 0;
        // limit
        int limit = 20;

        // 保存值
        List<AnswerData> dataList = new ArrayList<>();
        dataList.addAll(responseEntity.getBody().getData());

        // 后续继续访问
        // total = 500;
        for(int num = 20; num < total; num += 20){
            url = url(pageId, num);
            responseEntity = restTemplate.getForEntity(url, Answer.class);
            dataList.addAll(responseEntity.getBody().getData());
        }

        // 分析内容
        List<AnswerData> bigAnswerDataList = new ArrayList<>(); // 文章的长度过长
        int contentLengthThreadshold = 600; // 文章长度
        int voteupCountThreshold = 10; // 赞
        int voteupCountAnswerNum = 0;
        Iterator<AnswerData> answerDataIterator = dataList.iterator();
        while(answerDataIterator.hasNext()){
            AnswerData answerData = answerDataIterator.next();
            // 处理内容
            String content = answerData.getContent().replaceAll("<p>","<br />").replace("</p>","").replaceAll("<figure>(.*)</figure>","");
            answerData.setContent(content);

            if(answerData.getVoteup_count() <=  voteupCountThreshold){
                answerDataIterator.remove();
                voteupCountAnswerNum++;
                continue;
            }
            // 文章很长的省略：移到到大表中
            if(answerData.getContent().length() > contentLengthThreadshold){
                bigAnswerDataList.add(answerData);
                answerDataIterator.remove();
            }
        }




        // 生成html
        String saveFile = "C:\\Users\\hry\\Desktop\\tmp\\other\\zhihu\\" + pageId + "-" + name + ".html";
        StringBuilder sb = new StringBuilder();
        sb.append("标题 = ").append(name).append("<br />");
        sb.append("总数 = ").append(total).append(" 去除不符合阈值的数量 = ").append(voteupCountAnswerNum)
                .append(" 剩余数量 = ").append(total - voteupCountAnswerNum)
                .append(" 符合要求的答案 = ").append(dataList.size())
                .append(" 大文件的答案 = ").append(bigAnswerDataList.size())
                .append("<br />");
        // 生成内容
        for(int i = 0; i < dataList.size(); i++ ){
            AnswerData data = dataList.get(i);
            generateData(sb, i, data);
        }
        // 生成大内容
        sb.append("<br /> <br /> =================大文件======================= <br />");
        for(int i = 0; i < bigAnswerDataList.size(); i++ ){
            AnswerData data = bigAnswerDataList.get(i);
            generateData(sb, i, data);
        }

        // 保存到文件中
        try {
            FileUtils.writeStringToFile(new File(saveFile), sb.toString(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 生成img
        // 生成内容
        for(int i = 0; i < dataList.size(); i++ ){
            AnswerData data = dataList.get(i);
            String content =  data.getContent().replaceAll("<br>","")
                    .replaceAll("<br />", "");
            Char2ImgUtils.createImage(content,32 , new File(pngPathRoot + "\\" + i + ".png"),640);
        }
    }

    // 生成内容
    private static void generateData(StringBuilder sb, int i, AnswerData data) {
        sb.append("=============").append(i).append("=============").append("<br />");
        // 时间
        long createTime = data.getCreated_time();
        Date date = new Date(createTime * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        sb.append("创建时间:").append(simpleDateFormat.format(date)).append("<br />");
        // 资源
        sb.append("voteup_count:").append(data.getVoteup_count()).append(" comment_count:").append(data.getComment_count()).append("<br />");
        // content
        String content =  data.getContent();
        sb.append("contentlength: ").append(content.length()).append("<br />");

        sb.append(content);
        sb.append("<br />");
    }

    public static String url(String pageId, int offset){
        String urlMessage = "https://www.zhihu.com/api/v4/questions/{0}/answers?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&offset={1}&limit=20&sort_by=created";
        String url = MessageFormat.format(urlMessage,pageId,String.valueOf(offset));
        return url;
    }
}
