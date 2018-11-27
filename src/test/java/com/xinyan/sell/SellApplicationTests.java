package com.xinyan.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellApplicationTests {

    @Test
    public void contextLoads() {
        log.trace("log.trace...");
        log.debug("log.debug...");
        log.info("log.info...");
        log.warn("log.warn...");
        log.error("log.error...");
    }

}
