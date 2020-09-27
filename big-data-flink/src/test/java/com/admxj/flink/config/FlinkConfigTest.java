package com.admxj.flink.config;

import org.junit.Test;

/**
 * @author admxj
 * @version Id: FlinkConfigTest, v 0.1 2020/9/26 1:12 上午 admxj Exp $
 */
public class FlinkConfigTest {

    @Test
    public void testBuild() {
        FlinkConfig flinkConfig = FlinkConfig.build("flink.properties");
        System.out.println(flinkConfig);
    }
}