package com.hry.project.simple.csdnblog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by huangrongyou@yixin.im on 2018/4/2.
 */
public class CsdnBlogManger {
    private  List<ArticleBO> articleBOListGlobal; // 保存解析出来的文章
    private String urlPrex; // 博客地址前缀
    private int num; // 解析的页面数

    public CsdnBlogManger(String urlPrex, int num){
        articleBOListGlobal = new ArrayList<>();
        this.urlPrex = urlPrex;
        this.num = num;
    }

    public void parser(){
        for(int page = 1; num > 0; num--){
            // url = "https://blog.csdn.net/hry2015/article/list/" + page;
            String tmpUrl = urlPrex + page++;
            List<ArticleBO> articleBOList = CsdnBlogSinglePageParserUtil.execute(tmpUrl);
            if(articleBOList == null){
                break;
            }
            articleBOListGlobal.addAll(articleBOList);
        }
    }

    /**
     * 打印
     */
    public void printAll(){
        System.out.println("Total = " + articleBOListGlobal.size());
        articleBOListGlobal.forEach(o -> System.out.println(o));
    }

    /**
     * 计算出阅读总数
     * @return
     */
    public int countNumberOfView(){
        // 计算总数
        return articleBOListGlobal.stream().map(a-> a.getNumberOfView()).reduce(0,(a, b) -> a + b);
    }

    /**
     * 获取一个不可修改对象
     * @return
     */
    public List<ArticleBO> getUnModifiableListArticleBOListGlobal(){
        return Collections.unmodifiableList(articleBOListGlobal);
    }

}
