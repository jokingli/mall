package com.second.mall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads() {
        System.err.println(getOrderNumber());
    }

    public static final int MACHINE_ID = 1;
    /**
     * 生成订单号
     */
    public static String getOrderNumber() {
        int machineId = MACHINE_ID;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        System.err.println("hashCodeV：" + hashCodeV);
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正整型
        //edit---20181203 hzj
        Date date=new Date();
        return machineId + String.format("%015d", hashCodeV)+date.getTime();
    }
}
