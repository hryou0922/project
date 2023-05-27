package com.hry.project.capcut.content.vo;

import lombok.Data;

/**


 {
 "audio_fade": null,
 "cartoon_path": "",
 "category_id": "",
 "category_name": "local",
 "check_flag": 30719,
 "crop": {
 "lower_left_x": 0.0,
 "lower_left_y": 1.0,
 "lower_right_x": 1.0,
 "lower_right_y": 1.0,
 "upper_left_x": 0.0,
 "upper_left_y": 0.0,
 "upper_right_x": 1.0,
 "upper_right_y": 0.0
 },
 "crop_ratio": "free",
 "crop_scale": 1.0,
 "duration": 10800000000,
 "extra_type_option": 0,
 "formula_id": "",
 "freeze": null,
 "gameplay": null,
 "has_audio": false,
 "height": 2160,
 "id": "20D83B28-B2DD-4a9b-B30D-F663D5DE1DFE",
 "intensifies_audio_path": "",
 "intensifies_path": "",
 "is_unified_beauty_mode": false,
 "local_id": "",
 "local_material_id": "",
 "material_id": "",
 "material_name": "5a30f773aa358.jpg",
 "material_url": "",
 "matting": {
 "flag": 0,
 "has_use_quick_brush": false,
 "has_use_quick_eraser": false,
 "interactiveTime": [

 ],
 "path": "",
 "strokes": [

 ]
 },
 "media_path": "",
 "object_locked": null,
 "path": "D:/douyin/sucai/tupian/5a30f773aa358.jpg",
 "picture_from": "none",
 "picture_set_category_id": "",
 "picture_set_category_name": "",
 "reverse_intensifies_path": "",
 "reverse_path": "",
 "source_platform": 0,
 "stable": null,
 "team_id": "",
 "type": "photo",
 "video_algorithm": {
 "algorithms": [

 ],
 "deflicker": null,
 "motion_blur_config": null,
 "noise_reduction": null,
 "path": "",
 "time_range": null
 },
 "width": 3840
 }

 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 22:33
 */
@Data
public class MaterialsVideosVo implements BaseElementVo {


    /**
     * audio_fade : null
     * cartoon_path :
     * category_id :
     * category_name : local
     * check_flag : 30719
     * crop : {"lower_left_x":0,"lower_left_y":1,"lower_right_x":1,"lower_right_y":1,"upper_left_x":0,"upper_left_y":0,"upper_right_x":1,"upper_right_y":0}
     * crop_ratio : free
     * crop_scale : 1
     * duration : 10800000000
     * extra_type_option : 0
     * formula_id :
     * freeze : null
     * gameplay : null
     * has_audio : false
     * height : 2160
     * id : 20D83B28-B2DD-4a9b-B30D-F663D5DE1DFE
     * intensifies_audio_path :
     * intensifies_path :
     * is_unified_beauty_mode : false
     * local_id :
     * local_material_id :
     * material_id :
     * material_name : 5a30f773aa358.jpg
     * material_url :
     * matting : {"flag":0,"has_use_quick_brush":false,"has_use_quick_eraser":false,"interactiveTime":[],"path":"","strokes":[]}
     * media_path :
     * object_locked : null
     * path : D:/douyin/sucai/tupian/5a30f773aa358.jpg
     * picture_from : none
     * picture_set_category_id :
     * picture_set_category_name :
     * reverse_intensifies_path :
     * reverse_path :
     * source_platform : 0
     * stable : null
     * team_id :
     * type : photo
     * video_algorithm : {"algorithms":[],"deflicker":null,"motion_blur_config":null,"noise_reduction":null,"path":"","time_range":null}
     * width : 3840
     */

    private long duration;
    private int height;
    private String id;
    private String material_name;
    private String path;
    private String type;
    private int width;
}
