package com.hry.zhihu;

public class TestM {
    public static void main(String[] args){
        String name = "<br /><br /><br />你说呢？<br />";
        System.out.println(name.replaceAll("",""));
        System.out.println(name.replaceAll("(<br />)+","<br />"));
    }
}
