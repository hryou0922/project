package com.hry.project.dictation;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/3 19:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DictationApplication.class)
@ActiveProfiles("dev")
public class BaseTest {
}
