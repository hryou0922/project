package com.hry.project.capcut.content.vo;

import lombok.Data;

/**
 {
 "alpha": 1.0,
 "brightness_value": 0.0,
 "chroma_intensity": 0.0,
 "chroma_shadow": 0.0,
 "contrast_value": 0.0,
 "effect_adjust_param_1": 1.0,
 "effect_adjust_param_2": 1.0,
 "effect_adjust_param_3": 1.0,
 "fade_value": 0.0,
 "figure_slim": null,
 "figure_stretch": null,
 "figure_zoom": null,
 "filter_value": 1.0,
 "flags": 7,
 "graph": null,
 "highlight_value": 0.0,
 "id": "AC216D62-82DE-497c-AAE1-1B545001D04C",
 "last_volume": 1.0,
 "light_sensation_value": 0.0,
 "log_color_wheels_intensity": 1.0,
 "lut_value": 0.0,
 "mask_config": null,
 "particle_value": 0.0,
 "position": {
 "x": 0.0,
 "y": 0.0
 },
 "primary_color_wheels_intensity": 1.0,
 "rotation": 0.0,
 "saturation_value": 0.0,
 "scale": {
 "x": 0.9958333333333333,
 "y": 0.9958333333333333
 },
 "shadow_value": 0.0,
 "sharpen_value": 0.0,
 "smart_color_adjust_value": 1.0,
 "temperature_value": 0.0,
 "time_offset": 2233333,
 "tone_value": 0.0,
 "type": "video",
 "vignetting_value": 0.0,
 "volume": 1.0
 }
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 20:45
 */
@Data
public class KeyframesVideosVo implements BaseElementVo {


    /**
     * alpha : 1.0
     * brightness_value : 0.0
     * chroma_intensity : 0.0
     * chroma_shadow : 0.0
     * contrast_value : 0.0
     * effect_adjust_param_1 : 1.0
     * effect_adjust_param_2 : 1.0
     * effect_adjust_param_3 : 1.0
     * fade_value : 0.0
     * figure_slim : null
     * figure_stretch : null
     * figure_zoom : null
     * filter_value : 1.0
     * flags : 7
     * graph : null
     * highlight_value : 0.0
     * id : AC216D62-82DE-497c-AAE1-1B545001D04C
     * last_volume : 1.0
     * light_sensation_value : 0.0
     * log_color_wheels_intensity : 1.0
     * lut_value : 0.0
     * mask_config : null
     * particle_value : 0.0
     * position : {"x":0,"y":0}
     * primary_color_wheels_intensity : 1.0
     * rotation : 0.0
     * saturation_value : 0.0
     * scale : {"x":0.9958333333333333,"y":0.9958333333333333}
     * shadow_value : 0.0
     * sharpen_value : 0.0
     * smart_color_adjust_value : 1.0
     * temperature_value : 0.0
     * time_offset : 2233333
     * tone_value : 0.0
     * type : video
     * vignetting_value : 0.0
     * volume : 1.0
     */

    private double alpha;
    private double brightness_value;
    private double chroma_intensity;
    private double chroma_shadow;
    private double contrast_value;
    private double effect_adjust_param_1;
    private double effect_adjust_param_2;
    private double effect_adjust_param_3;
    private double fade_value;
//    private Object figure_slim;
//    private Object figure_stretch;
//    private Object figure_zoom;
    private double filter_value;
    private int flags;
//    private Object graph;
    private double highlight_value;
    private String id;
    private double last_volume;
    private double light_sensation_value;
    private double log_color_wheels_intensity;
    private double lut_value;
//    private Object mask_config;
    private double particle_value;
    private PositionBean position;
    private double primary_color_wheels_intensity;
    private double rotation;
    private double saturation_value;
    private ScaleBean scale;
    private double shadow_value;
    private double sharpen_value;
    private double smart_color_adjust_value;
    private double temperature_value;
    private long time_offset;
    private double tone_value;
    private String type;
    private double vignetting_value;
    private double volume;

    @Data
    public static class PositionBean {
        /**
         * x : 0.0
         * y : 0.0
         */

        private double x;
        private double y;

    }

    @Data
    public static class ScaleBean {
        /**
         * x : 0.9958333333333333
         * y : 0.9958333333333333
         */

        private double x;
        private double y;

    }
}
