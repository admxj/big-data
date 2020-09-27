package com.admxj.flink.course04;

import lombok.Data;

/**
 * @author admxj
 * @version Id: SearchVO, v 0.1 2020/9/28 12:17 上午 admxj Exp $
 */
@Data
public class SearchVO {
    private String logIp;
    private String logTime;
    private String processTime;
    private String callMethod;
    private String callTime;
    private String spendTime;
    private String status;
    private String msgCode;
    private String messageDesc;
    private String traceId;
    private String currencyCode;
    private String language;
    private String locale;
    private String environment;
    private String gmtCreate;
    private String memberId;
    private String memberLevel;
    private String unionId;
    private String openId;
    private String phoneNo;
    private String loginKey;
    private String queryIp;
    private String flatType;
    private String refId;
    private String scene;
    private String searchType;
    private String travelType;
    private String depCity1;
    private String arrCity1;
    private String depDate1;
    private String depCity2;
    private String arrCity2;
    private String depDate2;
    private String depCity3;
    private String arrCity3;
    private String depDate3;
    private String bookingClass;
    private String reqPassengers;
    private String userCurrentLineIndex;
    private String userSelectedLineGroups;
    private String cacheHit;
    private String cacheSaved;
    private String tripReduced;
    private String engineCacheStatus;
    private String searchTimes;
    private String fulfilled;
    private String finished;
    private String resultExist;
    private String resultCount;
    private int year;
    private int month;
    private int day;

    public SearchVO() {
    }

}
