package com.admxj.flink.course02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author admxj
 */
public class BatchWcJavaApp {

    static String logPath = "file:///data/logs/nginx";

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> text = env.readTextFile(logPath);

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
        }).groupBy(0).sum(1).print();


//        env.execute("Flink Batch Java");
    }
}
