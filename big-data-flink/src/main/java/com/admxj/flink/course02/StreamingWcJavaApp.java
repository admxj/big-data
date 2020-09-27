package com.admxj.flink.course02;

import com.admxj.flink.config.FlinkConfig;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SocketTextStreamFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * 说明:
 * 启动服务: nc -lk 9999
 *
 * @author admxj
 */
public class StreamingWcJavaApp {

    public static void main(String[] args) throws Exception {

        FlinkConfig flinkConfig = FlinkConfig.build();
        // 参数
        ParameterTool tool = ParameterTool.fromArgs(args);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> text = env.addSource(buildDataSource(flinkConfig));


        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String lineValue, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] tokens = lineValue.toLowerCase().split(" ");
                for (String token : tokens) {
                    if (token.length() > 0) {
                        collector.collect(new Tuple2<>(token, 1));
                    }
                }
            }
        }).keyBy(0).timeWindow(Time.seconds(5)).sum(1).addSink(new SinkFunction<Tuple2<String, Integer>>() {
            @Override
            public void invoke(Tuple2<String, Integer> value, Context context) throws Exception {
                System.out.println(value);
            }
        }).setParallelism(1);


        env.execute("StreamingWcJavaApp");
    }

    private static SourceFunction<String> buildDataSource(FlinkConfig flinkConfig) {
        int port = flinkConfig.getDataSourceServerPort();
        String hostname = flinkConfig.getDataSourceServerHost();

        return new SocketTextStreamFunction(hostname, port, "\n", 0);
    }
}
