package com.hry.project.capcut.content.vo;

import lombok.Data;

/**
 *
 {
 "app_id": 0,
 "category_id": "",
 "category_name": "local_music",
 "check_flag": 1,
 "duration": 238866667,
 "effect_id": "",
 "formula_id": "",
 "id": "7647BEBC-5F7A-43c4-847D-8DD46C880F08",
 "intensifies_path": "",
 "local_material_id": "",
 "music_id": "3ab8f97c-ae00-432e-8a85-6f087a69e77a",
 "name": "提取音频20230511-3",
 "path": "D:/douyin/sucai/mp3/不让我的眼泪陪我过夜#齐秦.mp3",
 "resource_id": "",
 "source_platform": 0,
 "team_id": "",
 "text_id": "",
 "tone_category_id": "",
 "tone_category_name": "",
 "tone_effect_id": "",
 "tone_effect_name": "",
 "tone_speaker": "",
 "tone_type": "",
 "type": "extract_music",
 "video_id": "",
 "wave_points": [

 ]
 }
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 23:35
 */
@Data
public class MaterialsAudiosVo implements BaseElementVo {

    /**
     * app_id : 0
     * category_id :
     * category_name : local_music
     * check_flag : 1
     * duration : 238866667
     * effect_id :
     * formula_id :
     * id : 7647BEBC-5F7A-43c4-847D-8DD46C880F08
     * intensifies_path :
     * local_material_id :
     * music_id : 3ab8f97c-ae00-432e-8a85-6f087a69e77a
     * name : 提取音频20230511-3
     * path : D:/douyin/sucai/mp3/不让我的眼泪陪我过夜#齐秦.mp3
     * resource_id :
     * source_platform : 0
     * team_id :
     * text_id :
     * tone_category_id :
     * tone_category_name :
     * tone_effect_id :
     * tone_effect_name :
     * tone_speaker :
     * tone_type :
     * type : extract_music
     * video_id :
     * wave_points : []
     */

    private int app_id;
    private String category_id;
    private String category_name;
    private int check_flag;
    private long duration;
    private String effect_id;
    private String formula_id;
    private String id;
    private String intensifies_path;
    private String local_material_id;
    private String music_id;
    private String name;
    private String path;
    private String resource_id;
    private int source_platform;
    private String team_id;
    private String text_id;
    private String tone_category_id;
    private String tone_category_name;
    private String tone_effect_id;
    private String tone_effect_name;
    private String tone_speaker;
    private String tone_type;
    private String type;
    private String video_id;


}
