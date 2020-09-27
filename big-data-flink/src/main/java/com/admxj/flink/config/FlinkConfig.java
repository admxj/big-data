package com.admxj.flink.config;

import com.admxj.flink.util.LoadProperties;
import com.admxj.flink.util.PropertyKey;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author admxj
 * @version Id: FlinkConfig, v 0.1 2020/9/26 1:07 上午 admxj Exp $
 */
@Data
public class FlinkConfig {
    private static final Logger logger = LoggerFactory.getLogger(FlinkConfig.class);

    @PropertyKey("datasource.server.host")
    private String dataSourceServerHost;

    @PropertyKey("datasource.server.port")
    private int dataSourceServerPort;

    public static FlinkConfig build(String filePath) {
        InputStream resourceAsStream = FlinkConfig.class.getClassLoader().getResourceAsStream(filePath);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            return LoadProperties.loadProperties(FlinkConfig.class, properties);
        } catch (IOException e) {
            logger.error("file not found: {}", filePath);
        }

        return null;
    }

    public static FlinkConfig build() {
        FlinkConfig flinkConfig = build("flink.properties");
        return flinkConfig;
    }
}
