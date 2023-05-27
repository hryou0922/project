package com.hry.project.capcut.content.vo;

import lombok.Data;

import java.util.List;

/**
 {
 "animations": [
 {
 "category_id": "",
 "category_name": "",
 "duration": 2300000,
 "id": "1729286",
 "material_type": "",
 "name": "右下擦除",
 "panel": "",
 "path": "C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/1729286/5e99fcba0a90d5bde52a85e034469aea",
 "platform": "all",
 "resource_id": "7090146831836910110",
 "start": 0,
 "type": "out"
 }
 ],
 "id": "87BE669E-D37E-4a8c-ACAC-1F1C5691700E",
 "type": "sticker_animation"
 }

 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 14:53
 */

@Data
public class MaterialsAnimationsVo implements BaseElementVo {

    /**
     * animations : [{"category_id":"","category_name":"","duration":2300000,"id":"1729286","material_type":"","name":"右下擦除","panel":"","path":"C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/1729286/5e99fcba0a90d5bde52a85e034469aea","platform":"all","resource_id":"7090146831836910110","start":0,"type":"out"}]
     * id : 87BE669E-D37E-4a8c-ACAC-1F1C5691700E
     * type : sticker_animation
     */

    private String id;
    private String type;
    private List<AnimationsBean> animations;


    @Data
    public static class AnimationsBean {
        /**
         * category_id :
         * category_name :
         * duration : 2300000
         * id : 1729286
         * material_type :
         * name : 右下擦除
         * panel :
         * path : C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/1729286/5e99fcba0a90d5bde52a85e034469aea
         * platform : all
         * resource_id : 7090146831836910110
         * start : 0
         * type : out
         */

        private String category_id;
        private String category_name;
        private int duration;
        private String id;
        private String material_type;
        private String name;
        private String panel;
        private String path;
        private String platform;
        private String resource_id;
        private int start;
        private String type;

    }
}
