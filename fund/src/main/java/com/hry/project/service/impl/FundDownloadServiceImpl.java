package com.hry.project.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hry.project.service.IFundDownloadService;
import com.hry.project.service.dto.FundInfosDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FundDownloadServiceImpl implements IFundDownloadService {
    private static final Logger logger = LoggerFactory.getLogger(FundDownloadServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void download(){
        String url = "http://api.fund.eastmoney.com/pinzhong/LJSYLZS?fundCode=513050&indexcode=000300&type=y&_=1571152609566";

        HttpHeaders headers=new HttpHeaders();
        List<String> refererList = new ArrayList<>();
        refererList.add("http://fund.eastmoney.com/513050.html?spm=search");
        headers.put(HttpHeaders.REFERER, refererList);
        HttpEntity request=new HttpEntity(null,headers);
        ResponseEntity<String> response=restTemplate.postForEntity(url,request,String.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObject = JSONObject.parseObject(response.getBody());
            int errCode = jsonObject.getInteger("ErrCode");
            if(errCode == 0) {
                JSONArray jsonArray = jsonObject.getJSONArray("Data");
                if(jsonArray.size() >= 1){
                    FundInfosDto fundInfosDto = parserDataArray(jsonArray, 0);
                 //   System.out.println(JSONObject.toJSONString(fundInfosDto));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    fundInfosDto.getFundInfoDtoList().stream().forEach(value -> System.out.println(dateFormat.format(value.getDate())+"\t" + value.getCumulativeRate()));
                }
                if(jsonArray.size() >= 2){
                    // 同类平均

                }
                if(jsonArray.size() >= 3){
                    // 沪深300

                }
               // System.out.println(JSONObject.toJSON(response.getBody()));
            }
        }
    }

    /**
     * 解析data
     * @param jsonArray
     */
    private FundInfosDto parserDataArray(JSONArray jsonArray, int jsonArrayIndex) {
        FundInfosDto fundInfosDto = new FundInfosDto();

        JSONObject dataJsonObject = jsonArray.getJSONObject(jsonArrayIndex);
        // 易方达中概互联50ET
        String name = dataJsonObject.getString("name");
        fundInfosDto.setName(name);
        // data
        List<FundInfosDto.FundInfoDto> fundInfoDtoList = new ArrayList<>();
        fundInfosDto.setFundInfoDtoList(fundInfoDtoList);
        JSONArray dataJsonArray = dataJsonObject.getJSONArray("data");
        for(int i =0; i < dataJsonArray.size(); i++ ){
            JSONArray jsonArrayFund = dataJsonArray.getJSONArray(i);
            // 时间
            Date date = new Date(jsonArrayFund.getLongValue(0));
            // 收益率
            double cumulativeRate = jsonArrayFund.getDoubleValue(1);

            FundInfosDto.FundInfoDto fundInfoDto = new FundInfosDto.FundInfoDto();
            fundInfoDto.setDate(date);
            fundInfoDto.setCumulativeRate(cumulativeRate);
            fundInfoDtoList.add(fundInfoDto);
        }
        return fundInfosDto;
    }
}
