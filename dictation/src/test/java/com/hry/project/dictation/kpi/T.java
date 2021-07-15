package com.hry.project.dictation.kpi;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/7/13 11:10
 */
public class T {

    @Test
    public void a(){
        byte[] b = Convert.hexToBytes("40470000000000010001000000000080271000877b2274797065223a227465726d696e616c222c22636f6d6d616e64223a226c696e6b222c2272657175657374223a7b224d6f64656c4e616d65223a224b5437284341543129222c224964656e74697479223a22383635343433303430373532323434222c224465766963654944223a22222c2244657669636554797065223a2231303332227d7d");
        System.out.println(Arrays.toString(b));
        System.out.println(Base64.encode(b));
    }
}
