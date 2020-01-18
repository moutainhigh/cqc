package com.cqc.common.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-18
 **/
public class OrderUtils {

    public static String generateOrderSn() {
        String hmmss = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        return hmmss + getOrderIdByUUId();
    }

    private static String getOrderIdByUUId() {
        //最大支持1-9个集群机器部署
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
//         0 代表前面补充0
//         4 代表长度为4
//         d 代表参数为正数型
        return  machineId+ String.format("%015d", hashCodeV);
    }

}
