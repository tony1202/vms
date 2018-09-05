package com.gfx.vms.base.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * @author tony
 * @date 2018/9/5
 * @Description: 生成uuid字符串
 */
public class UUIDUtils {

    private static TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
    private static RandomBasedGenerator randomBasedGenerator = Generators.randomBasedGenerator();

    /**
     * 基于时间的uuid
     * @return
     */
    public static String timeUUID(){
        return timeBasedGenerator.generate().toString();
    }

    /**
     * 随机UUID
     * @return
     */
    public static String randomUUID(){
        return randomBasedGenerator.generate().toString();
    }

    /**
     * 默认使用timeuuid
     * @return
     */
    public static String uuid(){
        return timeUUID().contains("-")?timeUUID().replaceAll("-",""):timeUUID();
    }
}
