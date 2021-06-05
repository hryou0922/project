package com.hry.project.dictation.speech.baidu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 百度 文本转语音
 */
public class BaiduText2VoiceMsg {
    private static final Logger logger = LoggerFactory.getLogger(BaiduText2VoiceMsg.class);


    /**
     * 填写网页上申请的appkey
     */
    private final String appKey = "ZgaYIW8ew8NHKiUm5ZchcNLE";

    /**
     *  填写网页上申请的APP SECRET
            */
    private final String secretKey = "d59wELkaMyKFEgt0BkE3z9GNV6bAkDGf";

    /**
     * 可以使用https
     */
    private final String url = "http://tsn.baidu.com/text2audio";

    /**
     *  // 发音人选择, 基础音库：0为度小美，1为度小宇，3为度逍遥，4为度丫丫，
     // 精品音库：5为度小娇，103为度米朵，106为度博文，110为度小童，111为度小萌，默认为度小美
     */
    private final int per = 0;
    /**
     *  // 语速，取值0-15，默认为5中语速
     */
    private final int spd = 5;
    /**
     *  // 音调，取值0-15，默认为5中语调
     */
    private final int pit = 5;
    /**
     *  // 音量，取值0-9，默认为5中音量
     */
    private final int vol = 5;

    /**
     *  // 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
     */
    private final int aue = 6;


    private String cuid = "1234567JAVA";

    private String token;

    /**
     * 录音跟目录
     */
    private String rootDir;

    public BaiduText2VoiceMsg(String rootDir) {
        this.rootDir = rootDir;
        // 创建目录
        new File(rootDir).mkdirs();
    }

    /**
     * / text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
     // 可以百度搜索"urlencode"
     * @param text
     * @param relativeDir 相对目录：如 /2024/12/
     * @return 如果执行成功，则返回对象，否则返回null
     */
    public VoiceInfoResultVo text2wav(String text, String relativeDir) {
        if(!StringUtils.hasText(text)){
            return null;
        }
        if(!StringUtils.hasText(token)){
            refreshToken();
        }
        if(StringUtils.hasText(relativeDir)){
            // 创建目录
            boolean mkdirResult = new File(rootDir + File.separator + relativeDir).mkdirs();
            System.out.println(rootDir + File.separator + relativeDir + " " +mkdirResult);
        }

        VoiceInfoResultVo voiceInfoResultVo = null;
        // 此处2次urlencode， 确保特殊字符被正确编码
        String params = "tex=" + ConnUtil.urlEncode(ConnUtil.urlEncode(text));
        params += "&per=" + per;
        params += "&spd=" + spd;
        params += "&pit=" + pit;
        params += "&vol=" + vol;
        params += "&cuid=" + cuid;
        params += "&tok=" + token;
        params += "&aue=" + aue;
        params += "&lan=zh&ctp=1";
        logger.info("请求地址usr=[{}]", url + "?" + params);
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
            printWriter.write(params);
            printWriter.close();
            String contentType = conn.getContentType();
            if (contentType.contains("audio/")) {
                byte[] bytes = ConnUtil.getResponseBytes(conn);
                String format = getFormat(aue);
                File file = new File(rootDir + File.separator + relativeDir + File.separator + text + "." + format);
                FileOutputStream os = new FileOutputStream(file);
                os.write(bytes);
                os.close();
                logger.info("audio file write to " + file.getAbsolutePath());
                voiceInfoResultVo = new VoiceInfoResultVo();
                voiceInfoResultVo.setVoiceFilePath(File.separator + relativeDir + File.separator + text + "." + format);
            } else {
                logger.error("ERROR: content-type= " + contentType);
                String res = ConnUtil.getResponseString(conn);
                logger.error(res);
            }
        }catch (IOException e){
            logger.error("[{}]语音转换失败", text, e);
        } catch (DemoException e) {
            logger.error("[{}]语音转换失败", text, e);
        }
        return voiceInfoResultVo;
    }

    /**
     * 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
     * @param aue
     * @return
     */
    private String getFormat(int aue) {
        String[] formats = {"mp3", "pcm", "pcm", "wav"};
        return formats[aue - 3];
    }

    /**
     * 刷新token
     */
    private void refreshToken(){
        TokenHolder holder = new TokenHolder(appKey, secretKey, TokenHolder.ASR_SCOPE);
        try {
            holder.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DemoException e) {
            e.printStackTrace();
        }
        this.token = holder.getToken();
    }
}
