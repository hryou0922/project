package com.hry.project.dictation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class VoiceUtils {
    private static final Logger logger = LoggerFactory.getLogger(VoiceUtils.class);

    public static void play(String file){
        AudioFormat audioFormat = null;
        SourceDataLine sourceDataLine = null;
        DataLine.Info dataLine_info = null;
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(file));
            audioFormat = audioInputStream.getFormat();
            logger.debug("文件[{}],每秒播放帧数：{},每秒播放帧数:{},总帧数：{},音频时长（秒）：{} ",
                    file, audioFormat.getSampleRate(),audioInputStream.getFrameLength(),
                    audioInputStream.getFrameLength() / audioFormat.getSampleRate());
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);

            byte[] b = new byte[1024];
            int len = 0;
            sourceDataLine.open(audioFormat, 1024);
            sourceDataLine.start();
            while ((len = audioInputStream.read(b)) > 0) {
                sourceDataLine.write(b, 0, len);
            }
            sourceDataLine.drain();
        } catch (LineUnavailableException e) {
            logger.error("播放录音文件[{}]失败", file, e);
        } catch (IOException e) {
            logger.error("播放录音文件[{}]失败", file, e);
        } catch (UnsupportedAudioFileException e) {
            logger.error("播放录音文件[{}]失败", file, e);
        } finally {
            if(audioInputStream != null){
                try {
                    audioInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sourceDataLine != null){
                sourceDataLine.close();
            }
        }



    }

}
