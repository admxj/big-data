package com.admxj.flink.course04;

import lombok.Data;

/**
 * @author admxj
 * @version Id: SearchTmpVO, v 0.1 2020/9/28 1:34 上午 admxj Exp $
 */
@Data
public class SearchTmpVO {
    private String logIp;
    private String processTime;
    private String traceId;
}
