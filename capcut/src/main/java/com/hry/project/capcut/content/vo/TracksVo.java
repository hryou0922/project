package com.hry.project.capcut.content.vo;

import com.hry.project.capcut.content.vo.common.SourceTimerangeVo;
import com.hry.project.capcut.content.vo.common.TargetTimerangeVo;
import lombok.Data;

import java.util.List;

/**
 {
 "attribute": 1,
 "flag": 0,
 "id": "25945A48-6C8C-45d7-AD1A-494194B20880",
 "segments": [
 {
 "cartoon": false,
 "clip": {
 "alpha": 1.0,
 "flip": {
 "horizontal": false,
 "vertical": false
 },
 "rotation": 0.0,
 "scale": {
 "x": 1.0,
 "y": 1.0
 },
 "transform": {
 "x": 0.0,
 "y": 0.0
 }
 },
 "enable_adjust": true,
 "enable_color_curves": true,
 "enable_color_wheels": true,
 "enable_lut": true,
 "enable_smart_color_adjust": false,
 "extra_material_refs": [
 "0349B9B5-FB07-41be-AB53-4409C4FE102F",
 "EB830E25-2EB1-4a29-A62E-E96E9C622E2C",
 "B1A142C7-526C-4aa8-A174-53A3A548975A",
 "EAC0C45F-4CDC-4d7e-91F9-3D904018BF30"
 ],
 "group_id": "",
 "hdr_settings": {
 "intensity": 1.0,
 "mode": 1,
 "nits": 1000
 },
 "id": "7AEF69E1-3DE0-4869-927E-AC14B2312D6B",
 "intensifies_audio": false,
 "is_placeholder": false,
 "is_tone_modify": false,
 "keyframe_refs": [
 "C0B59A2A-10F5-423c-B767-B4CD579BC117",
 "855C6513-F3BC-4695-A9E6-D44DEE370BB0"
 ],
 "last_nonzero_volume": 1.0,
 "material_id": "20D83B28-B2DD-4a9b-B30D-F663D5DE1DFE",
 "render_index": 0,
 "reverse": false,
 "source_timerange": {
 "duration": 238866667,
 "start": 0
 },
 "speed": 1.0,
 "target_timerange": {
 "duration": 238866667,
 "start": 0
 },
 "template_id": "",
 "template_scene": "default",
 "track_attribute": 1,
 "track_render_index": 0,
 "visible": true,
 "volume": 0.0
 }
 ],
 "type": "video"
 }

 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 14:45
 */
@Data
public class TracksVo implements BaseElementVo {

    /**
     * attribute : 1
     * flag : 0
     * id : 25945A48-6C8C-45d7-AD1A-494194B20880
     * segments : [{"cartoon":false,"clip":{"alpha":1,"flip":{"horizontal":false,"vertical":false},"rotation":0,"scale":{"x":1,"y":1},"transform":{"x":0,"y":0}},"enable_adjust":true,"enable_color_curves":true,"enable_color_wheels":true,"enable_lut":true,"enable_smart_color_adjust":false,"extra_material_refs":["0349B9B5-FB07-41be-AB53-4409C4FE102F","EB830E25-2EB1-4a29-A62E-E96E9C622E2C","B1A142C7-526C-4aa8-A174-53A3A548975A","EAC0C45F-4CDC-4d7e-91F9-3D904018BF30"],"group_id":"","hdr_settings":{"intensity":1,"mode":1,"nits":1000},"id":"7AEF69E1-3DE0-4869-927E-AC14B2312D6B","intensifies_audio":false,"is_placeholder":false,"is_tone_modify":false,"keyframe_refs":["C0B59A2A-10F5-423c-B767-B4CD579BC117","855C6513-F3BC-4695-A9E6-D44DEE370BB0"],"last_nonzero_volume":1,"material_id":"20D83B28-B2DD-4a9b-B30D-F663D5DE1DFE","render_index":0,"reverse":false,"source_timerange":{"duration":238866667,"start":0},"speed":1,"target_timerange":{"duration":238866667,"start":0},"template_id":"","template_scene":"default","track_attribute":1,"track_render_index":0,"visible":true,"volume":0}]
     * type : video
     */

