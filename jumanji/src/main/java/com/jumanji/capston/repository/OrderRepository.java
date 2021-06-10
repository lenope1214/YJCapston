package com.jumanji.capston.repository;

import com.jumanji.capston.data.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Timestamp> {

    List<Order> findALLByUser_Id(String user_Id);

    // 이성복 돼지???

    List<Order> findAllByShop_Id(String shopId);


    @Query(value = "select o.ID id,\n" +
            "       o.STATUS,\n" +
            "       o.ORDER_REQUEST orderRequest,\n" +
            "       o.PEOPLE,\n" +
            "       o.USE_POINT     usePoint,\n" +
            "       o.AMOUNT,\n" +
            "       o.ARRIVE_TIME   arriveTime,\n" +
            "       o.PAY_TIME      payTime,\n" +
            "       o.PG,\n" +
            "       o.PAY_METHOD    payMethod,\n" +
            "       o.ACCEPT,\n" +
            "       o.SHOP_ID       shopId,\n" +
            "       o.USER_ID       userId,\n" +
            "       o.REASON,\n" +
            "       case\n" +
            "           when r.id is not null then 'Y'\n" +
            "           else 'N' end\n" +
            "                     reviewed\n" +
            "from orders o\n" +
            "         left join REVIEWS R on o.ID = R.ORDER_ID\n" +
            "where o.USER_ID = :userId", nativeQuery = true)
//    @Query(value = "select orders.*,\n" +
//            "       case\n" +
//            "           when r.id is not null then 'Y'\n" +
//            "           else 'N' end\n" +
//            "                     reviewed\n" +
//            "from orders orders\n" +
//            "         left join REVIEWS R on orders.ID = R.ORDER_ID\n" +
//            "where orders.USER_ID = :userId", nativeQuery = true)
    List<Order.MyInfo> myOrderListContainsReviewed(String userId);


    @Query(value = "select \n" +
            "       case\n" +
            "           when o.USER_ID = :userId then 'Y'\n" +
            "           else 'N'\n" +
            "           end result\n" +
            "from ORDERS o\n" +
            "where id = :id", nativeQuery = true)
    char isMyOrder(String userId, Timestamp id);

    @Query(value = "select distinct nvl((select sum(o2.AMOUNT)\n" +
            "                 from orders o2\n" +
            "                 where o2.STATUS = 'pd'\n" +
            "                   and o2.SHOP_ID = :shopId\n" +
            "                   and o2.PAY_TIME >= to_char(sysdate - (to_char(sysdate, 'yyyyMMdd')  - :aDate), 'yyyyMMdd')),0) sumPd,\n" +
            "                nvl((select sum(o2.AMOUNT)\n" +
            "                 from orders o2\n" +
            "                 where o2.STATUS = 'rf'\n" +
            "                   and o2.SHOP_ID = :shopId\n" +
            "                   and o2.PAY_TIME >= to_char(sysdate - (to_char(sysdate, 'yyyyMMdd') - :aDate), 'yyyyMMdd')), 0) sumRf,\n" +
            "                to_char(sysdate - (to_char(sysdate, 'yyyyMMdd')  - :aDate), 'yyyyMMdd')\n" +
            "from ORDERS o\n" +
            "where o.PAY_TIME >= to_char(sysdate - (to_char(sysdate, 'yyyyMMdd')  - :aDate), 'yyyyMMdd')", nativeQuery = true)
    Statistics.betweenTotal getBetweenTotal(String shopId, String aDate);

//    Order findByOrderAndShop(Order order, Shop shop);

}