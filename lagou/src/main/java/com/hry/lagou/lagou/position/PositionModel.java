package com.hry.lagou.lagou.position;

import java.util.Map;

/**
 * 职位信息
 */
public class PositionModel {
    private String requestId;
    private String resubmitToken;
    private String msg;
    private boolean success;
    private Integer code;
    private Content content;


    // 内容
    public static class Content{
        private Integer pageNo; // 编码
        private Integer pageSize;  // 页面总数
        private Map<String, HRInfo> hrInfoMap;
        private PositionResult positionResult;

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Map<String, HRInfo> getHrInfoMap() {
            return hrInfoMap;
        }

        public void setHrInfoMap(Map<String, HRInfo> hrInfoMap) {
            this.hrInfoMap = hrInfoMap;
        }

        public PositionResult getPositionResult() {
            return positionResult;
        }

        public void setPositionResult(PositionResult positionResult) {
            this.positionResult = positionResult;
        }
    }


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResubmitToken() {
        return resubmitToken;
    }

    public void setResubmitToken(String resubmitToken) {
        this.resubmitToken = resubmitToken;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}


