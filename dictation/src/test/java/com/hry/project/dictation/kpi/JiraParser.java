package com.hry.project.dictation.kpi;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.hry.project.dictation.kpi.jira.JiraPageParser;
import com.hry.project.dictation.kpi.jira.JiraTaskVo;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/7/7 19:46
 */
public class JiraParser {

    @Test
    public void js() throws IOException {
        String cookie = "_ntes_nnid=d0d0877a0087d102c0adabe97303dc6f,1617878468203; _ga_49ELM1L14F=GS1.1.1621822136.1.1.1621822140.0; hb_MA-AF5D-8C93007541D1_source=login.netease.com; mp_MA-AF5D-8C93007541D1_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fkolibri.youdata.netease.com%2Fdash%2FdataConnection%2F450200774%22%2C%22updatedTime%22%3A%201632649235491%2C%22sessionStartTime%22%3A%201632648686436%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%2056%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%221102df2bcd2e432dcefb00a4de3363f8acc41201%22%2C%22persistedTime%22%3A%201632648686399%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%220f8896408d96f35773da3e90815bfb3e574db398%22%2C%22time%22%3A%201632649235491%7D%2C%22currentReferrer%22%3A%20%22https%3A%2F%2Fkolibri.youdata.netease.com%2Fdash%2FdataConnection%2F450200774%3Fcid%3D11238%22%2C%22sessionUuid%22%3A%20%22af027760c82fb912c2f7e74984bf9b3044b062e3%22%2C%22user_id%22%3A%20%22liekecallcenter%40yixin.im%22%7D; hb_MA-8D32-CBB074308F88_source=docs.popo.netease.com; hr_netease_cpp=94b5b046-0959-4896-b355-11410295f191; hb_MA-9FE8-3B0A80DFABF7_source=login.netease.com; mp_versions_hubble_jsSDK=DATracker.globals.1.6.12.9; op_state_id_1.0=nnot3hak8e; _ga=GA1.2.285673834.1621822078; _gid=GA1.2.720228080.1633934410; JSESSIONID=17BE06D8FDD03228B9FA4485915D1E42; atlassian.xsrf.token=BZL9-PJFZ-08YD-4XJD|0f7931f2c66bb86d1a24c6e9cfa2f892b989cd12|lin; gantt_date_format_dmy=yyyy-MM-dd; gantt_date_format_complete=yyyy-MM-dd HH:mm:ss; gantt_locale=zh_CN; jira.editor.user.mode=wysiwyg; hrs_online_op_state_id_1.0=fymp3yllkc; authOpenIdToken=auth:openId:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJFSFJfSVNTVUVSIiwiZXhwIjoxNjM2NjMyNDIwLCJpYXQiOjE2MzM5NTQwMjB9.wELedNAHrFp10CZXUAAeRPReV5awphS3B_Ohdby1EsA";
        JiraPageParser jiraPageParser = new JiraPageParser(cookie);
        JiraTaskVo vo = jiraPageParser.execute("IPAAS-1645");
        System.out.println(CommonJsonUtils.toJsonString(vo));

        vo = jiraPageParser.execute("IPAAS-2088");
        System.out.println(CommonJsonUtils.toJsonString(vo));
    }

