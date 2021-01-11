package com.second.mall;

import com.second.mall.modules.shopping.dao.IndentItemDao;
import com.second.mall.modules.shopping.entity.IndentItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class MallApplicationTests {

    @Autowired
    private IndentItemDao indentItemDao;

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

    @Test
    void item() {
//        IndentItem indentItem = new IndentItem();
//        indentItem.setProductId(2);
//        indentItem.setProductNum(12);
//        indentItem.setIndentId(1);
//        indentItem.setShopId(1);
//        indentItem.setCreateTime(LocalDateTime.now());
//        indentItem.setPostage(12);
//        indentItemDao.insertIndexItem(indentItem);
        List<IndentItem> indentItems = indentItemDao.selectIndexItemByIndentId(1);
        for (IndentItem indentItem : indentItems) {
            System.err.println(indentItem);
        }
    }

}
