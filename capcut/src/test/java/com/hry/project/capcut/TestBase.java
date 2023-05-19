package com.hry.project.capcut;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/12 23:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CapcutApplication.class)
@ActiveProfiles("dev")
public class TestBase {
}
