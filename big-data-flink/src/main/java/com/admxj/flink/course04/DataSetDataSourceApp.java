package com.admxj.flink.course04;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.DistinctOperator;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admxj
 * @version Id: DataSetDataSourceApp, v 0.1 2020/9/27 2:30 上午 admxj Exp $
 */
public class DataSetDataSourceApp {

    private static final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public static void main(String[] args) throws Exception {


        ExecutionEnvironment executionEnv = ExecutionEnvironment.getExecutionEnvironment();

//        fromCollection(executionEnv);
//        fromTextFile(executionEnv);
//        DataSource<SearchVO> dataSource = fromCsvFile(executionEnv);
//
//        MapOperator<SearchVO, SearchTmpVO> transferDatasource = dataSource.map((MapFunction<SearchVO, SearchTmpVO>) value -> {
//            SearchTmpVO searchTmpVO = new SearchTmpVO();
//            searchTmpVO.setLogIp(value.getLogIp());
//            searchTmpVO.setProcessTime(value.getProcessTime());
//            searchTmpVO.setTraceId(value.getTraceId());
//
//            return searchTmpVO;
//        });
//
//        transferDatasource.print();

        flatMapFunction(executionEnv);
    }

    public static void flatMapFunction(ExecutionEnvironment executionEnv) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("spring,spring boot");
        list.add("java,vue");
        list.add("react,react native");
        list.add("spring,spring mvc");
        list.add("java,jdk");
        list.add("java,orm");

        DataSource<String> dataSource = executionEnv.fromCollection(list);
        dataSource.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {

                for (String split : value.split(",")) {
                    out.collect(split);
                }
            }

        }).map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return new Tuple2<String, Integer>(value, 1);
            }
        }).distinct(0).sum(1).print();
    }

    private static DataSource<SearchVO> fromCsvFile(ExecutionEnvironment executionEnv) throws Exception {
        String filePath = "file:///Users/admxj/data/logs/csv/";
        CsvReader csvReader = executionEnv.readCsvFile(filePath);

        DataSource<SearchVO> dataSource = csvReader.fieldDelimiter("^").ignoreFirstLine()
                .includeFields(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true)
                .pojoType(SearchVO.class, "logIp", "logTime", "processTime", "callMethod", "callTime", "spendTime", "status", "msgCode", "messageDesc", "traceId", "currencyCode", "language", "locale", "environment", "gmtCreate", "memberId", "memberLevel", "unionId", "openId", "phoneNo", "loginKey", "queryIp", "flatType", "refId", "scene", "searchType", "travelType", "depCity1", "arrCity1", "depDate1", "depCity2", "arrCity2", "depDate2", "depCity3", "arrCity3", "depDate3", "bookingClass", "reqPassengers", "userCurrentLineIndex", "userSelectedLineGroups", "cacheHit", "cacheSaved", "tripReduced", "engineCacheStatus", "searchTimes", "fulfilled", "finished", "resultExist", "resultCount", "year", "month", "day");

        return dataSource;
    }

    private static void fromTextFile(ExecutionEnvironment executionEnv) throws Exception {
        String filePath = "file:///Users/admxj/data/logs/nginx";
        DataSource<String> dataSource = executionEnv.readTextFile(filePath);
        dataSource.print();
    }

    private static void fromCollection(ExecutionEnvironment executionEnv) throws Exception {
        DataSource<Integer> dataSource = executionEnv.fromCollection(list);
        dataSource.print();
    }

}
