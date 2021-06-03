package com.hry.project.dictation.test;

import org.junit.Test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestPlayVoice2 {
    private AudioFormat audioFormat = null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;
    // private String file = "D:/workspace_mars/wav/src/main/resources/music/12.wav";
    private String file="D:\\study\\project\\voice\\result.wav";
    private AudioInputStream audioInputStream = null;

    public TestPlayVoice2() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(file));
        //audioInputStream=AudioSystem.getAudioInputStream(new URL(file));
        audioFormat = audioInputStream.getFormat();
        System.out.println("每秒播放帧数："+audioFormat.getSampleRate());
        System.out.println("总帧数："+audioInputStream.getFrameLength());
        System.out.println("音频时长（秒）："+audioInputStream.getFrameLength()/audioFormat.getSampleRate());
        dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
    }

    public void play() throws IOException, LineUnavailableException {
        byte[] b = new byte[1024];
        int len = 0;
        sourceDataLine.open(audioFormat, 1024);
        sourceDataLine.start();
        while ((len = audioInputStream.read(b)) > 0) {
            sourceDataLine.write(b, 0, len);
        }
        audioInputStream.close();
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    public static void main(String[] args) {
        try {
            TestPlayVoice2 p = new TestPlayVoice2();

            p.play();
            p.play();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
