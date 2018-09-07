package com.hry.lagou.lagou;

import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.Main;
import com.hry.lagou.lagou.position.HRInfo;
import com.hry.lagou.lagou.position.PositionModel;
import com.hry.lagou.lagou.position.SinglePosition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
@Deprecated
public class Json2Mongodb {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void parseJson() throws IOException {
        String kd = "java";
        for(int i = 1; i <= 100; i++){
            String json = FileUtils.readFileToString(new File("D:\\tmp\\lagou\\"+kd+"\\json_" + i), "utf-8");
            PositionModel positionModel = JSONObject.parseObject(json, PositionModel.class);

            // hr info
            for(Map.Entry<String, HRInfo> hrInfoMap : positionModel.getContent().getHrInfoMap().entrySet()){
                HRInfo hrInfo = hrInfoMap.getValue();
//                List<HRInfo> hrInfoOldList = mongoTemplate.find(new Query(Criteria.where("userId").is(hrInfo.getUserId())), HRInfo.class);
                List<HRInfo> hrInfoOldList = mongoTemplate.find(new Query(Criteria.where("userId").is(hrInfo.getUserId())), HRInfo.class, "hrInfo");
                System.out.println("===" + hrInfoOldList);
                if(hrInfoOldList != null && hrInfoOldList.size() > 0){
                    System.out.println(hrInfo.getUserId() + "已经存在，增加职位" + hrInfoMap.getKey());
                    HRInfo hrInfoSize1 = hrInfoOldList.get(0);
                    hrInfoSize1.getPositionIdSet().add(hrInfoMap.getKey());
                    mongoTemplate.save(hrInfoSize1, "hrInfo");
                }else {
                    System.out.println(hrInfo.getUserId() + "增加职位" + hrInfoMap.getKey());
                    Set<String> positionIdSet = new HashSet<>();
                    positionIdSet.add(hrInfoMap.getKey());
                    hrInfo.setPositionIdSet(positionIdSet);
                    mongoTemplate.save(hrInfo, "hrInfo");
                }
            }

            // position info
            for(SinglePosition singlePosition : positionModel.getContent().getPositionResult().getResult()){
                List<SinglePosition> hrInfoOldList = mongoTemplate.find(new Query(Criteria.where("positionId").is(singlePosition.getPositionId())), SinglePosition.class, "position_" + kd);
                if(hrInfoOldList.size() == 0) {
                    System.out.println("增加记录 " + singlePosition.getPositionId());
                    mongoTemplate.save(singlePosition, "position_" + kd);
                }else {
                    System.out.println("已经存在记录 " + singlePosition.getPositionId());
                }
            }

        }

    }

}
