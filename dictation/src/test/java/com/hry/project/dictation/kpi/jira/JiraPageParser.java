package com.hry.project.dictation.kpi.jira;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/7/8 9:46
 */
public class JiraPageParser {
    /**
     * 请求地址前缀
     */
    private String preUrl = "http://jira.netease.com/browse/";

    private String cookie;

    public JiraPageParser(String cookie) {
        this.cookie = cookie;
    }

    public JiraTaskVo execute(String taskId){
        String url = preUrl + taskId;

        JiraTaskVo.SubTaskVo subTaskVo = new  JiraTaskVo.SubTaskVo();
        Document doc = getAndParserDocument(url, subTaskVo);

        JiraTaskVo jiraTaskVo =  new JiraTaskVo();
        jiraTaskVo.setOwer(subTaskVo.getManager());
        jiraTaskVo.setTotalCostTime(subTaskVo.getCostTime());
        jiraTaskVo.setUrl(url);
        // 任务数
        List<JiraTaskVo.SubTaskVo> subTaskVoList = new ArrayList<>();
        jiraTaskVo.setSubTaskVoList(subTaskVoList);

        // 有子任务，则这里解析
        Element subtasksEle =  doc.getElementById("view-subtasks");
        if(subtasksEle != null) {
            // 获取子任务列表
            List<String> subList = new ArrayList<>();
            Elements trEle = subtasksEle.getElementsByClass("issue-link");
            for(int i = 0; i < trEle.size(); i++){
                // Console.log(trEle.get(i));
                subList.add(trEle.get(i).attr("data-issue-key"));
            }

            // 获取主要任务
            for(String id : subList){
                JiraTaskVo.SubTaskVo vo = new  JiraTaskVo.SubTaskVo();
                getAndParserDocument(preUrl+ id, vo);
                subTaskVoList.add(vo);
            }
        }else {
            // 无子任务，则解析 经办人 和 时间工作时间
            subTaskVoList.add(new JiraTaskVo.SubTaskVo(subTaskVo.getManager(),subTaskVo.getCostTime()));
        }
        return jiraTaskVo;

    }

    private Document getAndParserDocument(String url,  JiraTaskVo.SubTaskVo subTaskVo) {
        String result2 = HttpRequest.post(url)
                .header(Header.COOKIE, cookie)//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        Document doc = Jsoup.parse(result2);
        Console.log("请求 {} ", url);
        // 经办人
        String manager;
        Element managerEle = doc.getElementById("assignee-val");
        manager = managerEle.text();

        // 花费时间
        String costTime = "";
        // 主任务
        Element costTimeEle = doc.getElementById("tt_aggregate_values_spent");
        if(costTimeEle == null){
            // 子任务
            costTimeEle = doc.getElementById("tt_single_values_spent");
        }
        if(costTimeEle != null) {
            costTime = costTimeEle.text();
        }


        subTaskVo.setManager(manager);
        subTaskVo.setCostTime(costTime);
        return doc;
    }


}
