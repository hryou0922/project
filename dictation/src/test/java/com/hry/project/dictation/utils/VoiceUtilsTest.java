package com.hry.project.dictation.utils;

import org.junit.Test;

public class VoiceUtilsTest {

    // public static void main(String[] args) {
    @Test
    public void a(){
        int num = 2;
        while(num-- > 0){
            String file = "D:\\study\\project\\voice\\result.wav";
            VoiceUtils.play(file);
        }
    }
}
