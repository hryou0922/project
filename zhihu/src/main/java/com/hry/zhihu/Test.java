package com.hry.zhihu;

import com.hry.zhihu.util.Char2ImgUtils;
import com.hry.zhihu.util.GeneratePage;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

        String name = "富二代有哪些身不由己的事情";
        String pageId = "282906593";
        int voteupCountThreshold = 5; // 赞
        int contentLengthThreadshold = 300; // 文章长度

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
        //total = 500;
        for(int num = 20; num < total; num += 20){
            url = url(pageId, num);
            responseEntity = restTemplate.getForEntity(url, Answer.class);
            dataList.addAll(responseEntity.getBody().getData());
        }

        // 分析内容
        List<AnswerData> bigAnswerDataList = new ArrayList<>(); // 文章的长度过长


        int voteupCountAnswerNum = 0;
        Iterator<AnswerData> answerDataIterator = dataList.iterator();
        while(answerDataIterator.hasNext()){
            AnswerData answerData = answerDataIterator.next();
            // 处理内容
            String content = answerData.getContent().replaceAll("<p>","<br />").replace("</p>","").replaceAll("<figure>(.*)</figure>","")
                    .replaceAll("(<br />)+","<br />");
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
//        for(int i = 0; i < dataList.size(); i++ ){
//            AnswerData data = dataList.get(i);
//            String content =  data.getContent().replaceAll("<br>","")
//                    .replaceAll("<br />", "");
//            Char2ImgUtils.createImage(content,32 , new File(pngPathRoot + "\\" + i + ".png"),640);
//        }

        // 上传文章
        generate(dataList, name);
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


    public static void generate( List<AnswerData> answerDataList, String titleName){
        String cookie = "ccid=263a11f4018ecd2eca267e10e0c031b1; UM_distinctid=16668278eb31dd-0d5fb130af83f6-8383268-e1000-16668278eb55d6; sso_uid_tt=d3c1f83fa623bea8762c126274404d9e; toutiao_sso_user=75d455d6fbde8c028ec81169edfd15a9; sso_login_status=1; sessionid=607c11d8a0c7b383a0f35977ad3c457c; _mp_test_key_1=93cee2248abd631a63bad77db4dfd0fa; uid_tt=1853dea8796a40852f5e2ed5c39350ef; uuid=\"w:c41582fb05b648f28a5a43b9ea85b2e9\"; tt_im_token=1539345792910678793567441543526038082753779389693883707710580625; _ga=GA1.2.908201833.1539347059; _ba=BA0.2-20181013-5110e-4aj2ButvOQ0WfekENwDm; _mp_auth_key=a3d7be0dd54f6aa94c7282a01ccfb382; _ga=GA1.3.908201833.1539347059; __tea_sdk__ssid=8080974e-b431-491e-bb57-1503b37e7fe6; tt_webid=6612070268485600782; __tea_sdk__user_unique_id=105182348856; ptcn_no=98c1222dd41fc8c152496cef10eefae0";
        // 添加记录
        GeneratePage generatePage = new GeneratePage(cookie, titleName);
        int contentLengthThreadShold = 1600;
        System.out.println("=========数据大小===========" + answerDataList.size());
        StringBuilder sbContent = new StringBuilder();
        for(AnswerData answerData : answerDataList){
            sbContent.append("<p>").append(answerData.getContent()).append("</p>");
            sbContent.append("<p>=</p>");
            if(sbContent.length() > contentLengthThreadShold){
                generatePage.generatePage(sbContent.toString());
                sbContent.delete( 0, sbContent.length() );
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