    private int attribute;
    private int flag;
    private String id;
    private String type;
    private List<SegmentsBean> segments;

    @Data
    public static class SegmentsBean {
        /**
         * cartoon : false
         * clip : {"alpha":1,"flip":{"horizontal":false,"vertical":false},"rotation":0,"scale":{"x":1,"y":1},"transform":{"x":0,"y":0}}
         * enable_adjust : true
         * enable_color_curves : true
         * enable_color_wheels : true
         * enable_lut : true
         * enable_smart_color_adjust : false
         * extra_material_refs : ["0349B9B5-FB07-41be-AB53-4409C4FE102F","EB830E25-2EB1-4a29-A62E-E96E9C622E2C","B1A142C7-526C-4aa8-A174-53A3A548975A","EAC0C45F-4CDC-4d7e-91F9-3D904018BF30"]
         * group_id :
         * hdr_settings : {"intensity":1,"mode":1,"nits":1000}
         * id : 7AEF69E1-3DE0-4869-927E-AC14B2312D6B
         * intensifies_audio : false
         * is_placeholder : false
         * is_tone_modify : false
         * keyframe_refs : ["C0B59A2A-10F5-423c-B767-B4CD579BC117","855C6513-F3BC-4695-A9E6-D44DEE370BB0"]
         * last_nonzero_volume : 1
         * material_id : 20D83B28-B2DD-4a9b-B30D-F663D5DE1DFE
         * render_index : 0
         * reverse : false
         * source_timerange : {"duration":238866667,"start":0}
         * speed : 1
         * target_timerange : {"duration":238866667,"start":0}
         * template_id :
         * template_scene : default
         * track_attribute : 1
         * track_render_index : 0
         * visible : true
         * volume : 0
         */

        private boolean cartoon;
        private ClipBean clip;
        private boolean enable_adjust;
        private boolean enable_color_curves;
        private boolean enable_color_wheels;
        private boolean enable_lut;
        private boolean enable_smart_color_adjust;
        private String group_id;
        private HdrSettingsBean hdr_settings;
        private String id;
        private boolean intensifies_audio;
        private boolean is_placeholder;
        private boolean is_tone_modify;
        private int last_nonzero_volume;
        private String material_id;
        private int render_index;
        private boolean reverse;
        private SourceTimerangeVo source_timerange;
        private int speed;
        private TargetTimerangeVo target_timerange;
        private String template_id;
        private String template_scene;
        private int track_attribute;
        private int track_render_index;
        private boolean visible;
        private int volume;
        private List<String> extra_material_refs;
        private List<String> keyframe_refs;

        @Data
        public static class ClipBean {
            /**
             * alpha : 1
             * flip : {"horizontal":false,"vertical":false}
             * rotation : 0
             * scale : {"x":1,"y":1}
             * transform : {"x":0,"y":0}
             */

            private int alpha;
            private FlipBean flip;
            private double rotation;
            private ScaleBean scale;
            private TransformBean transform;

            @Data
            public static class FlipBean {
                /**
                 * horizontal : false
                 * vertical : false
                 */

                private boolean horizontal;
                private boolean vertical;

            }

            @Data
            public static class ScaleBean {
                /**
                 * x : 1
                 * y : 1
                 */

                private double x;
                private double y;

            }

            @Data
            public static class TransformBean {
                /**
                 * x : 0
                 * y : 0
                 */

                private double x;
                private double y;

            }
        }

        @Data
        public static class HdrSettingsBean {
            /**
             * intensity : 1
             * mode : 1
             * nits : 1000
             */

            private int intensity;
            private int mode;
            private int nits;

        }

    }
}
