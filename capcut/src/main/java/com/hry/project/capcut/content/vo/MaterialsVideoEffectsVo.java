package com.hry.project.capcut.content.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 22:43
 */
@Data
public class MaterialsVideoEffectsVo implements BaseElementVo {


    /**
     * adjust_params : [{"default_value":0.33,"name":"effects_adjust_speed","value":0.33},{"default_value":1,"name":"effects_adjust_background_animation","value":1}]
     * apply_target_type : 2
     * apply_time_range : null
     * category_id : 7734
     * category_name : 自然
     * effect_id : 635039
     * formula_id :
     * id : 32561E69-D39A-4fd9-9F39-4DA1C247D92C
     * keyframe_refs : []
     * name : 下雨
     * path : C:/Users/Administrator/AppData/Local/JianyingPro/User Data/Cache/effect/635039/40a59ce61692a825c049cd5b15bc6ded
     * platform : all
     * render_index : 0
     * resource_id : 6734619419890160131
     * source_platform : 0
     * time_range : null
     * track_render_index : 0
     * type : video_effect
     * value : 1
     * version :
     */

    private int apply_target_type;
    private Object apply_time_range;
    private String category_id;
    private String category_name;
    private String effect_id;
    private String formula_id;
    private String id;
    private String name;
    private String path;
    private String platform;
    private int render_index;
    private String resource_id;
    private int source_platform;
    private Object time_range;
    private int track_render_index;
    private String type;
    private int value;
    private String version;
    private List<AdjustParamsBean> adjust_params;
    private List<String> keyframe_refs;

    @Data
    public static class AdjustParamsBean {
        /**
         * default_value : 0.33
         * name : effects_adjust_speed
         * value : 0.33
         */

        private double default_value;
        private String name;
        private double value;

    }
}
