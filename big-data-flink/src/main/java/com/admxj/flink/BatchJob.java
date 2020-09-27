package com.admxj.flink;

import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * @author admxj
 */
public class BatchJob {


    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        env.execute("Flink Batch Java");

    }
}
