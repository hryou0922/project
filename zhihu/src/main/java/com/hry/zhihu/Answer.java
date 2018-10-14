package com.hry.zhihu;

import java.util.List;

public class Answer {
    private List<AnswerData> data;
    private AnswerPaging paging;


    public List<AnswerData> getData() {
        return data;
    }

    public void setData(List<AnswerData> data) {
        this.data = data;
    }

    public String toPlainString(){
        String lineSep = "<br />";
        StringBuilder sb = new StringBuilder();
        if(paging != null){
            sb.append("totals=").append(paging.getTotals()).append(lineSep);
        }
        if(data != null){
            for(int i = 0 ; i < data.size(); i++){
                AnswerData answerData = data.get(i);
                sb.append("============").append(i).append("================").append(lineSep);
                sb.append(answerData.getContent()).append(lineSep);
            }
        }

        return sb.toString();
    }

    public AnswerPaging getPaging() {
        return paging;
    }

    public void setPaging(AnswerPaging paging) {
        this.paging = paging;
    }
}

class AnswerData{
    private String content;
    private long created_time; // 创建时间
    private int voteup_count;
    private int comment_count;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getVoteup_count() {
        return voteup_count;
    }

    public void setVoteup_count(int voteup_count) {
        this.voteup_count = voteup_count;
    }
}

class AnswerPaging{
    private Integer totals;

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }
}