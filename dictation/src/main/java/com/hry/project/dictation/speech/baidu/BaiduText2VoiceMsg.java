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
     *  // 发音人选择, 基础音库：0为度小美，1为度小宇，3为度逍遥，4为度丫丫，
     // 精品音库：5为度小娇，103为度米朵，106为度博文，110为度小童，111为度小萌，默认为度小美
     */
    private final int per = 0;
    /**
     *  // 语速，取值0-15，默认为5中语速
     */
    private final int spd = 1;
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

    public final String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

    private String cuid = "1234567JAVA";

    private String token;



    /**
     * // text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
     // 可以百度搜索"urlencode"
     private final String text = "你好啊";
     * @throws IOException
     * @throws DemoException
     */
    public void text2wav(String text) {
        if(!StringUtils.hasText(text)){
            return;
        }
        if(!StringUtils.hasText(token)){
            refreshToken();
        }
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
                File file = new File("result2." + format); // 打开mp3文件即可播放
                FileOutputStream os = new FileOutputStream(file);
                os.write(bytes);
                os.close();
                logger.info("audio file write to " + file.getAbsolutePath());
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