    @Test
    public void parser(){
        String file = "D:\\desktop\\tmp\\month\\绩效\\kpi2-t.xls";
     //   String costTimeFile = "D:\\desktop\\tmp\\month\\绩效\\kpi2-cost.xls";
        String costTimeFile = file;
        ExcelReader excelReader = ExcelUtil.getReader(file);
        int rowNum = excelReader.getRowCount();

        String cookie = "_ntes_nnid=d0d0877a0087d102c0adabe97303dc6f,1617878468203; _ga_49ELM1L14F=GS1.1.1621822136.1.1.1621822140.0; mp_MA-AF5D-8C93007541D1_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fkolibri.youdata.netease.com%2Fdash%2FdataConnection%2F450200774%22%2C%22updatedTime%22%3A%201632649235491%2C%22sessionStartTime%22%3A%201632648686436%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%2056%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%221102df2bcd2e432dcefb00a4de3363f8acc41201%22%2C%22persistedTime%22%3A%201632648686399%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%220f8896408d96f35773da3e90815bfb3e574db398%22%2C%22time%22%3A%201632649235491%7D%2C%22currentReferrer%22%3A%20%22https%3A%2F%2Fkolibri.youdata.netease.com%2Fdash%2FdataConnection%2F450200774%3Fcid%3D11238%22%2C%22sessionUuid%22%3A%20%22af027760c82fb912c2f7e74984bf9b3044b062e3%22%2C%22user_id%22%3A%20%22liekecallcenter%40yixin.im%22%7D; _ga=GA1.2.285673834.1621822078; hb_MA-9FE8-3B0A80DFABF7_source=login.netease.com; hb_MA-809E-C9B7A6CD76FA_source=login.netease.com; hb_MA-B81D-6D453EE22C87_source=login.netease.com; hrs_online_op_state_id_1.0=659drgi4rs; gantt_date_format_dmy=yyyy-MM-dd; gantt_date_format_complete=yyyy-MM-dd HH:mm:ss; gantt_locale=zh_CN; authOpenIdToken=auth:openId:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJFSFJfSVNTVUVSIiwiZXhwIjoxNjQ0MDYwNDg1LCJpYXQiOjE2NDEzODIwODUsInVzZXJuYW1lIjoiWVgwMjk1In0.3tgzSE9aS-VY9qrJFkU79xylOYIP8xw8c6rbp-wFng0; op_state_id_1.0=uxww962vep; op_session_id_1.0=9BFEDEBEAFD5D6EF6CCB5042E08910996C3ED6183362EA376FDFEFA62C7C569E99DCEA1ECF3DC8AC0820368D57B63DCD827F3F58B84EF014826CB373E3BA39F552F06D710B379C683F0EAF6A2B0391D4BCCFB24BED890ECA29EF6B7CC90AA7382721BCDC341EF3C108F72E411267B729E01777FE53DFFDEFA47B62548611CB8EA3B6B03EB044CECBE6ECFA025D42043F90712EA068D1CD070EFE8FC9CA49739C0E8E2AFF76DA3E14709F8968C2F6CD74; JSESSIONID=8F390840931B085268F78240EA58630B; atlassian.xsrf.token=BZL9-PJFZ-08YD-4XJD|2eb828edfcf5f79fb901c05aa6dcd9c47b3921f3|lin";
        JiraPageParser jiraPageParser = new JiraPageParser(cookie);

        List<JiraTaskVo> jiraTaskVoList = new ArrayList<>();
        for(int i = 0; i < rowNum; i++){
            // 任务id
            Cell cell = excelReader.getCell(1, i);
            if(StringUtils.isBlank(cell.getStringCellValue())){
                continue;
            }
            JiraTaskVo vo = jiraPageParser.execute(cell.getStringCellValue());
            // 任务名称
            vo.setName(excelReader.getCell(2, i).getStringCellValue());
            Console.log(CommonJsonUtils.toJsonString(vo));
            jiraTaskVoList.add(vo);
        }
        excelReader.close();
        // 保存
        save2CostTimeKpiFile(jiraTaskVoList, costTimeFile);
    }

