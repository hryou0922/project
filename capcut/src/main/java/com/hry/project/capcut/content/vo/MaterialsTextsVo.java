package com.hry.project.capcut.content.vo;

import lombok.Data;

/**

 {
 "add_type": 0,
 "alignment": 1,
 "background_alpha": 1.0,
 "background_color": "",
 "background_height": 1.0,
 "background_horizontal_offset": 0.0,
 "background_round_radius": 0.0,
 "background_style": 0,
 "background_vertical_offset": 0.0,
 "background_width": 1.0,
 "bold_width": 0.0,
 "border_color": "#000000",
 "border_width": 0.07999999821186066,
 "check_flag": 15,
 "content": "<font id=\"\" path=\"C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf\"><color=(1.000000, 1.000000, 1.000000, 1.000000)><size=5.000000><useLetterColor><u><outline color=(0.000000, 0.000000, 0.000000, 1.000000) width=0.080000>倘若你喜欢音乐，而我刚好有</outline></u></useLetterColor></size></color></font>",
 "font_category_id": "",
 "font_category_name": "",
 "font_id": "",
 "font_name": "",
 "font_path": "C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf",
 "font_resource_id": "",
 "font_size": 5.0,
 "font_source_platform": 0,
 "font_team_id": "",
 "font_title": "none",
 "font_url": "",
 "fonts": [

 ],
 "global_alpha": 1.0,
 "group_id": "",
 "has_shadow": false,
 "id": "67866807-4CEA-43c4-9665-606734C65310",
 "initial_scale": 1.0,
 "is_rich_text": false,
 "italic_degree": 0,
 "ktv_color": "",
 "layer_weight": 1,
 "letter_spacing": 0.15,
 "line_spacing": 0.02,
 "name": "",
 "preset_category": "",
 "preset_category_id": "",
 "preset_has_set_alignment": false,
 "preset_id": "",
 "preset_index": 0,
 "preset_name": "",
 "recognize_type": 0,
 "shadow_alpha": 0.8,
 "shadow_angle": -45.0,
 "shadow_color": "",
 "shadow_distance": 8.0,
 "shadow_point": {
 "x": 1.0182337649086284,
 "y": -1.0182337649086284
 },
 "shadow_smoothing": 1.0,
 "shape_clip_x": false,
 "shape_clip_y": false,
 "style_name": "白字黑边",
 "sub_type": 0,
 "text_alpha": 1.0,
 "text_color": "#ffffff",
 "text_preset_resource_id": "",
 "text_size": 30,
 "text_to_audio_ids": [

 ],
 "tts_auto_update": false,
 "type": "text",
 "typesetting": 0,
 "underline": true,
 "underline_offset": 0.22,
 "underline_width": 0.05,
 "use_effect_default_color": false,
 "words": [

 ]
 }
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 23:26
 */
@Data
public class MaterialsTextsVo implements BaseElementVo {

    /**
     * add_type : 0
     * alignment : 1
     * background_alpha : 1
     * background_color :
     * background_height : 1
     * background_horizontal_offset : 0
     * background_round_radius : 0
     * background_style : 0
     * background_vertical_offset : 0
     * background_width : 1
     * bold_width : 0
     * border_color : #000000
     * border_width : 0.07999999821186066
     * check_flag : 15
     * content : <font id="" path="C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf"><color=(1.000000, 1.000000, 1.000000, 1.000000)><size=5.000000><useLetterColor><u><outline color=(0.000000, 0.000000, 0.000000, 1.000000) width=0.080000>倘若你喜欢音乐，而我刚好有</outline></u></useLetterColor></size></color></font>
     * font_category_id :
     * font_category_name :
     * font_id :
     * font_name :
     * font_path : C:/Users/Administrator/AppData/Local/JianyingPro/Apps/4.0.1.9886/Resources/Font/SystemFont/zh-hans.ttf
     * font_resource_id :
     * font_size : 5
     * font_source_platform : 0
     * font_team_id :
     * font_title : none
     * font_url :
     * fonts : []
     * global_alpha : 1
     * group_id :
     * has_shadow : false
     * id : 67866807-4CEA-43c4-9665-606734C65310
     * initial_scale : 1
     * is_rich_text : false
     * italic_degree : 0
     * ktv_color :
     * layer_weight : 1
     * letter_spacing : 0.15
     * line_spacing : 0.02
     * name :
     * preset_category :
     * preset_category_id :
     * preset_has_set_alignment : false
     * preset_id :
     * preset_index : 0
     * preset_name :
     * recognize_type : 0
     * shadow_alpha : 0.8
     * shadow_angle : -45
     * shadow_color :
     * shadow_distance : 8
     * shadow_point : {"x":1.0182337649086284,"y":-1.0182337649086284}
     * shadow_smoothing : 1
     * shape_clip_x : false
     * shape_clip_y : false
     * style_name : 白字黑边
     * sub_type : 0
     * text_alpha : 1
     * text_color : #ffffff
     * text_preset_resource_id :
     * text_size : 30
     * text_to_audio_ids : []
     * tts_auto_update : false
     * type : text
     * typesetting : 0
     * underline : true
     * underline_offset : 0.22
     * underline_width : 0.05
     * use_effect_default_color : false
     * words : []
     */

    private int add_type;
    private int alignment;
    private int background_alpha;
    private String background_color;
    private int background_height;
    private int background_horizontal_offset;
    private int background_round_radius;
    private int background_style;
    private int background_vertical_offset;
    private int background_width;
    private int bold_width;
    private String border_color;
    private double border_width;
    private int check_flag;
    private String content;
    private String font_category_id;
    private String font_category_name;
    private String font_id;
    private String font_name;
    private String font_path;
    private String font_resource_id;
    private int font_size;
    private int font_source_platform;
    private String font_team_id;
    private String font_title;
    private String font_url;
    private int global_alpha;
    private String group_id;
    private boolean has_shadow;
    private String id;
    private int initial_scale;
    private boolean is_rich_text;
    private int italic_degree;
    private String ktv_color;
    private int layer_weight;
    private double letter_spacing;
    private double line_spacing;
    private String name;
    private String preset_category;
    private String preset_category_id;
    private boolean preset_has_set_alignment;
    private String preset_id;
    private int preset_index;
    private String preset_name;
    private int recognize_type;
    private double shadow_alpha;
    private int shadow_angle;
    private String shadow_color;
    private int shadow_distance;
    private int shadow_smoothing;
    private boolean shape_clip_x;
    private boolean shape_clip_y;
    private String style_name;
    private int sub_type;
    private int text_alpha;
    private String text_color;
    private String text_preset_resource_id;
    private int text_size;
    private boolean tts_auto_update;
    private String type;
    private int typesetting;
    private boolean underline;
    private double underline_offset;
    private double underline_width;
    private boolean use_effect_default_color;

}
