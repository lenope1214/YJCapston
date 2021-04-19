package com.jumanji.capston.repository;

import com.jumanji.capston.data.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String> {

//    @Query(value = "select s from Shop s order by (select sum(?1) )")
//    List<Shop> findBySort(String sort);

    //from shops s
    //where s.category = nvl('일식', s.category)
    //order by (select sum(score) / count(score) from REVIEWS ) desc;
    @Query(value = "select s.ID           id,\n" +
            "       NAME,\n" +
            "       INTRO,\n" +
            "       OPEN_TIME      openTime,\n" +
            "       CLOSE_TIME     closeTime,\n" +
            "       ADDRESS,\n" +
            "       ADDRESS_DETAIL addressDetail,\n" +
            "       IS_RS_POS      isRsPos,\n" +
            "       CATEGORY,\n" +
            "       OWNER_ID       ownerId,\n" +
            "       IS_OPEN        isOpen,\n" +
            "       IMG_PATH       imgPath,\n" +
            "       PHONE,\n" +
            "       case :sortTarget\n" +
            "           when 'score' then to_char(nvl(sum(r.score) / count(r.score), 0), 'FM0.0')\n" +
            "           when 'review' then to_char(nvl(count(r.id), 0))\n" +
            "           end        sort\n" +
            "from shops s\n" +
            "         left join REVIEWS r\n" +
            "                   on s.ID = r.SHOP_ID\n" +
            "where s.category = coalesce(:category, s.category)\n" +
            "  and s.name like concat('%', concat(coalesce(:name, s.name), '%'))\n" +
            "group by s.ID, NAME, INTRO, OPEN_TIME, CLOSE_TIME, ADDRESS, ADDRESS_DETAIL, IS_RS_POS, CATEGORY, OWNER_ID, IS_OPEN,\n" +
            "         IMG_PATH, PHONE, :sortTarget\n" +
            "order by sort desc", nativeQuery = true)
    List<Shop.Dao> ShopOrderByScore(String category);

//    @Query(value = "select * from shops s where s.category = coalesce(:category, s.category) order by (select sum(r.score) / count(r.score) from REVIEWS r) desc", nativeQuery = true)
////    @Query(value = "select * from shops s where s.category = coalesce(:category, s.category) order by (select sum(r.score) / count(r.score) from REVIEWS r)", nativeQuery = true)
////    @Query(value = "select s from  Shop s  where s.category = coalesce(?1, s.category) order by (select sum(score)/count(score) from Review ) desc ")
//    List<Shop> ShopByCategoryOrderByTarget(String category, String target);

//    @Query(value = "select :category from dual", nativeQuery = true)
//    List<Shop> test(String category);

    List<Shop> findByCategory(String category);

    List<Shop> findAllByOwner_Id(String owner_id);

    @Query(value = "select s from Shop s where s.owner.id=?1")
    List<Shop> findByOwnerId(String ownerId);
}