    private void save2CostTimeKpiFile(List<JiraTaskVo> jiraTaskVoList, String costTimeFile) {
        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(costTimeFile);
        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();
        for(int i = 0; i < jiraTaskVoList.size(); i++){
            // 标识: 1
            // 主体: 2
            // 类型：3
            // 花费
            List<JiraTaskVo.SubTaskVo> subTaskVoList = jiraTaskVoList.get(i).getSubTaskVoList();
            for(JiraTaskVo.SubTaskVo subTaskVo : subTaskVoList){
                // 名称
                String manager = subTaskVo.getManager();

                Cell costCell = null;
                switch (manager){
                    case "yixin.huangrongyou":
                        // 类型
                        costCell = writer.getOrCreateCell(5,i);
                        break;
                    case "yixin.wangchen":
                        costCell = writer.getOrCreateCell(7,i);
                        break;
                    case "yixin.zhuweiwen":
                        costCell = writer.getOrCreateCell(9,i);
                        break;
                    case "yixin.xiaoxiaoming":
                        costCell = writer.getOrCreateCell(11,i);
                        break;
                    case "yixin.wangshihong":
                        costCell = writer.getOrCreateCell(13,i);
                        break;
                        default:
                            System.out.println("不认识的人："+ manager);
                }
                if(costCell !=null && subTaskVo.getCostTime()  != null) {
                    costCell.setCellValue(subTaskVo.getCostTime());
                }
            }
        }
        //关闭writer，释放内存
        writer.close();
        // saveIntoDb(file);
    }

    @Test
    public void saveWordFromFile(){
        List<Cell> flagCellList = new ArrayList<>();
        List<Cell> topicCellList = new ArrayList<>();
        List<Cell> typeCellList = new ArrayList<>();

        String[] fileArray = new String[]{ "D:\\desktop\\tmp\\month\\kpi\\2021-09 3.1.0 ， 小程序3.0.0.xls",
                "D:\\desktop\\tmp\\month\\kpi\\2021-10 3.3.0 ， 小程序3.1.0.xls",
                "D:\\desktop\\tmp\\month\\kpi\\2021-10 3.3.0 ， 小程序3.1.1.xls",
                "D:\\desktop\\tmp\\month\\kpi\\SP1201 3.5.0 ， 小程序3.1.0.xls"
               //  ,"",""
        };

        for(String file : fileArray) {
            parserOneFile(flagCellList, topicCellList, typeCellList, file);
        }
        // 保存xls
        String parserFile = "D:\\desktop\\tmp\\month\\绩效\\kpi2-t.xls";
        save2KpiFile(flagCellList, topicCellList, typeCellList, parserFile);
    }

    private void parserOneFile(List<Cell> flagCellList, List<Cell> topicCellList, List<Cell> typeCellList, String file) {
        ExcelReader excelReader = ExcelUtil.getReader(file);
        int rowNum = excelReader.getRowCount();
        // 从第4行开始
        for(int i = 5; i < rowNum; i++){
        //    System.out.println("i=" + i);
            // 标识
            Cell flagCell = excelReader.getCell(1, i);
            flagCellList.add(flagCell);
        //    System.out.println("flagCell" + flagCell);
            // 主体
            Cell topicCell = excelReader.getCell(2, i);
            topicCellList.add(topicCell);
         //   System.out.println("topicCell" + topicCell);
            // 问题类型
            Cell typeCell = excelReader.getCell(3, i);
            typeCellList.add(typeCell);
          //  System.out.println("typeCell" + typeCell);
        }
    }

    private void save2KpiFile(List<Cell> flagCellList, List<Cell> topicCellList, List<Cell> typeCellList, String parserFile) {
        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(parserFile);
        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();
        for(int i = 0; i < flagCellList.size(); i++){
            // 标识
            Cell flagCell = writer.getOrCreateCell(1,i);
            flagCell.setHyperlink(flagCellList.get(i).getHyperlink());
            flagCell.setCellValue(flagCellList.get(i).getStringCellValue());

            // 主体
            Cell topicCell = writer.getOrCreateCell(2,i);
            topicCell.setCellValue(topicCellList.get(i).getStringCellValue());
            // 类型
            Cell typeCell = writer.getOrCreateCell(3,i);
            typeCell.setCellValue(typeCellList.get(i).getStringCellValue());
        }
        //关闭writer，释放内存
        writer.close();
        // saveIntoDb(file);
    }

}
