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
        String cookie = "_ntes_nnid=d0d0877a0087d102c0adabe97303dc6f,1617878468203; _ga=GA1.1.285673834.1621822078; _ga_49ELM1L14F=GS1.1.1621822136.1.1.1621822140.0; hb_MA-809E-C9B7A6CD76FA_source=kms.netease.com; JSESSIONID=8589F86969F5035C554726B2C9258598; atlassian.xsrf.token=BZL9-PJFZ-08YD-4XJD|4a5df5aa09227a00a22e52c1069714a5d551a87e|lin; gantt_date_format_dmy=yyyy-MM-dd; gantt_date_format_complete=yyyy-MM-dd HH:mm:ss; gantt_locale=zh_CN; op_state_id_1.0=pso5hdh4ai; jira.editor.user.mode=wysiwyg; hrs_online_op_state_id_1.0=5ufnazmstz";
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

        String cookie = "_ntes_nnid=d0d0877a0087d102c0adabe97303dc6f,1617878468203; _ga=GA1.1.285673834.1621822078; _ga_49ELM1L14F=GS1.1.1621822136.1.1.1621822140.0; hb_MA-809E-C9B7A6CD76FA_source=kms.netease.com; JSESSIONID=8589F86969F5035C554726B2C9258598; atlassian.xsrf.token=BZL9-PJFZ-08YD-4XJD|4a5df5aa09227a00a22e52c1069714a5d551a87e|lin; gantt_date_format_dmy=yyyy-MM-dd; gantt_date_format_complete=yyyy-MM-dd HH:mm:ss; gantt_locale=zh_CN; op_state_id_1.0=pso5hdh4ai; jira.editor.user.mode=wysiwyg; hrs_online_op_state_id_1.0=5ufnazmstz";
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
                }
                costCell.setCellValue(subTaskVo.getCostTime());
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

        String[] fileArray = new String[]{ "D:\\desktop\\tmp\\month\\kpi\\2021-03 2.22.0 ， 小程序2.9.1.xls",
                "D:\\desktop\\tmp\\month\\kpi\\2021-04 2.24.0 ， 小程序2.9.4.xls",
                "D:\\desktop\\tmp\\month\\kpi\\2021-04 2.25.0 ， 小程序2.10.0.xls",
                "D:\\desktop\\tmp\\month\\kpi\\2021-05 2.23.0 ， 小程序2.9.2.xls"
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
